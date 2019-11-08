package com.qf.pojo;

public class UserRoles {
    private int rid;
    private String username;
    private String role_name;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public UserRoles(String username) {
        this.username = username;
    }

    public UserRoles() {
    }

    @Override
    public String toString() {
        return "UserRoles{" +
                "rid=" + rid +
                ", username='" + username + '\'' +
                ", role_name='" + role_name + '\'' +
                '}';
    }
}
