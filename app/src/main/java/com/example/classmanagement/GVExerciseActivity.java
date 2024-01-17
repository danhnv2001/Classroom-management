package com.example.classmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.classmanagement.DB.DBHelper;
import com.example.classmanagement.model.Classroom;
import com.example.classmanagement.adapter.adapterGVClassEx;
import java.util.ArrayList;

public class GVExerciseActivity extends AppCompatActivity {
    ListView listViewGVClass;
    ArrayList<Classroom> ArrayListGVClass;
    DBHelper database;
    adapterGVClassEx adapterGVClassEx;
    String codegv_class;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gvexercise);
        database = new DBHelper(this);
        listViewGVClass = findViewById(R.id.listviewGVClassEx);

        ArrayListGVClass = new ArrayList<>();
        // lấy giá trị username từ GVHomeActivity thông quan Bundle
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            codegv_class = bundle.getString("data1");
        }
        //Cursor : truy cập, quan ly dữ liệu từ một bảng trong cơ sở dữ liệu SQLite

            Cursor cursor = database.getDataClassWithCondition(codegv_class);
            while (cursor.moveToNext()) {

                int id = cursor.getInt(0);
                String tenlop = cursor.getString(1);
                String thu = cursor.getString(2);
                String giohoc = cursor.getString(3);
                String place = cursor.getString(4);
                int numbersv = cursor.getInt(5);
                String gvname = cursor.getString(6);
                String gvcode = cursor.getString(7);

                ArrayListGVClass.add(new Classroom(id, tenlop, thu, giohoc, place, numbersv, gvname, gvcode));
            }
            adapterGVClassEx = new adapterGVClassEx(GVExerciseActivity.this, ArrayListGVClass);
            listViewGVClass.setAdapter(adapterGVClassEx);
            cursor.close();


        //chuyển qua danh sách bài tập trong lớp
        listViewGVClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(GVExerciseActivity.this,GVListExerciseActivity.class);
                int id_class = ArrayListGVClass.get(position).getId_class();
                //Gửi dữ liệu id qua activity bai tap để xem bai tap co thuoc lop nay hay khong
                intent.putExtra("id_class",id_class);
                startActivity(intent);
            }
        });
    }

}