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
import android.widget.TextView;

import com.example.ekpkdisnaker.R;

import java.util.ArrayList;

/**
 * Created by Sfyn on 04/02/2018.
 */

public class AdapterKeterampilan extends ArrayAdapter<String> {

    private Activity context;
    private Context mContext;
    private ArrayList<String> keterangan = new ArrayList<>();
    private ArrayList<String> tahun = new ArrayList<>();
    private AdapterAK1.OnEditLocationListener hapus;

    public AdapterKeterampilan(Activity context, ArrayList<String> keterangan, ArrayList<String> tahun,
                               AdapterAK1.OnEditLocationListener hapus) {
        super(context, R.layout.adapter_keterampilan, keterangan);

        this.context = context;
        this.mContext = context;
        this.keterangan = keterangan;
        this.tahun = tahun;
        this.hapus = hapus;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder viewHolder = null;
        if (v == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            v = layoutInflater.inflate(R.layout.adapter_keterampilan, null, true);
            viewHolder = new ViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        viewHolder.keterangan.setText(keterangan.get(position));
        viewHolder.tahun.setText(tahun.get(position));

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
        TextView keterangan, tahun, btn_hapus;
        ViewHolder(View view){
            keterangan = view.findViewById(R.id.keterangan);
            tahun = view.findViewById(R.id.tahun);
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
