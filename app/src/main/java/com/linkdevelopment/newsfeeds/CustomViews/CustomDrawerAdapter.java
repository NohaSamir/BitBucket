package com.linkdevelopment.newsfeeds.CustomViews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.linkdevelopment.newsfeeds.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nsamir on 1/24/2019.
 */
public class CustomDrawerAdapter extends RecyclerView.Adapter<CustomDrawerAdapter.DrawerItemHolder> {

    private Context context;
    private List<DrawerItem> drawerItemList;
    private int layoutResID;
    private DrawerItemHolder lastSelectedItem;
    private OnItemClickListener onItemClickListener;

    public CustomDrawerAdapter(Context context, int layoutResourceID, List<DrawerItem> listItems) {
        this.context = context;
        this.drawerItemList = listItems;
        this.layoutResID = layoutResourceID;
    }

    @NonNull
    @Override
    public DrawerItemHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(layoutResID, viewGroup, false);
        return new DrawerItemHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DrawerItemHolder drawerItemHolder, int position) {
        DrawerItem item = drawerItemList.get(position);
        drawerItemHolder.set(item);

        //Handle on first time , select first item
        if (position == 0) {
            drawerItemHolder.selected.setVisibility(View.VISIBLE);
            lastSelectedItem = drawerItemHolder;
        }
    }

    @Override
    public int getItemCount() {
        if (drawerItemList == null) {
            return 0;
        }
        return drawerItemList.size();
    }

    public class DrawerItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.drawer_itemName)
        TextView ItemName;
        @BindView(R.id.drawer_icon)
        ImageView icon;
        @BindView(R.id.drawer_selected)
        ImageView selected;

        DrawerItemHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void set(DrawerItem item) {
            //UI setting code
            icon.setImageDrawable(context.getResources().getDrawable(
                    item.getImgResID()));
            ItemName.setText(item.getItemName());
        }

        @Override
        public void onClick(View v) {
            if (lastSelectedItem != null)
                lastSelectedItem.selected.setVisibility(View.INVISIBLE);

            selected.setVisibility(View.VISIBLE);
            lastSelectedItem = this;

            if (onItemClickListener != null)
                onItemClickListener.onNavigationItemSelected(getAdapterPosition());
        }
    }

    public interface OnItemClickListener {
        void onNavigationItemSelected(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }
}