package com.diskominfo.ekpkdisnaker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.diskominfo.ekpkdisnaker.R;
import com.diskominfo.ekpkdisnaker.session.Session;

public class DetailAK1Activity extends AppCompatActivity {

    LinearLayout download,lapor;
    TextView no_register, nik, nama_peserta, tgl_lahir, jenis_kelamin, pendidikan, sts_kartu, masa_berlaku;
    ImageView btn_back;

    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ak1);

        btn_back = findViewById(R.id.btn_back);
        no_register = findViewById(R.id.no_register);
        nama_peserta = findViewById(R.id.nama_peserta);
        jenis_kelamin = findViewById(R.id.jenis_kelamin);
        nik = findViewById(R.id.nik);
        tgl_lahir = findViewById(R.id.tgl_lahir);
        pendidikan = findViewById(R.id.pendidikan);
        sts_kartu = findViewById(R.id.sts_kartu);
        masa_berlaku = findViewById(R.id.masa_berlaku);
        download = findViewById(R.id.download);
        lapor = findViewById(R.id.lapor);

        session = new Session(this);

        no_register.setText(getIntent().getStringExtra("no_register"));
        nik.setText(getIntent().getStringExtra("nik"));
        nama_peserta.setText(getIntent().getStringExtra("nama_peserta"));
        jenis_kelamin.setText(getIntent().getStringExtra("jenis_kelamin"));
        tgl_lahir.setText(getIntent().getStringExtra("tgl_lahir"));
        pendidikan.setText(getIntent().getStringExtra("pendidikan"));
        if (getIntent().getStringExtra("status").equals("0")) {
            download.setVisibility(View.GONE);
            lapor.setVisibility(View.GONE);
            sts_kartu.setText("PENGAJUAN");
            sts_kartu.setTextColor(Color.parseColor("#f4bd0e"));
        } else if (getIntent().getStringExtra("status").equals("1")) {
            sts_kartu.setText("DIVERIFIKASI");
            sts_kartu.setTextColor(Color.parseColor("#00B04E"));
        } else if (getIntent().getStringExtra("status").equals("2")) {
            sts_kartu.setText("DITANDATANGANI");
            sts_kartu.setTextColor(Color.parseColor("#81ecec"));
        } else if (getIntent().getStringExtra("status").equals("3")) {
            sts_kartu.setText("PENEMPATAN");
            sts_kartu.setTextColor(Color.parseColor("#00cec9"));
        }
        masa_berlaku.setText(getIntent().getStringExtra("masa_berlaku"));

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getIntent().getStringExtra("file_ak1").equals("kosong")) {
                    Toast.makeText(DetailAK1Activity.this, "Kartu AK1 belum tersedia", Toast.LENGTH_SHORT).show();
                } else {
                    downloadPdfContent(getIntent().getStringExtra("file_ak1").replace("public/", "storage/"));
                }
            }
        });

        lapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(DetailAK1Activity.this, "Sedang Maintenance", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(DetailAK1Activity.this, PenempatanActivity.class);
                it.putExtra("no_register", no_register.getText().toString());
                startActivity(it);
            }
        });

    }

    public void downloadPdfContent(String urlToDownload){

        DownloadManager downloadmanager = (DownloadManager) getSystemService(DetailAK1Activity.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(session.getBaseUrl()+urlToDownload);

        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle(no_register.getText().toString()+".pdf");
        request.setDescription("Downloading");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setVisibleInDownloadsUi(true);
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, no_register.getText().toString()+".pdf");
//        request.setDestinationUri(Uri.parse("file://Download/"+no_register.getText().toString()+".pdf"));
        downloadmanager.enqueue(request);

        Toast.makeText(DetailAK1Activity.this, "Download Dokumen", Toast.LENGTH_SHORT).show();

    }
}