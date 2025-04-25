package it.unicam.ids.Vseet.Model.Entities.DTO;

import it.unicam.ids.Vseet.Model.Entities.UserRole;

public class RoleUpdateRequest {
    private UserRole role;

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
