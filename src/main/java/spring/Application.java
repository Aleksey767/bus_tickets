package spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.config.BusTicketsApplication;
import spring.ticket.TicketDAO;
import spring.user.UserDAO;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {

        //Sample of using Spring
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BusTicketsApplication.class);
        TicketService ticketService = context.getBean(TicketService.class);
        UserDAO userDAO = context.getBean(UserDAO.class);
        TicketDAO ticketDAO = context.getBean(TicketDAO.class);

        userDAO.saveUser("Paul");
        userDAO.saveUser("Mario");
        ticketDAO.saveTicket(1, "WEEK");
        ticketDAO.saveTicket(2, "DAY");
        System.out.println(ticketDAO.fetchTicketById(1));
        System.out.println(userDAO.fetchUserById(2));
        System.out.println(ticketDAO.fetchTicketsByUserId(1));
        userDAO.deleteUserById(2);
        ticketDAO.updateTicketType(1, "YEAR");

        //Sample of using Spring Resources
        ticketService.loadTicketsFromFile();
        System.out.println(ticketService.getTickets());
    }
}