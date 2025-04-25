package it.unicam.ids.Vseet.Model.Entities;

public enum UserRole {
    CONTRIBUTOR,                //Can upload contents and need verification.
    AUTHORIZED_CONTRIBUTOR,     //Can upload contents without verification.
    ANIMATOR,                   //Can upload contests of contents and can verify content.
    CURATOR,                    //Can upload contents and can verify content.
    PLATFORM_MANAGER,           //Roles administrator, can verify contents.
    TOURIST,                    //Can see available contents, can upload content that needs verification.
}
