package tech.tolpuddle.faershaer_backend.http.dtos;

import tech.tolpuddle.faershaer_backend.domain.Person;

public record PersonDto(String id, String name, String portraitUrl, Double balance) {
    static public PersonDto fromPerson(Person p, Double balance) {
        return new PersonDto(p.getId(), p.getName(), p.getPortraitUrl(), balance);
    }
}
