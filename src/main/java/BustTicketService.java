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
    public void createTicket(String startDate,String ticketType) {
        BusTicket ticket = new BusTicket(startDate, ticketType);
        tickets.add(ticket);
    }
    public void createTicket(String ticketClass,String ticketType,String startDate,int price) {
        BusTicket ticket = new BusTicket(ticketClass,ticketType,startDate,price);
        tickets.add(ticket);
    }
}
