package tech.tolpuddle.faershaer_backend.http.dtos;

import tech.tolpuddle.faershaer_backend.domain.Event;

public record EventDto (String id, String label) {
    static public EventDto getDto(Event event) {
        return new EventDto(event.getId(), event.getLabel());
    }
}
