package com.linkdevelopment.newsfeeds.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.linkdevelopment.newsfeeds.Interactor.ArticleNewtworkInteractor;
import com.linkdevelopment.newsfeeds.Interactor.ArticleNewtworkInteractorImpl;
import com.linkdevelopment.newsfeeds.Network.ApiService;
import com.linkdevelopment.newsfeeds.Network.Models.Article;
import com.linkdevelopment.newsfeeds.Network.NetworkingModule;
import com.linkdevelopment.newsfeeds.Repository.ArticlesRepository;
import com.linkdevelopment.newsfeeds.Repository.ArticlesRepositoryImpl;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by nsamir on 1/23/2019.
 */
public class ArticleListViewModel extends AndroidViewModel {

    LiveData<List<Article>> articlesList;
    ArticlesRepository repository;

    @Inject
    ArticleListViewModel(@NonNull Application application) {
        super(application);

        //ToDo: Resolve Injection Error
        //Will Replaced by injection
        ApiService apiService = NetworkingModule.provideApiService(NetworkingModule.provideRetrofit());
        ArticleNewtworkInteractor articleNewtworkInteractor = new ArticleNewtworkInteractorImpl(apiService);
        repository = new ArticlesRepositoryImpl(articleNewtworkInteractor);

        if (articlesList == null) {
            articlesList = repository.getArticlesList();
        }
    }

    public LiveData<List<Article>> getArticlesList() {
        return articlesList;
    }


}
