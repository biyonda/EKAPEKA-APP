package com.diskominfo.ekpkdisnaker.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
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
import com.diskominfo.ekpkdisnaker.response.RegisterResponse;
import com.diskominfo.ekpkdisnaker.session.Session;
import com.diskominfo.ekpkdisnaker.table.Desa;
import com.diskominfo.ekpkdisnaker.table.Kecamatan;

import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Spinner jenis_kelamin, kawin, kd_pendidikan, agama, kecamatan, desa;
    LinearLayout select_tgl_lahir;
    EditText username, nama_lengkap, tmp_lahir, email, alamat, no_telp, nama_pendidikan, password, jurusan, tahun_lulus, nilai_pendidikan;
    TextView tgl_lahir;
    AppCompatButton btn_register;
    ProgressBar progress_register;

    final Calendar calendar = Calendar.getInstance();
    int yy = calendar.get(Calendar.YEAR);
    int mm = calendar.get(Calendar.MONTH);
    int dd = calendar.get(Calendar.DAY_OF_MONTH);

    Session session;
    Api api;
    Call<RegisterResponse> register;
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
        setContentView(R.layout.activity_register);

        jenis_kelamin = findViewById(R.id.jenis_kelamin);
        kawin = findViewById(R.id.kawin);
        kd_pendidikan = findViewById(R.id.kd_pendidikan);
        agama = findViewById(R.id.agama);
        kecamatan = findViewById(R.id.kecamatan);
        desa = findViewById(R.id.desa);
        select_tgl_lahir = findViewById(R.id.select_tgl_lahir);
        tgl_lahir = findViewById(R.id.tgl_lahir);
        btn_register = findViewById(R.id.btn_daftar);
        progress_register = findViewById(R.id.progress_register);
        username = findViewById(R.id.username);
        nama_lengkap = findViewById(R.id.nama_lengkap);
        tmp_lahir = findViewById(R.id.tmp_lahir);
        email = findViewById(R.id.email);
        alamat = findViewById(R.id.alamat);
        no_telp = findViewById(R.id.no_telp);
        nama_pendidikan = findViewById(R.id.nama_pendidikan);
        jurusan = findViewById(R.id.jurusan);
        tahun_lulus = findViewById(R.id.tahun_lulus);
        password = findViewById(R.id.password);
        nilai_pendidikan = findViewById(R.id.nilai_pendidikan);

        ArrayAdapter<CharSequence> bulan_spinner = ArrayAdapter.createFromResource(this, R.array.jk_array, R.layout.spinner_kecamatan);
        ArrayAdapter<CharSequence> kawin_spinner = ArrayAdapter.createFromResource(this, R.array.kawin_array, R.layout.spinner_kecamatan);
        ArrayAdapter<CharSequence> pendidikan_spinner = ArrayAdapter.createFromResource(this, R.array.pendidikan_array, R.layout.spinner_kecamatan);
        ArrayAdapter<CharSequence> agama_spinner = ArrayAdapter.createFromResource(this, R.array.agama_array, R.layout.spinner_kecamatan);
        bulan_spinner.setDropDownViewResource(R.layout.spinner_kecamatan);
        kawin_spinner.setDropDownViewResource(R.layout.spinner_kecamatan);
        pendidikan_spinner.setDropDownViewResource(R.layout.spinner_kecamatan);
        agama_spinner.setDropDownViewResource(R.layout.spinner_kecamatan);
        jenis_kelamin.setAdapter(bulan_spinner);
        kawin.setAdapter(kawin_spinner);
        kd_pendidikan.setAdapter(pendidikan_spinner);
        agama.setAdapter(agama_spinner);

        session = new Session(this);
        api = RetrofitClient.createService(Api.class);

        select_tgl_lahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(RegisterActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String tglAwal = String.valueOf(year) +"-"+String.valueOf(month + 1) + "-" + String.valueOf(dayOfMonth);
                        tgl_lahir.setText(tglAwal);
                    }
                }, yy, mm, dd);
                datePickerDialog.show();
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(kd_pendidikan.getSelectedItemPosition());

                if (kd_pendidikan.getSelectedItemPosition() == 0){
                    tmp_kd_pendidikan = "SD";
                } else if (kd_pendidikan.getSelectedItemPosition() == 1){
                    tmp_kd_pendidikan = "SLTP";
                } else if (kd_pendidikan.getSelectedItemPosition() == 2){
                    tmp_kd_pendidikan = "SLTA";
                } else if (kd_pendidikan.getSelectedItemPosition() == 3){
                    tmp_kd_pendidikan = "MAN";
                } else if (kd_pendidikan.getSelectedItemPosition() == 4){
                    tmp_kd_pendidikan = "SMK";
                } else if (kd_pendidikan.getSelectedItemPosition() == 5){
                    tmp_kd_pendidikan = "DI";
                } else if (kd_pendidikan.getSelectedItemPosition() == 6){
                    tmp_kd_pendidikan = "DII";
                } else if (kd_pendidikan.getSelectedItemPosition() == 7){
                    tmp_kd_pendidikan = "DII";
                } else if (kd_pendidikan.getSelectedItemPosition() == 8){
                    tmp_kd_pendidikan = "DIV";
                } else if (kd_pendidikan.getSelectedItemPosition() == 9){
                    tmp_kd_pendidikan = "S1";
                } else if (kd_pendidikan.getSelectedItemPosition() == 10){
                    tmp_kd_pendidikan = "S2";
                } else if (kd_pendidikan.getSelectedItemPosition() == 11){
                    tmp_kd_pendidikan = "S3";
                }

//                System.out.println(tmp_kd_pendidikan);
//                System.out.println(list_id_kecamatan.get(kecamatan.getSelectedItemPosition()));
//                System.out.println(list_id_desa.get(desa.getSelectedItemPosition()));

                register = api.register(username.getText().toString(), nama_lengkap.getText().toString(),
                        tmp_lahir.getText().toString(), (jenis_kelamin.getSelectedItem())+"", tgl_lahir.getText().toString(),
                        kawin.getSelectedItem()+"", tmp_kd_pendidikan+"", nama_pendidikan.getText().toString(),
                        jurusan.getText().toString(), alamat.getText().toString(), list_id_kecamatan.get(kecamatan.getSelectedItemPosition()),
                        list_id_desa.get(desa.getSelectedItemPosition()), no_telp.getText().toString(),
                        email.getText().toString(), password.getText().toString(), agama.getSelectedItem().toString(), tahun_lulus.getText().toString(), nilai_pendidikan.getText().toString());

                register.enqueue(new Callback<RegisterResponse>() {
                    @Override
                    public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                        if (response.isSuccessful()) {
                            session.setUserStatus(true, response.body().getRegister().getId().toString(), response.body().getRegister().getUsername(),
                                    response.body().getRegister().getNamaLengkap(), response.body().getRegister().getApiToken());
                            startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                            finish();
                            Toast.makeText(RegisterActivity.this, "Selamat datang "+response.body().getRegister().getNamaLengkap(), Toast.LENGTH_SHORT).show();
                            progress_register.setVisibility(View.GONE);
                        } else {
                            ApiError apiError = ErrorUtils.parseError(response);
                            Toast.makeText(RegisterActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                            progress_register.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onFailure(Call<RegisterResponse> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "Error, "+t.getMessage(), Toast.LENGTH_SHORT).show();
                        progress_register.setVisibility(View.GONE);
                    }
                });
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
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(RegisterActivity.this, R.layout.spinner_kecamatan, list_kecamatan);
                    arrayAdapter.setDropDownViewResource(R.layout.spinner_kecamatan);
                    kecamatan.setAdapter(arrayAdapter);
                } else {
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(RegisterActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Kecamatan>> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error, "+t.getMessage(), Toast.LENGTH_SHORT).show();
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
                        list_id_desa.add(response.body().getData().get(i).getId().toString());
                    }

                    //Ini buat ngisi Spinner
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(RegisterActivity.this, R.layout.spinner_kecamatan, list_desa);
                    arrayAdapter.setDropDownViewResource(R.layout.spinner_kecamatan);
                    desa.setAdapter(arrayAdapter);
                } else {
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(RegisterActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Desa>> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}