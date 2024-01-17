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

public class ADAddClassActivity extends AppCompatActivity {
    Button buttonAddClass;
    EditText edtTenlop,edtThu,edtGiohoc,edtMagv,edtPlace,edtNumbersv,edtGvname;
    DBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adadd_class);
        buttonAddClass = findViewById(R.id.btnAddclass);
        edtTenlop = findViewById(R.id.etTenlop);
        edtThu = findViewById(R.id.etThu);
        edtGiohoc = findViewById(R.id.etGiohoc);
        edtPlace = findViewById(R.id.etPlace);
        edtNumbersv = findViewById(R.id.etNumbersv);
        edtGvname = findViewById(R.id.etGvname);
        edtMagv = findViewById(R.id.etMagv);
        database = new DBHelper(this);

        buttonAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd();
            }
        });
    }
    //Dialog Add
    private void DialogAdd() {

        //Tạo đối tượng cửa sổ dialog
        Dialog dialog  =  new Dialog(this);

        //Nạp layout vào
        dialog.setContentView(R.layout.dialogaddclass);

        //Click No mới thoát, click ngoài ko thoát
        dialog.setCanceledOnTouchOutside(false);

        //Ánh xạ
        Button btnYes = dialog.findViewById(R.id.btnYesAddClass);
        Button btnNo = dialog.findViewById(R.id.btnNoAddClass);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Tenlop = edtTenlop.getText().toString();
                String Thu = edtThu.getText().toString();
                String Giohoc = edtGiohoc.getText().toString();
                String Place = edtPlace.getText().toString();
                String Numbersv = edtNumbersv.getText().toString();
                String GvName = edtGvname.getText().toString();
                String Magv = edtMagv.getText().toString();
                // phải nhập đầy đủ thông tin
                if(Tenlop.equals("") || Thu.equals("") || Giohoc.equals("")
                    ||Place.equals("") ||Numbersv.equals("")||GvName.equals("") ||Magv.equals("")){
                    Toast.makeText(ADAddClassActivity.this,"Chưa nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else {
                    Classroom classroom = CreatClassroom();

                    database.AddClass(classroom);
                    //Khởi tạo lại activity main
                    Intent intent = new Intent(ADAddClassActivity.this,ADClassActivity.class);
                    //finish();
                    startActivity(intent);
                    Toast.makeText(ADAddClassActivity.this,"Thêm thành công",Toast.LENGTH_SHORT).show();
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
    private Classroom CreatClassroom(){


        String Tenlop = edtTenlop.getText().toString();
        String Thu = edtThu.getText().toString();
        String Giohoc = edtGiohoc.getText().toString();
        String Place = edtPlace.getText().toString();
        int numbersv = Integer.parseInt(edtNumbersv.getText()+"");
        String GvName = edtGvname.getText().toString();
        String Magv = edtMagv.getText().toString();

        Classroom classroom = new Classroom(Tenlop,Thu,Giohoc,Place,numbersv,GvName,Magv);
        return classroom;
    }

}

