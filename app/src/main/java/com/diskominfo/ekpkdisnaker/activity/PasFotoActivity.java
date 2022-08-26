package com.diskominfo.ekpkdisnaker.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.diskominfo.ekpkdisnaker.R;
import com.diskominfo.ekpkdisnaker.api.Api;
import com.diskominfo.ekpkdisnaker.api.RetrofitClient;
import com.diskominfo.ekpkdisnaker.helpers.ApiError;
import com.diskominfo.ekpkdisnaker.helpers.ErrorUtils;
import com.diskominfo.ekpkdisnaker.helpers.ImageFilePath;
import com.diskominfo.ekpkdisnaker.response.BaseResponse;
import com.diskominfo.ekpkdisnaker.response.UserResponse;
import com.diskominfo.ekpkdisnaker.session.Session;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasFotoActivity extends AppCompatActivity {

    ImageView btn_back, img_pas_foto;
    TextView status;
    Button btn_upload;
    ProgressBar SHOW_PROGRESS;

    Session session;
    Api api;
    Call<UserResponse> getUser;
    Call<BaseResponse> uploadPasFoto;
    Bitmap bitmap;

    private static int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pas_foto);

        btn_back = findViewById(R.id.btn_back);
        img_pas_foto = findViewById(R.id.img_pas_foto);
        status = findViewById(R.id.status);
        btn_upload = findViewById(R.id.btn_upload);
        SHOW_PROGRESS = findViewById(R.id.SHOW_PROGRESS);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, REQUEST_CODE);
            }
        });

        session = new Session(PasFotoActivity.this);
        api = RetrofitClient.createServiceWithAuth(Api.class, session.getToken());
        getUser();
    }

    public void getUser() {
        getUser = api.getUser();
        getUser.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body().getUser().getPasFoto() != null) {
                        status.setText("Upload");
                        status.setTextColor(Color.parseColor("#008E3E"));
                        RequestOptions requestOptions = new RequestOptions();
                        requestOptions.centerCrop().signature(
                                new ObjectKey(String.valueOf(System.currentTimeMillis())));
                        Glide.with(PasFotoActivity.this)
                                .setDefaultRequestOptions(requestOptions)
                                .load(session.getBaseUrl()
                                        + response.body().getUser().getPasFoto().replace("public/", "storage/"))
                                .into(img_pas_foto);
                    } else {
                        status.setText("Belum");
                        status.setTextColor(Color.parseColor("#CE3032"));
                    }

                } else {
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(PasFotoActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(PasFotoActivity.this, "Error, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK && data != null && data.getData() != null) {
                Uri uri = data.getData();
                String realPath = ImageFilePath.getPath(PasFotoActivity.this, data.getData());
//                realPath = RealPathUtil.getRealPathFromURI_API19(this, data.getData());
                Log.i("TAG", "onActivityResult: file path : " + realPath);
                SHOW_PROGRESS.setVisibility(View.VISIBLE);
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    // Log.d(TAG, String.valueOf(bitmap));
                    upload();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String imageToString(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imgBytes, Base64.DEFAULT);
    }

    public void upload() {
        uploadPasFoto = api.uploadPasFoto(imageToString(bitmap));
        uploadPasFoto.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            getUser();
                            SHOW_PROGRESS.setVisibility(View.GONE);
                            Toast.makeText(PasFotoActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }, 1000);
                } else {
                    ApiError apiError = ErrorUtils.parseError(response);
                    Toast.makeText(PasFotoActivity.this, apiError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Toast.makeText(PasFotoActivity.this, "Error, "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}