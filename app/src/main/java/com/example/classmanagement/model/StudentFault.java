package com.example.classmanagement.model;

public class StudentFault {
    private int id_st;
    private String name;
    private String ma;
    private String solan;
    private String mota;
    private int id_ex;

    public StudentFault(int id_st, String name, String ma, String solan, String mota, int id_ex) {
        this.id_st = id_st;
        this.name = name;
        this.ma = ma;
        this.solan = solan;
        this.mota = mota;
        this.id_ex = id_ex;
    }

    public int getId_st() {
        return id_st;
    }

    public void setId_st(int id_st) {
        this.id_st = id_st;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getSolan() {
        return solan;
    }

    public void setSolan(String solan) {
        this.solan = solan;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public int getId_ex() {
        return id_ex;
    }

    public void setId_ex(int id_ex) {
        this.id_ex = id_ex;
    }
}
