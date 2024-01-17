package com.example.classmanagement.model;

public class Classroom {
    private int id_class;
    //tên môn học
    private String tenlop;
    //thứ học
    private String thu;
    private String giohoc;
    //địa điểm
    private String place;
    //Số lượng snh viên
    private int numbersv;
    //tên giảng viên phụ trách
    private String gvname;
    private String gvcode;

    public Classroom(int id_class, String tenlop, String thu, String giohoc, String place, int numbersv,
                     String gvname, String gvcode) {
        this.id_class = id_class;
        this.tenlop = tenlop;
        this.thu = thu;
        this.giohoc = giohoc;
        this.place = place;
        this.numbersv = numbersv;
        this.gvname = gvname;
        this.gvcode = gvcode;
    }

    public Classroom(String tenlop, String thu, String giohoc, String place, int numbersv, String gvname, String gvcode) {
        this.tenlop = tenlop;
        this.thu = thu;
        this.giohoc = giohoc;
        this.place = place;
        this.numbersv = numbersv;
        this.gvname = gvname;
        this.gvcode = gvcode;
    }

    //------------------id lop--------------------------------------------------------------------
    public int getId_class() {
        return id_class;
    }
    public void setId_class(int id_class) {
        this.id_class = id_class;
    }

    //------------------ten lop------------------------------------------------------------------
    public String getTenlop() {
        return tenlop;
    }
    public void setTenlop(String tenlop) {
        this.tenlop = tenlop;
    }

    //------------------thu---------------------------------------------------------------------
    public String getThu() {
        return thu;
    }
    public void setThu(String thu) {
        this.thu = thu;
    }

    //------------------giovao---------------------------------------------------------------------


    public String getGiohoc() {
        return giohoc;
    }

    public void setGiohoc(String giohoc) {
        this.giohoc = giohoc;
    }


    //------------------dia diem--------------------------------------------------------------------
    public String getPlace() {
        return place;
    }
    public void setPlace(String place) {
        this.place = place;
    }

    //------------------so sv---------------------------------------------------------------------
    public int getNumbersv() {
        return numbersv;
    }
    public void setNumbersv(int numbersv) {
        this.numbersv = numbersv;
    }

    //------------------ten gv---------------------------------------------------------------------
    public String getGvname() {
        return gvname;
    }
    public void setGvname(String gvname) {
        this.gvname = gvname;
    }

    //------------------magv---------------------------------------------------------------------


    public String getGvcode() {
        return gvcode;
    }

    public void setGvcode(String gvcode) {
        this.gvcode = gvcode;
    }
}
