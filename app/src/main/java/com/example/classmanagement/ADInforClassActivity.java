package com.example.classmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ADInforClassActivity extends AppCompatActivity {
    TextView tvTenlop, tvThu,tvGiohoc,tvPlace,tvNumbersv,tvGvname,tvMagv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adinfor_class);
        tvTenlop = findViewById(R.id.tvTenlop);
        tvThu = findViewById(R.id.tvThu);
        tvGiohoc = findViewById(R.id.tvGiohoc);
        tvPlace = findViewById(R.id.tvPlace);
        tvNumbersv = findViewById(R.id.tvNumbersv);
        tvGvname= findViewById(R.id.tvGvName);
        tvMagv= findViewById(R.id.tvGvcode);


        Intent intent = getIntent();
        String tenlop = intent.getStringExtra("tenlop");
        String thu = intent.getStringExtra("thu");
        String giohoc = intent.getStringExtra("giohoc");
        String place = intent.getStringExtra("place");
        int numbersv = intent.getIntExtra("numbersv",0);
        String gvname = intent.getStringExtra("gvname");
        String magv = intent.getStringExtra("gvcode");


        tvTenlop.setText(tenlop);
        tvThu.setText(thu);
        tvGiohoc.setText(giohoc);
        tvPlace.setText(place);
        tvNumbersv.setText(numbersv+"");
        tvGvname.setText(gvname);
        tvMagv.setText(magv);

    }
}

