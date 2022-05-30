package com.example.ekpkdisnaker.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ekpkdisnaker.R;
import com.example.ekpkdisnaker.api.Api;
import com.example.ekpkdisnaker.api.RetrofitClient;
import com.example.ekpkdisnaker.helpers.ApiError;
import com.example.ekpkdisnaker.helpers.ErrorUtils;
import com.example.ekpkdisnaker.response.BaseResponse;
import com.example.ekpkdisnaker.session.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahKeterampilanActivity extends AppCompatActivity {

    EditText keterampilan, tahun;
    Button btn_simpan;
    ProgressBar progressBar;

    Session session;
    Api api;
    Call<BaseResponse> tambahKeterampilan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_keterampilan);

        keterampilan = findViewById(R.id.keterampilan);
        tahun = findViewById(R.id.tahun);
        btn_simpan = findViewById(R.id.btn_simpan);
        progressBar = findViewById(R.id.progress_register);

        session = new Session(this);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                tambahKeterampilan = api.tambahKeterampilan(keterampilan.getText().toString(), tahun.getText().toString());
                tambahKeterampilan.enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(TambahKeterampilanActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                            setResult(99);
                            finish();
                        } else {
                            progressBar.setVisibility(View.GONE);
                            ApiError apiError = ErrorUtils.parseError(response);
                            Toast.makeText(TambahKeterampilanActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        progressBar.setVisibility(View.GONE);
                        Toast.makeText(TambahKeterampilanActivity.this, "Error, " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}