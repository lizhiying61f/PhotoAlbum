package com.zhiyingli.photoalbum.bean;

import java.io.Serializable;

/**
 * Created by zhiyingli on 2016/11/21.
 */

public class ImageItem implements Serializable{
    private static final long serialVersionUID = -2219030294840L;
    private int id;
    private String path;
    private String name;
    private String editUrl;
    private String title;
    private boolean isSelected;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEditUrl() {
        return editUrl;
    }

    public void setEditUrl(String editUrl) {
        this.editUrl = editUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
