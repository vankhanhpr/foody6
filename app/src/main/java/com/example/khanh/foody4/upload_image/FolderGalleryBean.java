package com.example.khanh.foody4.upload_image;

import java.util.ArrayList;

/**
 * Created by Khanh on 5/16/2017.
 */

public class FolderGalleryBean {
    String folder;
    ArrayList<ImageGalleryBean> imageInFolder;

    public FolderGalleryBean(){}

    public String getFolder() {
        return folder;
    }

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public ArrayList<ImageGalleryBean> getImageInFolder() {
        return imageInFolder;
    }

    public void setImageInFolder(ArrayList<ImageGalleryBean> imageInFolder) {
        this.imageInFolder = imageInFolder;
    }
}
