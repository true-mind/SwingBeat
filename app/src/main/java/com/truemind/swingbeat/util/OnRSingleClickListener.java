package com.truemind.swingbeat.util;

import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;

import com.truemind.swingbeat.Constants;

/**
 * Created by 현석 on 2017-06-02.
 */

public abstract class OnRSingleClickListener implements View.OnClickListener {

    private static final long MIN_CLICK_INTERVAL = 100;
    private long mLastClickTime;

    public abstract void onSingleClick(View v);

    @Override
    public final void onClick(View v) {
        long currentClickTime= SystemClock.uptimeMillis();
        long elapsedTime=currentClickTime-mLastClickTime;
        mLastClickTime=currentClickTime;

        if(elapsedTime<=MIN_CLICK_INTERVAL){
            return;
        }

        onSingleClick(v);
    }

}
