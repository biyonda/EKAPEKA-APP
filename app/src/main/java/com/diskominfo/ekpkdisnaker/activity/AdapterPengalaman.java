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
    private ArrayList<String> jabatan = new ArrayList<>();
    private ArrayList<String> lama_kerja = new ArrayList<>();
    private ArrayList<String> pemberi_kerja = new ArrayList<>();
    private ArrayList<String> uraian_tugas = new ArrayList<>();
    private AdapterPengalaman.OnEditLocationListener hapus;

    public AdapterPengalaman(Activity context,
                             ArrayList<String> jabatan,
                             ArrayList<String> lama_kerja,
                             ArrayList<String> pemberi_kerja,
                             ArrayList<String> uraian_tugas,
                             AdapterPengalaman.OnEditLocationListener hapus) {

        super(context, R.layout.adapter_pengalaman, jabatan);

        this.context = context;
        this.mContext = context;
        this.jabatan = jabatan;
        this.lama_kerja = lama_kerja;
        this.pemberi_kerja = pemberi_kerja;
        this.uraian_tugas = uraian_tugas;
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

        viewHolder.jabatan.setText(jabatan.get(position));
        viewHolder.lama_kerja.setText(lama_kerja.get(position));
        viewHolder.pemberi_kerja.setText(pemberi_kerja.get(position));
        viewHolder.uraian_tugas.setText(uraian_tugas.get(position));

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
        TextView jabatan, lama_kerja, pemberi_kerja, uraian_tugas, btn_hapus;
        ViewHolder(View view){
            jabatan = view.findViewById(R.id.jabatan);
            lama_kerja = view.findViewById(R.id.lama_kerja);
            pemberi_kerja = view.findViewById(R.id.pemberi_kerja);
            uraian_tugas = view.findViewById(R.id.uraian_tugas);
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
