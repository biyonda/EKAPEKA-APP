package com.example.ekpkdisnaker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.ekpkdisnaker.R;

public class SettingActivity extends AppCompatActivity {

    ImageView btn_back;
    RelativeLayout btn_pas,btn_ktp, btn_ijazah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btn_back = findViewById(R.id.btn_back);
        btn_pas = findViewById(R.id.btn_pas);
        btn_ktp = findViewById(R.id.btn_ktp);
        btn_ijazah = findViewById(R.id.btn_ijazah);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_pas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, PasFotoActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_ktp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, KTPActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn_ijazah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, IjazahActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}