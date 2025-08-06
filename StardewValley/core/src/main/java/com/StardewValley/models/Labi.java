package com.StardewValley.models;

import com.StardewValley.models.interactions.User;

import java.util.ArrayList;

public class Labi {
    private String name;
    private int ID;
    private boolean isPrivate;
    private String password;
    private boolean isVisible;
    private ArrayList<User> users;
    private User adminUser;

    public Labi(String name, int ID, boolean isPrivate, String password, boolean isVisible, User adminUser) {
        this.name = name;
        this.ID = ID;
        this.isPrivate = isPrivate;
        this.password = password;
        this.isVisible = isVisible;
        this.adminUser = adminUser;
        users = new ArrayList<>();
        users.add(adminUser);
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public String getPassword() {
        return password;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(User adminUser) {
        this.adminUser = adminUser;
    }
}
