package com.jkutkut.firebasestorage.model;

public class Profile {
    private String name;
    private String email;
    private String urlPhoto;

    public Profile() {
    }

    public Profile(String name, String email, String urlPhoto) {
        this.name = name;
        this.email = email;
        this.urlPhoto = urlPhoto;
    }

    // GETTERS AND SETTERS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
}
