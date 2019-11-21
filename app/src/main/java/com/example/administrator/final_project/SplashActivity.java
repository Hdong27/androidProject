package com.example.administrator.final_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

/**
 * Created by Lenovo on 2017-06-09.
 */
public class SplashActivity extends AppCompatActivity {

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps_splash);

        ImageView iv = (ImageView) findViewById(R.id.ivLogo2);

        GlideDrawableImageViewTarget ImageViewTarget = new GlideDrawableImageViewTarget(iv);
        Glide.with(this).load(R.raw.loading).into(iv);


        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        }, 3000);


    }
}  //아두이노 관련 코딩

