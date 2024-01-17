package com.example.classmanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.example.classmanagement.adapter.adapterClass;
import com.example.classmanagement.DB.DBHelper;
import com.example.classmanagement.model.Classroom;
import java.util.ArrayList;
public class ADClassActivity extends AppCompatActivity {

    ListView listViewClass;
    ArrayList<Classroom> ArrayListClass;
    DBHelper database;
    adapterClass adapterClass;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adclass);
        database = new DBHelper(this);
       listViewClass = findViewById(R.id.listviewClass);

        ArrayListClass = new ArrayList<>();

        //Cursor : truy cập, quan ly dữ liệu từ một bảng trong cơ sở dữ liệu SQLite
        Cursor cursor = database.getDataClass();
        while (cursor.moveToNext()){

            int id = cursor.getInt(0);
            String tenlop = cursor.getString(1);
            String thu = cursor.getString(2);
            String giohoc = cursor.getString(3);
            String place = cursor.getString(4);
            int numbersv = cursor.getInt(5);
            String gvname= cursor.getString(6);
            String gvcode = cursor.getString(7);

            ArrayListClass.add(new Classroom(id,tenlop,thu,giohoc,place,numbersv,gvname,gvcode));
        }
        adapterClass = new adapterClass(ADClassActivity.this, ArrayListClass);
        listViewClass.setAdapter(adapterClass);
        cursor.moveToFirst();
        cursor.close();

        //Chuyển qua danh sách sinh viên
        listViewClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ADClassActivity.this,ADStudentActivity.class);
                int id_class = ArrayListClass.get(position).getId_class();
                //Gửi dữ liệu id qua activity student để xem sinh viên học lop học này
                intent.putExtra("id_class",id_class);
                startActivity(intent);
            }
        });

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
                //Chuyển tới màn hình thêm lớp học
                Intent intent = new Intent(ADClassActivity.this,ADAddClassActivity.class);
                startActivity(intent);
                break;
            default:
                Intent intent2 = new Intent(ADClassActivity.this,MainActivity.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    //---------------------------Phương thức xóa lớp------------------------------------------------
    public void delete(final int position){

        DialogDeleteClass(position);
    }
    //---------------------------update lớp---------------------------------------------------------
    public void update(final int position){

        Cursor cursor = database.getDataClass();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            if(id == position){
                Intent intent = new Intent(ADClassActivity.this,ADUpdateClassActivity.class);
                intent.putExtra("id",position);

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

    //---------------------------Phương thức xem thông tin lớp--------------------------------------
    public void information(final int pos){


        Cursor cursor = database.getDataClass();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            if (id == pos) {
                Intent intent = new Intent(ADClassActivity.this, ADInforClassActivity.class);
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
    //----------------------------------------------------------------------------------------------

    //----------------------------Dialog Delete-----------------------------------------------------
    private void DialogDeleteClass(int position) {

        //Tạo đối tượng cửa sổ dialog
        Dialog dialog  =  new Dialog(this);

        //Nạp layout vào
        dialog.setContentView(R.layout.dialogdeleteclass);

        //Click No mới thoát, click ngoài ko thoát
        dialog.setCanceledOnTouchOutside(false);

        //Ánh xạ
        Button btnYes = dialog.findViewById(R.id.buttonYesDeleteClass);
        Button btnNo = dialog.findViewById(R.id.buttonNoDeleteClass);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = new DBHelper(ADClassActivity.this);
                //Xóa trong SQL
                database.DeleteClass(position);
                database.DeleteClassStudent(position);
                //Cập nhật lại listview
                Intent intent = new Intent(ADClassActivity.this,ADClassActivity.class);
                startActivity(intent);

            }
        });

        //Nếu no thì đóng dialog
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        //show dialog lên activity
        dialog.show();
    }


    //Nhấn nút back ở activity này thì chuyển qua activity được thiết lập riêng
    @Override
    public void onBackPressed() {
        counter++;
        if (counter ==1){
            Intent intent = new Intent(ADClassActivity.this,ADHomeActivity.class);
            startActivity(intent);
            finish();
        }
        super.onBackPressed();
    }

}