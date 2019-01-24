package com.linkdevelopment.newsfeeds.Interactor;

import com.linkdevelopment.newsfeeds.Network.Models.Article;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by nsamir on 1/23/2019.
 */
public interface ArticleNewtworkInteractor {

    Single<List<Article>> getArticles();
}
