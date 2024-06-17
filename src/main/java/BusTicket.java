public class BusTicket {
    String ticketClass;
    String ticketType;
    String startDate;
    int price;

    public BusTicket(String ticketClass, String ticketType, String startDate, int price) {
        this.ticketClass = ticketClass;
        this.ticketType = ticketType;
        this.startDate = startDate;
        this.price = price;

    }

    public BusTicket() {
        this.ticketClass = "";
        this.ticketType = "";
        this.startDate = "";
        this.price = 0;
    }

    public String toString() {


        return String.format("ticketClass:%s,ticketType:%s,startDate:%s,price:%d",
                this.ticketClass, this.ticketType, this.startDate, this.price, this.ticketType, this.price);
    }

    public String getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(String ticketClass) {
        this.ticketClass = ticketClass;
    }

    public String getTicketType() {
        return ticketType;
    }

    public void setTicketType(String ticketType) {
        this.ticketType = ticketType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

}
