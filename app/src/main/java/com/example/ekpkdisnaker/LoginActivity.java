package com.example.ekpkdisnaker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    AppCompatButton btn_login;
    ImageView show_password;
    Boolean showPasswordClicked = false;
    TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login);
        password = findViewById(R.id.password);
        show_password = findViewById(R.id.show_password);

        show_password.setBackgroundResource(R.drawable.ic_eye_open);
        show_password.setOnClickListener(mToggleShowPasswordButton);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }

    View.OnClickListener mToggleShowPasswordButton = new View.OnClickListener(){

        @Override
        public void onClick(View v){
            // change your button background

            if(showPasswordClicked){
                v.setBackgroundResource(R.drawable.ic_eye_closed);
                password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }else{
                v.setBackgroundResource(R.drawable.ic_eye_open);
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }

            showPasswordClicked = !showPasswordClicked; // reverse
        }

    };

}