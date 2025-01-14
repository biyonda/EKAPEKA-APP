package com.diskominfo.ekpkdisnaker.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.diskominfo.ekpkdisnaker.R;
import com.diskominfo.ekpkdisnaker.api.Api;
import com.diskominfo.ekpkdisnaker.api.RetrofitClient;
import com.diskominfo.ekpkdisnaker.helpers.ApiError;
import com.diskominfo.ekpkdisnaker.helpers.ErrorUtils;
import com.diskominfo.ekpkdisnaker.response.BaseResponse;
import com.diskominfo.ekpkdisnaker.session.Session;
import com.diskominfo.ekpkdisnaker.table.ak1;

import cn.pedant.SweetAlert.SweetAlertDialog;

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

    SwipeRefreshLayout swipe_refresh_layout;

    Session session;
    Api api;
    Call<BaseResponse> getKartuAK1;
    Call<BaseResponse<ak1>> getDataAK1;

    private ArrayList<String> number = new ArrayList<>();
    private ArrayList<String> no_register = new ArrayList<>();
    private ArrayList<String> nik = new ArrayList<>();
    private ArrayList<String> nama_peserta = new ArrayList<>();
    private ArrayList<String> tgl_lahir = new ArrayList<>();
    private ArrayList<String> jenis_kelamin = new ArrayList<>();
    private ArrayList<String> pendidikan = new ArrayList<>();
    private ArrayList<String> masa_berlaku = new ArrayList<>();
    private ArrayList<String> status = new ArrayList<>();
    private ArrayList<String> keterangan = new ArrayList<>();
    private ArrayList<String> sts_berlaku = new ArrayList<>();
    private ArrayList<String> file_ak1 = new ArrayList<>();

    AdapterAK1 adapterAK1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kartu_ak1);

        btn_back = findViewById(R.id.btn_back);
        btn_ak1 = findViewById(R.id.btn_ak1);
        list_data = findViewById(R.id.list_data);
        swipe_refresh_layout = findViewById(R.id.swipe_refresh_layout);

        session = new Session(KartuAK1Activity.this);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getDataAK1();
                        swipe_refresh_layout.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        btn_ak1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KartuAK1Activity.this, TambahAK1Activity.class);
                startActivity(intent);
            }
        });

        getDataAK1();
    }

    public void getDataAK1() {
        getDataAK1 = api.getDataAK1();
        getDataAK1.enqueue(new Callback<BaseResponse<ak1>>() {
            @Override
            public void onResponse(Call<BaseResponse<ak1>> call, Response<BaseResponse<ak1>> response) {
                if (response.isSuccessful()) {
                    number.clear();
                    no_register.clear();
                    nik.clear();
                    nama_peserta.clear();
                    tgl_lahir.clear();
                    jenis_kelamin.clear();
                    pendidikan.clear();
                    masa_berlaku.clear();
                    status.clear();
                    keterangan.clear();
                    file_ak1.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
                        number.add((i + 1) + "");
                        no_register.add(response.body().getData().get(i).getNoRegister());
                        nik.add(response.body().getData().get(i).getNikUser());
                        nama_peserta.add(response.body().getData().get(i).getInformasiPeserta().getNamaLengkap());
                        tgl_lahir.add(response.body().getData().get(i).getInformasiPeserta().getTglLahir());
                        jenis_kelamin.add(response.body().getData().get(i).getInformasiPeserta().getJnsKelamin());

                        pendidikan.add(response.body().getData().get(i).getInformasiPeserta().getKdPendidikan());

                        if (response.body().getData().get(i).getFileAk1() == null) {
                            file_ak1.add("kosong");
                        } else {
                            file_ak1.add(response.body().getData().get(i).getFileAk1());
                        }

                        masa_berlaku.add(response.body().getData().get(i).getMasaBerlaku());
                        status.add(response.body().getData().get(i).getStsAk1().toString());
                        keterangan.add(response.body().getData().get(i).getKeterangan());
                        sts_berlaku.add(response.body().getData().get(i).getStsBerlaku().toString());
                    }

                    adapterAK1 = new AdapterAK1(KartuAK1Activity.this, number, no_register, nama_peserta,
                            masa_berlaku, status, keterangan, sts_berlaku, new AdapterAK1.OnEditLocationListener() {
                        @Override
                        public void onClickAdapter(int position) {
                            Intent intent = new Intent(KartuAK1Activity.this, DetailAK1Activity.class);
                            intent.putExtra("no_register", no_register.get(position));
                            intent.putExtra("nik", nik.get(position));
                            intent.putExtra("nama_peserta", nama_peserta.get(position));
                            intent.putExtra("tgl_lahir", tgl_lahir.get(position));
                            intent.putExtra("jenis_kelamin", jenis_kelamin.get(position));
                            intent.putExtra("pendidikan", pendidikan.get(position));
                            intent.putExtra("masa_berlaku", masa_berlaku.get(position));
                            intent.putExtra("status", status.get(position));
                            intent.putExtra("keterangan", keterangan.get(position));
                            intent.putExtra("masa_berlaku", masa_berlaku.get(position));
                            intent.putExtra("file_ak1", file_ak1.get(position));
                            startActivity(intent);
                        }
                    }, new AdapterAK1.OnEditLocationListener() {
                        @Override
                        public void onClickAdapter(int position) {
                            if (status.get(position).equals("0")) {
                                Toast.makeText(KartuAK1Activity.this, "AK1 dengan dalam proses verifikasi", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intent = new Intent(KartuAK1Activity.this, PenempatanActivity.class);
                                intent.putExtra("no_register", no_register.get(position));
                                startActivity(intent);
                            }
                        }
                    }, new AdapterAK1.OnEditLocationListener() {
                        @Override
                        public void onClickAdapter(int position) {
                            Intent intent = new Intent(KartuAK1Activity.this, LaporActivity.class);
                            intent.putExtra("no_register", no_register.get(position));
                            startActivity(intent);
                        }
                    });
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