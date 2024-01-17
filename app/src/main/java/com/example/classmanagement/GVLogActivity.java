package com.example.classmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classmanagement.ADLogActivity;
import com.example.classmanagement.DB.DBHelper;
import com.example.classmanagement.DB.DatabaseHelper;

public class GVLogActivity extends AppCompatActivity {

    EditText gvcode, password;
    Button signin,signup;
    TextView tvad ,tvfp;
    int counter = 0;

    DatabaseHelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gvlog);
        gvcode = (EditText) findViewById(R.id.etgvcode);
        password = (EditText) findViewById(R.id.etpass);
        signin = (Button) findViewById(R.id.btndn);
        signup = (Button) findViewById(R.id.btndk);
        tvad = (TextView) findViewById(R.id.tvad);
        tvfp =(TextView) findViewById(R.id.tvforgot);
        DB = new DatabaseHelper(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String magv =  gvcode.getText().toString();
                String pass = password.getText().toString();

                if ( magv.equals("") || pass.equals("")) {
                    Toast.makeText(GVLogActivity.this, "Xin nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                }else {
                    Boolean checkmagvpass = DB.checkgvcodepassword( magv, pass);
                    if (checkmagvpass == true) {
                        Toast.makeText(GVLogActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),GVHomeActivity.class);
                        intent.putExtra("magv_check",magv);
                        startActivity(intent);
                    } else {
                        Toast.makeText(GVLogActivity.this, "Sai mã hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), GVSignupActivity.class);
                startActivity(intent);
            }
        });

        tvad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), ADLogActivity.class);
                startActivity(intent);
            }
        });
        tvfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), GVForgotActivity.class);
                startActivity(intent);
            }
        });
    }
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