import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class BusTicket {

    private String ticketClass;
    private String ticketType;
    private String startDate;
    private int price;

    public String toString() {
        return String.format("| Ticket Class - %s | Ticket Type - %s | Start Date - %s | Price - %d |\n----------------------------\n", this.ticketClass, this.ticketType, this.startDate, this.price);
    }
}