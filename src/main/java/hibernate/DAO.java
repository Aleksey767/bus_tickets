package hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class DAO {

    public void saveUser(SessionFactory sessionFactory, String name) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                User user = new User(name);
                session.persist(user);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    public void saveTicket(SessionFactory sessionFactory, long id, TicketType ticketType) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                User user = session.get(User.class, id);
                Ticket ticket = new Ticket(user, ticketType);
                session.persist(ticket);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    public User fetchUserById(SessionFactory sessionFactory, long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                User user = session.get(User.class, id);
                transaction.commit();
                return user;
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
        return null;
    }

    public Ticket fetchTicketById(SessionFactory sessionFactory, long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Ticket ticket = session.get(Ticket.class, id);
                transaction.commit();
                return ticket;
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
        return null;
    }

    public List<Ticket> fetchTicketByUserId(SessionFactory sessionFactory, long userId) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                User wantedUser = session.get(User.class, userId);
                Query<Ticket> query = session.createQuery("from Ticket where user = :wantedUser", Ticket.class);
                query.setParameter("wantedUser", wantedUser);
                List<Ticket> tickets = query.getResultList();
                transaction.commit();
                return tickets;
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
        return null;
    }

    public void updateTicketType(SessionFactory sessionFactory, TicketType ticketType, long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                int query = session.createMutationQuery("update Ticket set ticketType =:ticketType where id =:id")
                        .setParameter("ticketType", ticketType)
                        .setParameter("id", id)
                        .executeUpdate();
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }

    public void deleteUserById(SessionFactory sessionFactory, long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                User user = session.find(User.class, id);
                if (user != null) session.remove(user);
                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }
}