package com.example.banking_app_java.ui.splashscreen.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.banking_app_java.R;
import com.example.banking_app_java.databinding.FragmentSplashScreenBinding;
import com.example.banking_app_java.ui.detailsscreen.view.fragment.TrasactionFragment;
import com.example.banking_app_java.ui.fingurprintandpin.view.PinAndBiometryFragment;
import com.example.banking_app_java.ui.loginscreen.view.fragment.LoginFragment;
import com.example.banking_app_java.utils.SessionManager;

import java.util.concurrent.Executor;

public class SplashScreenFragment extends Fragment {

    private FragmentSplashScreenBinding binding;

    private SessionManager sessionManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSplashScreenBinding.inflate(inflater, container, false);
        sessionManager = new SessionManager(requireContext());
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String token = sessionManager.getToken();

                if (token == null || token.isEmpty()) {
                    requireActivity().getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.llmain_container,
                                    new LoginFragment())
                            .commit();
                } else {
                    requireActivity().getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.llmain_container,
                                    new PinAndBiometryFragment())
                            .commit();
                }
            }
        }, 2000);
        return binding.getRoot();
    }



}