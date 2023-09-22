package com.diskominfo.ekpkdisnaker.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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
import com.diskominfo.ekpkdisnaker.table.KelengkapanData;
import com.diskominfo.ekpkdisnaker.table.SettingUpdate;
import com.diskominfo.ekpkdisnaker.table.ak1;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahAK1Activity extends AppCompatActivity {

    RelativeLayout layout_add_1, layout_add_2, layout_add_3,layout_add_4, layout_add_5, layout_add_6, layout_add_7, layout_add_8;
    ImageView btn_back;
    LinearLayout btn_kartu_ak1;
    LinearLayout btn_refresh;
    TextView title_section_1, subtitle_section_1, count_section_1, prosentase_section_1,
            title_section_2, subtitle_section_2, count_section_2, prosentase_section_2,
            title_section_3, subtitle_section_3, count_section_3, prosentase_section_3,
            title_section_4, subtitle_section_4, count_section_4, prosentase_section_4,
            title_section_5, subtitle_section_5, count_section_5, prosentase_section_5,
            title_section_6, subtitle_section_6, count_section_6, prosentase_section_6,
            title_section_7, subtitle_section_7, count_section_7, prosentase_section_7,
            title_section_8, subtitle_section_8, count_section_8, prosentase_section_8;
    ProgressBar progress_section_1, progress_section_2, progress_section_3, progress_section_4, progress_section_5, progress_section_6, progress_section_7, progress_section_8;
    SwipeRefreshLayout swipe_refresh_layout;

    Session session;
    Api api;
    Call<BaseResponse<KelengkapanData>> kelengkapanData;
    Call<BaseResponse> getKartuAK1;
    Call<BaseResponse<ak1>> getDataAK1;

    double nilai_maksimum = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_ak1);

        btn_back = findViewById(R.id.btn_back);
        btn_kartu_ak1 = findViewById(R.id.btn_kartu_ak1);
        btn_refresh = findViewById(R.id.btn_refresh);
        swipe_refresh_layout = findViewById(R.id.swipe_refresh_layout);

        layout_add_1 = findViewById(R.id.layout_add_1);
        title_section_1 = findViewById(R.id.title_section_1);
        subtitle_section_1 = findViewById(R.id.subtitle_section_1);
        count_section_1 = findViewById(R.id.count_section_1);
        prosentase_section_1 = findViewById(R.id.prosentase_section_1);
        progress_section_1 = findViewById(R.id.progress_section_1);

        layout_add_2 = findViewById(R.id.layout_add_2);
        title_section_2 = findViewById(R.id.title_section_2);
        subtitle_section_2 = findViewById(R.id.subtitle_section_2);
        count_section_2 = findViewById(R.id.count_section_2);
        prosentase_section_2 = findViewById(R.id.prosentase_section_2);
        progress_section_2 = findViewById(R.id.progress_section_2);

        layout_add_3 = findViewById(R.id.layout_add_3);
        title_section_3 = findViewById(R.id.title_section_3);
        subtitle_section_3 = findViewById(R.id.subtitle_section_3);
        count_section_3 = findViewById(R.id.count_section_3);
        prosentase_section_3 = findViewById(R.id.prosentase_section_3);
        progress_section_3 = findViewById(R.id.progress_section_3);

        layout_add_4 = findViewById(R.id.layout_add_4);
        title_section_4 = findViewById(R.id.title_section_4);
        subtitle_section_4 = findViewById(R.id.subtitle_section_4);
        count_section_4 = findViewById(R.id.count_section_4);
        prosentase_section_4 = findViewById(R.id.prosentase_section_4);
        progress_section_4 = findViewById(R.id.progress_section_4);

        layout_add_5 = findViewById(R.id.layout_add_5);
        title_section_5 = findViewById(R.id.title_section_5);
        count_section_5 = findViewById(R.id.count_section_5);
        prosentase_section_5 = findViewById(R.id.prosentase_section_5);
        progress_section_5 = findViewById(R.id.progress_section_5);

        layout_add_6 = findViewById(R.id.layout_add_6);
        title_section_6 = findViewById(R.id.title_section_6);
        count_section_6 = findViewById(R.id.count_section_6);
        prosentase_section_6 = findViewById(R.id.prosentase_section_6);
        progress_section_6 = findViewById(R.id.progress_section_6);

        layout_add_7 = findViewById(R.id.layout_add_7);
        title_section_7 = findViewById(R.id.title_section_7);
        count_section_7 = findViewById(R.id.count_section_7);
        prosentase_section_7 = findViewById(R.id.prosentase_section_7);
        progress_section_7 = findViewById(R.id.progress_section_7);

        layout_add_8 = findViewById(R.id.layout_add_8);
        title_section_8 = findViewById(R.id.title_section_8);
        subtitle_section_8 = findViewById(R.id.subtitle_section_8);
        count_section_8 = findViewById(R.id.count_section_8);
        prosentase_section_8 = findViewById(R.id.prosentase_section_8);
        progress_section_8 = findViewById(R.id.progress_section_8);

        session = new Session(this);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getKelengkapanData();
                        swipe_refresh_layout.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        layout_add_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TambahAK1Activity.this, UbahProfileActivity.class);
                startActivity(intent);
            }
        });

        layout_add_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TambahAK1Activity.this, KTPActivity.class);
                startActivity(intent);
            }
        });

        layout_add_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TambahAK1Activity.this, PasFotoActivity.class);
                startActivity(intent);
            }
        });

        layout_add_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TambahAK1Activity.this, IjazahActivity.class);
                startActivity(intent);
            }
        });

        layout_add_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TambahAK1Activity.this, KeterampilanActivity.class);
                startActivity(intent);
            }
        });

        layout_add_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TambahAK1Activity.this, BahasaActivity.class);
                startActivity(intent);
            }
        });

        layout_add_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TambahAK1Activity.this, PengalamanKerjaActivity.class);
                startActivity(intent);
            }
        });

        layout_add_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TambahAK1Activity.this, PeminatanActivity.class);
                startActivity(intent);
            }
        });

        getKelengkapanData();

        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getKelengkapanData();
                    }
                }, 1000);
                Toast.makeText(TambahAK1Activity.this, "Refreshed", Toast.LENGTH_LONG).show();
            }
        });

        btn_kartu_ak1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                kelengkapanData = api.kelengkapanData();
                kelengkapanData.enqueue(new Callback<BaseResponse<KelengkapanData>>() {
                    @Override
                    public void onResponse(Call<BaseResponse<KelengkapanData>> call, Response<BaseResponse<KelengkapanData>> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getData().get(0).getProsentaseTotal() < nilai_maksimum) {
                                Toast.makeText(TambahAK1Activity.this, "Data Anda Tidak Lengkap", Toast.LENGTH_LONG).show();
                            } else {
                                new SweetAlertDialog(TambahAK1Activity.this, SweetAlertDialog.WARNING_TYPE)
                                        .setTitleText("Perhatian")
                                        .setContentText("Sebelum melanjutkan, mohon koreksi apakah telah lengkap data diri anda beserta data pendukung lainnya? Silahkan melanjutkan apabila telah lengkap")
                                        .setConfirmText("Lanjutkan").setCancelButtonBackgroundColor(R.color.tetriary)
                                        .setCancelButtonTextColor(R.color.main_blue_color).setCancelText("Batal")
                                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sDialog) {
                                                getKartuAK1 = api.getKartuAK1();
                                                getKartuAK1.enqueue(new Callback<BaseResponse>() {
                                                    @Override
                                                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                                                        if (response.isSuccessful()) {
                                                            sDialog
                                                                    .setTitleText("Sukses")
                                                                    .setContentText(response.body().getMessage())
                                                                    .setConfirmText("OK")
                                                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                                        @Override
                                                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                            sweetAlertDialog.dismiss();
                                                                            Intent intent = new Intent(TambahAK1Activity.this, MainActivity.class);
                                                                            startActivity(intent);
                                                                        }
                                                                    })
                                                                    .changeAlertType(SweetAlertDialog.SUCCESS_TYPE);
                                                        } else {
                                                            ApiError apiError = ErrorUtils.parseError(response);
                                                            sDialog
                                                                    .setTitleText("Gagal !!!")
                                                                    .setContentText(apiError.getMessage() + "")
                                                                    .setConfirmText("OK")
                                                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                                        @Override
                                                                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                                            sweetAlertDialog.dismiss();
                                                                        }
                                                                    })
                                                                    .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                                                        sDialog
                                                                .setTitleText("Gagal !!")
                                                                .setContentText(t.getMessage())
                                                                .setConfirmText("OK")
                                                                .setConfirmClickListener(null)
                                                                .changeAlertType(SweetAlertDialog.ERROR_TYPE);
                                                    }
                                                });

                                            }
                                        })
                                        .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                            @Override
                                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                sweetAlertDialog.dismissWithAnimation();
                                            }
                                        })
                                        .show();
                            }
                        } else {
                            ApiError apiError = ErrorUtils.parseError(response);
                            Toast.makeText(TambahAK1Activity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse<KelengkapanData>> call, Throwable t) {
                        Toast.makeText(TambahAK1Activity.this, "Error, "+t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    public void getKelengkapanData()
    {
        kelengkapanData = api.kelengkapanData();
        kelengkapanData.enqueue(new Callback<BaseResponse<KelengkapanData>>() {
            @Override
            public void onResponse(Call<BaseResponse<KelengkapanData>> call, Response<BaseResponse<KelengkapanData>> response) {
                if (response.isSuccessful()) {
                    count_section_1.setText(response.body().getData().get(0).getSection1().toString());
                    prosentase_section_1.setText(response.body().getData().get(0).getProsentaseSection1().toString() + "%");
                    progress_section_1.setProgress(response.body().getData().get(0).getProsentaseSection1());
                    count_section_2.setText(response.body().getData().get(0).getSection2().toString());
                    prosentase_section_2.setText(response.body().getData().get(0).getProsentaseSection2().toString() + "%");
                    progress_section_2.setProgress(response.body().getData().get(0).getProsentaseSection2());
                    count_section_3.setText(response.body().getData().get(0).getSection3().toString());
                    prosentase_section_3.setText(response.body().getData().get(0).getProsentaseSection3().toString() + "%");
                    progress_section_3.setProgress(response.body().getData().get(0).getProsentaseSection3());
                    count_section_4.setText(response.body().getData().get(0).getSection4().toString());
                    prosentase_section_4.setText(response.body().getData().get(0).getProsentaseSection4().toString() + "%");
                    progress_section_4.setProgress(response.body().getData().get(0).getProsentaseSection4());
                    count_section_8.setText(response.body().getData().get(0).getSection8().toString());
                    prosentase_section_8.setText(response.body().getData().get(0).getProsentaseSection8().toString() + "%");
                    progress_section_8.setProgress(response.body().getData().get(0).getProsentaseSection8());
                } else {
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(TambahAK1Activity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<KelengkapanData>> call, Throwable t) {
                Toast.makeText(TambahAK1Activity.this, "Error, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}