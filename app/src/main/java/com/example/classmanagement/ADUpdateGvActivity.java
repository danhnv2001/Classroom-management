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

public class ADUpdateGvActivity extends AppCompatActivity {
    EditText udMagv,udTengv,udNs,udGt,udSdt,udEmail;
    Button btnUpdate;
    DBHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adupdate_gv);
        Intent intent = getIntent();

        int id = intent.getIntExtra("id",0);

        String Magv = intent.getStringExtra("magv");
        String Tengv = intent.getStringExtra("tengv");
        String Ns = intent.getStringExtra("ns");
        String Gt = intent.getStringExtra("gt");
        String Sdt = intent.getStringExtra("sdt");
        String Email = intent.getStringExtra("email");



        udMagv = findViewById(R.id.etupMagv);
        udTengv = findViewById(R.id.etupTengv);
        udNs = findViewById(R.id.etupNs);
        udGt= findViewById(R.id.etupGt);
        udSdt = findViewById(R.id.etupSdt);
        udEmail = findViewById(R.id.etupEmail);

        btnUpdate = findViewById(R.id.btnUpdateGv);


        udMagv.setText(Magv);
        udTengv.setText(Tengv);
        udNs.setText(Ns);
        udGt.setText(Gt);
        udSdt.setText(Sdt);
        udEmail.setText(Email);


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
        dialog.setContentView(R.layout.dialogupdategv);

        //Click No mới thoát, click ngoài ko thoát
        dialog.setCanceledOnTouchOutside(false);

        //Ánh xạ
        Button btnYes = dialog.findViewById(R.id.btnYesUpdateGv);
        Button btnNo = dialog.findViewById(R.id.btnNoUpdateGv);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String magv = udMagv.getText().toString();
                String tengv = udTengv.getText().toString();
                String ns = udNs.getText().toString();
                String gt = udGt.getText().toString();
                String sdt= udSdt.getText().toString();
                String email= udEmail.getText().toString();

                Teacher teacher = updateTeacher();

                if(magv.equals("") || tengv.equals("") || ns.equals("") ||gt.equals("") ||
                        sdt.equals("") || email.equals("")) {
                    Toast.makeText(ADUpdateGvActivity.this,"Chưa nhập đủ thông tin!",Toast.LENGTH_SHORT).show();
                }
                else {

                    database.UpdateGV(teacher ,id);
                    //Khởi tạo lại activity main
                    Intent intent = new Intent(ADUpdateGvActivity.this,ADGvActivity.class);

                    startActivity(intent);
                    Toast.makeText(ADUpdateGvActivity.this,"Cập nhật thành công",Toast.LENGTH_SHORT).show();
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
    private Teacher updateTeacher(){

        String magv = udMagv.getText().toString();
        String tengv = udTengv.getText().toString();
        String ns = udNs.getText().toString();
        String gt = udGt.getText().toString();
        String sdt= udSdt.getText().toString();
        String email= udEmail.getText().toString();


        Teacher teacher = new Teacher(magv,tengv,ns,gt,sdt,email);
        return teacher;
    }
}
