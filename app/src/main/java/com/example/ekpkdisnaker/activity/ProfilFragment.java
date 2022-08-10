package com.example.ekpkdisnaker.activity;

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
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import com.example.ekpkdisnaker.response.BaseResponse;
import com.example.ekpkdisnaker.response.UserResponse;
import com.example.ekpkdisnaker.session.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilFragment extends Fragment {

    RelativeLayout btn_setting;
    RelativeLayout btn_logout;
    SwipeRefreshLayout swipe_refresh_layout;

    ImageView img_profil;
    TextView nama_lengkap, username, tmp_lahir, tgl_lahir, jenis_kelamin;
    TextView sts_kawin, agama, kd_pendidikan, nama_pendidikan;
    TextView no_telp, alamat;

    Session session;
    Api api;
    Call<UserResponse> getUser;
    Call<BaseResponse> getStatusAK1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        nama_lengkap = view.findViewById(R.id.nama_lengkap);
        username = view.findViewById(R.id.username);
        tmp_lahir = view.findViewById(R.id.tmp_lahir);
        tgl_lahir = view.findViewById(R.id.tgl_lahir);
        jenis_kelamin = view.findViewById(R.id.jenis_kelamin);
        sts_kawin = view.findViewById(R.id.sts_kawin);
        agama = view.findViewById(R.id.agama);
        kd_pendidikan = view.findViewById(R.id.kd_pendidikan);
        nama_pendidikan = view.findViewById(R.id.nama_pendidikan);
        no_telp = view.findViewById(R.id.no_telp);
        alamat = view.findViewById(R.id.alamat);
        btn_setting = view.findViewById(R.id.btn_setting);
        btn_logout = view.findViewById(R.id.btn_logout);
        img_profil = view.findViewById(R.id.img_profil);
        swipe_refresh_layout = view.findViewById(R.id.swipe_refresh_layout);

        session = new Session(getContext());
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_refresh_layout.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(), SettingActivity.class);
                startActivity(it);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                session.setUserStatus(false, "","", "", "");
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });

        getUser();
        getStatusAK1(session.getUsername());

        return view;
    }

    public void getUser() {
        getUser = api.getUser();
        getUser.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    nama_lengkap.setText(response.body().getUser().getNamaLengkap());
                    username.setText(response.body().getUser().getUsername());
                    String tempat_lahir = response.body().getUser().getTmpLahir() == null ? "" : response.body().getUser().getTmpLahir()+", ";
                    String tanggal_lahir = response.body().getUser().getTglLahir() == null ? "" : response.body().getUser().getTglLahir();
                    tmp_lahir.setText(tempat_lahir);
                    tgl_lahir.setText(tanggal_lahir);
                    jenis_kelamin.setText(response.body().getUser().getJnsKelamin());
                    sts_kawin.setText(response.body().getUser().getStsNikah());


//                    if (response.body().getUser().getJnsKelamin() == 1) {
//                        jenis_kelamin.setText("Laki-laki");
//                    } else if (response.body().getUser().getJnsKelamin() == 2) {
//                        jenis_kelamin.setText("Perempuan");
//                    }

//                    if (response.body().getUser().getStsNikah().equals("0")) {
//                        sts_kawin.setText("BELUM KAWIN");
//                    } else if (response.body().getUser().getStsNikah().equals("1")) {
//                        sts_kawin.setText("KAWIN");
//                    }

                    agama.setText(response.body().getUser().getAgama());

                    if (response.body().getUser().getKdPendidikan() == null) {
                        kd_pendidikan.setText("");
                    } else {
                        kd_pendidikan.setText(response.body().getUser().getKdPendidikan());
                    }

                    nama_pendidikan.setText(response.body().getUser().getNamaPendidikan());
                    no_telp.setText(response.body().getUser().getTelepon());
                    alamat.setText(response.body().getUser().getAlamat());

                    if (response.body().getUser().getPasFoto() != null) {
                        RequestOptions requestOptions = new RequestOptions();
                        requestOptions.centerCrop().signature(
                                new ObjectKey(String.valueOf(System.currentTimeMillis())));
                        Glide.with(getActivity())
                                .setDefaultRequestOptions(requestOptions)
                                .load(session.getBaseUrl()
                                        + response.body().getUser().getPasFoto().replace("public/", "storage/"))
                                .into(img_profil);
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
}