package com.linkdevelopment.newsfeeds;

import com.linkdevelopment.newsfeeds.Network.ArticleRepositoryModule;
import com.linkdevelopment.newsfeeds.Network.NetworkingModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {
        ApplicationModule.class,
        NetworkingModule.class,
        ArticleRepositoryModule.class})
@Singleton
public interface ApplicationComponent {
    void inject(NewsFeedsApplication application);
}
