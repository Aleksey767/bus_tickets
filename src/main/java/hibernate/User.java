package hibernate;

import jakarta.persistence.*;

import java.time.LocalDateTime;

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
    private LocalDateTime creationDate = LocalDateTime.now();

    public User(String name) {
        this.name = name;
    }

    public User() {

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return String.format("| ID : %d | Name : %s | Creation Date : %s |\n", id, name, creationDate);
    }
}