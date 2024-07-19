package tech.tolpuddle.faershaer_backend.http.dtos;

import tech.tolpuddle.faershaer_backend.domain.User;

public record AddUserDto (String id, String name, String portraitUrl) {
    public User getUser() {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPortraitUrl(portraitUrl);
        return user;
    }
}
