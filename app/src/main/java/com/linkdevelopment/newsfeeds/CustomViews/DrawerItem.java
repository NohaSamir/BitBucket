package com.linkdevelopment.newsfeeds.CustomViews;

/**
 * Created by nsamir on 1/23/2019.
 */
public class DrawerItem {

    private String ItemName;
    private int imgResID;

    public DrawerItem(String itemName, int imgResID) {
        super();
        ItemName = itemName;
        this.imgResID = imgResID;
    }

    String getItemName() {
        return ItemName;
    }

    int getImgResID() {
        return imgResID;
    }

}