package com.linkdevelopment.newsfeeds.Network;

import com.linkdevelopment.newsfeeds.Interactor.ArticleNewtworkInteractor;
import com.linkdevelopment.newsfeeds.Interactor.ArticleNewtworkInteractorImpl;
import com.linkdevelopment.newsfeeds.Repository.ArticlesRepository;
import com.linkdevelopment.newsfeeds.Repository.ArticlesRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ArticleRepositoryModule {

    @Provides
    @Singleton
    ArticleNewtworkInteractor provideArticleNetwork(ArticleNewtworkInteractorImpl networkInteractor) {
        return networkInteractor;
    }

    @Provides
    @Singleton
    ArticlesRepository provideArticleRepository(ArticleNewtworkInteractor networkInteractor) {
        return new ArticlesRepositoryImpl(networkInteractor);
    }
}
