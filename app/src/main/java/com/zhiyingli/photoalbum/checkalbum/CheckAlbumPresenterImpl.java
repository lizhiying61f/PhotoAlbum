package com.zhiyingli.photoalbum.checkalbum;

import com.zhiyingli.photoalbum.bean.ImageFolder;

import java.util.List;

/**
 * Created by zhiyingli on 2016/11/21.
 */

public class CheckAlbumPresenterImpl implements CheckAlbumPresenter,CheckAlbumInteractor.OnfinishedListener{
    private CheckAlbumView checkAlbumView;
    private CheckAlbumInteractor checkAlbumInteractor;

    public CheckAlbumPresenterImpl(CheckAlbumView checkAlbumView, CheckAlbumInteractor
            checkAlbumInteractor) {
        this.checkAlbumView = checkAlbumView;
        this.checkAlbumInteractor = checkAlbumInteractor;
    }

    @Override
    public void onResume() {
        checkAlbumInteractor.findAlbumItems(this);
    }

    @Override
    public void onDestroy() {
        checkAlbumView = null;
    }

    @Override
    public void onFinished(List<ImageFolder> items) {
        if (checkAlbumView != null){
            checkAlbumView.setAlbumItems(items);
        }
    }
}
