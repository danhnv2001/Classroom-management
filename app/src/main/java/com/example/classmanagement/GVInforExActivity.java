package com.example.classmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class GVInforExActivity extends AppCompatActivity {
    TextView tvten, tvngayra, tvhanlam, tvnoidung;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gvinfor_ex);
        tvten = findViewById(R.id.tvTenbai);
        tvngayra = findViewById(R.id.tvNgayra);
        tvhanlam = findViewById(R.id.tvHanlam);
        tvnoidung = findViewById(R.id.tvNoidung);


        Intent intent = getIntent();
        String tenbai = intent.getStringExtra("tenbai");
        String ngayra = intent.getStringExtra("ngayra");
        String hanlam = intent.getStringExtra("hanlam");
        String noidung = intent.getStringExtra("noidung");

        tvten.setText(tenbai);
        tvngayra.setText(ngayra);
        tvhanlam .setText(hanlam);
        tvnoidung.setText(noidung);

    }
}
