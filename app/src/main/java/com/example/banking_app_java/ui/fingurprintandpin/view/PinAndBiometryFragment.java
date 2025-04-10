package com.example.banking_app_java.ui.fingurprintandpin.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.banking_app_java.R;
import com.example.banking_app_java.databinding.FragmentPinAndBiometryBinding;
import com.example.banking_app_java.databinding.FragmentSplashScreenBinding;
import com.example.banking_app_java.ui.detailsscreen.view.fragment.TrasactionFragment;
import com.example.banking_app_java.utils.SessionManager;

import java.util.concurrent.Executor;


public class PinAndBiometryFragment extends Fragment {

    FragmentPinAndBiometryBinding binding;
    SessionManager sessionManager;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPinAndBiometryBinding.inflate(inflater, container, false);
        sessionManager = new SessionManager(requireContext());
        binding.llBtnBiometric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authenticateUser();
            }
        });
        return binding.getRoot();
    }

    private void authenticateUser() {
        Executor executor = ContextCompat.getMainExecutor(requireContext());

        biometricPrompt = new BiometricPrompt(this, executor,
                new BiometricPrompt.AuthenticationCallback() {
                    @Override
                    public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        Toast.makeText(requireContext(), "Authentication succeeded", Toast.LENGTH_SHORT).show();
                        requireActivity().getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.llmain_container,
                                new TrasactionFragment()).commit();
                    }

                    @Override
                    public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                        super.onAuthenticationError(errorCode, errString);
                        Toast.makeText(requireContext(), "Error: " + errString, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                        Toast.makeText(requireContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
                    }
                });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Biometric login")
                .setSubtitle("Log in using your fingerprint")
                .setNegativeButtonText("Cancel")
                .build();

        biometricPrompt.authenticate(promptInfo);
    }

}