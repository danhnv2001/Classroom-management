package com.example.classmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ADLogActivity extends AppCompatActivity {

    EditText email, password;
    Button signin;
    TextView textView;
    int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adlog);
        email = (EditText) findViewById(R.id.etemail);
        password = (EditText) findViewById(R.id.etpass);
        signin = (Button) findViewById(R.id.btndn);
        textView = (TextView) findViewById(R.id.tvad);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email= email.getText().toString();
                String Pass= password.getText().toString();
                if( Email.equals("") || Pass.equals("")){
                    Toast.makeText(com.example.classmanagement.ADLogActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }else{
                    if(Email.equals("danh2001@gmail.com") && Pass.equals("123456")){
                        Toast.makeText(com.example.classmanagement.ADLogActivity.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), ADHomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(com.example.classmanagement.ADLogActivity.this, "Wrong Email or Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), GVLogActivity.class);
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
