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
import com.example.classmanagement.model.Exercise;

public class GVAddExActivity extends AppCompatActivity {
    Button buttonAddEx;
    EditText edtTenbai,edtNgayra,edtHanlam,edtNoidung;
    DBHelper database;
    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gvadd_ex);
        buttonAddEx = findViewById(R.id.btnaddex);
        edtTenbai = findViewById(R.id.ettenbai);
        edtNgayra = findViewById(R.id.etngayra);
        edtHanlam= findViewById(R.id.ethanlam);
        edtNoidung = findViewById(R.id.etnoidung);
        database = new DBHelper(this);
        Intent intent = getIntent();
        int id_class = intent.getIntExtra("id_class",0);

        buttonAddEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAdd(id_class);
            }
        });
    }
    //Dialog Add
    private void DialogAdd(int id_class) {


        Dialog dialog  =  new Dialog(this);

        dialog.setContentView(R.layout.dialogaddex);

        //Click No mới thoát, click ngoài ko thoát
        dialog.setCanceledOnTouchOutside(false);

        //Ánh xạ
        Button btnYes = dialog.findViewById(R.id.btnYesAddEx);
        Button btnNo = dialog.findViewById(R.id.btnNoAddEx);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Tenbai = edtTenbai.getText().toString();
                String Ngayra = edtNgayra.getText().toString();
                String Hanlam = edtHanlam.getText().toString();
                String Noidung = edtNoidung.getText().toString();
                // phải nhập đầy đủ thông tin
                if(Tenbai.equals("") || Ngayra.equals("") || Hanlam.equals("") ||Noidung.equals("")){
                    Toast.makeText(GVAddExActivity.this,"Chưa nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else {
                   Exercise exercise = CreatEx(id_class);
                    database.AddEx(exercise);
                    //Khởi tạo lại activity main
                    Intent intent = new Intent(GVAddExActivity.this,GVListExerciseActivity.class);
                    //finish();
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("id_class",id_class);
                    startActivity(intent);
                    finish();
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    Toast.makeText(GVAddExActivity.this,"Thêm thành công",Toast.LENGTH_SHORT).show();
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
        dialog.show();
    }


    //Create Subject
    private Exercise CreatEx(int id_class){


        String Tenbai = edtTenbai.getText().toString();
        String Ngayra = edtNgayra.getText().toString();
        String Hanlam = edtHanlam.getText().toString();
        String Noidung = edtNoidung.getText().toString();

        Exercise exercise = new Exercise(Tenbai,Ngayra,Hanlam,Noidung,id_class);
        return exercise;
    }


}