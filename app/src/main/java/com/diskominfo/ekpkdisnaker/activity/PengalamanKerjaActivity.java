package com.diskominfo.ekpkdisnaker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.diskominfo.ekpkdisnaker.R;

public class PengalamanKerjaActivity extends AppCompatActivity {

    ImageView btn_back;
    ListView list_data;
    Button btn_tambah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengalaman_kerja);

        btn_back = findViewById(R.id.btn_back);
        list_data = findViewById(R.id.list_data);
        btn_tambah = findViewById(R.id.btn_tambah);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}