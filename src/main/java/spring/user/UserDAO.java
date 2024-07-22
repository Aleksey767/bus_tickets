package spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class UserDAO {

    @Value("${table.ability.modify}")
    private boolean switcher;

    private final DataSource dataSource;

    public UserDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void saveUser(String name) {
        if (switcher) {
            String addUser = "INSERT INTO \"User\" (name,status) VALUES (?,'DEACTIVATED')";
            jdbcTemplate.update(addUser, name);
        } else {
            throw new RuntimeException("[ERROR] Ability to modify User is OFF");
        }
    }

    public void deleteUserById(int id) {
        if (switcher) {
            String deleteUser = "DELETE FROM \"User\" WHERE id = ?";
            jdbcTemplate.update(deleteUser, id);
        } else {
            throw new RuntimeException("[ERROR] Ability to modify User is OFF");
        }
    }

    public String fetchUserById(int userId) {
        String sql = "SELECT * FROM \"User\" WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{userId},
                (rs, rowNum) -> String.format("| ID - %d | NAME - %s | CREATION TIME - %s | STATUS - %s |\n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getTimestamp("creation_date"),
                        rs.getString("status")));
    }
}