package springmvc.services;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import springmvc.enums.TicketType;
import springmvc.enums.UserStatus;
import springmvc.model.Ticket;
import springmvc.model.User;
import springmvc.repositories.TicketRepository;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class TicketService {

    private TicketRepository ticketRepository;

    private UserService userService;

    public void deleteTicketById(long id) {
        if (id == Long.MAX_VALUE || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number,greater than zero and not great than MAX_VALUE");
        }
        ticketRepository.deleteById(id);
    }

    public Ticket getTicketById(long id) {
        if (id == Long.MAX_VALUE || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number,greater than zero and not great than MAX_VALUE");
        }
        return ticketRepository.findById(id).get();
    }

    public List<Ticket> getTicketsByUserId(User user) {
        if (user.getCreationDate().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("Check your creation date in user object");
        }
        return ticketRepository.findByUser(user);
    }

    @Transactional
    public void updateTicketType(long id, TicketType ticketType) {
        if (id == Long.MAX_VALUE || id <= 0) {
            throw new IllegalArgumentException("ID must be a positive number,greater than zero and not great than MAX_VALUE");
        }
        Ticket ticket = ticketRepository.findById(id).get();
        ticket.setTicketType(ticketType);
        ticketRepository.save(ticket);
    }

    @Transactional
    public void addTicket(Ticket ticket) {
        if (ticket.getUser().getId() == Long.MAX_VALUE || ticket.getUser().getId() <= 0) {
            throw new IllegalArgumentException("ID must be a positive number,greater than zero and not great than MAX_VALUE");
        }
        User user = ticket.getUser();
        user.setStatus(UserStatus.ACTIVATED);
        ticket.setUser(user);
        userService.activateUser(user.getId());
        ticketRepository.save(ticket);
    }
}