import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Scanner;

public class Validator {
    public static void main(String[] args) throws JsonProcessingException {
        int x = 0;
        do {
            String input = getInput();
            BusTicket ticket = new ObjectMapper().readValue(input, BusTicket.class);
            System.out.println(ticket.toString());
            x++;
        } while (x < 5);
    }

    private static String getInput() {
        return new Scanner(System.in).nextLine();

    }
}
