package com.example.classmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ADInforGvActivity extends AppCompatActivity {
    TextView tvMagv, tvTengv,tvNs,tvGt,tvSdt,tvEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adinfor_gv);
        tvMagv = findViewById(R.id.tvMagv);
        tvTengv = findViewById(R.id.tvTengv);
        tvNs = findViewById(R.id.tvNs);
        tvGt = findViewById(R.id.tvGt);
        tvSdt = findViewById(R.id.tvSDT);
        tvEmail = findViewById(R.id.tvEmail);

        Intent intent = getIntent();
        String magv = intent.getStringExtra("magv");
        String tengv = intent.getStringExtra("tengv");
        String ns = intent.getStringExtra("ns");
        String gt = intent.getStringExtra("gt");
        String Sdt = intent.getStringExtra("sdt");
        String Email= intent.getStringExtra("email");



        tvMagv.setText(magv);
        tvTengv.setText(tengv);
        tvNs.setText(ns);
        tvGt.setText(gt);
        tvSdt.setText(Sdt);
        tvEmail.setText(Email);


    }
}