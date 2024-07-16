package springmvc.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import springmvc.enums.TicketType;
import springmvc.model.Ticket;
import springmvc.model.User;
import springmvc.repositories.TicketRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension .class)
class TicketServiceTest {

    private static final long ID = 1;

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private TicketService ticketService;

    private User user;

    private Ticket ticket;

    @BeforeEach
    void init()  {
        user = mock(User.class);
        ticket = mock(Ticket.class);
    }

    // Positive cases
    @Test
    void addTicket_shouldCallRepository() {
        when(ticket.getUser()).thenReturn(user);
        when(ticket.getUser().getId()).thenReturn(ID);
        ticketService.addTicket(ticket);

        verify(ticketRepository).save(ticket);
    }

    @Test
    void deleteTicketById_shouldCallRepository() {
        ticketService.deleteTicketById(ID);

        verify(ticketRepository).deleteById(ID);
    }

    @Test
    void getTicketById_shouldReturnTicket() {
        when(ticketRepository.findById(ID)).thenReturn(Optional.ofNullable(ticket));

        final Ticket actual = ticketService.getTicketById(ID);

        assertNotNull(actual);
        assertEquals(ticket, actual);
        verify(ticketRepository).findById(ID);
    }

    @Test
    void getTicketsByUserId_shouldReturnTickets() {
        List<Ticket> ticketList = Collections.singletonList(ticket);
        when(ticketRepository.findByUser(user)).thenReturn(ticketList);
        when(user.getCreationDate()).thenReturn(LocalDateTime.now());

        final List<Ticket> actual = ticketService.getTicketsByUserId(user);

        assertNotNull(actual);
        assertEquals(ticketList, actual);
        verify(ticketRepository).findByUser(user);
    }

    @Test
    void updateTicketType_shouldCallRepository() {
        when(ticketRepository.findById(ID)).thenReturn(Optional.ofNullable(ticket));
        ticketService.updateTicketType(ID, TicketType.DAY);

        verify(ticketRepository).findById(ID);
        verify(ticketRepository).save(ticket);
    }

    // Negative cases
    @Test
    void addTicket_shouldThrowException_whenUserIsNull() {
        when(ticket.getUser()).thenReturn(null);

        assertThrows(NullPointerException.class, () -> ticketService.addTicket(ticket));
    }

    @Test
    void deleteTicketById_shouldThrowException_whenIdDoesNotExist() {
        doThrow(new EmptyResultDataAccessException(1)).when(ticketRepository).deleteById(ID);

        assertThrows(EmptyResultDataAccessException.class, () -> ticketService.deleteTicketById(ID));
    }

    @Test
    void getTicketById_shouldThrowException_whenIdDoesNotExist() {
        doThrow(new EmptyResultDataAccessException(1)).when(ticketRepository).findById(ID);

        assertThrows(EmptyResultDataAccessException.class, () -> ticketService.getTicketById(ID));
    }

    @Test
    void getTicketByUserId_shouldThrowException_whenIdDoesNotExist() {
        when(user.getCreationDate()).thenReturn(LocalDateTime.now());
        doThrow(new EmptyResultDataAccessException(1)).when(ticketRepository).findByUser(user);

        assertThrows(EmptyResultDataAccessException.class, () -> ticketService.getTicketsByUserId(user));
    }

    @Test
    void updateTicketType_shouldThrowException_whenIdDoesNotExist() {
        doThrow(new EmptyResultDataAccessException(1)).when(ticketRepository).findById(ID);

        assertThrows(EmptyResultDataAccessException.class, () -> ticketService.updateTicketType(ID, TicketType.DAY));
    }

    // Corner cases
    @Test
    void deleteTicketById_shouldThrowException_whenIdIsZeroOrLessThanOne() {
        assertThrows(IllegalArgumentException.class, () -> ticketService.deleteTicketById(0));
        assertThrows(IllegalArgumentException.class, () -> ticketService.deleteTicketById(-1));
    }

    @Test
    void updateTicketType_shouldThrowException_whenIdIsNotAppropriate() {
        assertThrows(IllegalArgumentException.class, () -> ticketService.updateTicketType(9223372036854775807L, TicketType.DAY));
    }

    @Test
    void addTicket_shouldThrowException_WhenIdIsNotAppropriate() {
        when(ticket.getUser()).thenReturn(user);
        when(ticket.getUser().getId()).thenReturn(0L);
        when(ticket.getUser().getId()).thenReturn(Long.MAX_VALUE);
        when(ticket.getUser().getId()).thenReturn(-1L);
        assertThrows(IllegalArgumentException.class, () -> ticketService.addTicket(ticket));
    }

    @Test
    void getTicketById_shouldThrowException_whenIdIsNotAppropriate() {
        assertThrows(IllegalArgumentException.class, () -> ticketService.updateTicketType(-9223372036854775807L, TicketType.DAY));
    }

    @Test
    void getTicketByUserId_shouldThrowException_whenDateIsNotAppropriate() {
        when(user.getCreationDate()).thenReturn(LocalDateTime.now().plusYears(1));
        assertThrows(IllegalArgumentException.class, () -> ticketService.getTicketsByUserId(user));
    }
}