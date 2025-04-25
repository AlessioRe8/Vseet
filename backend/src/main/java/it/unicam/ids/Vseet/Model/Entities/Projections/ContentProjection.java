package it.unicam.ids.Vseet.Model.Entities.Projections;

import it.unicam.ids.Vseet.Model.Entities.ContentCategory;

public interface ContentProjection {
    Long getID();
    String getName();
    String getDescription();
    UserProjection getCreator();
    ContentCategory getContentCategory();
    boolean getVerified();
}
