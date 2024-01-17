package com.example.classmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.classmanagement.DB.DBHelper;
import com.example.classmanagement.model.StudentFault;

import java.util.ArrayList;

public class GVListStudentActivity extends AppCompatActivity {
    ListView  listView;
    Button btn_add;
    ArrayList<StudentFault> ArrayListSt;
    DBHelper database;
    int id_ex=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gvlist_student);
        database = new DBHelper(this);
        listView = findViewById(R.id.listViewEr);
        ArrayListSt = new ArrayList<>();
        Intent intent = getIntent();
        id_ex = intent.getIntExtra("id_ex",0);
        Cursor cursor = database.getDataStudent();
        while (cursor.moveToNext()){

            int id_sub = cursor.getInt(5);
            int id = cursor.getInt(0);
            String ten = cursor.getString(1);
            String ma = cursor.getString(2);
            String solan = cursor.getString(3);
            String noidung = cursor.getString(4);

            ArrayListSt.add(new StudentFault(id,ten, ma, solan, noidung,id_sub));
        }


        cursor.moveToFirst();
        cursor.close();

    }

}