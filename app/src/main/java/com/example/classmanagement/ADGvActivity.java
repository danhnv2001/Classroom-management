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
import android.widget.Button;
import android.widget.ListView;
import com.example.classmanagement.adapter.adapterGV;
import com.example.classmanagement.DB.DBHelper;
import com.example.classmanagement.model.Teacher;
import java.util.ArrayList;

public class ADGvActivity extends AppCompatActivity {
    ListView listViewGv;
    ArrayList<Teacher> ArrayListGv;
    DBHelper database;
    adapterGV adapterGV;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adgv);
        listViewGv = findViewById(R.id.listviewGV);
        database = new DBHelper(this);
        ArrayListGv = new ArrayList<>();

        //Cursor : truy cập, quan ly dữ liệu từ một bảng trong cơ sở dữ liệu SQLite
        Cursor cursor = database.getDataTeacher();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String magv = cursor.getString(1);
            String tengv = cursor.getString(2);
            String ns = cursor.getString(3);
            String gioitinh= cursor.getString(4);
            String sdt = cursor.getString(5);
            String email= cursor.getString(6);
            ArrayListGv.add(new Teacher(id,magv,tengv,ns,gioitinh,sdt,email));

        }
        adapterGV = new adapterGV(ADGvActivity.this, ArrayListGv);
        listViewGv.setAdapter(adapterGV);
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
                //Chuyển tới màn hình thêm giảng viên
                Intent intent = new Intent(ADGvActivity.this,ADAddGvActivity.class);
                startActivity(intent);
                break;
            default:
                Intent intent2 = new Intent(ADGvActivity.this,MainActivity.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    //---------------------------Phương thức xóa gv------------------------------------------------
    public void delete(final int position){

        DialogDeleteGv(position);
    }
    //---------------------------Phương thức update gv----------------------------------------------
    public void update(final int position){

        Cursor cursor = database.getDataTeacher();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            if(id == position){
                Intent intent = new Intent(ADGvActivity.this,ADUpdateGvActivity.class);
                intent.putExtra("id",position);

                String magv = cursor.getString(1);
                String tengv = cursor.getString(2);
                String ns = cursor.getString(3);
                String gt = cursor.getString(4);
                String sdt = cursor.getString(5);
                String email = cursor.getString(6);


                intent.putExtra("magv",magv);
                intent.putExtra("tengv",tengv);
                intent.putExtra("ns",ns);
                intent.putExtra("gt",gt);
                intent.putExtra("sdt",sdt);
                intent.putExtra("email",email);

                startActivity(intent);
            }
        }

    }

    //---------------------------Phương thức xem thông tin gv---------------------------------------
    public void information(final int pos){


        Cursor cursor = database.getDataTeacher();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            if (id == pos) {
                Intent intent = new Intent(ADGvActivity.this, ADInforGvActivity.class);
                intent.putExtra("id", pos);

                String magv = cursor.getString(1);
                String tengv = cursor.getString(2);
                String ns = cursor.getString(3);
                String gt = cursor.getString(4);
                String sdt = cursor.getString(5);
                String email = cursor.getString(6);


                intent.putExtra("magv",magv);
                intent.putExtra("tengv",tengv);
                intent.putExtra("ns",ns);
                intent.putExtra("gt",gt);
                intent.putExtra("sdt",sdt);
                intent.putExtra("email",email);

                startActivity(intent);
            }
        }

    }
    //----------------------------------------------------------------------------------------------

    //----------------------------Dialog delete-----------------------------------------------------
    private void DialogDeleteGv(int position) {

        //Tạo đối tượng cửa sổ dialog
        Dialog dialog  =  new Dialog(this);

        //Nạp layout vào
        dialog.setContentView(R.layout.dialogdeletegv);

        //Click No mới thoát, click ngoài ko thoát
        dialog.setCanceledOnTouchOutside(false);

        //Ánh xạ
        Button btnYes = dialog.findViewById(R.id.buttonYesDeleteGV);
        Button btnNo = dialog.findViewById(R.id.buttonNoDeleteGV);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = new DBHelper(ADGvActivity.this);
                //Xóa trong SQL
                database.DeleteTeacher(position);
                //Cập nhật lại listview
                Intent intent = new Intent(ADGvActivity.this,ADGvActivity.class);
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
            Intent intent = new Intent(ADGvActivity.this,ADHomeActivity.class);
            startActivity(intent);
            finish();
        }
        super.onBackPressed();
    }

}