import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter

public class ValidationResults {

    public int total;
    public int valid;
    public int zeroPriceCounter;
    public int startDateErrorsCounter;
    public int ticketTypeErrorsCounter;
    public int ticketClassErrorsCounter;

    public void setTotal() {
        this.total += 1;
    }

    public void setValid() {
        this.valid += 1;
    }

    public void setZeroPriceCounter() {
        this.zeroPriceCounter += 1;
    }

    public void setStartDateErrorsCounter() {
        this.startDateErrorsCounter += 1;
    }

    public void setTicketTypeErrorsCounter() {
        this.ticketTypeErrorsCounter += 1;
    }

    public void setTicketClassErrorsCounter() {
        this.ticketClassErrorsCounter += 1;
    }
}