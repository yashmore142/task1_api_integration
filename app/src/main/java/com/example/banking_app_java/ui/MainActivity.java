package com.example.banking_app_java.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.banking_app_java.R;
import com.example.banking_app_java.databinding.ActivityMainBinding;
import com.example.banking_app_java.ui.splashscreen.view.fragment.SplashScreenFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportFragmentManager().beginTransaction().replace(R.id.llmain_container,
                new SplashScreenFragment())
                .commit();

    }

}