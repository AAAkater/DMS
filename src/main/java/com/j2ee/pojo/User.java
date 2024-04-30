package com.j2ee.pojo;

public class User {

    private Integer user_id;
    private String user_name;
    private String user_password;

    public Integer getuser_id() {
        return user_id;
    }

    public void setuser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getuser_name() {
        return user_name;
    }

    public void setuser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getuser_password() {
        return user_password;
    }

    public void setuser_password(String user_password) {
        this.user_password = user_password;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_user_user_password='" + user_password + '\'' +
                '}';
    }
}
