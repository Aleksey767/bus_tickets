package spring;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import spring.ticket.Ticket;

import java.io.IOException;
import java.util.List;

@Service
public class TicketService {

    @Value("classpath:newTickets.json")
    private Resource tickets;

    private List<Ticket> ticketsFromFile;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void loadTicketsFromFile() throws IOException {
        ticketsFromFile = objectMapper.readValue(tickets.getFile(), new TypeReference<List<Ticket>>() {});
    }

    public List<Ticket> getTickets() {
        return ticketsFromFile;
    }
}