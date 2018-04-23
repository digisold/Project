package com.digisold.web.mybatis.entity;

public class SystemMenu {
    private Integer id;

    private String menuIcon;

    private String name;

    private Integer parentMenuId;

    private String url;

    public SystemMenu(Integer id, String menuIcon, String name, Integer parentMenuId, String url) {
        this.id = id;
        this.menuIcon = menuIcon;
        this.name = name;
        this.parentMenuId = parentMenuId;
        this.url = url;
    }

    public SystemMenu() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentMenuId() {
        return parentMenuId;
    }

    public void setParentMenuId(Integer parentMenuId) {
        this.parentMenuId = parentMenuId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}