package com.truemind.swingbeat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.os.Build;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by 현석 on 2017-04-20.
 */
public abstract class BaseActivity extends Activity {

    /**
     * Typeface로 폰트 적용
     * (배민 주아체로 적용)
     *
     * @param views 적용을 원하는 모든 TextView
     *              ,로 구분하여 무제한 개수의 동시 적용 가능
     * */
    public void setFontToViewBold(TextView... views) {
        Typeface NanumNormal = Typeface.createFromAsset(this.getAssets(), "BMJUA_ttf.ttf");

        for (TextView view : views)
            view.setTypeface(NanumNormal);
    }

    /**
     * Typeface로 폰트 적용
     * (배민 도현체로 적용)
     *
     * @param views 적용을 원하는 모든 TextView
     *              ,로 구분하여 무제한 개수의 동시 적용 가능
     * */
    public void setFontToViewBold2(TextView... views) {
        Typeface NanumNormal = Typeface.createFromAsset(this.getAssets(), "BMDOHYEON_ttf.ttf");

        for (TextView view : views)
            view.setTypeface(NanumNormal);
    }


    public void goBTSetting() {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.MAIN");
        intent.setClassName("com.android.settings", "com.android.settings.bluetooth.BluetoothSettings");
        startActivity(intent);
    }

    /**
     * 현재 context를 불러오기
     * @return activity
     * */
    public Activity getContext()
    {
        return this;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN) {
            int keyCode = event.getKeyCode();
            if(keyCode == KeyEvent.KEYCODE_DPAD_LEFT) {
                onkey1();
            }
            else if(keyCode == KeyEvent.KEYCODE_DPAD_RIGHT) {
                onkey2();
            }
            else if(keyCode == KeyEvent.KEYCODE_DPAD_UP) {
                onkey3();
            }
            else if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
                onkey4();
            }
            else if(keyCode == KeyEvent.KEYCODE_ENTER) {
                onkey5();
            }
            else if(keyCode == KeyEvent.KEYCODE_BACK){
                onKeyBack();
            }else if(keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
                AudioManager mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_LOWER,
                        AudioManager.FLAG_SHOW_UI);
            }else if(keyCode == KeyEvent.KEYCODE_VOLUME_UP){
                AudioManager mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
                mAudioManager.adjustStreamVolume(AudioManager.STREAM_MUSIC,
                        AudioManager.ADJUST_RAISE,
                        AudioManager.FLAG_SHOW_UI);
            }
            return true;
        }
        return false;
    }

    public abstract void onkey1();
    public abstract void onkey2();
    public abstract void onkey3();
    public abstract void onkey4();
    public abstract void onkey5();
    public abstract void onKeyBack();

}
