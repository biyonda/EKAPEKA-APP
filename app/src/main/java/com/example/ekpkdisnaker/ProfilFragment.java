package com.example.ekpkdisnaker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class ProfilFragment extends Fragment {

    RelativeLayout btn_setting;
    RelativeLayout btn_logout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        btn_setting = view.findViewById(R.id.btn_setting);
        btn_logout = view.findViewById(R.id.btn_logout);

        btn_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(), SettingActivity.class);
                startActivity(it);
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(), LoginActivity.class);
                startActivity(it);
            }
        });

        return view;
    }
}