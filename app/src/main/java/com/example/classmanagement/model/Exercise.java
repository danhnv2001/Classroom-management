package com.example.classmanagement.model;

public class Exercise {
    private int id_ex;
    private String tenbai;
    private String ngayra;
    private String hanlam;
    private String noidung;
    private int id_class;

    public Exercise(String tenbai, String ngayra, String hanlam, String noidung) {
        this.tenbai = tenbai;
        this.ngayra = ngayra;
        this.hanlam = hanlam;
        this.noidung = noidung;
    }

    public Exercise(int id_ex, String tenbai, String ngayra, String hanlam, String noidung, int id_class) {
        this.id_ex = id_ex;
        this.tenbai = tenbai;
        this.ngayra = ngayra;
        this.hanlam = hanlam;
        this.noidung = noidung;
        this.id_class = id_class;
    }

    public Exercise(String tenbai, String ngayra, String hanlam, String noidung, int id_class) {
        this.tenbai = tenbai;
        this.ngayra = ngayra;
        this.hanlam = hanlam;
        this.noidung = noidung;
        this.id_class = id_class;
    }

    public int getId_ex() {
        return id_ex;
    }

    public void setId_ex(int id_ex) {
        this.id_ex = id_ex;
    }

    public String getTenbai() {
        return tenbai;
    }

    public void setTenbai(String tenbai) {
        this.tenbai = tenbai;
    }

    public String getNgayra() {
        return ngayra;
    }

    public void setNgayra(String ngayra) {
        this.ngayra = ngayra;
    }

    public String getHanlam() {
        return hanlam;
    }

    public void setHanlam(String hanlam) {
        this.hanlam = hanlam;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public int getId_class() {
        return id_class;
    }

    public void setId_class(int id_class) {
        this.id_class = id_class;
    }
}
