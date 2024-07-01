import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * BusTicket class represents a ticket for a bus service.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class BusTicket {

    private int id;
    private String ticketClass;
    private String ticketType;
    private String startDate;
    private BigDecimal price;

    public BusTicket(String startDate, String ticketType) {
        this.id = 0;
        this.startDate = startDate;
        this.ticketType = ticketType;
        this.ticketClass = "Standard";
        this.price = BigDecimal.valueOf(0);
    }

    public String toString() {
        return String.format("| ID - %d | Ticket Class - %s | Ticket Type - %s | Start Date - %s | Price - %s |\n----------------------------\n", this.id, this.ticketClass, this.ticketType, this.startDate, this.price);
    }
}