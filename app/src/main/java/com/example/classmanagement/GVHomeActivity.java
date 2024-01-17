package com.example.classmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GVHomeActivity extends AppCompatActivity {
    Button gvlop,gvbt,gvdoimk,gvdx;
    TextView magv;
    int counter = 0;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gvhome);
        magv =(TextView) findViewById(R.id.gvcode_home);
        gvlop= (Button) findViewById(R.id.btnLopday);
        gvbt= (Button) findViewById(R.id.btnBaitap);
        gvdx= (Button) findViewById(R.id.btnExit);
        gvdoimk = (Button) findViewById(R.id.btnDoipass);
        Intent intent = getIntent();
        magv.setText("ID-CODE :"+"   "+intent.getStringExtra("magv_check"));

        gvlop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GVHomeActivity.this,GVListClassActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("data1", getIntent().getStringExtra("magv_check"));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        gvbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GVHomeActivity.this,GVExerciseActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("data1", getIntent().getStringExtra("magv_check"));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        gvdx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogExit();
            }
        });

        gvdoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GVHomeActivity.this,GVChangePassActivity.class);
                //lấy giá trị username từ GVLogActivity và gán gtri vào Bundle
                Bundle bundle = new Bundle();
                bundle.putString("data", getIntent().getStringExtra("magv_check"));
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    //Đăng xuất
    private void DialogExit() {

        //Tạo đối tượng cửa sổ dialog
        Dialog dialog  =  new Dialog(this);

        //Nạp layout vào
        dialog.setContentView(R.layout.dialogexit);

        //Click No mới thoát, click ngoài ko thoát
        dialog.setCanceledOnTouchOutside(false);

        //Ánh xạ
        Button btnYes = dialog.findViewById(R.id.buttonYes);
        Button btnNo = dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Khởi tạo lại activity main
                Intent intent = new Intent(getApplicationContext(), GVLogActivity.class);
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
    //nhaan bach de thoat ra ung dung
    public void onBackPressed() {
        counter++;
        if (counter >= 1){
            // Tạo sự kiện kết thúc app
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startActivity(startMain);
        }
    }

}