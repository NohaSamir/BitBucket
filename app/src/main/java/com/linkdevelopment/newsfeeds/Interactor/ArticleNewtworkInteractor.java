package com.linkdevelopment.newsfeeds.Interactor;

import android.arch.lifecycle.LiveData;

import com.linkdevelopment.newsfeeds.Network.Models.Article;

import java.util.List;

/**
 * Created by nsamir on 1/23/2019.
 */
public interface ArticleNewtworkInteractor {

    LiveData<List<Article>> getArticles();
}
