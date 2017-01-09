package com.zhiyingli.photoalbum.bean;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiyingli on 2016/11/21.
 */

public class ImageFolder implements Serializable {
    public List<ImageItem> images = new ArrayList<>();
    private String dir;
    private String firstImagePath;
    private String folderName;


    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
        int lastIndex = this.dir.lastIndexOf("/");
        this.folderName = this.dir.substring(lastIndex + 1);
    }

    public String getFirstImagePath() {
        if (TextUtils.isEmpty(firstImagePath)){
            if (images != null && images.size()>0){
                firstImagePath = images.get(0).getPath();
            }
        }
        return firstImagePath;
    }

    public void setFirstImagePath(String firstImagePath) {
        this.firstImagePath = firstImagePath;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
}
