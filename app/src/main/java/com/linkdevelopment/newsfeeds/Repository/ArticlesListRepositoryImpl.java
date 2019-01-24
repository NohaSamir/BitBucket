package com.linkdevelopment.newsfeeds.Repository;

import com.linkdevelopment.newsfeeds.Interactor.ArticleNewtworkInteractor;
import com.linkdevelopment.newsfeeds.Network.Models.Article;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by nsamir on 1/23/2019.
 */
public class ArticlesListRepositoryImpl implements ArticlesListRepository{

    ArticleNewtworkInteractor networkInteractor;

    @Inject
    public ArticlesListRepositoryImpl(ArticleNewtworkInteractor networkInteractor) {
        this.networkInteractor = networkInteractor;
    }

    public Observable<List<Article>> getArticlesList() {
        return networkInteractor.getArticles().toObservable();
    }
}
