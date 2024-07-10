package database;

import java.sql.*;
import java.util.ArrayList;

public class DAO {

    private static final String user = System.getenv("POSTGRESQL_USERNAME");
    private static final String pass = System.getenv("POSTGRESQL_PASSWORD");
    private static final String url = System.getenv("POSTGRESQL_URL");

    public void saveTicket(int userId, String ticketType) {
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            conn.setAutoCommit(false);
            Savepoint savepoint1 = conn.setSavepoint("Savepoint1");

            try {
                PreparedStatement st = conn.prepareStatement("INSERT INTO \"Ticket\" (user_id, ticket_type) VALUES (?, ?::ticket_type)");
                st.setInt(1, userId);
                st.setObject(2, ticketType);
                st.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback(savepoint1);
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name) {
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            conn.setAutoCommit(false);
            Savepoint savepoint1 = conn.setSavepoint("Savepoint1");

            try {
                PreparedStatement st = conn.prepareStatement("INSERT INTO \"User\" (name) VALUES (?)");
                st.setString(1, name);
                st.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback(savepoint1);
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUserAndTicket(String name, String ticketType) {
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            conn.setAutoCommit(false);
            Savepoint savepoint1 = conn.setSavepoint("Savepoint1");
            try {
                PreparedStatement userStmt = conn.prepareStatement("INSERT INTO \"User\" (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                userStmt.setString(1, name);
                userStmt.executeUpdate();
                ResultSet generatedKeys = userStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int userId = generatedKeys.getInt(1);
                    PreparedStatement ticketStmt = conn.prepareStatement("INSERT INTO \"Ticket\" (user_id, ticket_type) VALUES (?, ?::ticket_type)");
                    ticketStmt.setInt(1, userId);
                    ticketStmt.setObject(2, ticketType);
                    ticketStmt.executeUpdate();
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback(savepoint1);
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String fetchTicketById(int id) {
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM \"Ticket\" WHERE id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            return String.format("| ID - %d | User ID - %s | Ticket type - %s | Creation date - %s |\n", rs.getInt("id"),
                    rs.getInt("user_id"), rs.getString("ticket_type"), rs.getTimestamp("creation_date"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<String> fetchTicketsByUserId(int user_id) {
        ArrayList<String> array = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM \"Ticket\" WHERE user_id = ?");
            st.setInt(1, user_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String ticket = String.format("| ID - %d | User ID - %s | Ticket type - %s | Creation date - %s |\n", rs.getInt("id"),
                        rs.getInt("user_id"), rs.getString("ticket_type"), rs.getTimestamp("creation_date"));
                array.add(ticket);
            }
            return array;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String fetchUserByID(int id) {
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM \"User\" WHERE id = ?");
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            rs.next();
            return String.format("| ID - %d | Name - %s | Creation Date - %s |\n", rs.getInt("id"),
                    rs.getString("name"), rs.getTimestamp("creation_date"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateTicketType(int id, String newTicketType) {
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            conn.setAutoCommit(false);
            Savepoint savepoint1 = conn.setSavepoint("Savepoint1");

            try {
                PreparedStatement st = conn.prepareStatement("UPDATE \"Ticket\" SET ticket_type = ?::ticket_type WHERE id = ?");
                st.setString(1, newTicketType);
                st.setInt(2, id);
                st.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback(savepoint1);
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserById(int id) {
        try (Connection conn = DriverManager.getConnection(url, user, pass)) {
            conn.setAutoCommit(false);
            Savepoint savepoint1 = conn.setSavepoint("Savepoint1");

            try {
                PreparedStatement st = conn.prepareStatement("DELETE FROM \"User\" WHERE id = ?");
                st.setInt(1, id);
                st.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback(savepoint1);
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}