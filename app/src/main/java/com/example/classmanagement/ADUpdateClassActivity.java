package com.example.classmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.classmanagement.DB.DBHelper;
import com.example.classmanagement.model.Classroom;

public class ADUpdateClassActivity extends AppCompatActivity {
    EditText udTenlop,udThu,udGiohoc,udMagv,udPlace,udNumbersv,udGvname;
    Button btnUpdate;
    DBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adupdate_class);
        Intent intent = getIntent();

        int id = intent.getIntExtra("id",0);

        String Tenlop = intent.getStringExtra("tenlop");
        String Thu = intent.getStringExtra("thu");
        String Giohoc = intent.getStringExtra("giohoc");
        String Place = intent.getStringExtra("place");
        int Numbersv = intent.getIntExtra("numbersv",0);
        String Gvname = intent.getStringExtra("gvname");
        String Magv = intent.getStringExtra("gvcode");


        udTenlop = findViewById(R.id.etupTenlop);
        udThu = findViewById(R.id.etupThu);
        udGiohoc = findViewById(R.id.etupGiohoc);
        udPlace = findViewById(R.id.etupPlace);
        udNumbersv = findViewById(R.id.etupNumbersv);
        udGvname = findViewById(R.id.etupGvname);
        udMagv = findViewById(R.id.etupMagv);

       btnUpdate = findViewById(R.id.btnUpdateClass);


        udTenlop.setText(Tenlop);
        udThu.setText(Thu);
        udGiohoc.setText(Giohoc);
        udPlace.setText(Place);
        udNumbersv.setText(Numbersv+"");
        udGvname.setText(Gvname);
        udMagv.setText(Magv);


        database = new DBHelper(this);


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUpdate(id);
            }
        });
    }
    //Dialog Update
    private void DialogUpdate(int id) {

        //Tạo đối tượng cửa sổ dialog
        Dialog dialog  =  new Dialog(this);

        //Nạp layout vào
        dialog.setContentView(R.layout.dialogupdateclass);

        //Click No mới thoát, click ngoài ko thoát
        dialog.setCanceledOnTouchOutside(false);

        //Ánh xạ
        Button btnYes = dialog.findViewById(R.id.btnYesUpdateClass);
        Button btnNo = dialog.findViewById(R.id.btnNoUpdateClass);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tenlop = udTenlop.getText().toString();
                String thu = udThu.getText().toString();
                String giohoc = udGiohoc.getText().toString();
                String place = udPlace.getText().toString();
                String numbersv= udNumbersv.getText().toString();
                String gvname= udGvname.getText().toString();
                String gvcode = udMagv.getText().toString();

                Classroom classroom = updateClassroom();

                if(tenlop.equals("") ||  thu.equals("") || giohoc.equals("") ||gvcode.equals("") ||
                        place.equals("") || numbersv.equals("") ||gvname.equals("")) {
                    Toast.makeText(ADUpdateClassActivity.this,"Chưa nhập đủ thông tin!",Toast.LENGTH_SHORT).show();
                }
                else {

                    database.UpdateClass(classroom ,id);
                    //Khởi tạo lại activity main
                    Intent intent = new Intent(ADUpdateClassActivity.this,ADClassActivity.class);

                    startActivity(intent);
                    Toast.makeText(ADUpdateClassActivity.this,"Cập nhật thành công",Toast.LENGTH_SHORT).show();
                }
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
    //Create Subject
    private Classroom updateClassroom(){

        String Tenlop = udTenlop.getText().toString();
        String Thu = udThu.getText().toString();
        String Giohoc = udGiohoc.getText().toString();
        String Place = udPlace.getText().toString();
        int Numbersv = Integer.parseInt(udNumbersv.getText().toString());
        String Gvname = udGvname.getText().toString();
        String Gvcode = udMagv.getText().toString();

        Classroom classroom = new Classroom(Tenlop,Thu,Giohoc,Place,Numbersv,Gvname,Gvcode);
        return classroom;
    }
}

