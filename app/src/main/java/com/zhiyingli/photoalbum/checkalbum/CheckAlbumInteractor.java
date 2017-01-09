package com.zhiyingli.photoalbum.checkalbum;

import com.zhiyingli.photoalbum.bean.ImageFolder;

import java.util.List;

/**
 * Created by zhiyingli on 2016/11/21.
 */

public interface CheckAlbumInteractor {
    interface OnfinishedListener{
        void onFinished(List<ImageFolder> items);
    }

    void findAlbumItems(OnfinishedListener listener);
}
