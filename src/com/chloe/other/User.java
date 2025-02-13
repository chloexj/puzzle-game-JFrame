package com.chloe.other;

import java.util.Objects;

public class User {
    private String username;
    private String password;
    private int tryTime;
    public User(){}
    public User(String userName,String password){
        this.username =userName;
        this.password=password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getPassword(){
        return password;
    }
    public String getUsername(){
        return username;
    }

    @Override
    public String toString() {
        return "username=" + username +"&password=" + password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equalsIgnoreCase(user.username)&& Objects.equals(password, user.password);
    }

    public int getTryTime() {
        return tryTime;
    }

    public void setTryTime(int tryTime) {
        this.tryTime = tryTime;
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }
}
