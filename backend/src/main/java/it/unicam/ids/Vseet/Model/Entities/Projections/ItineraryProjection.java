package it.unicam.ids.Vseet.Model.Entities.Projections;

import java.util.List;

import it.unicam.ids.Vseet.Model.Entities.ContentCategory;

public interface ItineraryProjection {
    Long getID();
    String getName();
    String getDescription();
    UserProjection getCreator();
    boolean getVerified();
    List<ContentProjection> getPoints();
    ContentCategory getContentCategory();
    String getContentType();

    interface ContentProjection {
        Long getID();
        String getName();
        String getDescription();
    }
}
