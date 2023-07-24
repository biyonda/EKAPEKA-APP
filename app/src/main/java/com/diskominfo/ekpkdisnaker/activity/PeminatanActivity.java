package com.diskominfo.ekpkdisnaker.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.diskominfo.ekpkdisnaker.R;

public class PeminatanActivity extends AppCompatActivity {

    ImageView btn_back;
    EditText jabatan, lokasi, pelatihan;
    Spinner lokasi_penempatan, gaji;
    AppCompatButton btn_simpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peminatan);

        jabatan = findViewById(R.id.jabatan);
        lokasi = findViewById(R.id.lokasi);
        pelatihan = findViewById(R.id.pelatihan);
        lokasi_penempatan = findViewById(R.id.lokasi_penempatan);
        gaji = findViewById(R.id.gaji);
        btn_simpan = findViewById(R.id.btn_simpan);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}