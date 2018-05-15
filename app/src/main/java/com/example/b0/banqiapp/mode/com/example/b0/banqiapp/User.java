package com.example.b0.banqiapp;

import org.litepal.crud.DataSupport;

public class User extends DataSupport {
    private long id;
    private   String username;
    private String pwd;
    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
