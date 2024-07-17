package tech.tolpuddle.faershaer_backend.services;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import tech.tolpuddle.faershaer_backend.domain.Event;

@RequestScope
public class EventAccessor {
    private Event event;

    public EventAccessor(Event event) {
        this.event = event;
    }

    public Event getEvent() {
        return this.event;
    }
}
