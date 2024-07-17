package tech.tolpuddle.faershaer_backend.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String label;

    @ManyToMany
    List<Person> participants;

    @OneToMany(mappedBy = "event")
    List<Transaction> transactions;
}
