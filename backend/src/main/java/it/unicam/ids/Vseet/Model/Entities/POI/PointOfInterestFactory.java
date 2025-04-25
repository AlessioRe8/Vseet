package it.unicam.ids.Vseet.Model.Entities.POI;

import it.unicam.ids.Vseet.Model.Entities.ContentCategory;
import it.unicam.ids.Vseet.Model.Entities.Position;
import it.unicam.ids.Vseet.Model.Entities.User;
import org.springframework.stereotype.Component;

@Component
public class PointOfInterestFactory {
    public PointOfInterest createPOI(String type, String name, String description,
                                            ContentCategory category, User creator,
                                            Position position) {
        if ("CONCRETE".equalsIgnoreCase(type)) {
            return new ConcretePointOfInterest(name, description, category, creator, position);
        } else if ("LOGIC".equalsIgnoreCase(type)) {
            return new LogicPointOfInterest(name, description, category, creator, position);
        } else throw new IllegalArgumentException("Unknown type or not defined.");
    }
}
