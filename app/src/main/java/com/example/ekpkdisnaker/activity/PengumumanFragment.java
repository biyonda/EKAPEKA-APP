package com.example.ekpkdisnaker.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ekpkdisnaker.R;
import com.example.ekpkdisnaker.api.Api;
import com.example.ekpkdisnaker.api.RetrofitClient;
import com.example.ekpkdisnaker.helpers.ApiError;
import com.example.ekpkdisnaker.helpers.ErrorUtils;
import com.example.ekpkdisnaker.response.BaseResponse;
import com.example.ekpkdisnaker.session.Session;
import com.example.ekpkdisnaker.table.Pengumuman;
import com.example.ekpkdisnaker.table.ak1;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PengumumanFragment extends Fragment {

    ListView list_data;

    private ArrayList<String> judul = new ArrayList<>();
    private ArrayList<String> jenis = new ArrayList<>();
    private ArrayList<String> pengumuman = new ArrayList<>();
    private ArrayList<String> link = new ArrayList<>();

    Session session;
    Api api;
    Call<BaseResponse<Pengumuman>> getPengumuman;
    AdapterPengumuman adapterPengumuman;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pengumuman, container, false);

        list_data = view.findViewById(R.id.list_data);

        session = new Session(getContext());
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        getPengumuman();
        return view;
    }

    public void getPengumuman() {
        getPengumuman = api.getPengumuman();
        getPengumuman.enqueue(new Callback<BaseResponse<Pengumuman>>() {
            @Override
            public void onResponse(Call<BaseResponse<Pengumuman>> call, Response<BaseResponse<Pengumuman>> response) {
                if (response.isSuccessful()) {
                    judul.clear();
                    jenis.clear();
                    pengumuman.clear();
                    link.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
                        judul.add(response.body().getData().get(i).getNamaPengumuman());
                        jenis.add(response.body().getData().get(i).getJenisPengumuman().toString());
                        pengumuman.add(response.body().getData().get(i).getUraianPengumuman());
                        link.add(response.body().getData().get(i).getLinkPengumuman());
                    }

                    adapterPengumuman = new AdapterPengumuman(getActivity(), judul, jenis, pengumuman, link);
                    adapterPengumuman.notifyDataSetChanged();
                    list_data.setAdapter(adapterPengumuman);
                } else {
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(getContext(), apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Pengumuman>> call, Throwable t) {
                Toast.makeText(getContext(), "Error, " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}