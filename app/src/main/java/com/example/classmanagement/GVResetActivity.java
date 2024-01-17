package com.example.classmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classmanagement.DB.DatabaseHelper;

public class GVResetActivity extends AppCompatActivity {
    TextView magv;
    EditText pass,repass;
    Button confirm;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gvreset);

        magv =(TextView) findViewById(R.id.gvcode_reset_text);
        pass= (EditText) findViewById(R.id.password_reset);
        repass = (EditText) findViewById(R.id.repassword_reset);
        confirm=(Button) findViewById(R.id.btnconfirm);
        DB= new DatabaseHelper(this);
        Intent intent = getIntent();
        magv.setText(intent.getStringExtra("magv"));
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= magv.getText().toString();
                String password= pass.getText().toString();
                String repassword= repass.getText().toString();
                if(password.equals(repassword)) {
                    Boolean checkpasswordupdate = DB.updatepassword(user, password);
                    if (checkpasswordupdate == true) {
                        Intent intent = new Intent(getApplicationContext(), GVLogActivity.class);
                        startActivity(intent);
                        Toast.makeText(GVResetActivity.this, "Cập nhật mật khẩu mới thành công!", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(GVResetActivity.this, "Cập nhật không thành công!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(GVResetActivity.this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}