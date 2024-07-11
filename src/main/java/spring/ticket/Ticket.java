package spring.ticket;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "\"Ticket\"")
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ticket_id_seq")
    @SequenceGenerator(name = "ticket_id_seq", sequenceName = "ticket_id_seq", allocationSize = 1)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private long userId;

    @Enumerated
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "ticket_type", nullable = false)
    private TicketType ticketType;

    @Column(name = "creation_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private long creationDate;

    public Ticket() {
    }

    public Ticket(long userId, TicketType ticketType) {
        this.userId = userId;
        this.ticketType = ticketType;
        this.creationDate = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(creationDate));
        return String.format("| ID - %d | User ID - %d | Ticket Type - %s | Creation Date - %s\n", id, userId, ticketType, formattedDate);
    }
}