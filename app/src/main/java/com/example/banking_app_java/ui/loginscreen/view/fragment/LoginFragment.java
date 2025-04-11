package com.example.banking_app_java.ui.loginscreen.view.fragment;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.biometric.BiometricManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.example.banking_app_java.R;
import com.example.banking_app_java.databinding.FragmentLoginBinding;
import com.example.banking_app_java.databinding.SetupBiometryDialogBinding;
import com.example.banking_app_java.ui.detailsscreen.view.fragment.TrasactionFragment;
import com.example.banking_app_java.ui.loginscreen.model.LoginRequest;
import com.example.banking_app_java.ui.loginscreen.model.LoginResponse;
import com.example.banking_app_java.ui.loginscreen.viewmodel.LoginViewModel;
import com.example.banking_app_java.utils.SessionManager;


public class LoginFragment extends Fragment {
    LoginViewModel loginViewModel = new LoginViewModel();
    private SessionManager sessionManager;
    private FragmentLoginBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        sessionManager = new SessionManager(requireContext());
        observer();

        binding.tilUserName.setHint("User Name");
        binding.btnSignIn.setOnClickListener(view -> {
            if (binding.tilUserName.getText().isEmpty()) {
                binding.tilUserName.setError(getString(R.string.enter_user_name));
            } else if (binding.tilPassword.getEditText().getText().toString().equals("")) {
                binding.tilPassword.getEditText().setError(getString(R.string.enter_password));
            }else {
                LoginRequest loginRequest = new LoginRequest();
                loginRequest.setUsername(binding.tilUserName.getText().toString());
                loginRequest.setPassword(binding.tilPassword.getEditText().getText().toString());
                loginViewModel.loginUser(loginRequest);
            }
        });
        return binding.getRoot();
    }
    private void setupBiometricDialog(){
        SetupBiometryDialogBinding setupBiometryDialogBinding = SetupBiometryDialogBinding.inflate(LayoutInflater.from(requireContext()));

        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(setupBiometryDialogBinding.getRoot());
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        setupBiometryDialogBinding.btnBiometric.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setupBiometric();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void observer() {
        loginViewModel.loginResponse.observe(getViewLifecycleOwner(), new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                if (loginResponse.getSuccess()) {
                    setupBiometricDialog();
                    sessionManager.saveToken(loginResponse.getToken());
                }
            }
        });
        loginViewModel.errorData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.i("error", s);
            }
        });
    }

    private void setupBiometric() {
        BiometricManager biometricManager = BiometricManager.from(requireContext());
        int authStatus = biometricManager.canAuthenticate(
                BiometricManager.Authenticators.BIOMETRIC_WEAK | BiometricManager.Authenticators.DEVICE_CREDENTIAL);

        if (authStatus == BiometricManager.BIOMETRIC_SUCCESS) {

            requireActivity().getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).replace(R.id.llmain_container,
                            new TrasactionFragment())
                    .commit();
        } else {
            Toast.makeText(requireContext(), "Biometric not supported or not set up", Toast.LENGTH_LONG).show();
        }
    }
}
