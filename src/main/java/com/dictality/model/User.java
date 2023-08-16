package com.dictality.model;

public record User(String username, String password, int groupId) {

    public boolean isSameUser(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", groupId='" + groupId + '\'' +
                '}';
    }
}
