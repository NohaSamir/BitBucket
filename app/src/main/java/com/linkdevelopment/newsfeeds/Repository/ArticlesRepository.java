package com.linkdevelopment.newsfeeds.Repository;

import android.arch.lifecycle.LiveData;

import com.linkdevelopment.newsfeeds.Network.Models.Article;

import java.util.List;

/**
 * Created by nsamir on 1/23/2019.
 */
public interface ArticlesRepository {
    LiveData<List<Article>> getArticlesList();
}
