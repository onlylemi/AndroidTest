package com.onlylemi.test10_viewpager;

/**
 * Entity
 *
 * @author: onlylemi
 * @time: 2016-07-21 11:01
 */
public class Entity {

    private int img;
    private String imgUrl;
    private String title;

    public Entity(int img, String imgUrl, String title) {
        this.img = img;
        this.imgUrl = imgUrl;
        this.title = title;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
