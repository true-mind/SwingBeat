package com.truemind.swingbeat.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;

import com.truemind.swingbeat.R;

public class MpPlayer extends Service {
    MediaPlayer mp;
    public MpPlayer() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mp = MediaPlayer.create(this, R.raw.wikiloops_jam_my_momma_told_me_fade_out);
        mp.start();
    }

    /*
    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        mp = MediaPlayer.create(this, R.raw.wikiloops_jam_my_momma_told_me_fade_out);
        mp.start();
    }
    */

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.stop();
        mp.release();
    }
}

