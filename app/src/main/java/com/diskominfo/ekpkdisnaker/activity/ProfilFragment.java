package com.diskominfo.ekpkdisnaker.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.diskominfo.ekpkdisnaker.R;
import com.diskominfo.ekpkdisnaker.api.Api;
import com.diskominfo.ekpkdisnaker.api.RetrofitClient;
import com.diskominfo.ekpkdisnaker.helpers.ApiError;
import com.diskominfo.ekpkdisnaker.helpers.ErrorUtils;
import com.diskominfo.ekpkdisnaker.response.BaseResponse;
import com.diskominfo.ekpkdisnaker.response.UserResponse;
import com.diskominfo.ekpkdisnaker.session.Session;
import com.diskominfo.ekpkdisnaker.table.PopupProfil;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilFragment extends Fragment {

    RelativeLayout btn_setting, btn_logout, btn_barcode;
    SwipeRefreshLayout swipe_refresh_layout;

    ImageView img_profil;
    TextView nama_lengkap, username, tmp_lahir, tgl_lahir, jenis_kelamin;
    TextView sts_kawin, agama, kd_pendidikan, nama_pendidikan;
    TextView no_telp, alamat;

    String nama_popup, nik_popup, foto_popup, kartu_popup, berlaku_popup, dibuat_popup, link_popup;

    Session session;
    Api api;
    Call<UserResponse> getUser;
    Call<BaseResponse> getStatusAK1;
    Call<BaseResponse<PopupProfil>> getKartuPeserta;

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
        btn_barcode = view.findViewById(R.id.btn_barcode);
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

        getKartuPeserta = api.getKartuPeserta(session.getUsername());
        getKartuPeserta.enqueue(new Callback<BaseResponse<PopupProfil>>() {
            @Override
            public void onResponse(Call<BaseResponse<PopupProfil>> call, Response<BaseResponse<PopupProfil>> response) {
                if (response.isSuccessful()) {
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        nama_popup = response.body().getData().get(i).getNama();
                        nik_popup = response.body().getData().get(i).getNik();
                        foto_popup = response.body().getData().get(i).getFoto();
                        kartu_popup = response.body().getData().get(i).getNoRegister();
                        berlaku_popup = response.body().getData().get(i).getBerlaku();
                        dibuat_popup = response.body().getData().get(i).getDibuat();
                        link_popup = response.body().getData().get(i).getLink();
                    }
                    if (nik_popup != null) {
                        btn_barcode.setVisibility(View.VISIBLE);
                    } else {
                        btn_barcode.setVisibility(View.GONE);
                    }
                } else {
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(getContext(), apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<PopupProfil>> call, Throwable t) {
                Toast.makeText(getContext(), "Error, "+t.getMessage(), Toast.LENGTH_SHORT).show();
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

        btn_barcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUpBarcode(nama_popup, nik_popup, foto_popup, kartu_popup, berlaku_popup, dibuat_popup, link_popup);
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
                    nama_lengkap.setText(response.body().getUser().getNamaLengkap());
                    username.setText(response.body().getUser().getUsername());
                    String tempat_lahir = response.body().getUser().getTmpLahir() == null ? "" : response.body().getUser().getTmpLahir()+", ";
                    String tanggal_lahir = response.body().getUser().getTglLahir() == null ? "" : response.body().getUser().getTglLahir();
                    tmp_lahir.setText(tempat_lahir);
                    tgl_lahir.setText(tanggal_lahir);
                    jenis_kelamin.setText(response.body().getUser().getJnsKelamin());
                    sts_kawin.setText(response.body().getUser().getStsNikah());
                    agama.setText(response.body().getUser().getAgama());

                    if (response.body().getUser().getKdPendidikan() == null) {
                        kd_pendidikan.setText("");
                    } else {
                        kd_pendidikan.setText(response.body().getUser().getKdPendidikan());
                    }

                    nama_pendidikan.setText(response.body().getUser().getNamaPendidikan());
                    no_telp.setText(response.body().getUser().getTelepon());
                    alamat.setText(response.body().getUser().getAlamat());

//                    if (response.body().getUser().getPasFoto() != null) {
//                        RequestOptions requestOptions = new RequestOptions();
//                        requestOptions.centerCrop().signature(
//                                new ObjectKey(String.valueOf(System.currentTimeMillis())));
//                        Glide.with(getActivity())
//                                .setDefaultRequestOptions(requestOptions)
//                                .load(session.getBaseUrl()
//                                        + response.body().getUser().getPasFoto().replace("public/", "storage/"))
//                                .into(img_profil);
//                    }
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

    public void popUpBarcode(String nama_popup, String nik_popup, String foto_popup, String kartu_popup, String berlaku_popup, String dibuat_popup, String link_popup) {
        final Dialog dialog = new Dialog(getContext());
        dialog.setTitle("Barcode");
        View v = getLayoutInflater().inflate(R.layout.popup_profil, null);
        dialog.setContentView(v);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);

        ImageView close = v.findViewById(R.id.close);
        ImageView barcode = v.findViewById(R.id.barcode);
        ImageView pas_foto = v.findViewById(R.id.pas_foto);
        TextView nama_peserta = v.findViewById(R.id.nama_peserta);
        TextView nik_peserta = v.findViewById(R.id.nik_peserta);
        TextView no_kartu = v.findViewById(R.id.no_kartu);
        TextView masa_berlaku = v.findViewById(R.id.masa_berlaku);
        TextView tgl_dibuat = v.findViewById(R.id.tgl_dibuat);

        nama_peserta.setText(nama_popup);
        nik_peserta.setText(nik_popup);
        no_kartu.setText("ID Kartu : " + kartu_popup);
        masa_berlaku.setText(berlaku_popup);
        tgl_dibuat.setText(dibuat_popup);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop().signature(new ObjectKey(String.valueOf(System.currentTimeMillis())));
        Glide.with(getActivity()).setDefaultRequestOptions(requestOptions).load(foto_popup).into(pas_foto);

        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        try {
//          BitMatrix bitMatrix = multiFormatWriter.encode(kartu_popup, BarcodeFormat.CODE_128, 500, 60, null);
            BitMatrix bitMatrix = multiFormatWriter.encode(link_popup, BarcodeFormat.QR_CODE, 1000, 1000, null);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
            barcode.setImageBitmap(bitmap);
        } catch (Exception e) {
            e.printStackTrace();;
        }

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
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
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}