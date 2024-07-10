package spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.config.BusTicketsApplication;
import spring.ticket.TicketDAO;
import spring.user.UserDAO;

public class Application {

    public static void main(String[] args) {
        //Sample of using Spring
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BusTicketsApplication.class);

        UserDAO userDAO = context.getBean(UserDAO.class);
        TicketDAO ticketDAO = context.getBean(TicketDAO.class);

        userDAO.saveUser("Paul");
        userDAO.saveUser("Mario");
        ticketDAO.saveTicket(1, "WEEK");
        ticketDAO.saveTicket(2, "DAY");
        System.out.println(ticketDAO.fetchTicketById(1));
        System.out.println(userDAO.fetchUserByID(2));
        System.out.println(ticketDAO.fetchTicketsByUserId(1));
        userDAO.deleteUserById(2);
        ticketDAO.updateTicketType(1, "YEAR");
    }
}