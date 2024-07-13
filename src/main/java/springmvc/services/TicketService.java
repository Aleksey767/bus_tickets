package springmvc.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import springmvc.enums.TicketType;
import springmvc.enums.UserStatus;
import springmvc.model.Ticket;
import springmvc.model.User;
import springmvc.repositories.TicketRepository;

import java.io.IOException;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserService userService;

    @Value("classpath:newTickets.json")
    private Resource tickets;

    public void deleteTicketById(long id) {
        ticketRepository.deleteById(id);
    }

    public Ticket getTicketById(long id) {
        return ticketRepository.findById(id).get();
    }

    public List<Ticket> getTicketsByUserId(User user) {
        return ticketRepository.findByUser(user);
    }

    @Transactional
    public void updateTicketType(long id, TicketType ticketType) {
        Ticket ticket = ticketRepository.findById(id).get();
        ticket.setTicketType(ticketType);
        ticketRepository.save(ticket);
    }

    @Transactional
    public void addTicket(Ticket ticket) {
        User user = ticket.getUser();
        user.setStatus(UserStatus.ACTIVATED);
        ticket.setUser(user);
        userService.activateUser(user.getId());
        ticketRepository.save(ticket);
    }

    private List<Ticket> ticketsFromFile;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public void loadTicketsFromFile() throws IOException {
        ticketsFromFile = objectMapper.readValue(tickets.getFile(), new TypeReference<List<Ticket>>() {

        });
    }

    public List<Ticket> getTicketsFromFile() {
        return ticketsFromFile;
    }
}