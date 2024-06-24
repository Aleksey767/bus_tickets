import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class ValidationResults {

    private int totalViolations;
    private int validViolations;
    private int priceViolations;
    private int startDateViolations;
    private int ticketTypeViolations;
    private int ticketClassViolations;

    public void incrementTotalViolations() {
        this.totalViolations++;
    }

    public void incrementValidViolations() {
        this.validViolations++;
    }

    public void incrementPriceViolations() {
        this.priceViolations++;
    }

    public void incrementStartDateViolations() {
        this.startDateViolations++;
    }

    public void incrementTicketTypeViolations() {
        this.ticketTypeViolations++;
    }

    public void incrementTicketClassViolations() {
        this.ticketClassViolations++;
    }
}