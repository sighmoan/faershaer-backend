package tech.tolpuddle.faershaer_backend.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    String id;

    String name;

    String portraitUrl;

    @OneToMany(mappedBy="user")
    List<Person> persons;

    public String getPortraitUrl() {
        return portraitUrl;
    }
}
