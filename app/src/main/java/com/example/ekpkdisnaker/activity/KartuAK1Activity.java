package com.example.ekpkdisnaker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ekpkdisnaker.R;
import cn.pedant.SweetAlert.SweetAlertDialog;
import android.graphics.Color;
import android.widget.LinearLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KartuAK1Activity extends AppCompatActivity {

    ImageView btn_back;
    LinearLayout btn_ak1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kartu_ak1);

        btn_back = findViewById(R.id.btn_back);
        btn_ak1 = findViewById(R.id.btn_ak1);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_ak1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(KartuAK1Activity.this, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Perhatian")
                .setContentText("Sebelum melanjutkan, mohon lengkapi data diri termasuk foto ktp, pas foto, ijazah, keterampilan (bila ada) di Profil Peserta")
                .setConfirmText("Lanjutkan")
                .setCancelText("Batal")
                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sDialog) {
                        sDialog
                                .setTitleText("Deleted!")
                                .setContentText("Your imaginary file has been deleted!")
                                .setConfirmText("OK")
                                .setConfirmClickListener(null)
                                .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                    }
                })
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        sweetAlertDialog.dismissWithAnimation();
                    }
                })
                .show();
            }
        });

    }
}