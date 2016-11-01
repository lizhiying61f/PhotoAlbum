package com.zhiyingli.photoalbum;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] strs = {"1","2","3","4","5","6"};
        String[] strs1 = {"1","2","3"};
        String[] strs2 = {"4","5","6"};

        HashMap<String,String> map = new HashMap<>();
        HashMap<String,String> map1 = new HashMap<>();
        HashMap<String,String> map2 = new HashMap<>();
        for (String str:strs){
            map.put(str,"");
        }
        for (String str: strs1){
            map1.put(str,str);
        }

        map.putAll(map1);

        for (Map.Entry entry:map.entrySet()){
            Log.e("TAG","key------"+entry.getKey());
            Log.e("TAG","value-------"+entry.getValue());
        }

        for (String str:strs2){
            map2.put(str,str);
        }
        map.putAll(map2);
        for (Map.Entry entry:map.entrySet()){
            Log.e("TAG","key======="+entry.getKey());
            Log.e("TAG","value======="+entry.getValue());
        }


    }
}
