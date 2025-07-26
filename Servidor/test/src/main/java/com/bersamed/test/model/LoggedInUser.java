package com.bersamed.test.model;

public class LoggedInUser {
    private String id;
    private String name;

    public LoggedInUser(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
