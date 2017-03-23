package com.prettygirl_mvvm.activity;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.prettygirl_mvvm.R;
import com.prettygirl_mvvm.manager.ImageLoaderManager;
import com.prettygirl_mvvm.network.HttpServiceGenerator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {
ScaleAnimation scaleAnimation ;
    @BindView(R.id.splash)
    ImageView mSplashImg;
    private Unbinder unbinder;
    String splashUrl="http://7xi8d6.com1.z0.glb.clouddn.com/2017-03-15-17126482_115753765623699_4225645012014071808_n.jpg";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder=   ButterKnife.bind(this);
        initAnim();

        showGirl(splashUrl);
    }

    private void initAnim() {
        scaleAnimation = new ScaleAnimation(1.0f, 1.1f, 1.0f, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(4000);
        mSplashImg.startAnimation(scaleAnimation);

        //缩放动画监听
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(MainActivity.this, PicListActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
    public void showGirl(String girlUrl) {
        Glide.with(this)
                .load(girlUrl)
                .animate(scaleAnimation)
                .into(mSplashImg);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}