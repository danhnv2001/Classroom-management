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

public class GVChangePassActivity extends AppCompatActivity {
    EditText etpassnow, etpassnew, etrepass;
    Button btnchange;
    String user;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gvchange_pass);
        etpassnow= (EditText) findViewById(R.id.password_now);
        etpassnew= (EditText) findViewById(R.id.password_change);
        etrepass= (EditText) findViewById(R.id.repassword_change);
        btnchange= (Button) findViewById(R.id.btnchange);
        DB= new DatabaseHelper(this);

        btnchange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // lấy giá trị username từ GVHomeActivity thông quan Bundle
                Bundle bundle = getIntent().getExtras();
                if (bundle != null) {
                    user = bundle.getString("data");
                }
                String passnow= etpassnow.getText().toString();
                String passnew=etpassnew.getText().toString();
                String repassnew= etrepass.getText().toString();
                Boolean checkpassnow= DB.checkpassword(passnow);
                if(checkpassnow ==true){
                    if(passnew.equals(repassnew)){
                        Boolean changepassword = DB.updatepassword(user ,passnew);
                        if(changepassword==true){
                            Intent intent = new Intent(getApplicationContext(), GVHomeActivity.class);
                            startActivity(intent);
                            Toast.makeText(GVChangePassActivity.this, "Đổi mật khẩu thành công!", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(GVChangePassActivity.this, "Giá trị mật khẩu mới không khớp!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(GVChangePassActivity.this, "Sai mật khẩu!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}