package com.qman.web.module.base;

public class SideBarItem {

    private boolean active;

    private String name;

    private String path;

    private String icon;

    public SideBarItem(boolean active, String name, String path, String icon) {
        super();
        this.active = active;
        this.name = name;
        this.path = path;
        this.icon = icon;
    }

    public SideBarItem(String name, String path, String icon) {
        this(false, name, path, icon);
    }

    public boolean isActive() { return active; }

    public String getName() { return name; }

    public String getPath() { return path; }

    public String getIcon() { return icon; }

    public void setName(String name) { this.name = name; }

    public void setPath(String path) { this.path = path; }

    public void setIcon(String icon) { this.icon = icon; }

    public void setActive(boolean active) { this.active = active; }
}
