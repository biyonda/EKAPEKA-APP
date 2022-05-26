package com.example.ekpkdisnaker.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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

public class LoginActivity extends AppCompatActivity {

    AppCompatButton btn_login;
    ImageView show_password;
    Boolean showPasswordClicked = false;
    TextView username, password;
    LinearLayout register;
    ProgressBar SHOW_PROGRESS;

    Session session;
    Api api;
    Call<UserResponse> login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        session = new Session(this);
        api = RetrofitClient.createService(Api.class);

        btn_login = findViewById(R.id.btn_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        show_password = findViewById(R.id.show_password);
        register = findViewById(R.id.register);
        SHOW_PROGRESS = findViewById(R.id.SHOW_PROGRESS);

        show_password.setBackgroundResource(R.drawable.ic_eye_open);
        show_password.setOnClickListener(mToggleShowPasswordButton);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(username.getText().toString());
                System.out.println(password.getText().toString());
                SHOW_PROGRESS.setVisibility(View.VISIBLE);
                login = api.login(username.getText().toString(), password.getText().toString());
                login.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (response.isSuccessful()) {
                            session.setUserStatus(true, response.body().getUser().getId().toString(), response.body().getUser().getUsername(),
                                    response.body().getUser().getNamaLengkap(), response.body().getUser().getApiToken());
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                            Toast.makeText(LoginActivity.this, "Selamat datang "+response.body().getUser().getNamaLengkap(), Toast.LENGTH_SHORT).show();
                            SHOW_PROGRESS.setVisibility(View.GONE);
                        } else {
                            ApiError apiError = ErrorUtils.parseError(response);
                            Toast.makeText(LoginActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                            SHOW_PROGRESS.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Error, "+t.getMessage(), Toast.LENGTH_SHORT).show();
                        SHOW_PROGRESS.setVisibility(View.GONE);
                    }
                });
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }

    View.OnClickListener mToggleShowPasswordButton = new View.OnClickListener(){

        @Override
        public void onClick(View v){
            // change your button background

            if(showPasswordClicked){
                v.setBackgroundResource(R.drawable.ic_eye_closed);
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else{
                v.setBackgroundResource(R.drawable.ic_eye_open);
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }

            showPasswordClicked = !showPasswordClicked; // reverse
        }

    };

}