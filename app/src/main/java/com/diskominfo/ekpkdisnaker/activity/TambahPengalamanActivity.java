package com.diskominfo.ekpkdisnaker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.diskominfo.ekpkdisnaker.R;
import com.diskominfo.ekpkdisnaker.api.Api;
import com.diskominfo.ekpkdisnaker.api.RetrofitClient;
import com.diskominfo.ekpkdisnaker.helpers.ApiError;
import com.diskominfo.ekpkdisnaker.helpers.ErrorUtils;
import com.diskominfo.ekpkdisnaker.response.BaseResponse;
import com.diskominfo.ekpkdisnaker.session.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TambahPengalamanActivity extends AppCompatActivity {

    ImageView btn_back;
    EditText jabatan, lama_kerja, pemberi_kerja, uraian_tugas;
    Button btn_simpan;

    Session session;
    Api api;
    Call<BaseResponse> tambahPengalaman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pengalaman);

        jabatan = findViewById(R.id.jabatan);
        lama_kerja = findViewById(R.id.lama_kerja);
        pemberi_kerja = findViewById(R.id.pemberi_kerja);
        uraian_tugas = findViewById(R.id.uraian_tugas);
        btn_back = findViewById(R.id.btn_back);
        btn_simpan = findViewById(R.id.btn_simpan);

        session = new Session(this);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

       btn_simpan.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               tambahPengalaman = api.tambahPengalaman(jabatan.getText().toString(), lama_kerja.getText().toString(), pemberi_kerja.getText().toString(), uraian_tugas.getText().toString());
               tambahPengalaman.enqueue(new Callback<BaseResponse>() {
                   @Override
                   public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                       if (response.isSuccessful()) {
                           Toast.makeText(TambahPengalamanActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                           finish();
                       } else {
                           ApiError apiError = ErrorUtils.parseError(response);
                           Toast.makeText(TambahPengalamanActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                       }
                   }

                   @Override
                   public void onFailure(Call<BaseResponse> call, Throwable t) {
                       Toast.makeText(TambahPengalamanActivity.this, "Error, " + t.getMessage(), Toast.LENGTH_SHORT).show();

                   }
               });
           }
       });

    }
}