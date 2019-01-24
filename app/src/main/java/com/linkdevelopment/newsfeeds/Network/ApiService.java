package com.linkdevelopment.newsfeeds.Network;

import com.linkdevelopment.newsfeeds.Network.Models.ArticlesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by nsamir on 1/23/2019.
 */
public interface ApiService {

    @GET("articles")
    Call<ArticlesResponse> getArticles();
}
