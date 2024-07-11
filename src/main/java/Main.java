import java.io.IOException;
import java.math.BigDecimal;

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
        //Sample of using BusTicketService
        BusTicketService busTicketService = new BusTicketService();
        busTicketService.createTicket(1, "CLASS1", "TRAIN", "DATE1", BigDecimal.valueOf(55.11));
        busTicketService.createTicket(2, "CLASS2", "TRAIN", "DATE2", BigDecimal.valueOf(58.00));
        busTicketService.createTicket(3, "CLASS2", "TRAIN", "DATE2", BigDecimal.valueOf(70.00));
        busTicketService.removeById(1);
        System.out.println(busTicketService.getById(2));
        for (BusTicket busTicket : busTicketService.searchByType("TRAIN")) {
            System.out.println(busTicket);
        }
        for (BusTicket busTicket : busTicketService.searchByPrice(BigDecimal.valueOf(10.00), BigDecimal.valueOf(60.00))) {
            System.out.println(busTicket);
        }
        //Sample of using Validator
        Validator.runValidator();
    }
}