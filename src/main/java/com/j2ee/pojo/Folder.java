package com.j2ee.pojo;

public class Folder {
    private int folder_id;
    private int folder_from;
    private String folder_name;
    private int user_id;

    public Folder(int folder_id, int folder_from, String folder_name, int user_id) {
        this.folder_id = folder_id;
        this.folder_from = folder_from;
        this.folder_name = folder_name;
        this.user_id = user_id;
    }

    public Folder() {
    }

    public int getFolder_id() {
        return folder_id;
    }

    public void setFolder_id(int folder_id) {
        this.folder_id = folder_id;
    }

    public int getFolder_from() {
        return folder_from;
    }

    public void setFolder_from(int folder_from) {
        this.folder_from = folder_from;
    }

    public String getFolder_name() {
        return folder_name;
    }

    public void setFolder_name(String folder_name) {
        this.folder_name = folder_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "folder_id=" + folder_id +
                ", folder_from=" + folder_from +
                ", folder_name='" + folder_name + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
