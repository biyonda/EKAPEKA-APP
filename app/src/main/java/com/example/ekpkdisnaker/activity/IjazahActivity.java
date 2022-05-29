package com.example.ekpkdisnaker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.example.ekpkdisnaker.R;
import com.example.ekpkdisnaker.api.Api;
import com.example.ekpkdisnaker.api.RetrofitClient;
import com.example.ekpkdisnaker.helpers.ApiError;
import com.example.ekpkdisnaker.helpers.ErrorUtils;
import com.example.ekpkdisnaker.response.UserResponse;
import com.example.ekpkdisnaker.session.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class IjazahActivity extends AppCompatActivity {

    ImageView btn_back;
    TextView lihat, status;

    Session session;
    Api api;
    Call<UserResponse> getUser;

    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ijazah);

        btn_back = findViewById(R.id.btn_back);
        lihat = findViewById(R.id.lihat);
        status = findViewById(R.id.status);

        session = new Session(this);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());
        getUser();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        lihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setDataAndType(Uri.parse(session.getBaseUrl()+url.replace("public/", "storage/")), "application/pdf");
                startActivity(intent);
            }
        });
    }

    public void getUser() {
        getUser = api.getUser();
        getUser.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getUser().getFileIjazah() != null) {
                        status.setText("Upload");
                        status.setTextColor(Color.parseColor("#008E3E"));
                        url = response.body().getUser().getFileIjazah();
                    } else {
                        status.setText("Belum");
                        status.setTextColor(Color.parseColor("#CE3032"));
                    }

                } else {
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(IjazahActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(IjazahActivity.this, "Error, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}