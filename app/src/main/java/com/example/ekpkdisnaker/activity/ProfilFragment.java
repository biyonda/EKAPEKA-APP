package com.example.ekpkdisnaker.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.ekpkdisnaker.R;
import com.example.ekpkdisnaker.session.Session;

public class ProfilFragment extends Fragment {

    RelativeLayout btn_setting;
    RelativeLayout btn_logout;
    Session session;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profil, container, false);

        btn_setting = view.findViewById(R.id.btn_setting);
        btn_logout = view.findViewById(R.id.btn_logout);

        session = new Session(getContext());

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
                session.setUserStatus(false, "","", "", "");
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
            }
        });

        return view;
    }
}