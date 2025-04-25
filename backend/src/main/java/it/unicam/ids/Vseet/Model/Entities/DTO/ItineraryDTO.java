package it.unicam.ids.Vseet.Model.Entities.DTO;

import it.unicam.ids.Vseet.Model.Entities.ContentCategory;

public class ItineraryDTO {
    private final String name;
    private final String description;
    private final ContentCategory contentCategory;
    public ItineraryDTO(String name, String description, ContentCategory category) {
        this.name = name;
        this.description = description;
        this.contentCategory = category;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ContentCategory getContentCategory() {
        return contentCategory;
    }
}
