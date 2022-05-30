package com.example.ekpkdisnaker.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.ekpkdisnaker.R;
import com.example.ekpkdisnaker.api.Api;
import com.example.ekpkdisnaker.api.RetrofitClient;
import com.example.ekpkdisnaker.response.BaseResponse;
import com.example.ekpkdisnaker.session.Session;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    Session session;
    Api api;
    Call<BaseResponse> updateToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        session = new Session(this);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (task.isSuccessful()) {
                            String firebase_token = task.getResult().getToken();
                            updateToken = api.updateToken(firebase_token);
                            updateToken.enqueue(new Callback<BaseResponse>() {
                                @Override
                                public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                    if (response.isSuccessful()) {
                                        Log.d("TAG", "onResponse: Berhasil");
                                    } else {
                                        Log.d("TAG", "onResponse: Gagal");
                                    }
                                }

                                @Override
                                public void onFailure(Call<BaseResponse> call, Throwable t) {
                                    Log.d("TAG", "onResponse: Gagal");
                                }
                            });
                        }
                    }
                });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (session.getUserLoggedIn()) {
                    Intent home = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(home);
                    finish();
                } else {
                    Intent home = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(home);
                    finish();
                }
            }
        }, 2000);
    }
}