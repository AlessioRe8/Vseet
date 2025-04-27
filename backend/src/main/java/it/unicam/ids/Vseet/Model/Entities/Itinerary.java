package it.unicam.ids.Vseet.Model.Entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Itinerary extends Content{
    @ManyToMany
    @JoinTable(
    name = "content_points",
    joinColumns = @JoinColumn(name = "itinerary_id"),
    inverseJoinColumns = @JoinColumn(name = "points_id")
)
    private List<Content> points;
    @SuppressWarnings("unused")
    private String contentType = "itinerary";

    public Itinerary(String name, String description, User creator, ContentCategory category) {
        super(name, description, creator, category);
        this.points = new ArrayList<>();
    }

    protected Itinerary() {
        super();
    }

    public void addContent(Content content) {
        points.add(content);
    }

    public void setPoints(List<Content> points) {
        this.points = points;
    }
    public List<Content> getPoints() {
        return points;
    }

}
