package com.example.banking_app_java.ui.detailsscreen.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.banking_app_java.network.Repository;
import com.example.banking_app_java.ui.detailsscreen.model.TransactionResponse;

import java.util.ArrayList;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DetailsViewModel extends ViewModel {
    private Repository repository = new Repository();
    private final CompositeDisposable disposable = new CompositeDisposable();
    public MutableLiveData<ArrayList<TransactionResponse>> transactionResponse = new MutableLiveData<>();
    public MutableLiveData<String> errorData = new MutableLiveData<>("");

    public void getTransaction(String request) {
        disposable.add(
                repository.getTransaction(request)
                        .subscribeOn(Schedulers.io())
                        .subscribeWith(new DisposableObserver<ArrayList<TransactionResponse>>() {
                            @Override
                            public void onNext(ArrayList<TransactionResponse> response) {
                                transactionResponse.postValue(response);
                            }

                            @Override
                            public void onError(Throwable e) {
                                errorData.postValue(e.getMessage());
                                Log.i("errorHTTP", e.getMessage() + e.getCause());
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
