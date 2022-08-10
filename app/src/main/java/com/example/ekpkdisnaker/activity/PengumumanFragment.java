package com.example.ekpkdisnaker.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
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
    SwipeRefreshLayout swipe_refresh_layout;

    private ArrayList<String> id = new ArrayList<>();
    private ArrayList<String> judul = new ArrayList<>();
    private ArrayList<String> jenis = new ArrayList<>();
    private ArrayList<String> pengumuman = new ArrayList<>();
    private ArrayList<String> link = new ArrayList<>();
    private ArrayList<String> file = new ArrayList<>();
    private ArrayList<String> tanggal = new ArrayList<>();

    Session session;
    Api api;
    Call<BaseResponse<Pengumuman>> getPengumuman;
    Call<BaseResponse> getStatusAK1;
    AdapterPengumuman adapterPengumuman;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pengumuman, container, false);

        list_data = view.findViewById(R.id.list_data);
        swipe_refresh_layout = view.findViewById(R.id.swipe_refresh_layout);

        session = new Session(getContext());
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());

        swipe_refresh_layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipe_refresh_layout.setRefreshing(false);
                    }
                }, 1000);
            }
        });

        getPengumuman();
        getStatusAK1(session.getUsername());

        list_data.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                System.out.println(id.get(i));
                Intent intent = new Intent(getActivity(), DetailPengumumanActivity.class);
                intent.putExtra("id", id.get(i));
                intent.putExtra("judul", judul.get(i));
                intent.putExtra("pengumuman", pengumuman.get(i));
                intent.putExtra("file", file.get(i));
                intent.putExtra("tanggal", tanggal.get(i));
                intent.putExtra("link", link.get(i));
                startActivity(intent);
            }
        });
        return view;
    }

    public void getPengumuman() {
        getPengumuman = api.getPengumuman();
        getPengumuman.enqueue(new Callback<BaseResponse<Pengumuman>>() {
            @Override
            public void onResponse(Call<BaseResponse<Pengumuman>> call, Response<BaseResponse<Pengumuman>> response) {
                if (response.isSuccessful()) {
                    id.clear();
                    judul.clear();
                    jenis.clear();
                    pengumuman.clear();
                    link.clear();
                    file.clear();
                    tanggal.clear();

                    for (int i = 0; i < response.body().getData().size(); i++) {
                        id.add(response.body().getData().get(i).getId().toString());
                        judul.add(response.body().getData().get(i).getNamaPengumuman());
                        jenis.add(response.body().getData().get(i).getJenisPengumuman().toString());
                        pengumuman.add(response.body().getData().get(i).getUraianPengumuman());
                        link.add(response.body().getData().get(i).getLinkPengumuman());
                        file.add(response.body().getData().get(i).getFilePengumuman());
                        tanggal.add(response.body().getData().get(i).getCreatedAt());
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

    public void getStatusAK1(String nik) {
        getStatusAK1 = api.getStatusAK1(nik);
        getStatusAK1.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getMessage().equals("1")) {
                        popUpUpdate();
                    }
                } else {
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(getContext(), apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Error, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void popUpUpdate() {
        final Dialog dialog = new Dialog(getContext());
        dialog.setTitle("Gambar Barang");
        View v = getLayoutInflater().inflate(R.layout.popup_update, null);
        dialog.setContentView(v);
        Button update = v.findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.asa.asri_larisso"));
//                startActivity(intent);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}