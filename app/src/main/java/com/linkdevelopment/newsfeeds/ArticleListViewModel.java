package com.linkdevelopment.newsfeeds;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import android.view.View;

import com.linkdevelopment.newsfeeds.Interactor.ArticleNewtworkInteractor;
import com.linkdevelopment.newsfeeds.Interactor.ArticleNewtworkInteractorImpl;
import com.linkdevelopment.newsfeeds.Network.ApiService;
import com.linkdevelopment.newsfeeds.Network.Models.Article;
import com.linkdevelopment.newsfeeds.Network.NetworkingModule;
import com.linkdevelopment.newsfeeds.Repository.ArticlesListRepository;
import com.linkdevelopment.newsfeeds.Repository.ArticlesListRepositoryImpl;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by nsamir on 1/23/2019.
 */
public class ArticleListViewModel extends AndroidViewModel {

    Observable<List<Article>> articlesList;

    @Inject
    ArticlesListRepository repository;

    View view;

    @Inject
    public ArticleListViewModel(@NonNull Application application) {
        super(application);
    }

    public Observable<List<Article>> getArticlesList() {
        //ToDo: Resolve Injection Error
        //Replace by injection
        ApiService apiService = NetworkingModule.provideApiService(NetworkingModule.provideRetrofit());
        ArticleNewtworkInteractor articleNewtworkInteractor = new ArticleNewtworkInteractorImpl(apiService);
        repository = new ArticlesListRepositoryImpl(articleNewtworkInteractor);

        if (articlesList == null) {
            articlesList = repository.getArticlesList();
        }
        return articlesList;
    }

}
