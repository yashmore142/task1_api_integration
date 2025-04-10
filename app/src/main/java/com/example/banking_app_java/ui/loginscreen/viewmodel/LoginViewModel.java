package com.example.banking_app_java.ui.loginscreen.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.banking_app_java.network.Repository;
import com.example.banking_app_java.ui.loginscreen.model.LoginRequest;
import com.example.banking_app_java.ui.loginscreen.model.LoginResponse;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginViewModel extends ViewModel {
    private  Repository repository = new Repository();
    private final CompositeDisposable disposable = new CompositeDisposable();
    public MutableLiveData<LoginResponse> loginResponse = new MutableLiveData<>();
    public MutableLiveData<String> errorData = new MutableLiveData<>("");
    public void loginUser(LoginRequest request) {
        disposable.add(
                repository.getLogin(request)
                        .subscribeOn(Schedulers.io())
                        .subscribeWith(new DisposableObserver<LoginResponse>() {
                            @Override
                            public void onNext(LoginResponse response) {
                                loginResponse.postValue(response);
                            }

                            @Override
                            public void onError(Throwable e) {
                                errorData.postValue(e.getMessage());
                                Log.i("errorHTTP", e.getMessage()+ e.getCause());
                            }

                            @Override
                            public void onComplete() {
                            }
                        })
        );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
