import java.util.ArrayList;

/**
 * BustTicketService class provides methods to create, manage, and search for BusTicket objects.
 */
public class BustTicketService {

    private static ArrayList<BusTicket> tickets;

    public BustTicketService() {
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

    public void createTicket(int id, String ticketClass, String ticketType, String startDate, int price) {
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

    public ArrayList<BusTicket> searchByPrice(int startPrice, int endPrice) {
        ArrayList<BusTicket> filteredTickets = new ArrayList<>();
        for (BusTicket ticket : tickets) {
            if (ticket.price >= startPrice && ticket.price <= endPrice) {
                filteredTickets.add(ticket);
            }
        }
        return filteredTickets;
    }

    public ArrayList<BusTicket> searchByType(String ticketType) {
        ArrayList<BusTicket> filteredTickets = new ArrayList<>();
        for (BusTicket ticket : tickets) {
            if (ticket.ticketType.equals(ticketType)) {
                filteredTickets.add(ticket);
            }
        }
        return filteredTickets;
    }
}