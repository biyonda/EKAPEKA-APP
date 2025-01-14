package com.diskominfo.ekpkdisnaker.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
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

import com.diskominfo.ekpkdisnaker.R;
import com.diskominfo.ekpkdisnaker.api.Api;
import com.diskominfo.ekpkdisnaker.api.RetrofitClient;
import com.diskominfo.ekpkdisnaker.helpers.ApiError;
import com.diskominfo.ekpkdisnaker.helpers.ErrorUtils;
import com.diskominfo.ekpkdisnaker.response.BaseResponse;
import com.diskominfo.ekpkdisnaker.response.UserResponse;
import com.diskominfo.ekpkdisnaker.session.Session;
import com.diskominfo.ekpkdisnaker.table.Desa;
import com.diskominfo.ekpkdisnaker.table.Kecamatan;

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
    ImageView show_password;
    Boolean showPasswordClicked = false;
    AppCompatButton btn_simpan;
    ProgressBar progress_register;
    TextView tgl_lahir;
    EditText nama_lengkap, tmp_lahir, email, alamat, no_telp, nama_pendidikan, password, jurusan, tahun_lulus, nilai_pendidikan;

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
        tahun_lulus = findViewById(R.id.tahun_lulus);
        nilai_pendidikan = findViewById(R.id.nilai_pendidikan);
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
        show_password = findViewById(R.id.show_password);

        session = new Session(this);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        show_password.setBackgroundResource(R.drawable.ic_eye_open);
        show_password.setOnClickListener(mToggleShowPasswordButton);

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
                                tmp_kd_pendidikan = kd_pendidikan.getSelectedItem().toString();

                                ubahProfile = api.ubahProfile(
                                        nama_lengkap.getText().toString(),
                                        tmp_lahir.getText().toString(),
                                        jenis_kelamin.getSelectedItem().toString(),
                                        tgl_lahir.getText().toString(),
                                        sts_kawin.getSelectedItem()+"",
                                        tmp_kd_pendidikan+"",
                                        nama_pendidikan.getText().toString(),
                                        jurusan.getText().toString(),
                                        alamat.getText().toString(),
                                        list_id_kecamatan.get(kecamatan.getSelectedItemPosition()),
                                        list_id_desa.get(desa.getSelectedItemPosition()),
                                        no_telp.getText().toString(),
                                        email.getText().toString(),
                                        password.getText().toString(),
                                        agama.getSelectedItem().toString(),
                                        tahun_lulus.getText().toString(),
                                        nilai_pendidikan.getText().toString());

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
                                            Intent intent = new Intent(UbahProfileActivity.this, MainActivity.class);
                                            startActivity(intent);
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

        getKecamatan();
        getUser();
    }

    public void getUser() {
        getUser = api.getUser();
        getUser.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    nama_lengkap.setText(response.body().getUser().getNamaLengkap());
                    String tempat_lahir = response.body().getUser().getTmpLahir() == null ? "" : response.body().getUser().getTmpLahir();
                    String tanggal_lahir = response.body().getUser().getTglLahir() == null ? "" : response.body().getUser().getTglLahir();
                    tmp_lahir.setText(tempat_lahir);
                    tgl_lahir.setText(tanggal_lahir);

                    if (response.body().getUser().getJnsKelamin().equals("LAKI-LAKI")) {
                        jenis_kelamin.setSelection(0);
                    } else if (response.body().getUser().getJnsKelamin().equals("PEREMPUAN")) {
                        jenis_kelamin.setSelection(1);
                    } else {
                        jenis_kelamin.setSelection(0);
                    }

                    if (response.body().getUser().getStsNikah().equals("BELUM KAWIN")) {
                        sts_kawin.setSelection(0);
                    } else if (response.body().getUser().getStsNikah().equals("KAWIN")) {
                        sts_kawin.setSelection(1);
                    } else if (response.body().getUser().getStsNikah().equals("CERAI")) {
                        sts_kawin.setSelection(2);
                    } else {
                        sts_kawin.setSelection(0);
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
                    } else {
                        agama.setSelection(0);
                    }

                    if (response.body().getUser().getKdPendidikan().equals("SD")) {
                        kd_pendidikan.setSelection(0);
                    } else if (response.body().getUser().getKdPendidikan().equals("SLTP")) {
                        kd_pendidikan.setSelection(1);
                    } else if (response.body().getUser().getKdPendidikan().equals("SLTA")) {
                        kd_pendidikan.setSelection(2);
                    } else if (response.body().getUser().getKdPendidikan().equals("MAN")) {
                        kd_pendidikan.setSelection(3);
                    } else if (response.body().getUser().getKdPendidikan().equals("SMK")) {
                        kd_pendidikan.setSelection(4);
                    } else if (response.body().getUser().getKdPendidikan().equals("DI")) {
                        kd_pendidikan.setSelection(5);
                    } else if (response.body().getUser().getKdPendidikan().equals("DII")) {
                        kd_pendidikan.setSelection(6);
                    } else if (response.body().getUser().getKdPendidikan().equals("DIII")) {
                        kd_pendidikan.setSelection(7);
                    } else if (response.body().getUser().getKdPendidikan().equals("DIV")) {
                        kd_pendidikan.setSelection(8);
                    } else if (response.body().getUser().getKdPendidikan().equals("S1")) {
                        kd_pendidikan.setSelection(9);
                    } else if (response.body().getUser().getKdPendidikan().equals("S2")) {
                        kd_pendidikan.setSelection(10);
                    } else if (response.body().getUser().getKdPendidikan().equals("S3")) {
                        kd_pendidikan.setSelection(11);
                    } else {
                        kd_pendidikan.setSelection(0);
                    }

                    String idKecamatan = response.body().getUser().getKecamatan();
                    String idDesa = response.body().getUser().getDeskel();

                    int kecamatanIndex = list_id_kecamatan.indexOf(idKecamatan);
                    if (kecamatanIndex >= 0) {
                        kecamatan.setSelection(kecamatanIndex);
                        getDesa(idKecamatan, idDesa); // Panggil getDesa setelah mengatur kecamatan
                    }

                    // Pindahkan pengaturan desa ke dalam callback getDesa
                    getDesa(idKecamatan, idDesa);

                    nama_pendidikan.setText(response.body().getUser().getNamaPendidikan());
                    no_telp.setText(response.body().getUser().getTelepon());
                    alamat.setText(response.body().getUser().getAlamat());
                    email.setText(response.body().getUser().getEmail());
                    nama_pendidikan.setText(response.body().getUser().getNamaPendidikan());
                    jurusan.setText(response.body().getUser().getJurusanPendidikan());
                    tahun_lulus.setText(response.body().getUser().getTahunLulus());
                    nilai_pendidikan.setText(response.body().getUser().getNilaiPendidikan());
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
                        list_kecamatan.add(response.body().getData().get(i).getNama());
                        list_id_kecamatan.add(response.body().getData().get(i).getIdKec());
                    }

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

    public void getDesa(String id_kec, String idDesa) {
        getDesa = api.getDesa(id_kec);
        getDesa.enqueue(new Callback<BaseResponse<Desa>>() {
            @Override
            public void onResponse(Call<BaseResponse<Desa>> call, Response<BaseResponse<Desa>> response) {
                if (response.isSuccessful()) {
                    list_desa.clear();
                    list_id_desa.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
                        list_desa.add(response.body().getData().get(i).getNama());
                        list_id_desa.add(response.body().getData().get(i).getId().toString());
                    }

                    // Isi spinner desa
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(UbahProfileActivity.this, R.layout.spinner_kecamatan, list_desa);
                    arrayAdapter.setDropDownViewResource(R.layout.spinner_kecamatan);
                    desa.setAdapter(arrayAdapter);

                    int kecamatanIndex = list_id_kecamatan.indexOf(id_kec);
                    if (kecamatanIndex >= 0) {
                        kecamatan.setSelection(kecamatanIndex);
                    }

                    // Atur posisi spinner desa setelah data diterima
                    int desaIndex = list_id_desa.indexOf(idDesa);
                    if (desaIndex >= 0) {
                        desa.setSelection(desaIndex);
                    }
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