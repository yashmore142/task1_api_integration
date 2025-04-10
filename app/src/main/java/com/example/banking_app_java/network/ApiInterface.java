package com.example.banking_app_java.network;

import com.example.banking_app_java.ui.detailsscreen.model.TransactionResponse;
import com.example.banking_app_java.ui.loginscreen.model.LoginRequest;
import com.example.banking_app_java.ui.loginscreen.model.LoginResponse;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("login")
    Observable<LoginResponse> login(@Body LoginRequest loginRequest);

    @GET("transactions")
    Observable<ArrayList<TransactionResponse>> getTransaction(@Header("Authorization") String token);


}
