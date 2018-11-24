package com.itparkbynipun.imageloader;

/**
 * Created by agarw on 11/23/2018.
 */

public class getImage {
    String name;
    String imageURL;

    public getImage(){

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public getImage(String name, String imageURL) {
        this.name = name;
        this.imageURL = imageURL;
    }


}
