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
import com.example.classmanagement.model.Teacher;

public class ADAddGvActivity extends AppCompatActivity {
    Button buttonAddGv;
    EditText edtMagv , edtTengv, edtNs ,edtGt, edtSdt,edtEmail;
    DBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adadd_gv);
        buttonAddGv = findViewById(R.id.btnAddgv);
        edtMagv = findViewById(R.id.etMagv);
        edtTengv = findViewById(R.id.etTengv);
        edtNs = findViewById(R.id.etNs);
        edtGt = findViewById(R.id.etGt);
        edtSdt = findViewById(R.id.etSdt);
        edtEmail = findViewById(R.id.etEmail);


        database = new DBHelper(this);

        buttonAddGv.setOnClickListener(new View.OnClickListener() {
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
        dialog.setContentView(R.layout.dialogaddgv);

        //Click No mới thoát, click ngoài ko thoát
        dialog.setCanceledOnTouchOutside(false);

        //Ánh xạ
        Button btnYes = dialog.findViewById(R.id.btnYesAddGV);
        Button btnNo = dialog.findViewById(R.id.btnNoAddGV);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Magv = edtMagv.getText().toString();
                String Tengv = edtTengv.getText().toString();
                String Ns = edtNs.getText().toString();
                String Gt = edtGt.getText().toString();
                String Sdt = edtSdt.getText().toString();
                String Email = edtEmail.getText().toString();
                // phải nhập đầy đủ thông tin
                if(Magv.equals("") || Tengv.equals("") || Ns.equals("") || Gt.equals("")
                        ||Sdt.equals("") ||Email.equals("")){
                    Toast.makeText(ADAddGvActivity.this,"Chưa nhập đủ thông tin",Toast.LENGTH_SHORT).show();
                }
                else {
                    Teacher teacher = CreatTeacher();

                    database.AddTeacher(teacher);
                    //Khởi tạo lại activity main
                    Intent intent = new Intent(ADAddGvActivity.this,ADGvActivity.class);
                    //finish();
                    startActivity(intent);
                    Toast.makeText(ADAddGvActivity.this,"Thêm thành công",Toast.LENGTH_SHORT).show();
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
    private Teacher CreatTeacher(){


        String Magv = edtMagv.getText().toString();
        String Tengv = edtTengv.getText().toString();
        String Ns = edtNs.getText().toString();
        String Gt = edtGt.getText().toString();
        String Sdt = edtSdt.getText().toString();
        String Email = edtEmail.getText().toString();

        Teacher teacher = new Teacher(Magv,Tengv,Ns,Gt,Sdt,Email);
        return teacher;
    }

}

