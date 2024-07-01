import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        //Sample of using Custom Collections
        CustomHashSet<Integer> set = new CustomHashSet<>();
        CustomArrayList<Object> list = new CustomArrayList<>();

        set.add(1);
        set.add(2);
        set.add(3);
        set.remove(2);
        System.out.println(set.contains(2));

        for (Integer e : set) {
            System.out.println(e);
        }

        System.out.println("--------------------------");
        list.add(1);
        list.add("Andersen");
        list.add(6.6);
        list.remove(0);
        list.remove(6.6);
        System.out.println(list.get(0));
    }
//        ValidationResults validationResults = new ValidationResults();
//        String content = new String(Files.readAllBytes(Paths.get("src/main/java/tickets.json")));
//        JSONArray jsonArray = new JSONArray(content);
//
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject jsonTicket = jsonArray.getJSONObject(i);
//            BusTicket ticket = new ObjectMapper().readValue(jsonTicket.toString(), BusTicket.class);
//            Validator.validateTicket(ticket, validationResults);
//            validationResults.incrementTotalViolations();
//        }
//        System.out.printf("----------------------------\n| TOTAL - %d |\n", validationResults.getTotalViolations());
//        System.out.printf("| VALID - %d |\n", validationResults.getValidViolations());
//        System.out.println(Validator.calculatePopularViolation(validationResults));
//    }
}