package com.example.classmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.classmanagement.DB.DBHelper;
import com.example.classmanagement.DB.DatabaseHelper;

public class GVForgotActivity extends AppCompatActivity {
    EditText gvcode;
    Button reset;
    DatabaseHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gvforgot);
       gvcode= (EditText)findViewById(R.id.gvcode_reset);
        reset= (Button) findViewById(R.id.btnreset);
        DB= new DatabaseHelper(this);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String magv=gvcode.getText().toString();
                Boolean checkgvcode =DB.checkgvcode(magv);
                if(checkgvcode==true){
                    Intent intent=new Intent(getApplicationContext(),GVResetActivity.class);
                    intent.putExtra("magv",magv);
                    startActivity(intent);
                }else{
                    Toast.makeText(GVForgotActivity.this,"Mã giảng viên không đúng", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}