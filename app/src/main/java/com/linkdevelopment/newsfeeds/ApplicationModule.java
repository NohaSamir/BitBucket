package com.linkdevelopment.newsfeeds;

import android.content.Context;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {

    @Provides
    public Context providesContext() {
        return NewsFeedsApplication.getInstance();
    }
}
