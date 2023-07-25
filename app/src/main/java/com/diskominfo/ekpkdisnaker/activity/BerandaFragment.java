package com.diskominfo.ekpkdisnaker.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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
import com.diskominfo.ekpkdisnaker.table.SettingUpdate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BerandaFragment extends Fragment {

    LinearLayout btn_setting;
    LinearLayout btn_ak1;
    TextView nama_pengguna, foto_ktp, pas_foto, ijazah;
    SwipeRefreshLayout swipe_refresh_layout;

    Session session;
    Api api;
    Call<UserResponse> getUser;
    Call<BaseResponse> getStatusAK1;
    Call<BaseResponse<SettingUpdate>> getSettingUpdate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);

        btn_ak1 = view.findViewById(R.id.btn_ak1);
        nama_pengguna = view.findViewById(R.id.nama_pengguna);
        foto_ktp = view.findViewById(R.id.foto_ktp);
        pas_foto = view.findViewById(R.id.pas_foto);
        ijazah = view.findViewById(R.id.ijazah);
        swipe_refresh_layout = view.findViewById(R.id.swipe_refresh_layout);

        session = new Session(getContext());
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());
        nama_pengguna.setText(session.getNama());

        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getUser();
                        popUpSettingUpdate();
                        getStatusAK1(session.getUsername());
                        swipe_refresh_layout.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        btn_ak1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(), KartuAK1Activity.class);
                startActivity(it);
            }
        });

        getUser();
        popUpSettingUpdate();
        getStatusAK1(session.getUsername());

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

    public void getStatusAK1(String nik) {
        getStatusAK1 = api.getStatusAK1(nik);
        getStatusAK1.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getMessage().equals("1")) {
                        popUpUpdate();
                    }
                } else {
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(getContext(), apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void popUpUpdate() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setTitle("Gambar Barang");
        View v = getLayoutInflater().inflate(R.layout.popup_update, null);
        dialog.setContentView(v);
        Button update = v.findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.asa.asri_larisso"));
//                startActivity(intent);
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void popUpSettingUpdate() {
        getSettingUpdate = api.getSettingUpdate();
        getSettingUpdate.enqueue(new Callback<BaseResponse<SettingUpdate>>() {
            @Override
            public void onResponse(Call<BaseResponse<SettingUpdate>> call, Response<BaseResponse<SettingUpdate>> response) {
                if (response.isSuccessful()) {
                    if (response.body().getData().get(0).getValue().equals(1)) {
                        final Dialog dialog = new Dialog(getContext());
                        dialog.setTitle("Update");
                        View v = getLayoutInflater().inflate(R.layout.popup_update, null);
                        dialog.setContentView(v);
                        Button update = v.findViewById(R.id.update);
                        TextView keterangan_update = v.findViewById(R.id.keterangan_update);
                        keterangan_update.setText(response.body().getData().get(0).getKeterangan());
                        update.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                    }
                } else {
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(getContext(), apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<SettingUpdate>> call, Throwable t) {
                Toast.makeText(getContext(), "Error, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}