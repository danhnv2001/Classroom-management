package com.example.classmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import com.example.classmanagement.DB.DBHelper;
import com.example.classmanagement.adapter.adapterExercise;
import com.example.classmanagement.model.Exercise;
import java.util.ArrayList;

public class GVListExerciseActivity extends AppCompatActivity {
    ListView listViewEx;
    ArrayList<Exercise> ArrayListEx;
    DBHelper database;
    adapterExercise adapterExercise;
    int id_class=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gvlist_exercise);


        database = new DBHelper(this);
        listViewEx = findViewById(R.id.listviewEx);
        Intent intent = getIntent();
        id_class = intent.getIntExtra("id_class",0);

        ArrayListEx = new ArrayList<>();

        Cursor cursor = database.getDataEx(id_class);
        while (cursor.moveToNext()){

            int id_sub = cursor.getInt(5);
            int id = cursor.getInt(0);
            String tenbai = cursor.getString(1);
            String ngayra = cursor.getString(2);
            String hanlam = cursor.getString(3);
            String noidung = cursor.getString(4);

            ArrayListEx.add(new Exercise(id,tenbai, ngayra, hanlam, noidung,id_sub));
        }
        adapterExercise = new adapterExercise(GVListExerciseActivity.this, ArrayListEx);
        listViewEx.setAdapter(adapterExercise);

        cursor.moveToFirst();
        cursor.close();

    }
    //Nạp một menu add vào actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuadd,menu);
        return true;
    }

    //-----Bắt sự kiện khi click vào Add------------------------------------------------------------
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuAdd:
                //Chuyển tới màn hình thêm bai tap
                Intent intent = new Intent(this,GVAddExActivity.class);
                intent.putExtra("id_class",id_class);
                startActivity(intent);
                break;
            default:
                Intent intent1= new Intent(this,GVExerciseActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //---------------------------Phương thức xem thông tin bai--------------------------------------
    public void information(final int pos){


        Cursor cursor = database.getDataEx(id_class);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            if (id == pos) {
                Intent intent = new Intent(GVListExerciseActivity.this, GVInforExActivity.class);
                intent.putExtra("id", pos);

                String tenbai = cursor.getString(1);
                String ngayra = cursor.getString(2);
                String hanlam = cursor.getString(3);
                String noidung= cursor.getString(4);
                int id_class = cursor.getInt(5);


                intent.putExtra("tenbai" ,tenbai);
                intent.putExtra("ngayra",ngayra);
                intent.putExtra("hanlam",hanlam);
                intent.putExtra("noidung",noidung);
                intent.putExtra("id_class",id_class);

                startActivity(intent);
            }
        }

    }
    //----------------------------------------------------------------------------------------------


}