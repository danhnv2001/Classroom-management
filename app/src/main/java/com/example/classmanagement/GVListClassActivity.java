package com.example.classmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.classmanagement.DB.DBHelper;
import com.example.classmanagement.adapter.adapterGvClass;
import com.example.classmanagement.model.Classroom;

import java.util.ArrayList;

public class GVListClassActivity extends AppCompatActivity {
    ListView listViewGVClass;
    ArrayList<Classroom> ArrayListGVClass;
    DBHelper database;
    adapterGvClass adapterGvClass;
    String codegv_class;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gvlist_class);
        database = new DBHelper(this);
        listViewGVClass = findViewById(R.id.listviewGVClass);

        ArrayListGVClass = new ArrayList<>();
        // lấy giá trị username từ GVHomeActivity thông quan Bundle
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            codegv_class = bundle.getString("data1");
        }
        //Cursor : truy cập, quan ly dữ liệu từ một bảng trong cơ sở dữ liệu SQLite
        Cursor cursor = database.getDataClassWithCondition(codegv_class);
        while (cursor.moveToNext()){

            int id = cursor.getInt(0);
            String tenlop = cursor.getString(1);
            String thu = cursor.getString(2);
            String giohoc = cursor.getString(3);
            String place = cursor.getString(4);
            int numbersv = cursor.getInt(5);
            String gvname= cursor.getString(6);
            String gvcode = cursor.getString(7);

            ArrayListGVClass.add(new Classroom(id,tenlop,thu,giohoc,place,numbersv,gvname,gvcode));
        }
        adapterGvClass = new adapterGvClass(GVListClassActivity.this, ArrayListGVClass);
        listViewGVClass.setAdapter(adapterGvClass);
        cursor.close();

        //Chuyển qua thong tin chi tiet lop hoc

    }
    public void informationclass(final int pos){

        Cursor cursor = database.getDataClass();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            if (id == pos) {
                Intent intent = new Intent(GVListClassActivity.this, ADInforClassActivity.class);
                intent.putExtra("id", pos);

                String tenlop = cursor.getString(1);
                String thu = cursor.getString(2);
                String giohoc = cursor.getString(3);
                String place = cursor.getString(4);
                int numbersv = cursor.getInt(5);
                String gvname = cursor.getString(6);
                String gvcode = cursor.getString(7);

                intent.putExtra("tenlop",tenlop);
                intent.putExtra("thu",thu);
                intent.putExtra("giohoc",giohoc);
                intent.putExtra("place",place);
                intent.putExtra("numbersv",numbersv);
                intent.putExtra("gvname",gvname);
                intent.putExtra("gvcode",gvcode);

                startActivity(intent);
            }
        }

    }
}






















