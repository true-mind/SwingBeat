package com.truemind.swingbeat.util;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import com.truemind.swingbeat.R;

/**
 * Created by 현석 on 2017-06-01.
 */

public class NewMediaPlayer {

    public MediaPlayer mp;

    public NewMediaPlayer(Context context){
        this.mp = MediaPlayer.create(context, R.raw.wikiloops_jam_my_momma_told_me_fade_out);
    }

    public void playMedia(){
        mp.start();
    }

    public void destroyMedia() {
        mp.stop();
    }

}
