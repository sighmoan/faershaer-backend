package tech.tolpuddle.faershaer_backend.http.dtos;

import tech.tolpuddle.faershaer_backend.domain.User;

public record UserDto(String id, String name, String portraitUrl) {
    static public UserDto getFromUser(User user) {
        return new UserDto(user.getId(), user.getName(), user.getPortraitUrl());
    }
}
