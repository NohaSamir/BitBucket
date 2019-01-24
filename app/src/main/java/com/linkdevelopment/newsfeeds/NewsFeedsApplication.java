package com.linkdevelopment.newsfeeds;

import android.app.Application;

/**
 * Created by nsamir on 1/23/2019.
 */
public class NewsFeedsApplication extends Application {

    private static ApplicationComponent applicationComponent;

    private static NewsFeedsApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        injectDependencies();
        instance = this;
    }

    private void injectDependencies() {
        applicationComponent = DaggerApplicationComponent.builder().build();
        applicationComponent.inject(this);
    }

    public static NewsFeedsApplication getInstance() {
        return instance;
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
