package com.qman.web.module.user.model;

public class ChangePassRequest {

    private String currentPassword;

    private String newPassword;

    public String getCurrentPassword() { return currentPassword; }

    public String getNewPassword() { return newPassword; }

    public void setCurrentPassword(String currentPassword) { this.currentPassword = currentPassword; }

    public void setNewPassword(String newPassword) { this.newPassword = newPassword; }
}
