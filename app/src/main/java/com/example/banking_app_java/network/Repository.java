package com.example.banking_app_java.network;

import com.example.banking_app_java.ui.detailsscreen.model.TransactionResponse;
import com.example.banking_app_java.ui.loginscreen.model.LoginRequest;
import com.example.banking_app_java.ui.loginscreen.model.LoginResponse;

import java.util.ArrayList;

import io.reactivex.Observable;

public class Repository {
    private ApiInterface mApiService;

    public Repository() {
        mApiService = new ApiClient().getClient().create(ApiInterface.class);
    }

    public Observable<LoginResponse> getLogin(LoginRequest loginRequest) {
        return mApiService.login(loginRequest);
    }

    public Observable<ArrayList<TransactionResponse>> getTransaction(String token) {
        return mApiService.getTransaction(token);
    }

}
