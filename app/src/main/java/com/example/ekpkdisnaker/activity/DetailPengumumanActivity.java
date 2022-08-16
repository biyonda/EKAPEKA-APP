package com.example.ekpkdisnaker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.example.ekpkdisnaker.R;
import com.example.ekpkdisnaker.session.Session;

public class DetailPengumumanActivity extends AppCompatActivity {

    String judul, pengumuman, file, tanggal, link;
    TextView txt_judul, txt_pengumuman, txt_tanggal, txt_link;
    ImageView gambar, btn_back;

    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pengumuman);

        btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        session = new Session(this);
        judul = getIntent().getStringExtra("judul");
        pengumuman = getIntent().getStringExtra("pengumuman");
        file = getIntent().getStringExtra("file");
        tanggal = "Tanggal : " + getIntent().getStringExtra("tanggal").substring(0, 10);
        link = getIntent().getStringExtra("link");

        gambar = findViewById(R.id.gambar);
        txt_judul = findViewById(R.id.judul);
        txt_pengumuman = findViewById(R.id.pengumuman);
        txt_tanggal = findViewById(R.id.tanggal);
        txt_link = findViewById(R.id.link);

        txt_judul.setText(judul);
        txt_pengumuman.setText(pengumuman);
        txt_tanggal.setText(tanggal);
        txt_link.setText(link);

        gambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomImage();
            }
        });

        txt_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = getIntent().getStringExtra("link");

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop().signature(
                new ObjectKey(String.valueOf(System.currentTimeMillis())));
        Glide.with(DetailPengumumanActivity.this)
                .setDefaultRequestOptions(requestOptions)
                .load(session.getBaseUrl()
                        +"storage/"+file)
                .into(gambar);
    }

    public void zoomImage() {
        final Dialog dialog = new Dialog(DetailPengumumanActivity.this);
        dialog.setTitle("Gambar Barang");
        View v = getLayoutInflater().inflate(R.layout.popup_img, null);
        dialog.setContentView(v);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);

        ImageView close = v.findViewById(R.id.close);
        ImageView image = v.findViewById(R.id.image);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.centerCrop().signature(
                new ObjectKey(String.valueOf(System.currentTimeMillis())));
        Glide.with(DetailPengumumanActivity.this)
                .setDefaultRequestOptions(requestOptions)
                .load(session.getBaseUrl()
                        +"storage/"+file)
                .into(image);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}