package com.diskominfo.ekpkdisnaker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.diskominfo.ekpkdisnaker.R;

public class SettingActivity extends AppCompatActivity {

    ImageView btn_back;
    RelativeLayout btn_ubah, btn_pas,btn_ktp, btn_ijazah, btn_skill, btn_bahasa, btn_pengalaman, btn_peminatan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btn_back = findViewById(R.id.btn_back);
        btn_ubah = findViewById(R.id.btn_ubah);
        btn_pas = findViewById(R.id.btn_pas);
        btn_ktp = findViewById(R.id.btn_ktp);
        btn_ijazah = findViewById(R.id.btn_ijazah);
        btn_skill = findViewById(R.id.btn_skill);
        btn_bahasa = findViewById(R.id.btn_bahasa);
        btn_pengalaman = findViewById(R.id.btn_pengalaman);
        btn_peminatan = findViewById(R.id.btn_peminatan);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_ubah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, UbahProfileActivity.class);
                startActivity(intent);
            }
        });

        btn_pas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, PasFotoActivity.class);
                startActivity(intent);
            }
        });

        btn_ktp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, KTPActivity.class);
                startActivity(intent);
            }
        });

        btn_ijazah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, IjazahActivity.class);
                startActivity(intent);
            }
        });

        btn_skill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, KeterampilanActivity.class);
                startActivity(intent);
            }
        });

        btn_bahasa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, BahasaActivity.class);
                startActivity(intent);
            }
        });

        btn_pengalaman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, PengalamanKerjaActivity.class);
                startActivity(intent);
            }
        });

        btn_peminatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, PeminatanActivity.class);
                startActivity(intent);
            }
        });
    }
}