package springmvc.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import springmvc.enums.UserStatus;

import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
    @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "creation_date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime creationDate;

    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "status", nullable = false)
    private UserStatus status;

    public User(String name) {
        this.name = name;
        this.creationDate = LocalDateTime.now();
        this.status = UserStatus.DEACTIVATED;
    }

    @Override
    public String toString() {
        return String.format("| ID : %d | Name : %s | Creation Date : %s | Status : %s |\n", id, name, creationDate, status);
    }
}