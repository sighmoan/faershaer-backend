package tech.tolpuddle.faershaer_backend.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String name;

    @ManyToOne
    User user;

    @OneToMany(mappedBy = "payer", cascade = CascadeType.REMOVE)
    List<Transaction> transactions;

    @ManyToOne
    Event event;

    public String getPortraitUrl() {
        if(user == null) return "";

        return user.getPortraitUrl();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Event getEvent() {
        return event;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
