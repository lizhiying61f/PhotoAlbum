package com.zhiyingli.photoalbum.checkalbum;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;
import android.text.TextUtils;

import com.zhiyingli.photoalbum.AppUtils;
import com.zhiyingli.photoalbum.bean.ImageFolder;
import com.zhiyingli.photoalbum.bean.ImageItem;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zhiyingli on 2016/11/21.
 */

public class CheckAlbumInteractorImpl implements CheckAlbumInteractor{
    private ImageFolder currentImageFolder;
    private ImageFolder imageAll;
    private ContentResolver mContentResolver;
    private ArrayList<ImageFolder> mDirPaths = new ArrayList<>();
    private HashMap<String, Integer> tmpDir = new HashMap<>();
    private List<ImageItem> imageList = new ArrayList<>();
    @Override
    public void findAlbumItems(OnfinishedListener listener) {
        listener.onFinished(creatAlbumList());
    }

    private List<ImageFolder> creatAlbumList() {
        mContentResolver = AppUtils.getApplication().getContentResolver();
        imageAll = new ImageFolder();
        imageAll.setDir("全部照片");
        currentImageFolder = imageAll;
        mDirPaths.add(imageAll);
        getOthers();
        return mDirPaths;
    }

    private void getOthers() {
        try {
            Cursor mCursor = mContentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                    null, MediaStore.Images.Media.MIME_TYPE + " like ?",
                    new String[]{"image/%"},
                    MediaStore.Images.Media.DATE_MODIFIED + " DESC");
            if (mCursor != null) {
                if (mCursor.moveToFirst()) {
                    int mDate = mCursor.getColumnIndex(MediaStore.Images.Media.DATA);
                    int mId = mCursor.getColumnIndex(MediaStore.Images.Media._ID);
                    String path;
                    do {
                        path = mCursor.getString(mDate);
                        if (TextUtils.isEmpty(path)) {
                            continue;
                        }
                        if (!path.endsWith(".gif")) {
                            int id = mCursor.getInt(mId);
                            ImageItem imageItem = new ImageItem();
                            imageItem.setId(id);
                            imageItem.setPath(path);
                            imageAll.images.add(imageItem);
                            File parentFile = new File(path).getParentFile();
                            if (parentFile == null) {
                                continue;
                            }
                            ImageFolder imageFolder = null;
                            String dirPath = parentFile.getAbsolutePath();
                            if (!tmpDir.containsKey(dirPath)) {
                                imageFolder = new ImageFolder();
                                imageFolder.setDir(dirPath);
                                imageFolder.setFirstImagePath(path);
                                mDirPaths.add(imageFolder);
                                tmpDir.put(dirPath, mDirPaths.indexOf(imageFolder));
                            } else {
                                imageFolder = mDirPaths.get(tmpDir.get(dirPath));
                            }
                            imageFolder.images.add(imageItem);
                        }
                    } while (mCursor.moveToNext());
                }
            }
            if (mCursor != null) {
                mCursor.close();
            }
            tmpDir = null;
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
