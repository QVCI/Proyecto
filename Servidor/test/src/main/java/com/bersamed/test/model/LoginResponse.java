package com.bersamed.test.model;

public class LoginResponse {
    private String token;
    private LoggedInUser user;

    public LoginResponse(String token, LoggedInUser user) {
        this.token = token;
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public LoggedInUser getUser() {
        return user;
    }
}
