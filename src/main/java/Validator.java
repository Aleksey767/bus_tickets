import java.time.LocalDate;

public class Validator {

    //Find most popular violation
    static String calculatePopularViolation(ValidationResults results) {

        int price = results.getZeroPriceCounter(), ticketClass = results.getTicketClassErrorsCounter(), ticketType = results.getTicketTypeErrorsCounter(), startData = results.getStartDateErrorsCounter();
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

    static void validateTicket(BusTicket ticket, ValidationResults results) {

        String ticketType = ticket.getTicketType(), startDate = ticket.getStartDate(), ticketClass = ticket.getTicketClass();
        boolean isValid = true;
        int price = ticket.getPrice();

        //ticketClass
        if (ticketClass == null || ticketClass.isEmpty()) {
            results.setTicketClassErrorsCounter();
            isValid = false;
            System.out.println("[ERROR]Your ticket has no ticket class!");
        }
        //Price
        if (price == 0) {
            results.setZeroPriceCounter();
            isValid = false;
            System.out.println("[ERROR]Your ticket has no price!");
        } else {
            if (price % 2 != 0) {
                results.setZeroPriceCounter();
                isValid = false;
                System.out.println("[ERROR]Your ticket price should be even!");
            }
        }
        // ticketType
        if (ticketType == null || ticketType.isEmpty()) {
            results.setTicketTypeErrorsCounter();
            isValid = false;
            System.out.println("[ERROR]Your ticket has no ticket type!");
        } else {
            if (ticketType.equalsIgnoreCase("DAY") || ticketType.equalsIgnoreCase("WEEK") ||
                    ticketType.equalsIgnoreCase("YEAR") || ticketType.equalsIgnoreCase("MONTH")) {
            } else {
                results.setTicketTypeErrorsCounter();
                isValid = false;
                System.out.println("[ERROR]Your ticket type is not valid!");
            }
        }
        //StartDate
        if (startDate == null || startDate.isEmpty()) {
            results.setStartDateErrorsCounter();
            isValid = false;
            System.out.println("[ERROR]You have no start date!");
        } else {
            LocalDate currentDate = LocalDate.now();
            LocalDate ticketDate = LocalDate.parse(startDate);
            if (currentDate.isBefore(ticketDate)) {
                results.setStartDateErrorsCounter();
                isValid = false;
                System.out.println("[ERROR]Invalid ticket date!");
            }
        }
        //Valid counter and print data
        if (isValid) {
            results.setValid();
            System.out.print(ticket);
        } else {
            System.out.println("----------------------------");
        }
    }
}