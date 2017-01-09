package com.zhiyingli.photoalbum;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.zhiyingli.photoalbum.checkalbum.CheckAlbumActivity;

public class MainActivity extends AppCompatActivity {
    private EditText editText;
    public static String CHECKPICTURENUMBER = "checkpicturenumber";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = (EditText) findViewById(R.id.edittext);
    }

    public void toCheckAlbumActivity(View v){
        if (TextUtils.isEmpty(editText.getText())){
            return;
        }
        Intent intent = new Intent(MainActivity.this,CheckAlbumActivity.class);
        intent.putExtra(CHECKPICTURENUMBER,Integer.parseInt(editText.getText().toString()));
        startActivity(intent);
    }
}

