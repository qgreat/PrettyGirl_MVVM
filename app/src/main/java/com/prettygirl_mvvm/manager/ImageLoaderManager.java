package com.prettygirl_mvvm.manager;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.prettygirl_mvvm.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;


public class ImageLoaderManager {
    private static ImageLoaderManager instance = new ImageLoaderManager();

    public static ImageLoaderManager getInstance() {
        return instance;
    }
private  RequestManager requestManager;
    private Picasso imageLoader;

    private ImageLoaderManager() {
    }

    public void init(Context context) {
        imageLoader = Picasso.with(context);
        requestManager =Glide.with(context);
    }

    public void displayImage(ImageView view, String url) {
        if(url == null) {
            view.setImageResource(R.mipmap.ic_launcher);
            return;
        }
        else if (TextUtils.isEmpty(url)) {//空图片显示
            view.setImageResource(R.mipmap.ic_launcher);
            return;
        }
         requestManager
                .load(url)
                .dontAnimate()
                .into(view);

                /*.placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)*/
        if (view.getWidth() == 0 && view.getHeight() == 0){

        }
        else {

        }
        if (view == null) {
            return;
        }
//        creator.into(view);
    }

}
