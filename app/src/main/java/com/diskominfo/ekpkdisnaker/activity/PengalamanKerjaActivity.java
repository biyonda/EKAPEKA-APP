package com.diskominfo.ekpkdisnaker.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.diskominfo.ekpkdisnaker.R;
import com.diskominfo.ekpkdisnaker.api.Api;
import com.diskominfo.ekpkdisnaker.api.RetrofitClient;
import com.diskominfo.ekpkdisnaker.helpers.ApiError;
import com.diskominfo.ekpkdisnaker.helpers.ErrorUtils;
import com.diskominfo.ekpkdisnaker.response.BaseResponse;
import com.diskominfo.ekpkdisnaker.session.Session;
import com.diskominfo.ekpkdisnaker.table.Keterampilan;
import com.diskominfo.ekpkdisnaker.table.Pengalaman;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengalamanKerjaActivity extends AppCompatActivity {

    ImageView btn_back;
    ListView list_data;
    Button btn_tambah;

    Session session;
    Api api;
    Call<BaseResponse<Pengalaman>> getPengalaman;
    Call<BaseResponse> hapusPengalaman;

    AdapterPengalaman adapterPengalaman;

    private ArrayList<String> id = new ArrayList<>();
    private ArrayList<String> jabatan = new ArrayList<>();
    private ArrayList<String> lama_kerja = new ArrayList<>();
    private ArrayList<String> pemberi_kerja = new ArrayList<>();
    private ArrayList<String> uraian_tugas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengalaman_kerja);

        btn_back = findViewById(R.id.btn_back);
        list_data = findViewById(R.id.list_data);
        btn_tambah = findViewById(R.id.btn_tambah);

        session = new Session(this);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void getData(){
        getPengalaman = api.getPengalaman();
        getPengalaman.enqueue(new Callback<BaseResponse<Pengalaman>>() {
            @Override
            public void onResponse(Call<BaseResponse<Pengalaman>> call, Response<BaseResponse<Pengalaman>> response) {
                if (response.isSuccessful()) {
                    id.clear();
                    jabatan.clear();
                    lama_kerja.clear();
                    pemberi_kerja.clear();
                    uraian_tugas.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
                        id.add(response.body().getData().get(i).getId().toString());
                        jabatan.add(response.body().getData().get(i).getJabatan());
                        lama_kerja.add(response.body().getData().get(i).getLamaKerja());
                        pemberi_kerja.add(response.body().getData().get(i).getLamaKerja());
                        uraian_tugas.add(response.body().getData().get(i).getLamaKerja());
                    }

                    adapterPengalaman = new AdapterPengalaman(PengalamanKerjaActivity.this, jabatan, lama_kerja, pemberi_kerja, uraian_tugas, new AdapterAK1.OnEditLocationListener() {
                        @Override
                        public void onClickAdapter(int position) {
                            hapusPengalaman = api.hapusPengalaman(id.get(position));
                            hapusPengalaman.enqueue(new Callback<BaseResponse>() {
                                @Override
                                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                    if (response.isSuccessful()) {
                                        id.remove(position);
                                        jabatan.remove(position);
                                        lama_kerja.remove(position);
                                        pemberi_kerja.remove(position);
                                        uraian_tugas.remove(position);
                                        adapterPengalaman.notifyDataSetChanged();
                                        Toast.makeText(PengalamanKerjaActivity.this, "Hapus Pengalaman Berhasil", Toast.LENGTH_SHORT).show();
                                    } else {
                                        ApiError apiError = ErrorUtils.parseError(response);
                                        Toast.makeText(PengalamanKerjaActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<BaseResponse> call, Throwable t) {
                                    Toast.makeText(PengalamanKerjaActivity.this, "Error, " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    adapterPengalaman.notifyDataSetChanged();
                    list_data.setAdapter(adapterPengalaman);
                } else {
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(PengalamanKerjaActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Pengalaman>> call, Throwable t) {
                Toast.makeText(PengalamanKerjaActivity.this, "Error, " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode == 99) {
                getData();
            }
        }
    }
}