package com.linkdevelopment.newsfeeds.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.linkdevelopment.newsfeeds.BuildConfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
public class NetworkingModule {

    public static final String baseUrl = "https://newsapi.org/v1/";

    @Singleton
    @Provides
    public static Retrofit provideRetrofit() {
        //Can register type adapters here
        Gson gson = new GsonBuilder().create();

        //Logging
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY); // set your desired log level
            //Add Authorization to request
            clientBuilder.addInterceptor(logging);
        }

        clientBuilder.addInterceptor(new QueryParameterInterceptor("apiKey", BuildConfig.ApiKey));
        clientBuilder.addInterceptor(new QueryParameterInterceptor("source", "the-next-web"));

        OkHttpClient client = clientBuilder.build();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(client)
                .build();
    }

    @Singleton
    @Provides
    public static ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }


}
