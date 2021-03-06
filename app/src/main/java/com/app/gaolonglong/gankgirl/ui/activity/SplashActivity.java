package com.app.gaolonglong.gankgirl.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.app.gaolonglong.gankgirl.R;
import com.app.gaolonglong.gankgirl.model.gank.GanHuo;
import com.app.gaolonglong.gankgirl.retrofit.gank.GankRetrofit;
import com.app.gaolonglong.gankgirl.retrofit.gank.GankService;
import com.app.gaolonglong.gankgirl.ui.activity.home.HomeActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {
    private ImageView image;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //全屏显示
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.welcome_layout);

        init();
    }

    private void init() {
        image = (ImageView) findViewById(R.id.welcome_image);

        GankRetrofit.getRetrofit()
                .create(GankService.class)
                .getGanHuo("福利",1,1)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GanHuo>() {
                    @Override
                    public void onCompleted() {
                        animateImage();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Glide.with(SplashActivity.this)
                                .load(R.drawable.wall_picture)
                                .into(image);
                        animateImage();
                    }

                    @Override
                    public void onNext(GanHuo ganHuo) {
                        Glide.with(SplashActivity.this)
                                .load(ganHuo.getResults().get(0).getUrl())
                                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                                .into(image);
                    }
                });

    }

    private void animateImage() {
        //缩放动画
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f,1.1f,1.0f,1.1f,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(2500);
        image.startAnimation(scaleAnimation);

        //缩放动画监听
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                startActivity(new Intent(SplashActivity.this,HomeActivity.class));

                overridePendingTransition(0,0);

                SplashActivity.this.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
