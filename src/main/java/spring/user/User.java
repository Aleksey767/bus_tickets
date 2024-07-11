package spring.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;

import java.text.SimpleDateFormat;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "\"User\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "creation_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private long creationDate;

    @Enumerated
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "user_status", nullable = false)
    private UserStatus status;

    public User(String name) {
        this.name = name;
        this.creationDate = System.currentTimeMillis();
        this.status = UserStatus.DEACTIVATED;
    }

    public User() {
    }

    @Override
    public String toString() {
        String formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(creationDate));
        return String.format("| ID : %d | Name : %s | Creation Date : %s | Status : %s |\n", id, name, formattedDate, status);
    }
}