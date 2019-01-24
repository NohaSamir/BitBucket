package com.linkdevelopment.newsfeeds.Views;

import com.linkdevelopment.newsfeeds.Network.Models.Article;

import java.util.List;

/**
 * Created by nsamir on 1/24/2019.
 */
public interface ArticleListContract {

    void loadArticles();

    void showArticleList(List<Article> articles);

    void openArticleDetails(Article article);

    void showError();


    void bindNavigationDrawer();

    void searchInList(String text);


    void onExploreClicked();

    void onChatClicked();

    void onGalleryClicked();

    void onWishListClicked();

    void onMagazinClicked();

}
