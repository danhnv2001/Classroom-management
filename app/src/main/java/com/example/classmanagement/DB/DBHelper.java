package com.example.classmanagement.DB;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.classmanagement.model.Classroom;
import com.example.classmanagement.model.Exercise;
import com.example.classmanagement.model.Student;
import com.example.classmanagement.model.Teacher;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    //Tên database
    private static String DATABASE_NAME = "classmanagement";


    //--------------Bảng lop học--------------------------------------------------------------------
    private static String TABLE_CLASS = "classroom";
    private static String ID_CLASS = "idclass";
    private static String TENLOP = "tenlop";
    private static String THU = "thu";
    private static String GIOHOC = "giohoc";
    private static String PLACE = "place";
    private static String NUMBERSV = "numbersv";
    private static String GVNAME = "gvname";
    private static String MAGV = "magv";
    private static int VERSION = 1;


    //---------------Bảng sinh viên-----------------------------------------------------------------
    private static String TABLE_STUDENT = "student";
    private static String ID_STUDENT = "idstudent";
    private static String STT = "stt";
    private static String STUDENT_NAME = "sudentname";
    private static String STUDENT_CODE = "studentcode";
    private static String SEX = "sex";
    private static String NGANH ="nganh";
    private static String DIEMCC= "diemcc";
    private static String DIEMKT= "diemkt";
    private static String DIEMCUOIKI= "diemcuoiki";
    private static String TONGDIEM="tongdiem";


    //--------------------------Bảng giảng viên-----------------------------------------------------
    private static String TABLE_TEACHER = "giangvien";
    private static String ID_GV = "idgv";
    private static String CODE_TEACHER = "codeteacher";
    private static String TENGV = "tengv";
    private static String NGAYSINH = "ngaysinh";
    private static String GIOITINH = "gioitinh";
    private static String SDT = "sdt";
    private static String EMAIL = "email";


    //----------------------------Bảng bài tập------------------------------------------------------
    private static String TABLE_EXERCISE= "exercise";
    private static String ID_BAI = "idbai";
    private static String TENBAI = "tenbai";
    private static String NGAYRA = "ngayra";
    private static String HANLAM = "studentcode";
    private static String NOIDUNG = "noidung";




    //--------------------------------------------------------------------------------------------

    private Context context;

    //Tạo bảng lop học
    private String SQLQuery = "CREATE TABLE "+ TABLE_CLASS +" ( "+ID_CLASS+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +TENLOP+" TEXT, "
            +THU+" TEXT, "
            +GIOHOC+" TEXT, "
            +PLACE+" TEXT, "
            +NUMBERSV+" INTEGER, "
            +GVNAME+" TEXT,"
            +MAGV+" TEXT)";
    //------------------------------------------------------------------------------------------------

    //Tạo bảng sinh viên
    private String SQLQuery1 = "CREATE TABLE "+ TABLE_STUDENT +" ( "+ID_STUDENT+" integer primary key AUTOINCREMENT, "
            +STT+" TEXT, "
            +STUDENT_NAME+" TEXT, " // dạng text
            +STUDENT_CODE+" TEXT, "
            +SEX+" TEXT, "
            +NGANH+" TEXT, "
            +DIEMCC+" DOUBLE, " //dang double
            +DIEMKT+" DOUBLE, "
            +DIEMCUOIKI+" DOUBLE, "
            +TONGDIEM+" DOUBLE, "
            +ID_CLASS+" INTEGER , FOREIGN KEY ( "+ ID_CLASS +" ) REFERENCES "+ // tham chiếu đến ID_CLASS trong bảng TABLE_CLASS
            TABLE_CLASS+"("+ID_CLASS+"))";
    //----------------------------------------------------------------------------------------------

    //Tạo bảng giảng viên
    private String SQLQuery2 = "CREATE TABLE "+ TABLE_TEACHER +" ( "+ID_GV+" integer primary key AUTOINCREMENT, "
            +CODE_TEACHER+" TEXT, "
            +TENGV+" TEXT, "
            +NGAYSINH+" TEXT,"
            +GIOITINH+" TEXT,"
            +SDT+" TEXT,"
            +EMAIL+" TEXT)";


    //----------------------------------------------------------------------------------------------
    //Tạo bảng bài tập
    private String SQLQuery3 = "CREATE TABLE "+ TABLE_EXERCISE +" ( "+ID_BAI+" integer primary key AUTOINCREMENT, "
            +TENBAI+" TEXT, "
            +NGAYRA+" TEXT, "
            +HANLAM+" TEXT, "
            +NOIDUNG+" TEXT, "
            +ID_CLASS+" INTEGER , FOREIGN KEY ( "+ ID_CLASS +" ) REFERENCES "+
            TABLE_CLASS+"("+ID_CLASS+"))";

    //----------------------------------------------------------------------------------------------


    //constructor  cho lớp dùng để tạo và quản lý cơ sở dữ liệu SQLite
    public DBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Tạo 3 bảng CLass ,Student va Teacher
        db.execSQL(SQLQuery);
        db.execSQL(SQLQuery1);
        db.execSQL(SQLQuery2);
        db.execSQL(SQLQuery3);
    }

    @Override
    public void onUpgrade(
            SQLiteDatabase db, int oldVersion, int newVersion) {
    }

//--------------------------------------THAO TÁC VỚI LỚP HỌC---------------------------------=----------------------------------
    //-----------------------thêm lớp học-----------------------------------------------------------
    public void AddClass(Classroom classroom){
        SQLiteDatabase db = this.getWritableDatabase();
        //không thể lưu trực tiếp xuống insert nên thông qua contentvalues
        ContentValues values = new ContentValues();

        values.put(TENLOP,classroom. getTenlop());
        values.put(THU,classroom.getThu());
        values.put(GIOHOC,classroom.getGiohoc());
        values.put(PLACE,classroom.getPlace());
        values.put(NUMBERSV,classroom.getNumbersv());
        values.put(GVNAME,classroom.getGvname());
        values.put(MAGV,classroom.getGvcode());

        db.insert(TABLE_CLASS,null,values);
        //đóng lại db
        db.close();
    }

    //-------------------------------cập nhật lớp học-----------------------------------------------
    public boolean UpdateClass(Classroom classroom, int id){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TENLOP,classroom. getTenlop());
        values.put(THU,classroom.getThu());
        values.put(GIOHOC,classroom.getGiohoc());
        values.put(PLACE,classroom.getPlace());
        values.put(NUMBERSV,classroom.getNumbersv());
        values.put(GVNAME,classroom.getGvname());
        values.put(MAGV,classroom.getGvcode());

        db.update(TABLE_CLASS,values,ID_CLASS+" = "+id,null);
        Log.e("Ok : ",id+" - id "+values.get(TENLOP)+" + "+values.get(THU)+" + "+values.get(GIOHOC)+
                " + "+ values.get(PLACE)+ " + "+ values.get(NUMBERSV) + " + "+ values.get(GVNAME)+" + "+values.get(MAGV));
        return true;
    }

    //--------------------Lấy tất cả thông tin lớp hoc-----------------------------------------------
    public Cursor getDataClass(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_CLASS,null);
        return res;
    }

    //-------------------Lấy tất cả thông tin lớp hoc có mã giảng viên------------------------------
    public Cursor getDataClassWithCondition(String gvCode) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_CLASS + " WHERE MAGV = ?", new String[]{gvCode});
        return res;
    }

    //---------------------xóa lớp` học-------------------------------------------------------------
    public int DeleteClass(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_CLASS,ID_CLASS+" = "+i,null);
        return res;
    }

    //-----------------------------------xóa sv trong lớp-------------------------------------------
    public int DeleteClassStudent(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_STUDENT,ID_CLASS+" = "+i,null);
        return res;
    }
//--------------------------------------------------------------------------------------------------------------------------------

//---------------------------------------THAO TÁC VỚI GIẢNG VIÊN-------------------------------------------------------------------
    //----------------------------------thêm giảng viên---------------------------------------------
public void AddTeacher(Teacher teacher){
    SQLiteDatabase db = this.getWritableDatabase();
    //không thể lưu trực tiếp xuống insert nên thông qua contentvalues
    ContentValues values = new ContentValues();

    values.put(CODE_TEACHER,teacher. getGv_code());
    values.put(TENGV,teacher.getName());
    values.put(NGAYSINH,teacher. getDate_of_birth());
    values.put(GIOITINH,teacher. getSex());
    values.put(SDT,teacher. getSdt());
    values.put(EMAIL,teacher. getEmail());

    db.insert(TABLE_TEACHER,null,values);

    db.close();
}
    //-------------------------------cập nhật giảng viên---------------------------------------------
    public boolean UpdateGV(Teacher teacher,int id){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(CODE_TEACHER,teacher. getGv_code());
        values.put(TENGV,teacher.getName());
        values.put(NGAYSINH,teacher. getDate_of_birth());
        values.put(GIOITINH,teacher. getSex());
        values.put(SDT,teacher. getSdt());
        values.put(EMAIL,teacher. getEmail());


        db.update(TABLE_TEACHER,values,ID_GV+" = "+id,null);
        Log.e("Ok : ",id+" - id "+values.get(CODE_TEACHER)+" + "+values.get(TENGV)+" + "+values.get(NGAYSINH)+" + "+
                values.get(GIOITINH)+ " + "+ values.get(SDT)+ " + "+ values.get(EMAIL));
        return true;
}

    //--------------------Lấy tất cả giảng viên------------------------------------------------------
    public Cursor getDataTeacher(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_TEACHER,null);
        return res;
    }


    //---------------------xóa giảng viên-------------------------------------------------------------
    public int DeleteTeacher(int i){
        SQLiteDatabase db = this.getWritableDatabase();
        int res = db.delete(TABLE_TEACHER,ID_GV+" = "+i,null);
        return res;
    }
//--------------------------------------------------------------------------------------------------------------------------------


//----------------------------------THAO TÁC VỚI SINH VIÊN-----------------------------------------------------------------------
    //-------------------------------Them danh sách sv----------------------------------------------
    public void AddStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STT,student. getStt());
        values.put(STUDENT_NAME,student. getTen());
        values.put(STUDENT_CODE,student.getMa());
        values.put(SEX,student.getGioiTinh());
        values.put(NGANH,student.getNganh());
        values.put(DIEMCC,student.getDiem1());
        values.put(DIEMKT,student.getDiem2());
        values.put(DIEMCUOIKI,student.getDiem3());
        values.put(TONGDIEM,student.getDiem4());

        long result=db.insert(TABLE_STUDENT,null,values);
        if (result != -1) {
            Log.d("DBHelper", "Student added successfully");
        } else {
            Log.e("DBHelper", "Error adding student");
        }
        //đóng lại db
        db.close();
}

    //-------------------------------Lay tất cả sinh viên-------------------------------------------
    public Cursor getDataStudent(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_STUDENT,null);
        return res;
    }

    //------------------------------Xóa danh sách sinh viên-----------------------------------------
//----------------------------------------------------------------------------------------------------------------------------------

//----------------------------------THAO TÁC VỚI BÀI TẬP--------------------------------------------------------------------------
    //-------------------------Thêm bài tập---------------------------------------------------------
    public void AddEx(Exercise exercise) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TENBAI, exercise.getTenbai());
        values.put(NGAYRA, exercise.getNgayra());
        values.put(HANLAM, exercise.getHanlam());
        values.put(NOIDUNG, exercise.getNoidung());
        values.put(ID_CLASS, exercise.getId_class());

        db.insert(TABLE_EXERCISE, null, values);
        db.close();
    }

    //-------------------------Lấy bài tập thuộc môn học đó--------------------------------------
    public Cursor getDataEx(int id_class){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_EXERCISE+" WHERE "+ID_CLASS+" = "+id_class,null);
        return res;
    }



}