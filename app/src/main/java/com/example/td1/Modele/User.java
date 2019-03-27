package com.example.td1.Modele;

public class User {
    private int id_user;
    private String mail;
    private String password;
    private String userType;


    public User(int id_user, String mail, String password, String userType) {
        this.id_user = id_user;
        this.mail = mail;
        this.password = password;
        this.userType = userType;
    }


    public int getId_user() { return id_user; }

    public void setId_user(int id_user) { this.id_user = id_user; }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
