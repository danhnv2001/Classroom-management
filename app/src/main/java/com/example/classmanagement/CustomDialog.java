package com.example.classmanagement;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.classmanagement.model.Student;


public class CustomDialog extends Dialog {

    public Context context;
    private Student student;
    private TextView diem1, diem2, diem3, diem4;
    private Button buttonOK;
    private Button buttonCancel, btnUpdate;

    public CustomDialog(Student student, Context context) {
        super(context);
        this.context = context;
        this.student = student;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_dialog);

        this.diem1 = findViewById(R.id.diemcc);
        this.diem2 = findViewById(R.id.diembt);
        this.diem3 = findViewById(R.id.diemgk);
        this.diem4 = findViewById(R.id.diemtong);

        diem1.setText("Điểm chuyên cần: " + student.getDiem1() + "");
        diem2.setText("Điểm bài tập: " + student.getDiem2() + "");
        diem3.setText("Điểm cuối kì: " + student.getDiem3() + "");
        diem4.setText("Điểm tổng kết: " + student.getDiem4() + "");

        this.buttonCancel = (Button) findViewById(R.id.btn_cancel);


        this.buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonCancelClick();
            }
        });
    }

    private void buttonCancelClick() {
        this.dismiss();
    }
}

