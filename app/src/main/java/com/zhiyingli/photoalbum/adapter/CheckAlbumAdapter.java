package com.zhiyingli.photoalbum.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhiyingli.photoalbum.R;
import com.zhiyingli.photoalbum.bean.ImageFolder;

import java.io.File;
import java.util.List;

/**
 * Created by zhiyingli on 2016/11/22.
 */

public class CheckAlbumAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<ImageFolder> imageFolders;
    private Context mContext;

    public CheckAlbumAdapter(List<ImageFolder> imageFolders, Context context) {
        this.imageFolders = imageFolders;
        inflater = LayoutInflater.from(context);
        mContext = context;
    }

    @Override
    public int getCount() {
        return imageFolders.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_checkalbum, null);
            holder.icon = (ImageView) convertView.findViewById(R.id.checkalbum_icon);
            holder.title = (TextView) convertView.findViewById(R.id.checkalbum_title);
            holder.size = (TextView) convertView.findViewById(R.id.checkalbum_size);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        ImageFolder imageFolder = imageFolders.get(position);

        try {
            if (imageFolder != null && !TextUtils.isEmpty(imageFolder.getFirstImagePath())) {
                File file = new File(imageFolder.getFirstImagePath());
                if (file.exists()) {
                    Glide.with(mContext)
                            .load(imageFolder.getFirstImagePath())
//                            .placeholder(R.drawable.image_default)
//                            .error(R.drawable.image_default)
                            .into(holder.icon);
                } else {
//                    holder.icon.setImageResource(R.drawable.sort_empty);
                }

                holder.size.setText(imageFolder.images.size() + " 张");
                holder.title.setText(imageFolder.getFolderName());
            }
        } catch (NullPointerException e) {

        }


        return convertView;
    }

    static class ViewHolder {
        ImageView icon;
        TextView title;
        TextView size;
    }
}
