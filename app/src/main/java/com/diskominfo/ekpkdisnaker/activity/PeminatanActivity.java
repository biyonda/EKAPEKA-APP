package com.diskominfo.ekpkdisnaker.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.diskominfo.ekpkdisnaker.R;
import com.diskominfo.ekpkdisnaker.api.Api;
import com.diskominfo.ekpkdisnaker.api.RetrofitClient;
import com.diskominfo.ekpkdisnaker.helpers.ApiError;
import com.diskominfo.ekpkdisnaker.helpers.ErrorUtils;
import com.diskominfo.ekpkdisnaker.response.UserResponse;
import com.diskominfo.ekpkdisnaker.session.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeminatanActivity extends AppCompatActivity {

    ImageView btn_back;
    EditText jabatan, lokasi_kerja, pelatihan;
    Spinner lokasi_penempatan, gaji;
    AppCompatButton btn_simpan;
    Spinner gaji_spinner, lokasi_spinner;

    Session session;
    Api api;
    Call<UserResponse> getUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peminatan);

        jabatan = findViewById(R.id.jabatan);
        pelatihan = findViewById(R.id.pelatihan);
        lokasi_penempatan = findViewById(R.id.lokasi_penempatan);
        lokasi_kerja = findViewById(R.id.lokasi_kerja);
        gaji = findViewById(R.id.gaji);
        btn_simpan = findViewById(R.id.btn_simpan);
        btn_back = findViewById(R.id.btn_back);

        ArrayAdapter<CharSequence> gaji_spinner = ArrayAdapter.createFromResource(this, R.array.gaji_array, R.layout.spinner_kecamatan);
        ArrayAdapter<CharSequence> lokasi_spinner = ArrayAdapter.createFromResource(this, R.array.lokasi_array, R.layout.spinner_kecamatan);
        gaji_spinner.setDropDownViewResource(R.layout.spinner_kecamatan);
        lokasi_spinner.setDropDownViewResource(R.layout.spinner_kecamatan);
        gaji.setAdapter(gaji_spinner);
        lokasi_penempatan.setAdapter(lokasi_spinner);

        session = new Session(this);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
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
                    jabatan.setText(response.body().getUser().getJabatanDiinginkan());
                    lokasi_kerja.setText(response.body().getUser().getLokasiDiinginkan());
                    pelatihan.setText(response.body().getUser().getPelatihanDiinginkan());
                    if (response.body().getUser().getLokasiPenempatan() == null) {
                        lokasi_penempatan.setSelection(0);
                    } else if (response.body().getUser().getLokasiPenempatan().equals("Dalam Negeri - Dalam Tempat Tinggal")) {
                        lokasi_penempatan.setSelection(0);
                    } else if (response.body().getUser().getLokasiPenempatan().equals("Dalam Negeri - Luar Tempat Tinggal")) {
                        lokasi_penempatan.setSelection(1);
                    } else if (response.body().getUser().getLokasiPenempatan().equals("Luar Negeri")) {
                        lokasi_penempatan.setSelection(2);
                    }
                    if (response.body().getUser().getUpahDiinginkan() == null) {
                        gaji.setSelection(0);
                    } else if (response.body().getUser().getUpahDiinginkan().equals("1 <= Rp. 1.000.000,-")) {
                        gaji.setSelection(0);
                    } else if (response.body().getUser().getUpahDiinginkan().equals("2 Rp. 1.000.000,- s.d Rp. 2.500.000,-")) {
                        gaji.setSelection(1);
                    } else if (response.body().getUser().getUpahDiinginkan().equals("3 Rp. 2.500.000,- s.d Rp. 5.000.000,-")) {
                        gaji.setSelection(2);
                    } else if (response.body().getUser().getUpahDiinginkan().equals("4 > Rp. 5.000.000,-")) {
                        gaji.setSelection(3);
                    }
                } else {
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(PeminatanActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(PeminatanActivity.this, "Error, " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}