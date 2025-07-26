package com.bersamed.test.data.model;

public class LoginResponse {
    private String token;
    private LoggedInUser user;

    public String getToken() {
        return token;
    }

    public LoggedInUser getUser() {
        return user;
    }
}

