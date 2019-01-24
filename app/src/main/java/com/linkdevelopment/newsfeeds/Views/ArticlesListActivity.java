package com.linkdevelopment.newsfeeds.Views;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.linkdevelopment.newsfeeds.CustomViews.CustomDrawerAdapter;
import com.linkdevelopment.newsfeeds.CustomViews.DrawerItem;
import com.linkdevelopment.newsfeeds.Network.Models.Article;
import com.linkdevelopment.newsfeeds.R;
import com.linkdevelopment.newsfeeds.Utils.Constants;
import com.linkdevelopment.newsfeeds.ViewModel.ArticleListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesListActivity extends AppCompatActivity
        implements CustomDrawerAdapter.OnItemClickListener, ArticleListContract {

    @BindView(R.id.articleRecycler)
    RecyclerView articleRecycler;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.drawer_list)
    RecyclerView navigationList;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;

    ArticleListViewModel articleListViewModel;

    private SearchView searchView;
    private List<Article> articles;
    private ArticlesAdapter mAdapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles_list);
        context = this;
        ButterKnife.bind(this);

        articleListViewModel = ViewModelProviders.of(this).get(ArticleListViewModel.class);

        setSupportActionBar(toolbar);
        bindNavigationDrawer();

        articles = new ArrayList<>();
        mAdapter = new ArticlesAdapter(this, articles, this::openArticleDetails);
        articleRecycler.setAdapter(mAdapter);

        loadArticles();

        swipeRefreshLayout.setOnRefreshListener(this::loadArticles);
    }

    @Override
    public void loadArticles() {
        swipeRefreshLayout.setRefreshing(true);
        articleListViewModel.getArticlesList().observe(this, articles -> {
            swipeRefreshLayout.setRefreshing(false);
            showArticleList(articles);
        });
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        // Get the SearchView and set the searchable configuration
        MenuItem searchViewItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchViewItem.getActionView();

        SearchView.OnQueryTextListener queryTextListener = new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                // This is your adapter that will be filtered
                searchInList(newText);
                return true;
            }

            public boolean onQueryTextSubmit(String query) {
                // **Here you can get the value "query" which is entered in the search box.**
                hideKeyboardFrom(context, searchView);
                return true;
            }
        };
        searchView.setOnQueryTextListener(queryTextListener);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
      /*  int id = item.getItemId();

        if (id == R.id.action_search) {
            //searchView.cl
            hideKeyboardFrom(this ,searchView);
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onNavigationItemSelected(int position) {
        if (position == 0) {
            onExploreClicked();
        } else if (position == 1) {
            onChatClicked();
        } else if (position == 2) {
            onGalleryClicked();
        } else if (position == 3) {
            onWishListClicked();
        } else if (position == 4) {
            onMagazinClicked();
        }

        //I'm not know if it's required or not ?
        /*DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);*/
    }

    @Override
    public void showArticleList(List<Article> articles) {

        if (articles != null) {
            this.articles = articles;
            mAdapter = new ArticlesAdapter(this, articles, this::openArticleDetails);
            articleRecycler.setAdapter(mAdapter);
        } else
            showError();
    }

    @Override
    public void openArticleDetails(Article article) {
        Intent intent = new Intent(this, ArticleDetailsActivity.class);
        intent.putExtra(Constants.ARTICLE, article);
        startActivity(intent);
    }

    @Override
    public void showError() {
        Toast.makeText(this, R.string.error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void searchInList(String text) {
        mAdapter.filter(text);
    }

    @Override
    public void onExploreClicked() {
        Toast.makeText(this, R.string.explore, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onChatClicked() {
        Toast.makeText(this, R.string.live_chat, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGalleryClicked() {
        Toast.makeText(this, R.string.gallery, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWishListClicked() {
        Toast.makeText(this, R.string.wish_list, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMagazinClicked() {
        Toast.makeText(this, R.string.magazine, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void bindNavigationDrawer()
    {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Navigation Drawer
        ArrayList<DrawerItem> dataList = new ArrayList<>();
        dataList.add(new DrawerItem(getString(R.string.explore), R.drawable.ic_explore));
        dataList.add(new DrawerItem(getString(R.string.live_chat), R.drawable.ic_live));
        dataList.add(new DrawerItem(getString(R.string.gallery), R.drawable.ic_gallery));
        dataList.add(new DrawerItem(getString(R.string.wish_list), R.drawable.ic_wishlist));
        dataList.add(new DrawerItem(getString(R.string.magazine), R.drawable.ic_magazine));


        CustomDrawerAdapter drawerAdapter = new CustomDrawerAdapter(this, R.layout.custom_drawer_item, dataList);
        navigationList.setAdapter(drawerAdapter);
        drawerAdapter.setOnItemClickListener(this);
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
