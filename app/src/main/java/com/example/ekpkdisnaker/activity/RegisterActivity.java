package com.example.ekpkdisnaker.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ekpkdisnaker.R;
import com.example.ekpkdisnaker.api.Api;
import com.example.ekpkdisnaker.api.RetrofitClient;
import com.example.ekpkdisnaker.helpers.ApiError;
import com.example.ekpkdisnaker.helpers.ErrorUtils;
import com.example.ekpkdisnaker.response.RegisterResponse;
import com.example.ekpkdisnaker.session.Session;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Spinner jenis_kelamin, kawin, kd_pendidikan, agama;
    LinearLayout select_tgl_lahir;
    TextView tgl_lahir, username, nama_lengkap, tmp_lahir, email, alamat, no_telp, nama_pendidikan, password;
    AppCompatButton btn_register;
    ProgressBar progress_register;

    final Calendar calendar = Calendar.getInstance();
    int yy = calendar.get(Calendar.YEAR);
    int mm = calendar.get(Calendar.MONTH);
    int dd = calendar.get(Calendar.DAY_OF_MONTH);

    Session session;
    Api api;
    Call<RegisterResponse> register;
    String tmp_kd_pendidikan = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        jenis_kelamin = findViewById(R.id.jenis_kelamin);
        kawin = findViewById(R.id.kawin);
        kd_pendidikan = findViewById(R.id.kd_pendidikan);
        agama = findViewById(R.id.agama);
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
        password = findViewById(R.id.password);

        ArrayAdapter<CharSequence> bulan_spinner = ArrayAdapter.createFromResource(this, R.array.jk_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> kawin_spinner = ArrayAdapter.createFromResource(this, R.array.kawin_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> pendidikan_spinner = ArrayAdapter.createFromResource(this, R.array.pendidikan_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> agama_spinner = ArrayAdapter.createFromResource(this, R.array.agama_array, android.R.layout.simple_spinner_item);
        bulan_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kawin_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pendidikan_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        agama_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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

                System.out.println(tmp_kd_pendidikan);

                register = api.register(username.getText().toString(), nama_lengkap.getText().toString(),
                        tmp_lahir.getText().toString(), (jenis_kelamin.getSelectedItemPosition()+1)+"", tgl_lahir.getText().toString(),
                        kawin.getSelectedItemPosition()+"", tmp_kd_pendidikan+"", nama_pendidikan.getText().toString(),
                        alamat.getText().toString(), no_telp.getText().toString(), email.getText().toString(), password.getText().toString(),
                        agama.getSelectedItem().toString());

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
    }
}