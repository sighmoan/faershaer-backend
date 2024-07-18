package tech.tolpuddle.faershaer_backend.services;

import tech.tolpuddle.faershaer_backend.domain.User;

public class UserAccessor {
    private User user;

    public UserAccessor() {
    }

    public UserAccessor(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
