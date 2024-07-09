package spring.config;

import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import spring.ticket.TicketDAO;
import spring.user.UserDAO;

import javax.sql.DataSource;

@Configuration
public class BusTicketsApplication {

    @Bean
    public DataSource dataSource() {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUser("aleksey767");
        dataSource.setPassword("andersen");
        dataSource.setURL("jdbc:postgresql://localhost:5432/my_ticket_service_db");
        return dataSource;
    }

    @Bean
    public UserDAO userDAO() {
        return new UserDAO(dataSource());
    }

    @Bean
    public TicketDAO ticketDAO() {
        return new TicketDAO(dataSource());
    }
}