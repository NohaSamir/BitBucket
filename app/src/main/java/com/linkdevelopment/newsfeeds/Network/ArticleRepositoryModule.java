package com.linkdevelopment.newsfeeds.Network;

import com.linkdevelopment.newsfeeds.Interactor.ArticleNewtworkInteractor;
import com.linkdevelopment.newsfeeds.Interactor.ArticleNewtworkInteractorImpl;
import com.linkdevelopment.newsfeeds.Repository.ArticlesListRepository;
import com.linkdevelopment.newsfeeds.Repository.ArticlesListRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ArticleRepositoryModule {

    @Provides
    @Singleton
    public ArticleNewtworkInteractor provideArticleNetwork(ArticleNewtworkInteractorImpl networkInteractor) {
        return networkInteractor;
    }

    //ToDo:
    /*@Provides
    @Singleton
    public ArticleMemoryInteractor provideArticleMemory(ArticleMemoryInteractorImpl memoryInteractor) {
        return memoryInteractor;
    }

    @Provides
    public ArticleDatabaseInteractor provideArticleDatabase(ArticleDatabaseInteractorImpl databaseInteractor) {
        return databaseInteractor;
    }*/

    @Provides
    @Singleton
    public ArticlesListRepository provideArticleRepository(ArticleNewtworkInteractor networkInteractor/*,
                                                           ArticleDatabaseInteractor databaseInteractor,
                                                           ArticleMemoryInteractor ArticleMemoryInteractor*/) {
        return new ArticlesListRepositoryImpl( networkInteractor);
    }
}
