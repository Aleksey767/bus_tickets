import java.util.ArrayList;

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
}
