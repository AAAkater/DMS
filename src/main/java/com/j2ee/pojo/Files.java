package com.j2ee.pojo;

public class Files {
   private int file_id;
   private int user_id;
   private String file_address;
   private String file_name;
   private int file_from;

    public Files(int file_id, int user_id, String file_address, String file_name, int file_from) {
        this.file_id = file_id;
        this.user_id = user_id;
        this.file_address = file_address;
        this.file_name = file_name;
        this.file_from = file_from;
    }



    public Files() {
    }

    public int getFile_id() {
        return file_id;
    }

    public void setFile_id(int file_id) {
        this.file_id = file_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getFile_address() {
        return file_address;
    }

    public void setFile_address(String file_address) {
        this.file_address = file_address;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public int getFile_from() {
        return file_from;
    }

    public void setFile_from(int file_from) {
        this.file_from = file_from;
    }
}
