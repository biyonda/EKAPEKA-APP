package com.example.ekpkdisnaker.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.ekpkdisnaker.R;
import com.example.ekpkdisnaker.api.Api;
import com.example.ekpkdisnaker.api.RetrofitClient;
import com.example.ekpkdisnaker.response.RegisterResponse;
import com.example.ekpkdisnaker.session.Session;

import java.util.Calendar;

import retrofit2.Call;

public class UbahProfileActivity extends AppCompatActivity {

    Spinner jenis_kelamin, kawin, kd_pendidikan, agama;
    LinearLayout select_tgl_lahir;
    ImageView btn_back;
    AppCompatButton btn_simpan;
    ProgressBar progress_register;
    TextView tgl_lahir, nama_lengkap, tmp_lahir, email, alamat, no_telp, nama_pendidikan, password;

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
        setContentView(R.layout.activity_ubah_profile);

        btn_back = findViewById(R.id.btn_back);
        jenis_kelamin = findViewById(R.id.jenis_kelamin);
        kawin = findViewById(R.id.kawin);
        kd_pendidikan = findViewById(R.id.kd_pendidikan);
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
        api = RetrofitClient.createService(Api.class);

        select_tgl_lahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(UbahProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String tglAwal = String.valueOf(year) +"-"+String.valueOf(month + 1) + "-" + String.valueOf(dayOfMonth);
                        tgl_lahir.setText(tglAwal);
                    }
                }, yy, mm, dd);
                datePickerDialog.show();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}