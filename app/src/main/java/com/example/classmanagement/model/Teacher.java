package com.example.classmanagement.model;

public class Teacher {
    private int id_gv;
    private String gv_code;
    private String name;
    private String date_of_birth;
    private String sex;
    private String sdt;
    private String email;


    public Teacher(int id_gv, String gv_code, String name,
                   String date_of_birth, String sex, String sdt, String email) {
        this.id_gv = id_gv;
        this.gv_code = gv_code;
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.sex = sex;
        this.sdt = sdt;
        this.email = email;

    }

    public Teacher(String gv_code, String name, String date_of_birth, String sex, String sdt,
                   String email) {
        this.gv_code = gv_code;
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.sex = sex;
        this.sdt = sdt;
        this.email = email;
    }

    public int getId_gv() {
        return id_gv;
    }

    public void setId_gv(int id_gv) {
        this.id_gv = id_gv;
    }

    public String getGv_code() {
        return gv_code;
    }
    public void setGv_code(String gv_code) {
        this.gv_code = gv_code;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }
    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public String getSex() {
        return sex;
    }
    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSdt() {
        return sdt;
    }
    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
