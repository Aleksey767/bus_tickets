import lombok.*;

/**
 * BusTicket class represents a ticket for a bus service.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class BusTicket {

    int id;
    String ticketClass;
    String ticketType;
    String startDate;
    int price;

    public BusTicket(String startDate, String ticketType) {
        this.id = 0;
        this.startDate = startDate;
        this.ticketType = ticketType;
        this.ticketClass = "Standard";
        this.price = 0;
    }

    public String toString() {
        return String.format("| ID - %d | Ticket Class - %s | Ticket Type - %s | Start Date - %s | Price - %d |\n----------------------------\n", this.id, this.ticketClass, this.ticketType, this.startDate, this.price);
    }
}