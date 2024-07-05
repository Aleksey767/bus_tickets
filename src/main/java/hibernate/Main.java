package hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {

    public static void main(String[] args) {
        //Sample of using Hibernate
        DAO dao = new DAO();
        try (SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory()) {
            dao.saveUser(sessionFactory, "Brandon");
            dao.saveUser(sessionFactory, "Rick");
            dao.saveUser(sessionFactory, "Kimura");
            dao.saveTicket(sessionFactory,1,TicketType.DAY);
            dao.saveTicket(sessionFactory,2,TicketType.YEAR);
            dao.saveTicket(sessionFactory,3,TicketType.WEEK);
            dao.saveTicket(sessionFactory,3,TicketType.DAY);
            dao.saveTicket(sessionFactory,3,TicketType.MONTH);
            System.out.println(dao.fetchTicketByUserId(sessionFactory,3));
            System.out.println(dao.fetchTicketById(sessionFactory,3));
            System.out.println(dao.fetchUserById(sessionFactory,1));
            dao.updateTicketType(sessionFactory,TicketType.MONTH,1);
            dao.deleteUserById(sessionFactory,1);
        }
    }
}