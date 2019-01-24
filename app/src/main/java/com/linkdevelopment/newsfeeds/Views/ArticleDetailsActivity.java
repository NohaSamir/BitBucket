package com.linkdevelopment.newsfeeds.Views;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.linkdevelopment.newsfeeds.Network.Models.Article;
import com.linkdevelopment.newsfeeds.R;
import com.linkdevelopment.newsfeeds.Utils.DateUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ArticleDetailsActivity extends AppCompatActivity {

    @BindView(R.id.articleImageView)
    ImageView articleImageView;

    @BindView(R.id.titleTextView)
    TextView titleTextView;

    @BindView(R.id.authorTextView)
    TextView authorTextView;

    @BindView(R.id.dateTextView)
    TextView dateTextView;

    @BindView(R.id.bodyTextView)
    TextView bodyTextView;

    @BindView(R.id.websiteButton)
    Button websiteButton;

    private Article mArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent.hasExtra(ArticlesListActivity.ARTICLE)) {
            mArticle = intent.getParcelableExtra(ArticlesListActivity.ARTICLE);
            bindArticleDetails(mArticle);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            onSearchClicked();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void onSearchClicked() {
        Toast.makeText(this, R.string.search, Toast.LENGTH_SHORT).show();
    }

    private void bindArticleDetails(Article article) {
        if (!TextUtils.isEmpty(article.getUrlToImage())) {
            Glide.with(this)
                    .load(article.getUrlToImage())
                    .into(articleImageView);
        }

        titleTextView.setText(article.getTitle());
        dateTextView.setText(DateUtils.formatDate(article.getPublishedAt()));
        authorTextView.setText(getString(R.string.by, article.getAuthor()));
        bodyTextView.setText(article.getDescription());
    }

    @OnClick(R.id.websiteButton)
    void openWebsite()
    {
        String url = mArticle.getUrl();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(browserIntent);
    }
}
