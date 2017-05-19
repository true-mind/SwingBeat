package com.truemind.swingbeat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.truemind.swingbeat.BaseActivity;
import com.truemind.swingbeat.R;

/**
 * Created by 현석 on 2017-05-18.
 */

public class IntroActivity extends BaseActivity {

    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        ImageView titleLogo = (ImageView)findViewById(R.id.titleLogo);
        Animation animation = AnimationUtils.loadAnimation(getContext(),R.anim.alpha);
        titleLogo.startAnimation(animation);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(IntroActivity.this, GateActivity.class);
                startActivity(intent);
                finish();

            }
        }, 3000);

    }
}