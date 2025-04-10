package com.example.banking_app_java.utils;

import android.content.SharedPreferences;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import android.content.Context;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class SessionManager {
    private final SharedPreferences sharedPreferences;

    public SessionManager(Context context) {
        try {
            MasterKey masterKey = new MasterKey.Builder(context)
                    .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                    .build();

            sharedPreferences = EncryptedSharedPreferences.create(
                    context,
                    "secure_prefs",
                    masterKey,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException("Failed to initialize EncryptedSharedPreferences", e);
        }
    }

    public void saveToken(String token) {
        sharedPreferences.edit().putString("auth_token", token).apply();
    }

    public String getToken() {
        return sharedPreferences.getString("auth_token", null);
    }

    public void clearToken() {
        sharedPreferences.edit().remove("auth_token").apply();
    }
}
