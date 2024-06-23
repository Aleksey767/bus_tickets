import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class BusTicket {

    String ticketClass;
    String ticketType;
    String startDate;
    int price;

    public String toString() {
        return String.format("| Ticket Class - %s | Ticket Type - %s | Start Date - %s | Price - %d |\n----------------------------\n", this.ticketClass, this.ticketType, this.startDate, this.price);
    }
}