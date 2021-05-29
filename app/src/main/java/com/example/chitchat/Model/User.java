package com.example.chitchat.Model;

public class User {
    private String ID;
    private String Username;
    private String Images;
    private String Email;

    public User(String ID, String Username, String Images,String Email) {
        this.ID = ID;
        this.Username = Username;
        this.Images = Images;
        this.Email=Email;
    }

    public User() {
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getImages() {
        return Images;
    }

    public void setImages(String images) {
        Images = images;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
