package it.unicam.ids.Vseet.Model.Entities.Projections;

import it.unicam.ids.Vseet.Model.Entities.UserRole;

public interface UserProjection {
    String getEmail();
    UserRole getRole();
}
