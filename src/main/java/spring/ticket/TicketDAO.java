package spring.ticket;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class TicketDAO {

    private final DataSource dataSource;

    public TicketDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Value("${table.ability.modify}")
    private boolean switcher;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Transactional
    public void saveTicket(int userId, String ticketType) {
        if (switcher) {
            String createTicket = "INSERT INTO \"Ticket\" (user_id, ticket_type) VALUES (?, ?::ticket_type)";
            String changeUserStatus = "UPDATE \"User\" SET status = 'ACTIVATED'::user_status WHERE id = ?";
            jdbcTemplate.update(createTicket, userId, ticketType);
            jdbcTemplate.update(changeUserStatus, userId);
        } else {
            throw new RuntimeException("[ERROR] Ability to modify Ticket is OFF");
        }
    }

    public void updateTicketType(int id, String newTicketType) {
        String updateTicket = "UPDATE \"Ticket\" SET ticket_type = ?::ticket_type WHERE id = ?";
        jdbcTemplate.update(updateTicket, newTicketType, id);
    }

    public List<String> fetchTicketsByUserId(int userId) {
        String sql = "SELECT * FROM \"Ticket\" WHERE user_id = ?";

        return jdbcTemplate.query(sql, new Object[]{userId}, (rs, rowNum) ->
                String.format("| ID - %d | User ID - %s | Ticket type - %s | Creation date - %s |\n",
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("ticket_type"),
                        rs.getTimestamp("creation_date")));
    }

    public String fetchTicketById(int id) {
        String sql = "SELECT * FROM \"Ticket\" WHERE id = ?";

        return jdbcTemplate.queryForObject(sql, new Object[]{id},
                (rs, rowNum) -> String.format("| ID - %d | User ID - %s | Ticket type - %s | Creation date - %s |\n",
                        rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getString("ticket_type"),
                        rs.getTimestamp("creation_date")));
    }
}