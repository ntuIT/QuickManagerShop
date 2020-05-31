package com.qman.web.masterdata.model;

public class UserGender {

    private Byte value;

    private String displayName;

    public UserGender(Byte value, String displayName) {
        super();
        this.value = value;
        this.displayName = displayName;
    }

    public Byte getValue() { return value; }

    public String getDisplayName() { return displayName; }

    public void setValue(Byte value) { this.value = value; }

    public void setDisplayName(String displayName) { this.displayName = displayName; }
}
