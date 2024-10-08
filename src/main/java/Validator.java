import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    static String calculatePopularViolation(ValidationResults results) {
        int price = results.getPriceViolations(), ticketClass = results.getTicketClassViolations(),
                ticketType = results.getTicketTypeViolations(), startData = results.getStartDateViolations();

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
        /**
         * Validates a BusTicket object and updates the ValidationResults accordingly.
         *
         * @param ticket  The BusTicket object to be validated.
         * @param results The ValidationResults object to store the validation outcomes.
         */
        boolean isValid = true;
        BigDecimal price = ticket.getPrice();

        if (ticketClass == null || ticketClass.isEmpty()) {
            results.incrementTicketClassViolations();
            isValid = false;
            System.out.println("[ERROR] Your ticket has no ticket class!");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            results.incrementPriceViolations();
            isValid = false;
            System.out.println("[ERROR] Your ticket has no price!");
        } else {
            BigDecimal priceInCents = price.multiply(BigDecimal.valueOf(100));
            int priceInCentsInt = priceInCents.setScale(0, RoundingMode.HALF_UP).intValueExact();

            if (priceInCentsInt % 2 != 0) {
                results.incrementPriceViolations();
                isValid = false;
                System.out.println("[ERROR] Your ticket price should be even!");
            }
        }
        if (ticketType == null || ticketType.isEmpty()) {
            results.incrementTicketTypeViolations();
            isValid = false;
            System.out.println("[ERROR] Your ticket has no ticket type!");
        } else {
            if (ticketType.equalsIgnoreCase("DAY") || ticketType.equalsIgnoreCase("WEEK") ||
                    ticketType.equalsIgnoreCase("YEAR") || ticketType.equalsIgnoreCase("MONTH")) {
            } else {
                results.incrementTicketTypeViolations();
                isValid = false;
                System.out.println("[ERROR] Your ticket type is not valid!");
            }
        }
        if (startDate == null || startDate.isEmpty()) {
            results.incrementStartDateViolations();
            isValid = false;
            System.out.println("[ERROR] You have no start date!");
        } else {
            LocalDate currentDate = LocalDate.now();
            LocalDate ticketDate = LocalDate.parse(startDate);
            if (currentDate.isBefore(ticketDate)) {
                results.incrementStartDateViolations();
                isValid = false;
                System.out.println("[ERROR] Invalid ticket date!");
            }
        }
        if (isValid) {
            results.incrementValidViolations();
            System.out.print(ticket);
        } else {
            System.out.println("----------------------------");
        }
    }

    static void runValidator() throws IOException {
        ValidationResults validationResults = new ValidationResults();
        String content = new String(Files.readAllBytes(Paths.get("src/main/resources/tickets.json")));
        JSONArray jsonArray = new JSONArray(content);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonTicket = jsonArray.getJSONObject(i);
            BusTicket ticket = new ObjectMapper().readValue(jsonTicket.toString(), BusTicket.class);
            Validator.validateTicket(ticket, validationResults);
            validationResults.incrementTotalViolations();
        }
        System.out.printf("----------------------------\n| TOTAL - %d |\n", validationResults.getTotalViolations());
        System.out.printf("| VALID - %d |\n", validationResults.getValidViolations());
        System.out.println(Validator.calculatePopularViolation(validationResults));
    }
}