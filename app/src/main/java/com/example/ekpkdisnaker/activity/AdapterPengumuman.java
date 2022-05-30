package com.example.ekpkdisnaker.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ekpkdisnaker.R;

import java.util.ArrayList;

/**
 * Created by Sfyn on 04/02/2018.
 */

public class AdapterPengumuman extends ArrayAdapter<String> {

    private Activity context;
    private Context mContext;
    private ArrayList<String> judul = new ArrayList<>();
    private ArrayList<String> jenis = new ArrayList<>();
    private ArrayList<String> pengumuman = new ArrayList<>();
    private ArrayList<String> link = new ArrayList<>();

    public AdapterPengumuman(Activity context, ArrayList<String> judul, ArrayList<String> jenis, ArrayList<String> pengumuman,
                             ArrayList<String> link) {
        super(context, R.layout.adapter_pengumuman, judul);

        this.context = context;
        this.mContext = context;
        this.judul = judul;
        this.jenis = jenis;
        this.pengumuman = pengumuman;
        this.link = link;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder viewHolder = null;
        if (v == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            v = layoutInflater.inflate(R.layout.adapter_pengumuman, null, true);
            viewHolder = new ViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        viewHolder.judul.setText(judul.get(position));
        viewHolder.pengumuman.setText(pengumuman.get(position));
        if (link.get(position) == null) {
            viewHolder.link.setText("-");
        } else {
            viewHolder.link.setText(link.get(position));
        }

        if (jenis.get(position).equals("1")) {
            viewHolder.jenis.setText("Pengumuman");
            viewHolder.gambar.setImageResource(R.drawable.notif_ic_info);
            viewHolder.judul.setTextColor(Color.parseColor("#0d6efd"));
        } else if (jenis.get(position).equals("2")) {
            viewHolder.jenis.setText("Info Lowongan");
            viewHolder.gambar.setImageResource(R.drawable.notif_ic_loker);
            viewHolder.judul.setTextColor(Color.parseColor("#FF4B3E"));
        } else if (jenis.get(position).equals("3")) {
            viewHolder.jenis.setText("Update Sistem");
            viewHolder.gambar.setImageResource(R.drawable.notif_ic_system);
            viewHolder.judul.setTextColor(Color.parseColor("#F9871B"));
        }

        return v;
    }

    class ViewHolder{
        ImageView gambar;
        TextView judul, jenis, pengumuman, link;
        ViewHolder(View view){
            gambar = view.findViewById(R.id.gambar);
            judul = view.findViewById(R.id.judul);
            jenis = view.findViewById(R.id.jenis);
            pengumuman = view.findViewById(R.id.pengumuman);
            link = view.findViewById(R.id.link);
        }
    }

    @Override
    public int getPosition(String item) {
        return super.getPosition(item);
    }

    public interface OnEditLocationListener {
        void onClickAdapter(int position);
    }

}
