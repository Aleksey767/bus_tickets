package spring.ticket;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class TicketDAO {

    private final DataSource dataSource;

    public TicketDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveTicket(int userId, String ticketType) {
        try (Connection conn = dataSource.getConnection()) {
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

    public String fetchTicketById(int id) {
        try (Connection conn = dataSource.getConnection()) {
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
        try (Connection conn = dataSource.getConnection()) {
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

    public void updateTicketType(int id, String newTicketType) {
        try (Connection conn = dataSource.getConnection()) {
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
}