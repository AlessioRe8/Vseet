package it.unicam.ids.Vseet.Model.Entities.POI;
import it.unicam.ids.Vseet.Model.Entities.*;
import jakarta.persistence.*;

@Entity
public class ConcretePointOfInterest extends Content implements PointOfInterest {
    @Embedded
    private Position position;
    @SuppressWarnings("unused")
    private String contentType = "poi";

    public ConcretePointOfInterest(String name, String description, ContentCategory category, User creator, Position position) {
        super(name, description, creator, category);
        this.position = position;
    }

    public ConcretePointOfInterest() {
        super();
    }

    @Override
    public String getType() {
        return "Concrete";
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}

