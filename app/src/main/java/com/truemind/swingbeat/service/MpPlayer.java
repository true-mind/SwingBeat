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
    public int onStartCommand(Intent intent, int flags, int startId) {
        mp = MediaPlayer.create(this, R.raw.wikiloops_jam_my_momma_told_me_fade_out);
        mp.start();
        return super.onStartCommand(intent, START_NOT_STICKY, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mp.stop();
        mp.release();
    }
}

