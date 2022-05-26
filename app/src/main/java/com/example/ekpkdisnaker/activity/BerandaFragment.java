package com.example.ekpkdisnaker.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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


public class BerandaFragment extends Fragment {

    LinearLayout btn_setting;
    LinearLayout btn_ak1;
    TextView nama_pengguna, foto_ktp, pas_foto, ijazah;

    Session session;
    Api api;
    Call<UserResponse> getUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);

        btn_ak1 = view.findViewById(R.id.btn_ak1);
        btn_setting = view.findViewById(R.id.btn_setting);
        nama_pengguna = view.findViewById(R.id.nama_pengguna);
        foto_ktp = view.findViewById(R.id.foto_ktp);
        pas_foto = view.findViewById(R.id.pas_foto);
        ijazah = view.findViewById(R.id.ijazah);

        session = new Session(getContext());
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());
        nama_pengguna.setText(session.getNama());

        btn_ak1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(), KartuAK1Activity.class);
                startActivity(it);
            }
        });

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(), SettingActivity.class);
                startActivity(it);
            }
        });

        getUser();

        return view;
    }

    public void getUser() {
        getUser = api.getUser();
        getUser.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getUser().getFotoKtp() != null) {
                        foto_ktp.setText("Upload");
                        foto_ktp.setTextColor(Color.parseColor("#008E3E"));
                    } else {
                        foto_ktp.setText("Belum");
                        foto_ktp.setTextColor(Color.parseColor("#CE3032"));
                    }

                    if (response.body().getUser().getPasFoto() != null) {
                        pas_foto.setText("Upload");
                        pas_foto.setTextColor(Color.parseColor("#008E3E"));
                    } else {
                        pas_foto.setText("Belum");
                        pas_foto.setTextColor(Color.parseColor("#CE3032"));
                    }

                    if (response.body().getUser().getFileIjazah() != null) {
                        ijazah.setText("Upload");
                        ijazah.setTextColor(Color.parseColor("#008E3E"));
                    } else {
                        ijazah.setText("Belum");
                        ijazah.setTextColor(Color.parseColor("#CE3032"));
                    }
                } else {
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(getContext(), apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}