package com.diskominfo.ekpkdisnaker.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.diskominfo.ekpkdisnaker.R;

import java.util.ArrayList;

/**
 * Created by Sfyn on 04/02/2018.
 */

public class AdapterAK1 extends ArrayAdapter<String> {

    private Activity context;
    private Context mContext;
    private ArrayList<String> number = new ArrayList<>();
    private ArrayList<String> no_register = new ArrayList<>();
    private ArrayList<String> nama_peserta = new ArrayList<>();
    private ArrayList<String> masa_berlaku = new ArrayList<>();
    private ArrayList<String> status = new ArrayList<>();
    private ArrayList<String> keterangan = new ArrayList<>();
    private ArrayList<String> sts_berlaku = new ArrayList<>();
    private OnEditLocationListener detail;
    private OnEditLocationListener penempatan;
    private OnEditLocationListener lapor;

    public AdapterAK1(Activity context,
                      ArrayList<String> number,
                      ArrayList<String> no_register,
                      ArrayList<String> nama_peserta,
                      ArrayList<String> masa_berlaku,
                      ArrayList<String> status,
                      ArrayList<String> keterangan,
                      ArrayList<String> sts_berlaku,
                      OnEditLocationListener detail,
                      OnEditLocationListener penempatan,
                      OnEditLocationListener lapor) {
        super(context, R.layout.adapter_ak1, no_register);

        this.context = context;
        this.mContext = context;
        this.number = number;
        this.no_register = no_register;
        this.nama_peserta = nama_peserta;
        this.masa_berlaku = masa_berlaku;
        this.status = status;
        this.keterangan = keterangan;
        this.sts_berlaku = sts_berlaku;
        this.detail = detail;
        this.penempatan = penempatan;
        this.lapor = lapor;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder viewHolder = null;
        if (v == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            v = layoutInflater.inflate(R.layout.adapter_ak1, null, true);
            viewHolder = new ViewHolder(v);
            v.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) v.getTag();
        }

        viewHolder.number.setText(number.get(position));
        viewHolder.no_register.setText(no_register.get(position));
        viewHolder.masa_berlaku.setText(masa_berlaku.get(position));

        if (status.get(position).equals("0")) {
            viewHolder.btn_penempatan.setVisibility(View.GONE);
            viewHolder.sts_kartu.setText("PENGAJUAN");
            viewHolder.sts_kartu.setTextColor(Color.parseColor("#f4bd0e"));
        } else if (status.get(position).equals("1")) {
            viewHolder.btn_penempatan.setVisibility(View.GONE);
            viewHolder.sts_kartu.setText("DIVERIFIKASI");
            viewHolder.sts_kartu.setTextColor(Color.parseColor("#00B04E"));
        } else if (status.get(position).equals("2")) {
            viewHolder.btn_penempatan.setVisibility(View.VISIBLE);
            viewHolder.sts_kartu.setText("DITANDATANGANI");
            viewHolder.sts_kartu.setTextColor(Color.parseColor("#81ecec"));
        } else if (status.get(position).equals("3")) {
            viewHolder.btn_penempatan.setVisibility(View.GONE);
            viewHolder.sts_kartu.setText("PENEMPATAN");
            viewHolder.sts_kartu.setTextColor(Color.parseColor("#00cec9"));
        } else if (status.get(position).equals("4")) {
            viewHolder.btn_penempatan.setVisibility(View.GONE);
            viewHolder.sts_kartu.setText("DITOLAK");
            viewHolder.sts_kartu.setTextColor(Color.parseColor("#ce0005"));
        }

        viewHolder.keterangan.setText(keterangan.get(position));

        viewHolder.btn_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (detail != null) {
                    detail.onClickAdapter(position);
                }
            }
        });

        viewHolder.btn_penempatan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (penempatan != null) {
                    penempatan.onClickAdapter(position);
                }
            }
        });

        viewHolder.btn_lapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lapor != null) {
                    lapor.onClickAdapter(position);
                }
            }
        });

        if (status.get(position).equals("0") || status.get(position).equals("1")) {
            viewHolder.btn_detail.setVisibility(View.GONE);
            viewHolder.btn_penempatan.setVisibility(View.GONE);
            viewHolder.btn_lapor.setVisibility(View.GONE);
            if (status.get(position).equals("1")) {
                viewHolder.ly_keterangan.setVisibility(View.VISIBLE);
                viewHolder.keterangan.setText("Sedang proses tanda tangan pengantar kerja");
                viewHolder.keterangan.setTextColor(Color.parseColor("#00B04E"));
            } else {
                viewHolder.ly_keterangan.setVisibility(View.GONE);
            }
        } else if (status.get(position).equals("4")) {
            viewHolder.btn_detail.setVisibility(View.GONE);
            viewHolder.btn_penempatan.setVisibility(View.GONE);
            viewHolder.btn_lapor.setVisibility(View.GONE);
            viewHolder.ly_keterangan.setVisibility(View.VISIBLE);
            viewHolder.keterangan.setTextColor(Color.parseColor("#ce0005"));
        } else if (status.get(position).equals("2")) {
            viewHolder.btn_detail.setVisibility(View.VISIBLE);
            viewHolder.ly_keterangan.setVisibility(View.GONE);
            viewHolder.btn_penempatan.setVisibility(View.VISIBLE);
            if (sts_berlaku.get(position).equals("0")) {
                viewHolder.btn_lapor.setVisibility(View.VISIBLE);
            }
        } else if (status.get(position).equals("3")) {
            viewHolder.btn_penempatan.setVisibility(View.GONE);
            viewHolder.btn_lapor.setVisibility(View.GONE);
            viewHolder.ly_keterangan.setVisibility(View.VISIBLE);
            viewHolder.keterangan.setText("Terima kasih anda telah melapor penempatan kerja");
            viewHolder.keterangan.setTextColor(Color.parseColor("#00cec9"));
        } else {
            viewHolder.ly_keterangan.setVisibility(View.GONE);
        }

        return v;
    }

    class ViewHolder{
        LinearLayout btn_detail, btn_penempatan, btn_lapor, ly_keterangan;
        TextView number, no_register, masa_berlaku, keterangan, sts_kartu;
        ViewHolder(View view){
            ly_keterangan = view.findViewById(R.id.ly_keterangan);
            btn_detail = view.findViewById(R.id.btn_detail);
            btn_penempatan = view.findViewById(R.id.btn_penempatan);
            btn_lapor = view.findViewById(R.id.btn_lapor);
            number = view.findViewById(R.id.number);
            no_register = view.findViewById(R.id.no_register);
            masa_berlaku = view.findViewById(R.id.masa_berlaku);
            sts_kartu = view.findViewById(R.id.sts_kartu);
            keterangan = view.findViewById(R.id.keterangan);
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
