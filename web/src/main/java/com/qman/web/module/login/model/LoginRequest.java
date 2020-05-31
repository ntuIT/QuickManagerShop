package com.qman.web.module.login.model;

public class LoginRequest {

    private String username;

    private String password;

    private boolean keepLogin;

    public boolean isKeepLogin() { return keepLogin; }

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public void setUsername(String username) { this.username = username; }

    public void setPassword(String password) { this.password = password; }

    public void setKeepLogin(boolean keepLogin) { this.keepLogin = keepLogin; }
}
