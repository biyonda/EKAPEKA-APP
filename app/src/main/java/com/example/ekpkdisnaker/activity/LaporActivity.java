package com.example.ekpkdisnaker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

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

public class LaporActivity extends AppCompatActivity {

    ImageView btn_back;
    Button btn_lapor;

    Api api;
    Session session;
    Call<BaseResponse> updateLapor;

    String no_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lapor);

        session = new Session(this);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());
        no_register = getIntent().getStringExtra("no_register");

        btn_lapor = findViewById(R.id.btn_lapor);
        btn_back = findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_lapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateLapor = api.updateLapor(no_register);
                updateLapor.enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(LaporActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                            finish();
                        } else {
                            ApiError apiError = ErrorUtils.parseError(response);
                            Toast.makeText(LaporActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        Toast.makeText(LaporActivity.this, "Error, "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}