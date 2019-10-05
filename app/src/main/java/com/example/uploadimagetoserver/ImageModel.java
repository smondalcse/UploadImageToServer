package com.example.uploadimagetoserver;

/**
 * Created by sanat on 5/2/2018.
 */

public class ImageModel {

    private String id;
    private String img_title;
    private String image_name;

    public ImageModel(String id, String img_title, String image_name) {
        this.id = id;
        this.img_title = img_title;
        this.image_name = image_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg_title() {
        return img_title;
    }

    public void setImg_title(String img_title) {
        this.img_title = img_title;
    }

    public String getImage_name() {
        return image_name;
    }

    public void setImage_name(String image_name) {
        this.image_name = image_name;
    }
}
