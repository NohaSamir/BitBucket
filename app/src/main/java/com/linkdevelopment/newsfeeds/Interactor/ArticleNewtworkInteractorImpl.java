package com.linkdevelopment.newsfeeds.Interactor;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.linkdevelopment.newsfeeds.Network.ApiService;
import com.linkdevelopment.newsfeeds.Network.Models.Article;
import com.linkdevelopment.newsfeeds.Network.Models.ArticlesResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nsamir on 1/23/2019.
 */
public class ArticleNewtworkInteractorImpl implements ArticleNewtworkInteractor {

    ApiService apiService;

    @Inject
    public ArticleNewtworkInteractorImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public LiveData<List<Article>> getArticles() {

        MutableLiveData<List<Article>> data = new MutableLiveData<>();
        apiService.getArticles().enqueue(new Callback<ArticlesResponse>() {
            @Override
            public void onResponse(@NonNull Call<ArticlesResponse> call, @NonNull Response<ArticlesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    data.setValue(response.body().getArticles());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArticlesResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
