package com.example.ekpkdisnaker.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
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
import com.example.ekpkdisnaker.response.RegisterResponse;
import com.example.ekpkdisnaker.response.UserResponse;
import com.example.ekpkdisnaker.session.Session;
import com.example.ekpkdisnaker.table.Desa;
import com.example.ekpkdisnaker.table.Kecamatan;

import java.util.ArrayList;
import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UbahProfileActivity extends AppCompatActivity {

    Spinner jenis_kelamin, sts_kawin, kd_pendidikan, agama, kecamatan, desa;
    LinearLayout select_tgl_lahir;
    ImageView btn_back;
    AppCompatButton btn_simpan;
    ProgressBar progress_register;
    TextView tgl_lahir;
    EditText nama_lengkap, tmp_lahir, email, alamat, no_telp, nama_pendidikan, password, jurusan;

    final Calendar calendar = Calendar.getInstance();
    int yy = calendar.get(Calendar.YEAR);
    int mm = calendar.get(Calendar.MONTH);
    int dd = calendar.get(Calendar.DAY_OF_MONTH);

    Session session;
    Api api;
    Call<UserResponse> getUser;
    Call<BaseResponse> ubahProfile;
    Call<BaseResponse<Kecamatan>> getKecamatan;
    Call<BaseResponse<Desa>> getDesa;
    String tmp_kd_pendidikan = "";

    ArrayList<String> list_kecamatan = new ArrayList<>();
    ArrayList<String> list_id_kecamatan = new ArrayList<>();

    ArrayList<String> list_desa = new ArrayList<>();
    ArrayList<String> list_id_desa = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_profile);

        btn_back = findViewById(R.id.btn_back);
        jenis_kelamin = findViewById(R.id.jenis_kelamin);
        sts_kawin = findViewById(R.id.kawin);
        kd_pendidikan = findViewById(R.id.kd_pendidikan);
        jurusan = findViewById(R.id.jurusan);
        kecamatan = findViewById(R.id.kecamatan);
        desa = findViewById(R.id.desa);
        agama = findViewById(R.id.agama);
        select_tgl_lahir = findViewById(R.id.select_tgl_lahir);
        tgl_lahir = findViewById(R.id.tgl_lahir);
        btn_simpan = findViewById(R.id.btn_simpan);
        progress_register = findViewById(R.id.progress_register);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        tmp_lahir = findViewById(R.id.tmp_lahir);
        email = findViewById(R.id.email);
        alamat = findViewById(R.id.alamat);
        no_telp = findViewById(R.id.no_telp);
        nama_pendidikan = findViewById(R.id.nama_pendidikan);
        password = findViewById(R.id.password);

        session = new Session(this);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        select_tgl_lahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(UbahProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String tglAwal = String.valueOf(year) + "-" + String.valueOf(month + 1) + "-" + String.valueOf(dayOfMonth);
                        tgl_lahir.setText(tglAwal);
                    }
                }, yy, mm, dd);
                datePickerDialog.show();
            }
        });

        ArrayAdapter<CharSequence> bulan_spinner = ArrayAdapter.createFromResource(this, R.array.jk_array, R.layout.spinner_kecamatan);
        ArrayAdapter<CharSequence> kawin_spinner = ArrayAdapter.createFromResource(this, R.array.kawin_array, R.layout.spinner_kecamatan);
        ArrayAdapter<CharSequence> pendidikan_spinner = ArrayAdapter.createFromResource(this, R.array.pendidikan_array, R.layout.spinner_kecamatan);
        ArrayAdapter<CharSequence> agama_spinner = ArrayAdapter.createFromResource(this, R.array.agama_array, R.layout.spinner_kecamatan);
        bulan_spinner.setDropDownViewResource(R.layout.spinner_kecamatan);
        kawin_spinner.setDropDownViewResource(R.layout.spinner_kecamatan);
        pendidikan_spinner.setDropDownViewResource(R.layout.spinner_kecamatan);
        agama_spinner.setDropDownViewResource(R.layout.spinner_kecamatan);
        jenis_kelamin.setAdapter(bulan_spinner);
        sts_kawin.setAdapter(kawin_spinner);
        kd_pendidikan.setAdapter(pendidikan_spinner);
        agama.setAdapter(agama_spinner);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new SweetAlertDialog(UbahProfileActivity.this, SweetAlertDialog.WARNING_TYPE)
                        .setTitleText("Perhatian")
                        .setContentText("Apakah Anda yakin akan merubah data Anda ?")
                        .setConfirmText("Lanjutkan").setCancelButtonBackgroundColor(R.color.tetriary)
                        .setCancelButtonTextColor(R.color.main_blue_color).setCancelText("Batal")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                if (kd_pendidikan.getSelectedItemPosition() == 0){
                                    tmp_kd_pendidikan = "1A";
                                } else if (kd_pendidikan.getSelectedItemPosition() == 1){
                                    tmp_kd_pendidikan = "2B";
                                } else if (kd_pendidikan.getSelectedItemPosition() == 2){
                                    tmp_kd_pendidikan = "3C";
                                } else if (kd_pendidikan.getSelectedItemPosition() == 3){
                                    tmp_kd_pendidikan = "4D";
                                } else if (kd_pendidikan.getSelectedItemPosition() == 4){
                                    tmp_kd_pendidikan = "5E";
                                } else if (kd_pendidikan.getSelectedItemPosition() == 5){
                                    tmp_kd_pendidikan = "6F";
                                } else if (kd_pendidikan.getSelectedItemPosition() == 6){
                                    tmp_kd_pendidikan = "7G";
                                }

                                ubahProfile = api.ubahProfile(nama_lengkap.getText().toString(),
                                        tmp_lahir.getText().toString(), (jenis_kelamin.getSelectedItemPosition()+1)+"", tgl_lahir.getText().toString(),
                                        sts_kawin.getSelectedItem()+"", tmp_kd_pendidikan+"", nama_pendidikan.getText().toString(),
                                        jurusan.getText().toString(), alamat.getText().toString(), list_id_kecamatan.get(kecamatan.getSelectedItemPosition()),
                                        list_id_desa.get(desa.getSelectedItemPosition()), no_telp.getText().toString(),
                                        email.getText().toString(), password.getText().toString(), agama.getSelectedItem().toString());

                                ubahProfile.enqueue(new Callback<BaseResponse>() {
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
                                                            getUser();
                                                            sweetAlertDialog.dismiss();
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
                                                            getUser();
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
        });

        getUser();
    }

    public void getUser() {
        getUser = api.getUser();
        getUser.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    nama_lengkap.setText(response.body().getUser().getNamaLengkap());
                    tmp_lahir.setText(response.body().getUser().getTmpLahir() + ", ");
                    tgl_lahir.setText(response.body().getUser().getTglLahir());

                    if (response.body().getUser().getJnsKelamin() == 1) {
                        jenis_kelamin.setSelection(0);
                    } else if (response.body().getUser().getJnsKelamin() == 2) {
                        jenis_kelamin.setSelection(1);
                    }

                    if (response.body().getUser().getStsNikah().equals("BELUM KAWIN")) {
                        sts_kawin.setSelection(0);
                    } else if (response.body().getUser().getStsNikah().equals("KAWIN")) {
                        sts_kawin.setSelection(1);
                    } else if (response.body().getUser().getStsNikah().equals("CERAI")) {
                        sts_kawin.setSelection(2);
                    }

                    if (response.body().getUser().getAgama().equals("ISLAM")) {
                        agama.setSelection(0);
                    } else if (response.body().getUser().getAgama().equals("KRISTEN")) {
                        agama.setSelection(1);
                    } else if (response.body().getUser().getAgama().equals("KATOLIK")) {
                        agama.setSelection(2);
                    } else if (response.body().getUser().getAgama().equals("HINDU")) {
                        agama.setSelection(3);
                    } else if (response.body().getUser().getAgama().equals("BUDHA")) {
                        agama.setSelection(4);
                    } else if (response.body().getUser().getAgama().equals("KONGHUCU")) {
                        agama.setSelection(5);
                    }

                    if (response.body().getUser().getKdPendidikan().equals("1A")) {
                        kd_pendidikan.setSelection(0);
                    } else if (response.body().getUser().getKdPendidikan().equals("2B")) {
                        kd_pendidikan.setSelection(1);
                    } else if (response.body().getUser().getKdPendidikan().equals("3C")) {
                        kd_pendidikan.setSelection(2);
                    } else if (response.body().getUser().getKdPendidikan().equals("4D")) {
                        kd_pendidikan.setSelection(3);
                    } else if (response.body().getUser().getKdPendidikan().equals("5E")) {
                        kd_pendidikan.setSelection(4);
                    } else if (response.body().getUser().getKdPendidikan().equals("6F")) {
                        kd_pendidikan.setSelection(5);
                    } else if (response.body().getUser().getKdPendidikan().equals("7G")) {
                        kd_pendidikan.setSelection(6);
                    }

                    nama_pendidikan.setText(response.body().getUser().getNamaPendidikan());
                    no_telp.setText(response.body().getUser().getTelepon());
                    alamat.setText(response.body().getUser().getAlamat());
                    email.setText(response.body().getUser().getEmail());
                    nama_pendidikan.setText(response.body().getUser().getNamaPendidikan());
                } else {
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(UbahProfileActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(UbahProfileActivity.this, "Error, " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        getKecamatan();

        kecamatan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                getDesa(list_id_kecamatan.get(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void getKecamatan(){
        getKecamatan = api.getKecamatan();
        getKecamatan.enqueue(new Callback<BaseResponse<Kecamatan>>() {
            @Override
            public void onResponse(Call<BaseResponse<Kecamatan>> call, Response<BaseResponse<Kecamatan>> response) {
                if (response.isSuccessful()) {
                    list_kecamatan.clear();
                    list_id_kecamatan.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
//                        list_kota.add(response.body().getData().get(i).getType()+" "+response.body().getData().get(i).getCityName());
                        list_kecamatan.add(response.body().getData().get(i).getNama());
                        list_id_kecamatan.add(response.body().getData().get(i).getIdKec());
                    }

                    //Ini buat ngisi Spinner
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(UbahProfileActivity.this, R.layout.spinner_kecamatan, list_kecamatan);
                    arrayAdapter.setDropDownViewResource(R.layout.spinner_kecamatan);
                    kecamatan.setAdapter(arrayAdapter);
                } else {
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(UbahProfileActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Kecamatan>> call, Throwable t) {
                Toast.makeText(UbahProfileActivity.this, "Error, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getDesa(String id_kec) {
        getDesa = api.getDesa(id_kec);
        getDesa.enqueue(new Callback<BaseResponse<Desa>>() {
            @Override
            public void onResponse(Call<BaseResponse<Desa>> call, Response<BaseResponse<Desa>> response) {
                if (response.isSuccessful()) {
                    list_desa.clear();
                    list_id_desa.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
//                        list_kota.add(response.body().getData().get(i).getType()+" "+response.body().getData().get(i).getCityName());
                        list_desa.add(response.body().getData().get(i).getNama());
                        list_id_desa.add(response.body().getData().get(i).getIdKel());
                    }

                    //Ini buat ngisi Spinner
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(UbahProfileActivity.this, R.layout.spinner_kecamatan, list_desa);
                    arrayAdapter.setDropDownViewResource(R.layout.spinner_kecamatan);
                    desa.setAdapter(arrayAdapter);
                } else {
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(UbahProfileActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Desa>> call, Throwable t) {
                Toast.makeText(UbahProfileActivity.this, "Error, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}