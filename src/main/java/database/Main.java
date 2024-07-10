package database;

public class Main {

    public static void main(String[] args) {
        //Sample of using database
        DAO dao = new DAO();
        dao.saveTicket(5, "WEEK");
        dao.saveUser("Paul");
        dao.saveUserAndTicket("Harry", "MONTH");
        System.out.println(dao.fetchTicketById(5));
        System.out.println(dao.fetchUserByID(8));
        System.out.println(dao.fetchTicketsByUserId(1));
        dao.deleteUserById(2);
        dao.updateTicketType(2, "YEAR");
    }
}
