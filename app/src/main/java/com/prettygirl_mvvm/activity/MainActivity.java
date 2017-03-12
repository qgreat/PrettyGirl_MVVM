package com.prettygirl_mvvm.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.prettygirl_mvvm.R;
import com.prettygirl_mvvm.manager.ImageLoaderManager;
import com.prettygirl_mvvm.network.HttpServiceGenerator;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //懒得写application类，你们别像我这么懒哦
    }

    public void onClick(View v){
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.btTicket:
                intent.setClass(this,PicListActivity.class);
                break;
        }
        startActivity(intent);
    }
}
