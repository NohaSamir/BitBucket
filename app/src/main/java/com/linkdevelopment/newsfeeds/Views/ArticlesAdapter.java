package com.linkdevelopment.newsfeeds.Views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.linkdevelopment.newsfeeds.Network.Models.Article;
import com.linkdevelopment.newsfeeds.R;
import com.linkdevelopment.newsfeeds.Utils.DateUtils;
import com.linkdevelopment.newsfeeds.Utils.GlideApp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nsamir on 1/24/2019.
 */
public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticleViewHolder> {

    private final static int FADE_DURATION = 500;
    private List<Article> items;
    private Context context;
    private ItemClickListener listener;
    private List<Article> filteredList;
    private boolean filter;

    ArticlesAdapter(Context context, List<Article> items, ItemClickListener listener) {
        this.items = items;
        this.context = context;
        this.listener = listener;
        filteredList = items;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                                int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_article, parent, false);
        return new ArticleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        Article item = filteredList.get(position);
        if (!filter) setScaleAnimation(holder.itemView);
        holder.set(item);
    }

    @Override
    public int getItemCount() {
        if (filteredList == null) {
            return 0;
        }
        return filteredList.size();
    }

    class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.articleImageView)
        ImageView articleImageView;

        @BindView(R.id.titleTextView)
        TextView titleTextView;

        @BindView(R.id.authorTextView)
        TextView authorTextView;

        @BindView(R.id.dateTextView)
        TextView dateTextView;

        ArticleViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void set(Article item) {
            //UI setting code

            if (!TextUtils.isEmpty(item.getUrlToImage())) {
                GlideApp.with(context)
                        .load(item.getUrlToImage())
                        .into(articleImageView);
            }

            titleTextView.setText(item.getTitle());
            dateTextView.setText(DateUtils.formatDate(item.getPublishedAt()));
            authorTextView.setText(context.getString(R.string.by, item.getAuthor()));
        }

        @Override
        public void onClick(View v) {
            listener.getSelectedItem(filteredList.get(getAdapterPosition()));
        }
    }

    interface ItemClickListener {
        void getSelectedItem(Article article);
    }

    /**
     * Here is the key method to apply the animation
     */
    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.5f, 1.0f, 0.5f, 1.0f, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }


    void filter(String text) {
        filter = true;
        filteredList = new ArrayList<>();

        if (text.isEmpty()) {
            filteredList.addAll(items);
        } else {
            text = text.toLowerCase();
            for (Article item : items) {
                if (item.getTitle().toLowerCase().contains(text) ||
                        item.getAuthor().toLowerCase().contains(text)) {
                    filteredList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }
}