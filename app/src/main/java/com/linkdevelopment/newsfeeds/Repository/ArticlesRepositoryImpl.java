package com.linkdevelopment.newsfeeds.Repository;

import android.arch.lifecycle.LiveData;

import com.linkdevelopment.newsfeeds.Interactor.ArticleNewtworkInteractor;
import com.linkdevelopment.newsfeeds.Network.Models.Article;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by nsamir on 1/23/2019.
 */
public class ArticlesRepositoryImpl implements ArticlesRepository {

    ArticleNewtworkInteractor networkInteractor;
    LiveData<List<Article>> articleList;

    @Inject
    public ArticlesRepositoryImpl(ArticleNewtworkInteractor networkInteractor) {
        this.networkInteractor = networkInteractor;
        articleList = networkInteractor.getArticles();
    }

    public LiveData<List<Article>> getArticlesList() {
        return articleList;
    }
}
