package com.example.ekpkdisnaker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ekpkdisnaker.R;
import com.example.ekpkdisnaker.api.Api;
import com.example.ekpkdisnaker.api.RetrofitClient;
import com.example.ekpkdisnaker.helpers.ApiError;
import com.example.ekpkdisnaker.helpers.ErrorUtils;
import com.example.ekpkdisnaker.response.BaseResponse;
import com.example.ekpkdisnaker.response.UserResponse;
import com.example.ekpkdisnaker.session.Session;
import com.example.ekpkdisnaker.table.ak1;

import cn.pedant.SweetAlert.SweetAlertDialog;

import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KartuAK1Activity extends AppCompatActivity {

    ImageView btn_back;
    LinearLayout btn_ak1;
    ListView list_data;

    Session session;
    Api api;
    Call<BaseResponse> getKartuAK1;
    Call<BaseResponse<ak1>> getDataAK1;

    private ArrayList<String> number = new ArrayList<>();
    private ArrayList<String> no_register = new ArrayList<>();
    private ArrayList<String> nama_peserta = new ArrayList<>();
    private ArrayList<String> masa_berlaku = new ArrayList<>();
    private ArrayList<String> status = new ArrayList<>();

    AdapterAK1 adapterAK1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kartu_ak1);

        btn_back = findViewById(R.id.btn_back);
        btn_ak1 = findViewById(R.id.btn_ak1);
        list_data = findViewById(R.id.list_data);

        session = new Session(KartuAK1Activity.this);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());
        getDataAK1();

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
                        .setConfirmText("Lanjutkan").setCancelButtonBackgroundColor(R.color.tetriary)
                        .setCancelButtonTextColor(R.color.main_blue_color).setCancelText("Batal")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                getKartuAK1 = api.getKartuAK1();
                                getKartuAK1.enqueue(new Callback<BaseResponse>() {
                                    @Override
                                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                        if (response.isSuccessful()) {
                                            sDialog
                                                    .setTitleText("Sukses")
                                                    .setContentText(response.body().getMessage())
                                                    .setConfirmText("OK")
                                                    .setConfirmClickListener(null)
                                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                        } else {
                                            ApiError apiError = ErrorUtils.parseError(response);
                                            sDialog
                                                    .setTitleText("Gagal !!!")
                                                    .setContentText(apiError.getMessage() + "")
                                                    .setConfirmText("OK")
                                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                        @Override
                                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                            getDataAK1();
                                                            sweetAlertDialog.dismiss();
                                                        }
                                                    })
                                                    .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                                        sDialog
                                                .setTitleText("Gagal !!")
                                                .setContentText(t.getMessage())
                                                .setConfirmText("OK")
                                                .setConfirmClickListener(null)
                                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                    }
                                });

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

    public void getDataAK1() {
        getDataAK1 = api.getDataAK1();
        getDataAK1.enqueue(new Callback<BaseResponse<ak1>>() {
            @Override
            public void onResponse(Call<BaseResponse<ak1>> call, Response<BaseResponse<ak1>> response) {
                if (response.isSuccessful()) {
                    number.clear();
                    no_register.clear();
                    nama_peserta.clear();
                    masa_berlaku.clear();
                    status.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
                        number.add((i+1)+"");
                        no_register.add(response.body().getData().get(i).getNoRegister());
                        nama_peserta.add(response.body().getData().get(i).getPeserta().getNamaLengkap());
                        masa_berlaku.add(response.body().getData().get(i).getMasaBerlaku());
                        status.add(response.body().getData().get(i).getStsAk1().toString());
                    }

                    adapterAK1 = new AdapterAK1(KartuAK1Activity.this, number, no_register, nama_peserta,
                            masa_berlaku, status);
                    adapterAK1.notifyDataSetChanged();
                    list_data.setAdapter(adapterAK1);
                } else {
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(KartuAK1Activity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<ak1>> call, Throwable t) {
                Toast.makeText(KartuAK1Activity.this, "Error, " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}