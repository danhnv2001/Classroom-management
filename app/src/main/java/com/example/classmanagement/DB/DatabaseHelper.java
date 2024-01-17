package com.example.classmanagement.DB;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import com.example.classmanagement.model.Teacher;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    DBHelper DB;
    public static final String DBNAME = "Login.db";

    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
        this.context =context;
        this.DB = new DBHelper(context);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(gvcode TEXT primary key, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    //ham them gia tri
    public Boolean insertData(String gvcode, String password) {
        if (getCodeteacher(gvcode) == true) {
            SQLiteDatabase MyDB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("gvcode", gvcode);
            contentValues.put("password", password);
            long result = MyDB.insert("users", null, contentValues);
            if (result == -1) {
                return false;
            }
        }
            return true;
    }

    // hàm quên mật khẩu và lấy lại mật khẩu
    public Boolean updatepassword(String gvcode, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", password);
        long result = MyDB.update("users", contentValues, "gvcode=?", new String[]{gvcode});
        if (result == -1) return false;
        else
            return true;
    }


    //ham kiem tra xem ma gv da duoc dang ki hay chua
    public Boolean checkgvcode(String magv) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where gvcode = ?", new String[]{magv});
        if (cursor.getCount() > 0)
            return true; //đã dki
        else
            return false; //chưa dki
    }


    //ham kiem tra tk va mk khi login
    public Boolean checkgvcodepassword(String gvcode, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where gvcode = ? and password = ?", new String[]{gvcode, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    //ham kiem tra mật khẩu có đúng không
    public Boolean checkpassword(String pass) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where password = ?", new String[]{pass});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean getCodeteacher(String test) {
        ArrayList<Teacher> ArrayListGv = new ArrayList<>();
        DBHelper dbHelper = new DBHelper(context); // Tạo đối tượng DBHelper
        Cursor cursor = dbHelper.getDataTeacher();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String magv = cursor.getString(1);
            String tengv = cursor.getString(2);
            String ns = cursor.getString(3);
            String gioitinh = cursor.getString(4);
            String sdt = cursor.getString(5);
            String email = cursor.getString(6);
            ArrayListGv.add(new Teacher(id, magv, tengv, ns, gioitinh, sdt, email));
        }
        for (Teacher teacher : ArrayListGv) {
            String magv = teacher.getGv_code();
            if (magv.equals(test)) {
                return true;
            }
        }
        return false;
    }
}