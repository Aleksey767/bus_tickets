import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * BusTicketService class provides methods to create, manage, and search for BusTicket objects.
 */
public class BusTicketService {

    public static ArrayList<BusTicket> tickets;

    public BusTicketService() {
        tickets = new ArrayList<>();
    }

    public void createTicket() {
        BusTicket ticket = new BusTicket();
        tickets.add(ticket);
    }

    public void createTicket(String startDate, String ticketType) {
        BusTicket ticket = new BusTicket(startDate, ticketType);
        tickets.add(ticket);
    }

    public void createTicket(int id, String ticketClass, String ticketType, String startDate, BigDecimal price) {
        BusTicket ticket = new BusTicket(id, ticketClass, ticketType, startDate, price);
        tickets.add(ticket);
    }

    public boolean removeById(int id) {
        for (BusTicket ticket : tickets) {
            if (ticket.getId() == id) {
                tickets.remove(ticket);
                return true;
            }
        }
        return false;
    }

    public BusTicket getById(int id) {
        if (id >= 0 && id <= tickets.size()) {
            for (BusTicket ticket : tickets) {
                if (ticket.getId() == id) {
                    return ticket;
                }
            }
        }
        return null;
    }

    public ArrayList<BusTicket> searchByPrice(BigDecimal startPrice, BigDecimal endPrice) {
        ArrayList<BusTicket> filteredTickets = new ArrayList<>();
        for (BusTicket ticket : tickets) {
            if (ticket.getPrice().compareTo(startPrice) >= 0 && ticket.getPrice().compareTo(endPrice) <= 0) {
                filteredTickets.add(ticket);
            }
        }
        return filteredTickets;
    }

    public ArrayList<BusTicket> searchByType(String ticketType) {
        ArrayList<BusTicket> filteredTickets = new ArrayList<>();
        for (BusTicket ticket : tickets) {
            if (ticket.getTicketType().equals(ticketType)) {
                filteredTickets.add(ticket);
            }
        }
        return filteredTickets;
    }
}