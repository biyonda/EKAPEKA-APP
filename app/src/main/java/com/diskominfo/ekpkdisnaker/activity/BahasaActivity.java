package com.diskominfo.ekpkdisnaker.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.diskominfo.ekpkdisnaker.R;
import com.diskominfo.ekpkdisnaker.api.Api;
import com.diskominfo.ekpkdisnaker.api.RetrofitClient;
import com.diskominfo.ekpkdisnaker.helpers.ApiError;
import com.diskominfo.ekpkdisnaker.helpers.ErrorUtils;
import com.diskominfo.ekpkdisnaker.response.BaseResponse;
import com.diskominfo.ekpkdisnaker.response.UserResponse;
import com.diskominfo.ekpkdisnaker.session.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BahasaActivity extends AppCompatActivity {

    ImageView btn_back;
    LinearLayout btn_simpan;
    CheckBox cb_inggris,cb_mandarin,cb_jepang,cb_korea,cb_jerman,cb_rusia,cb_italia,cb_hindi,cb_lainnya;
    StringBuilder selectedBahasa;
    TextView list_bahasa;

    Session session;
    Api api;
    Call<UserResponse> getUser;
    Call<BaseResponse> ubahBahasa;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bahasa);

        session = new Session(this);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        selectedBahasa = new StringBuilder();

        btn_back = findViewById(R.id.btn_back);
        cb_inggris = findViewById(R.id.cb_inggris);
        cb_mandarin = findViewById(R.id.cb_mandarin);
        cb_jepang = findViewById(R.id.cb_jepang);
        cb_korea = findViewById(R.id.cb_korea);
        cb_jerman = findViewById(R.id.cb_jerman);
        cb_rusia = findViewById(R.id.cb_rusia);
        cb_italia = findViewById(R.id.cb_italia);
        cb_hindi = findViewById(R.id.cb_hindi);
        cb_lainnya = findViewById(R.id.cb_lainnya);
        list_bahasa = findViewById(R.id.list_bahasa);
        btn_simpan = findViewById(R.id.btn_simpan);

        getUser();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectedBahasa.setLength(0);

                if (cb_inggris.isChecked()) {
                    selectedBahasa.append("Bahasa Inggris,");
                }
                if (cb_mandarin.isChecked()) {
                    selectedBahasa.append("Bahasa Mandarin,");
                }
                if (cb_jepang.isChecked()) {
                    selectedBahasa.append("Bahasa Jepang,");
                }
                if (cb_korea.isChecked()) {
                    selectedBahasa.append("Bahasa Korea,");
                }
                if (cb_jerman.isChecked()) {
                    selectedBahasa.append("Bahasa Jerman,");
                }
                if (cb_rusia.isChecked()) {
                    selectedBahasa.append("Bahasa Rusia,");
                }
                if (cb_italia.isChecked()) {
                    selectedBahasa.append("Bahasa Italia,");
                }
                if (cb_hindi.isChecked()) {
                    selectedBahasa.append("Bahasa Hindi,");
                }
                if (cb_lainnya.isChecked()) {
                    selectedBahasa.append("Lainnya,");
                }

                String selectedBahasaString = selectedBahasa.toString().trim();
                String resultString = "";
                if (!selectedBahasaString.isEmpty()) {
                    selectedBahasaString = selectedBahasa.substring(0, selectedBahasa.length() - 1);
                    resultString = selectedBahasaString;
                } else  {
                    resultString = "";
                }

                ubahBahasa = api.ubahBahasa(resultString);
                ubahBahasa.enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                        if (response.isSuccessful()) {
                            Toast.makeText(BahasaActivity.this, "Data bahasa berhasil diperbaharui", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            ApiError apiError = ErrorUtils.parseError(response);
                            Toast.makeText(BahasaActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        Toast.makeText(BahasaActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    public void getUser() {
        getUser = api.getUser();
        getUser.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    list_bahasa.setText(response.body().getUser().getBahasaDikuasai());
                    if (response.body().getUser().getBahasaDikuasai().contains("Bahasa Inggris")) {
                        cb_inggris.setChecked(true);
                    }
                    if (response.body().getUser().getBahasaDikuasai().contains("Bahasa Mandarin")) {
                        cb_mandarin.setChecked(true);
                    }
                    if (response.body().getUser().getBahasaDikuasai().contains("Bahasa Jepang")) {
                        cb_jepang.setChecked(true);
                    }
                    if (response.body().getUser().getBahasaDikuasai().contains("Bahasa Korea")) {
                        cb_korea.setChecked(true);
                    }
                    if (response.body().getUser().getBahasaDikuasai().contains("Bahasa Jerman")) {
                        cb_jerman.setChecked(true);
                    }
                    if (response.body().getUser().getBahasaDikuasai().contains("Bahasa Italia")) {
                        cb_italia.setChecked(true);
                    }
                    if (response.body().getUser().getBahasaDikuasai().contains("Bahasa Rusia")) {
                        cb_rusia.setChecked(true);
                    }
                    if (response.body().getUser().getBahasaDikuasai().contains("Bahasa Hindi")) {
                        cb_hindi.setChecked(true);
                    }
                    if (response.body().getUser().getBahasaDikuasai().contains("Lainnya")) {
                        cb_lainnya.setChecked(true);
                    }

                } else {
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(BahasaActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(BahasaActivity.this, "Error, " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}