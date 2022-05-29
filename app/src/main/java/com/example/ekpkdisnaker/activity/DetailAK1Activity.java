package com.example.ekpkdisnaker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ekpkdisnaker.R;

public class DetailAK1Activity extends AppCompatActivity {

    LinearLayout download,lapor;
    TextView nik,nama_pengguna, tgl_lahir, jk, pendidikan, sts_kartu, masa_berlaku;
    ImageView btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ak1);

        btn_back = findViewById(R.id.btn_back);
        nama_pengguna = findViewById(R.id.nama_pengguna);
        nik = findViewById(R.id.nik);
        tgl_lahir = findViewById(R.id.tgl_lahir);
        pendidikan = findViewById(R.id.pendidikan);
        sts_kartu = findViewById(R.id.sts_kartu);
        masa_berlaku = findViewById(R.id.masa_berlaku);
        download = findViewById(R.id.download);
        lapor = findViewById(R.id.lapor);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        lapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}