package com.example.ekpkdisnaker;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class BerandaFragment extends Fragment {

    LinearLayout btn_setting;
    LinearLayout btn_ak1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);

        btn_ak1 = view.findViewById(R.id.btn_ak1);
        btn_setting = view.findViewById(R.id.btn_setting);

        btn_ak1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(), KartuAK1Activity.class);
                startActivity(it);
            }
        });

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(), SettingActivity.class);
                startActivity(it);
            }
        });

        return view;
    }
}