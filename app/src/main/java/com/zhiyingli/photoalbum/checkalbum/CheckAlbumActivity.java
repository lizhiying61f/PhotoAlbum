package com.zhiyingli.photoalbum.checkalbum;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.zhiyingli.photoalbum.MainActivity;
import com.zhiyingli.photoalbum.R;
import com.zhiyingli.photoalbum.adapter.CheckAlbumAdapter;
import com.zhiyingli.photoalbum.albumpicture.AlbumPictureActivity;
import com.zhiyingli.photoalbum.bean.ImageFolder;

import java.util.List;

public class CheckAlbumActivity extends AppCompatActivity implements CheckAlbumView , AdapterView.OnItemClickListener{
    private CheckAlbumPresenter albumPresenter;
    private ListView listView;
    public static final int CHECKALBUMPICTURE = 233;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRequestPermissions();
        setContentView(R.layout.activity_check_album);
        listView = (ListView) findViewById(R.id.mlistview);
        albumPresenter = new CheckAlbumPresenterImpl(this,new CheckAlbumInteractorImpl());
    }

    private void mRequestPermissions() {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            int permission = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permission == PackageManager.PERMISSION_GRANTED){
                initAlbum();
            }else {
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1<<5);
            }
        }else {
            initAlbum();
        }
    }

    private void initAlbum() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        albumPresenter.onResume();
    }

    @Override
    public void setAlbumItems(List<ImageFolder> items) {
//        Toast.makeText(this,"------"+items.size(),Toast.LENGTH_SHORT).show();
        CheckAlbumAdapter albumAdapter = new CheckAlbumAdapter(items,CheckAlbumActivity.this);
        listView.setAdapter(albumAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    protected void onDestroy() {
        albumPresenter.onDestroy();
        super.onDestroy();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1<<5) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setMessage("请求访问相册权限")
                        .setPositiveButton("同意", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1<<5);
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create();
                dialog.dismiss();
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        int count = getIntent().getIntExtra(MainActivity.CHECKPICTURENUMBER, 9);
        Intent intent = new Intent(CheckAlbumActivity.this, AlbumPictureActivity.class);
        intent.putExtra(MainActivity.CHECKPICTURENUMBER,count);
        startActivityForResult(intent,CHECKALBUMPICTURE);
    }
}
