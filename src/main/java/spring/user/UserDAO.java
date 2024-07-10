package spring.user;

import javax.sql.DataSource;
import java.sql.*;

public class UserDAO {

    private final DataSource dataSource;

    public UserDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void saveUser(String name) {
        try (Connection conn = dataSource.getConnection()) {
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

    public String fetchUserByID(int id) {
        try (Connection conn = dataSource.getConnection()) {
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

    public void deleteUserById(int id) {
        try (Connection conn = dataSource.getConnection()) {
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