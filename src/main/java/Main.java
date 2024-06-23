import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws IOException {

        CustomArrayList arrayList = new CustomArrayList();
        for (int i = 0; i <= 9; i++) {
            arrayList.add("1" + i);
        }
        System.out.println(arrayList.remove(1));
        System.out.println(arrayList.remove("12"));
        System.out.println(arrayList.get(4));

        ValidationResults validationResults = new ValidationResults();
        String content = new String(Files.readAllBytes(Paths.get("src/main/java/tickets.json")));
        JSONArray jsonArray = new JSONArray(content);

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonTicket = jsonArray.getJSONObject(i);
            BusTicket ticket = new ObjectMapper().readValue(jsonTicket.toString(), BusTicket.class);
            Validator.validateTicket(ticket, validationResults);
            validationResults.setTotal();
        }
        System.out.printf("----------------------------\n| TOTAL - %d |\n", validationResults.getTotal());
        System.out.printf("| VALID - %d |\n", validationResults.getValid());
        System.out.println(Validator.calculatePopularViolation(validationResults));
    }
}