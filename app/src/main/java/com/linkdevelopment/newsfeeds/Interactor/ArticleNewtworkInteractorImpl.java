package com.linkdevelopment.newsfeeds.Interactor;

import com.linkdevelopment.newsfeeds.Network.ApiService;
import com.linkdevelopment.newsfeeds.Network.Models.Article;
import com.linkdevelopment.newsfeeds.Network.Models.ArticlesResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

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
    public Single<List<Article>> getArticles() {
        return apiService.getArticles()
                .map(ArticlesResponse::copyFromResponse);
        //ToDo: Save data in database and cache
                /*.doOnSuccess(databaseInteractor::saveData)
                .doOnSuccess(memoryInteractor::saveData);*/
    }
}
