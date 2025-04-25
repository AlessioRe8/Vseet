package it.unicam.ids.Vseet.Model.Entities.DTO;

import it.unicam.ids.Vseet.Model.Entities.ContentCategory;
import it.unicam.ids.Vseet.Model.Entities.Position;

public class PointOfInterestDTO {
    private String name;
    private String description;
    private ContentCategory contentCategory;
    private Position position;

    public PointOfInterestDTO(String name, String description, ContentCategory category, Position position) {
        this.name = name;
        this.description = description;
        this.contentCategory = category;
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public ContentCategory getContentCategory() {
        return contentCategory;
    }

    public void setContentCategory(ContentCategory category) {
        this.contentCategory = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
