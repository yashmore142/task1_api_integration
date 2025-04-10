package com.example.banking_app_java.ui.detailsscreen.view.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.banking_app_java.R;
import com.example.banking_app_java.databinding.FragmentTrasactionBinding;
import com.example.banking_app_java.ui.detailsscreen.view.adapter.TransactionAdapter;
import com.example.banking_app_java.ui.detailsscreen.viewmodel.DetailsViewModel;
import com.example.banking_app_java.ui.loginscreen.view.fragment.LoginFragment;
import com.example.banking_app_java.utils.SessionManager;

public class TrasactionFragment extends Fragment {
    private FragmentTrasactionBinding binding;
    SessionManager sessionManager;
    DetailsViewModel viewModel = new DetailsViewModel();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTrasactionBinding.inflate(inflater, container, false);
        sessionManager = new SessionManager(requireContext());
        viewModel.getTransaction(sessionManager.getToken());
        binding.btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sessionManager.clearToken();
                requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.llmain_container,
                        new LoginFragment()).commit();
            }
        });
        observer();
        return binding.getRoot();
    }

    private void observer() {
        viewModel.transactionResponse.observe(getViewLifecycleOwner(), transactionResponse -> {
            TransactionAdapter transactionAdapter = new TransactionAdapter(transactionResponse);
            binding.rvTransaction.setAdapter(transactionAdapter);
        });

    }

}