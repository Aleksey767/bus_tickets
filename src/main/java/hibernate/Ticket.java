package hibernate;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.time.LocalDateTime;

@Entity
@Table(name = "\"Ticket\"")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_id_seq")
    @SequenceGenerator(name = "ticket_id_seq", sequenceName = "ticket_id_seq", allocationSize = 1)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "ticket_type", nullable = false)
    private TicketType ticketType;

    @Column(name = "creation_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime creationDate;

    public Ticket() {
    }

    public Ticket(User user, TicketType ticketType) {
        this.user = user;
        this.ticketType = ticketType;
        this.creationDate = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public TicketType getTicketType() {
        return ticketType;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTicketType(TicketType ticketType) {
        this.ticketType = ticketType;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return String.format("| ID - %d | User ID - %d | Ticket Type - %s | Creation Date - %s\n", id, user.getId(), ticketType, creationDate);
    }
}