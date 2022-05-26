package com.example.ekpkdisnaker.session;

import android.content.Context;
import android.content.SharedPreferences;

public class Session {

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    Context context;

    public Session(Context context) {
        this.context = context;
        this.preferences = context.getSharedPreferences("EKPKDISNAKER", context.MODE_PRIVATE);
        this.editor = preferences.edit();
    }

    public void setUserStatus(Boolean loggedIn, String id, String username, String nama, String token){
        editor.putBoolean("loggedIn", loggedIn);
        editor.putString("id", id);
        editor.putString("username", username);
        editor.putString("nama", nama);
        editor.putString("token", token);
        editor.commit();
    }

    public String getBaseUrl() {
        return preferences.getString("baseUrl", "api.pdesoebandi.id");
    }

    public boolean getUserLoggedIn() {
        return preferences.getBoolean("loggedIn", false);
    }

    public String getToken() {
        return preferences.getString("token", "");
    }

    public String getId() {
        return preferences.getString("id", "");
    }

    public String getUsername() {
        return preferences.getString("username", "");
    }

    public String getNama() {
        return preferences.getString("nama", "");
    }
}

