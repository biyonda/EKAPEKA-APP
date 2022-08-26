package com.diskominfo.ekpkdisnaker.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KeterampilanActivity extends AppCompatActivity {

    ImageView btn_back;
    ListView list_data;
    Button btn_tambah;

    Session session;
    Api api;
    Call<BaseResponse<Keterampilan>> getKeterampilan;
    Call<BaseResponse> hapusKeterampilan;

    AdapterKeterampilan adapterKeterampilan;

    private ArrayList<String> id = new ArrayList<>();
    private ArrayList<String> keterangan = new ArrayList<>();
    private ArrayList<String> tahun = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keterampilan);

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

        btn_tambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KeterampilanActivity.this, TambahKeterampilanActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        getData();
    }

    public void getData(){
        getKeterampilan = api.getKeterampilan();
        getKeterampilan.enqueue(new Callback<BaseResponse<Keterampilan>>() {
            @Override
            public void onResponse(Call<BaseResponse<Keterampilan>> call, Response<BaseResponse<Keterampilan>> response) {
                if (response.isSuccessful()) {
                    id.clear();
                    keterangan.clear();
                    tahun.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
                        id.add(response.body().getData().get(i).getId().toString());
                        keterangan.add(response.body().getData().get(i).getKeterangan());
                        tahun.add(response.body().getData().get(i).getTahun());
                    }

                    adapterKeterampilan = new AdapterKeterampilan(KeterampilanActivity.this, keterangan,
                            tahun, new AdapterAK1.OnEditLocationListener() {
                        @Override
                        public void onClickAdapter(int position) {
                            hapusKeterampilan = api.hapusKeterampilan(id.get(position));
                            hapusKeterampilan.enqueue(new Callback<BaseResponse>() {
                                @Override
                                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                    if (response.isSuccessful()) {
                                        id.remove(position);
                                        keterangan.remove(position);
                                        tahun.remove(position);
                                        adapterKeterampilan.notifyDataSetChanged();
                                        Toast.makeText(KeterampilanActivity.this, "Hapus Keterampilan Berhasil", Toast.LENGTH_SHORT).show();
                                    } else {
                                        ApiError apiError = ErrorUtils.parseError(response);
                                        Toast.makeText(KeterampilanActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<BaseResponse> call, Throwable t) {
                                    Toast.makeText(KeterampilanActivity.this, "Error, " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });
                    adapterKeterampilan.notifyDataSetChanged();
                    list_data.setAdapter(adapterKeterampilan);
                } else {
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(KeterampilanActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Keterampilan>> call, Throwable t) {
                Toast.makeText(KeterampilanActivity.this, "Error, " + t.getMessage(), Toast.LENGTH_SHORT).show();
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