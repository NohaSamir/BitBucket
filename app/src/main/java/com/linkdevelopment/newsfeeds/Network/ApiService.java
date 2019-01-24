package com.linkdevelopment.newsfeeds.Network;

import com.linkdevelopment.newsfeeds.Network.Models.ArticlesResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by nsamir on 1/23/2019.
 */
public interface ApiService {

    //ToDo: save api key
    @GET("articles")
    Single<ArticlesResponse> getArticles();
}
