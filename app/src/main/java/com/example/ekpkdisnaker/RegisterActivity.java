package com.example.ekpkdisnaker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;

public class RegisterActivity extends AppCompatActivity {

    Spinner jenis_kelamin, kawin, kd_pendidikan;
    LinearLayout select_tgl_lahir;
    TextView tgl_lahir;

    final Calendar calendar = Calendar.getInstance();
    int yy = calendar.get(Calendar.YEAR);
    int mm = calendar.get(Calendar.MONTH);
    int dd = calendar.get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        jenis_kelamin = findViewById(R.id.jenis_kelamin);
        kawin = findViewById(R.id.kawin);
        kd_pendidikan = findViewById(R.id.kd_pendidikan);
        select_tgl_lahir = findViewById(R.id.select_tgl_lahir);
        tgl_lahir = findViewById(R.id.tgl_lahir);

        ArrayAdapter<CharSequence> bulan_spinner = ArrayAdapter.createFromResource(this, R.array.jk_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> kawin_spinner = ArrayAdapter.createFromResource(this, R.array.kawin_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> pendidikan_spinner = ArrayAdapter.createFromResource(this, R.array.pendidikan_array, android.R.layout.simple_spinner_item);
        bulan_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kawin_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jenis_kelamin.setAdapter(bulan_spinner);
        kawin.setAdapter(kawin_spinner);
        kd_pendidikan.setAdapter(pendidikan_spinner);

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

    }
}