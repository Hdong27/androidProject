package com.example.administrator.final_project;

/**
 * Created by Lenovo on 2017-06-09.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.estimote.sdk.SystemRequirementsChecker;

public class GpsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps_main);
        setTitle("전자발찌 위치감지");
        ImageView iv = (ImageView) findViewById(R.id.ivLogo2);

        GlideDrawableImageViewTarget ImageViewTarget = new GlideDrawableImageViewTarget(iv);
        Glide.with(this).load(R.raw.loading).into(iv);

    }

    @Override
    protected void onResume() {
        super.onResume();

        //블루투스 권한 및 활성화코드
        SystemRequirementsChecker.checkWithDefaultDialogs(this);
    }
}
