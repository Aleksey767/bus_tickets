import java.time.LocalDate;

/**
 * Validator has been created for the validation of fields of the BustTicket class.
 */
public class Validator {

    /**
     * Calculates the most frequent type of violation in the validation results.
     *
     * @param results The ValidationResults object containing the error counters.
     * @return A string describing the most frequent violation type.
     */
    private String calculatePopularViolation(ValidationResults results) {
        int price = results.getZeroPriceCounter();
        int ticketClass = results.getTicketClassErrorsCounter();
        int ticketType = results.getTicketTypeErrorsCounter();
        int startData = results.getStartDateErrorsCounter();
        int max = Math.max(Math.max(price, ticketClass), Math.max(ticketType, startData));
        String printData = "";

        if (max == 0) {
            printData = "| THERE ARE NO VIOLATIONS |\n";
        } else if (max == price) {
            printData = "| MOST POPULAR VIOLATION - PRICE |\n";
        } else if (max == ticketClass) {
            printData = "| MOST POPULAR VIOLATION - TICKET CLASS |\n";
        } else if (max == ticketType) {
            printData = "| MOST POPULAR VIOLATION - TICKET TYPE |\n";
        } else if (max == startData) {
            printData = "| MOST POPULAR VIOLATION - START DATA |\n";
        }
        return printData;
    }

    /**
     * Validates a BusTicket object and updates the ValidationResults accordingly.
     *
     * @param ticket  The BusTicket object to be validated.
     * @param results The ValidationResults object to store the validation outcomes.
     */
    private void validateTicket(BusTicket ticket, ValidationResults results) {
        String ticketType = ticket.getTicketType();
        String startDate = ticket.getStartDate();
        String ticketClass = ticket.getTicketClass();
        boolean isValid = true;
        int price = ticket.getPrice();

        if (ticketClass == null || ticketClass.isEmpty()) {
            results.setTicketClassErrorsCounter();
            isValid = false;
            System.out.println("[ERROR] Your ticket has no ticket class!");
        }
        if (price == 0) {
            results.setZeroPriceCounter();
            isValid = false;
            System.out.println("[ERROR] Your ticket has no price!");
        } else {
            if (price % 2 != 0) {
                results.setZeroPriceCounter();
                isValid = false;
                System.out.println("[ERROR] Your ticket price should be even!");
            }
        }
        if (ticketType == null || ticketType.isEmpty()) {
            results.setTicketTypeErrorsCounter();
            isValid = false;
            System.out.println("[ERROR] Your ticket has no ticket type!");
        } else {
            if (ticketType.equalsIgnoreCase("DAY") || ticketType.equalsIgnoreCase("WEEK") ||
                    ticketType.equalsIgnoreCase("YEAR") || ticketType.equalsIgnoreCase("MONTH")) {
            } else {
                results.setTicketTypeErrorsCounter();
                isValid = false;
                System.out.println("[ERROR] Your ticket type is not valid!");
            }
        }
        if (startDate == null || startDate.isEmpty()) {
            results.setStartDateErrorsCounter();
            isValid = false;
            System.out.println("[ERROR] You have no start date!");
        } else {
            LocalDate currentDate = LocalDate.now();
            LocalDate ticketDate = LocalDate.parse(startDate);
            if (currentDate.isBefore(ticketDate)) {
                results.setStartDateErrorsCounter();
                isValid = false;
                System.out.println("[ERROR] Invalid ticket date!");
            }
        }
        if (isValid) {
            results.setValid();
            System.out.print(ticket);
        } else {
            System.out.println("----------------------------");
        }
    }
}