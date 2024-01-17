package com.example.classmanagement;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.classmanagement.DB.DBHelper;
import com.example.classmanagement.DB.DatabaseHelper;
import com.example.classmanagement.model.Teacher;

import java.util.ArrayList;

//Dữ liệu xử lý đăng kí cho giảng viên
public class GVSignupActivity extends AppCompatActivity {
    EditText magv, password, repassword ;
    Button signup, signin;
    DatabaseHelper DB;
    DBHelper database;
    ArrayList<Teacher> ArrayListGv = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gvsignup);
        magv = (EditText) findViewById(R.id.codegv);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DatabaseHelper(this);
        database = new DBHelper(this);
        Cursor cursor = database.getDataTeacher();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String magv = cursor.getString(1);
            String tengv = cursor.getString(2);
            String ns = cursor.getString(3);
            String gioitinh= cursor.getString(4);
            String sdt = cursor.getString(5);
            String email= cursor.getString(6);

            ArrayListGv.add(new Teacher(id,magv,tengv,ns,gioitinh,sdt,email));
        }

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codegv = magv.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                if (codegv.equals("") || pass.equals("") || repass.equals("") ) {
                    Toast.makeText(GVSignupActivity.this, "Xin hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    if (pass.equals(repass)) {
                        Boolean check_registration_code = DB.checkgvcode(codegv);
                        if(check_code_exists(codegv) == true) { //kiem tra co ton tai ma gv hay khong
                            if(check_registration_code == false) { //kiem tra  ma gv da duoc dki hay chua
                                Boolean insert = DB.insertData(codegv, pass);

                                    if (insert == true) {
                                    Toast.makeText(GVSignupActivity.this, "Đăng kí thành công!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), GVSignupActivity.class);
                                    startActivity(intent);
                                    } else {
                                    Toast.makeText(GVSignupActivity.this, "Đăng kí thất bại!", Toast.LENGTH_SHORT).show();
                                    }
                            }else {
                                Toast.makeText(GVSignupActivity.this, "Mã giảng viên đã được đăng kí!", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(GVSignupActivity.this, "Mã giảng viên không tồn tại!", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(GVSignupActivity.this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
                    }

                }


            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), GVLogActivity.class);
                startActivity(intent);
            }
        });
    }

    //kiem tra ma giang vien ton tai hay khong
    public Boolean check_code_exists(String check) {
        for (Teacher teacher : ArrayListGv) {
            String magv = teacher.getGv_code();
            if(magv.equals(check)){
                return true;
            }
        }
        return false;
    }
}

