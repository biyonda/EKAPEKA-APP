package com.diskominfo.ekpkdisnaker.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.diskominfo.ekpkdisnaker.R;

import java.util.ArrayList;

/**
 * Created by Sfyn on 04/02/2018.
 */

public class AdapterPengalaman extends ArrayAdapter<String> {

    private Activity context;
    private Context mContext;
    private ArrayList<String> tempat_kerja = new ArrayList<>();
    private ArrayList<String> posisi = new ArrayList<>();
    private ArrayList<String> durasi = new ArrayList<>();
    private ArrayList<String> uraian = new ArrayList<>();
    private AdapterAK1.OnEditLocationListener hapus;

    public AdapterPengalaman(Activity context,
                             ArrayList<String> tempat_kerja,
                             ArrayList<String> posisi,
                             ArrayList<String> durasi,
                             ArrayList<String> uraian,
                             AdapterAK1.OnEditLocationListener hapus) {

        super(context, R.layout.adapter_pengalaman, uraian);

        this.context = context;
        this.mContext = context;
        this.tempat_kerja = tempat_kerja;
        this.posisi = posisi;
        this.durasi = durasi;
        this.uraian = uraian;
        this.hapus = hapus;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder viewHolder = null;
        if (v == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            v = layoutInflater.inflate(R.layout.adapter_pengalaman, null, true);
            viewHolder = new ViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        viewHolder.tempat_kerja.setText(tempat_kerja.get(position));
        viewHolder.posisi.setText(posisi.get(position));
        viewHolder.durasi.setText(durasi.get(position));
        viewHolder.uraian.setText(uraian.get(position));

        viewHolder.btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hapus != null) {
                    hapus.onClickAdapter(position);
                }
            }
        });

        return v;
    }

    class ViewHolder{
        TextView tempat_kerja, posisi, durasi, uraian, btn_hapus;
        ViewHolder(View view){
            tempat_kerja = view.findViewById(R.id.tempat_kerja);
            posisi = view.findViewById(R.id.posisi);
            durasi = view.findViewById(R.id.durasi);
            uraian = view.findViewById(R.id.uraian);
            btn_hapus = view.findViewById(R.id.btn_hapus);
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
