package tech.tolpuddle.faershaer_backend.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    String id;

    String name;

    String portraitUrl;

    @OneToMany(mappedBy="user")
    List<Person> persons;

    public String getPortraitUrl() {
        return portraitUrl;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPortraitUrl(String portraitUrl) {
        this.portraitUrl = portraitUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
