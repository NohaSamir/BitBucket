package com.linkdevelopment.newsfeeds;

import android.content.Context;

import dagger.Module;
import dagger.Provides;


@Module
class ApplicationModule {

    @Provides
    Context providesContext() {
        return NewsFeedsApplication.getInstance();
    }
}
