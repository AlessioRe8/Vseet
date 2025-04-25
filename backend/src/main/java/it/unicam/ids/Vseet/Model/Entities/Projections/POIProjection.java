package it.unicam.ids.Vseet.Model.Entities.Projections;


import it.unicam.ids.Vseet.Model.Entities.ContentCategory;
import it.unicam.ids.Vseet.Model.Entities.Position;

public interface POIProjection {
    Long getID();
    String getName();
    String getDescription();
    ContentCategory getContentCategory();
    UserProjection getCreator();
    Position getPosition();
    boolean getVerified();
    String getContentType();
}
