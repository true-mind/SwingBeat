package com.truemind.swingbeat.ui.rhythm;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.truemind.swingbeat.BaseActivity;
import com.truemind.swingbeat.Constants;
import com.truemind.swingbeat.R;
import com.truemind.swingbeat.service.MpPlayer;
import com.truemind.swingbeat.ui.GateActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class RhythmActivity extends BaseActivity {

    private ImageView move1_1;
    private ImageView move1_2;
    private ImageView move1_3;
    private ImageView move2_1;
    private ImageView move2_2;
    private ImageView move2_3;
    private ImageView move3_1;
    private ImageView move3_2;
    private ImageView move3_3;

    private ImageView timer1;
    private ImageView timer2;
    private ImageView timer3;

    private LinearLayout button1;
    private LinearLayout button2;
    private LinearLayout button3;
    private LinearLayout buttonQuit;

    private TextView tv;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv_combo;
    private TextView tv_current;
    private TextView tv_quit;

    private Handler media_handler;
    private Handler end_handler;
    private Handler bad_handler;
    private Handler good_handler;
    private Handler perfect_handler;

    private Timer goToNext;

    private boolean[] isOnView;
    private boolean[] isGood;
    private boolean[] isPerfect;
    private boolean[] isBad;

    private ArrayList<ImageView> moveList;

    private int combo;
    private int countdown = 3;
    private long sec;
    private int beat = 999;

    private int mIndex1;
    private int mIndex2;
    private int mIndex3;

    private int maxCombo;
    private int perfect_count;
    private int good_count;
    private int bad_count;

    private int lane_width;

    private boolean notPlaying;

    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener;

    private SoundPool sound_good;
    private SoundPool sound_bad;
    private SoundPool sound_perfect;
    private SoundPool sound_good_init;

    private static int track_good;
    private static int track_bad;
    private static int track_perfect;
    private static int track_good_init;

    private Animation bounce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rhythm_new);

        initView();
        initHandler();
        initListener();
        initCountDown();
        initSound();
    }

    public SoundPool build(int para1, int para2, int para3) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return new SoundPool.Builder()
                    .build();
        } else {
            return new SoundPool(para1, para2, para3);
        }
    }

    private void initSound() {
        sound_perfect = build(1, AudioManager.STREAM_MUSIC, 0);
        sound_bad = build(1, AudioManager.STREAM_MUSIC, 0);
        sound_good = build(1, AudioManager.STREAM_MUSIC, 0);
        sound_good_init = build(1, AudioManager.STREAM_MUSIC, 0);

        track_good = sound_good.load(getContext(), R.raw.applause, 1);
        track_good_init = sound_good_init.load(getContext(), R.raw.applause_init, 1);
        track_bad = sound_bad.load(getContext(), R.raw.boos, 1);
        track_perfect = sound_perfect.load(getContext(), R.raw.burst, 1);
    }

    private void goToScore(long sec){
        goToNext = new Timer();

        goToNext.schedule(new TimerTask(){
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(combo>maxCombo){
                            maxCombo = combo;
                        }
                        Constants.RHYTHM_MAX_COMBO = maxCombo;
                        Constants.RHYTHM_BAD = bad_count;
                        Constants.RHYTHM_GOOD = good_count;
                        Constants.RHYTHM_PERFECT = perfect_count;

                        sound_good_init.stop(track_good_init);
                        sound_perfect.stop(track_perfect);
                        sound_good.stop(track_good);
                        sound_bad.stop(track_bad);

                        Intent serviceIntent = new Intent(RhythmActivity.this, MpPlayer.class);
                        stopService(serviceIntent);
                        Intent intent = new Intent(RhythmActivity.this, RhythmResult.class);
                        startActivity(intent);
                    }
                });
            }
        }, sec);
    }

    private void initCountDown() {
        maxCombo=0;
        perfect_count=0;
        good_count=0;
        bad_count=0;

        new CountDownTimer(3100, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                if(countdown==3){
                    timer1.setImageResource(R.drawable.timer_on);
                }
                else if(countdown==2){
                    timer2.setImageResource(R.drawable.timer_on);
                }else if(countdown==1){
                    timer3.setImageResource(R.drawable.timer_on);
                }
                countdown--;
            }

            @Override
            public void onFinish() {
                timer1.setVisibility(View.GONE);
                timer2.setVisibility(View.GONE);
                timer3.setVisibility(View.GONE);
                initMoves();
            }
        }.start();
    }

    private void controlMoves(int i, long time){
        if(notPlaying){
            return;
        }
        switch(i){
            case 1:
                if(mIndex1==0){
                    startAfter(0, mIndex1, time, move1_1);
                    mIndex1++;
                }else if(mIndex1==1){
                    startAfter(0, mIndex1, time, move1_2);
                    mIndex1++;
                }else{
                    startAfter(0, mIndex1, time, move1_3);
                    mIndex1=0;
                }
                break;
            case 2:
                if(mIndex2==0){
                    startAfter(1, mIndex2, time, move2_1);
                    mIndex2++;
                }else if(mIndex2==1){
                    startAfter(1, mIndex2, time, move2_2);
                    mIndex2++;
                }else{
                    startAfter(1, mIndex2, time, move2_3);
                    mIndex2=0;
                }
                break;
            case 3:
                if(mIndex3==0){
                    startAfter(2, mIndex3, time, move3_1);
                    mIndex3++;
                }else if(mIndex3==1){
                    startAfter(2, mIndex3, time, move3_2);
                    mIndex3++;
                }else{
                    startAfter(2, mIndex3, time, move3_3);
                    mIndex3=0;
                }
                break;
            default:
                break;
        }
    }

    private void initMoves(){
        media_handler.sendEmptyMessageDelayed(0, 1900);
        sec = 1400;

        controlMoves(1, sec);
        sec+=beat/2;
        controlMoves(1, sec);
        sec+=beat/2;

        controlMoves(2, sec);
        sec+=beat;
        controlMoves(3, sec);
        sec+=beat;
        controlMoves(1, sec);
        sec+=beat;
        controlMoves(2, sec);
        sec+=beat;


        controlMoves(1, sec);
        sec+=beat;
        controlMoves(3, sec);
        sec+=beat;
        controlMoves(2, sec);
        sec+=beat;
        controlMoves(3, sec);
        sec+=beat;

        controlMoves(2, sec);
        sec+=beat;
        controlMoves(3, sec);
        sec+=beat;
        controlMoves(1, sec);
        sec+=beat;
        controlMoves(2, sec);
        sec+=beat;

        controlMoves(1, sec);
        sec+=beat;
        controlMoves(3, sec);
        sec+=beat;
        controlMoves(2, sec);
        sec+=beat*2;

        //반주

        controlMoves(1, sec);
        sec+=beat/2;
        controlMoves(1, sec);
        sec+=beat;
        controlMoves(3, sec);
        sec+=beat/2;

        controlMoves(2, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat;
        controlMoves(3, sec);
        sec+=beat/2;

        controlMoves(1, sec);
        sec+=beat/2;
        controlMoves(1, sec);
        sec+=beat;
        controlMoves(3, sec);
        sec+=beat/2;

        controlMoves(2, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat;
        controlMoves(3, sec);
        sec+=beat/2;

        controlMoves(3, sec);
        sec+=beat/2;
        controlMoves(1, sec);
        sec+=beat;
        controlMoves(2, sec);
        sec+=beat/2;

        controlMoves(1, sec);
        sec+=beat/2;
        controlMoves(3, sec);
        sec+=beat;
        controlMoves(2, sec);
        sec+=beat/2;

        controlMoves(3, sec);
        sec+=beat/2;
        controlMoves(1, sec);
        sec+=beat;
        controlMoves(2, sec);
        sec+=beat/2;

        controlMoves(1, sec);
        sec+=beat/2;
        controlMoves(3, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat/2;
        controlMoves(1, sec);
        sec+=beat/2;

        //후렴

        controlMoves(3, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat/2;
        controlMoves(3, sec);
        sec+=beat/2;

        controlMoves(1, sec);
        sec+=beat;
        controlMoves(2, sec);
        sec+=beat/2;
        controlMoves(1, sec);
        sec+=beat/2;

        controlMoves(2, sec);
        sec+=beat/2;
        controlMoves(3, sec);
        sec+=beat/4;
        controlMoves(2, sec);
        sec+=beat/4;
        controlMoves(3, sec);
        sec+=beat/2;
        controlMoves(1, sec);
        sec+=beat/2;

        sec+=beat;
        controlMoves(2, sec);
        sec+=beat/2;
        controlMoves(1, sec);
        sec+=beat/2;

        controlMoves(2, sec);
        sec+=beat/2;
        controlMoves(1, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat/2;
        controlMoves(3, sec);
        sec+=beat/2;

        sec+=beat;
        controlMoves(2, sec);
        sec+=beat/2;
        controlMoves(1, sec);
        sec+=beat/2;

        controlMoves(3, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat/4;
        controlMoves(1, sec);
        sec+=beat/4;
        controlMoves(3, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat/2;

        // 2절

        sec+=beat/2;
        controlMoves(1, sec);
        sec+=beat/2;
        controlMoves(1, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat/2;

        controlMoves(3, sec);
        sec+=beat/4;
        controlMoves(2, sec);
        sec+=beat/4;
        controlMoves(3, sec);
        sec+=beat/4*3;
        controlMoves(1, sec);
        sec+=beat/4*3;

        sec+=beat;
        controlMoves(2, sec);
        sec+=beat/2;
        controlMoves(1, sec);
        sec+=beat/2;

        controlMoves(2, sec);
        sec+=beat/2;
        controlMoves(3, sec);
        sec+=beat/2;
        sec+=beat;

        sec+=beat/2;
        controlMoves(1, sec);
        sec+=beat/2;
        controlMoves(3, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat/2;

        controlMoves(3, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat/2;
        sec+=beat;

        sec+=beat/2;
        controlMoves(1, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat/2;
        controlMoves(1, sec);
        sec+=beat/2;

        sec+=beat/2;
        controlMoves(1, sec);
        sec+=beat/2*3;

        controlMoves(1, sec);
        controlMoves(3, sec);
        sec+=beat;
        controlMoves(2, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat/2;

        // 2절 후렴

        controlMoves(3, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat/2;
        controlMoves(3, sec);
        sec+=beat/2;

        controlMoves(1, sec);
        sec+=beat;
        controlMoves(2, sec);
        sec+=beat/2;
        controlMoves(1, sec);
        sec+=beat/2;

        controlMoves(1, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat/2;
        controlMoves(3, sec);
        sec+=beat/2;

        sec+=beat;
        controlMoves(2, sec);
        sec+=beat/2;
        controlMoves(1, sec);
        sec+=beat/2;

        controlMoves(2, sec);
        sec+=beat/2;
        controlMoves(1, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat/2;
        controlMoves(3, sec);
        sec+=beat/2;

        sec+=beat;
        controlMoves(2, sec);
        sec+=beat/2;
        controlMoves(1, sec);
        sec+=beat/2;

        controlMoves(3, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat/4;
        controlMoves(1, sec);
        sec+=beat/4;
        controlMoves(3, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat/2;

        controlMoves(3, sec);
        sec+=beat;
        controlMoves(2, sec);
        sec+=beat/2;

        controlMoves(3, sec);
        sec+=beat;
        controlMoves(3, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat/2;

        controlMoves(3, sec);
        sec+=beat;
        controlMoves(1, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat/2;

        controlMoves(3, sec);
        sec+=beat;
        controlMoves(3, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat/2;

        controlMoves(1, sec);
        controlMoves(3, sec);
        sec+=beat;

        controlMoves(2, sec);
        controlMoves(3, sec);
        sec+=beat;


        controlMoves(3, sec);
        sec+=beat;
        controlMoves(3, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat/2;

        controlMoves(3, sec);
        sec+=beat;
        controlMoves(1, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat/2;

        controlMoves(3, sec);
        sec+=beat;
        controlMoves(3, sec);
        sec+=beat/2;
        controlMoves(2, sec);
        sec+=beat/2;

        controlMoves(1, sec);
        controlMoves(3, sec);
        sec+=beat;

        controlMoves(2, sec);
        controlMoves(3, sec);
        sec+=beat;

        controlMoves(1, sec);
        controlMoves(3, sec);

        sec+=10000;
        goToScore(sec);
    }


    private void initMediaPlayer() {
        Intent intent = new Intent(RhythmActivity.this, MpPlayer.class);
        startService(intent);
    }

    private void initListener() {
        AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int result = am.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
            onAudioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    switch(focusChange){
                        case AudioManager.AUDIOFOCUS_LOSS:
                            goBack();
                            break;
                    }
                }
            };
        }

        buttonQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(bounce);
                int perfect = -1;
                int good = -1;
                int bad = -1;
                int view = -1;
                for(int i=0; i<3; i++){
                    if(isPerfect[i]){
                        perfect = i;
                    }
                    else if(isGood[i]){
                        good = i;
                    }
                    else if(isBad[i]){
                        bad = i;
                    }
                    else if(isOnView[i]){
                        view = i;
                    }
                }
                if(perfect!=-1){
                    perfect_count++;
                    tv_current.setText(getResources().getString(R.string.result_perfect));
                    Log.d("MyTag", "in button1 -- perfect:"+perfect);
                    Log.d("MyTag", "in button1 -- perfect:["+isPerfect[0]+"]["+isPerfect[1]+"]["+isPerfect[2]+"]["+isPerfect[3]+"]["+isPerfect[4]+"]["+isPerfect[5]+"]["+isPerfect[6]+"]["+isPerfect[7]+"]["+isPerfect[8]+"]");
                    Log.d("MyTag", "in button1 -- isOnView:["+isOnView[0]+"]["+isOnView[1]+"]["+isOnView[2]+"]["+isOnView[3]+"]["+isOnView[4]+"]["+isOnView[5]+"]["+isOnView[6]+"]["+isOnView[7]+"]["+isOnView[8]+"]");

                    end(moveList.get(perfect), perfect);
                    upCombo();
                }
                else if(good!=-1){
                    good_count++;
                    tv_current.setText(getResources().getString(R.string.result_good));
                    end(moveList.get(good), good);
                    Log.d("MyTag", "in button1 -- good:"+good);
                    Log.d("MyTag", "in button1 -- isOnView:["+isOnView[0]+"]["+isOnView[1]+"]["+isOnView[2]+"]["+isOnView[3]+"]["+isOnView[4]+"]["+isOnView[5]+"]["+isOnView[6]+"]["+isOnView[7]+"]["+isOnView[8]+"]");

                    upCombo();
                }
                else if(bad!=-1){
                    bad_count++;
                    tv_current.setText(getResources().getString(R.string.result_bad));
                    end(moveList.get(bad), bad);
                    Log.d("MyTag", "in button1 -- bad:"+bad);
                    Log.d("MyTag", "in button1 -- isOnView:["+isOnView[0]+"]["+isOnView[1]+"]["+isOnView[2]+"]["+isOnView[3]+"]["+isOnView[4]+"]["+isOnView[5]+"]["+isOnView[6]+"]["+isOnView[7]+"]["+isOnView[8]+"]");

                    upCombo();
                }
                else if(view!=-1){
                    tv_current.setText("");
                    end(moveList.get(view), view);
                    initCombo();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(bounce);
                int perfect = -1;
                int good = -1;
                int bad = -1;
                int view = -1;
                for(int i=3; i<6; i++){
                    if(isPerfect[i]){
                        perfect = i;
                    }
                    else if(isGood[i]){
                        good = i;
                    }
                    else if(isBad[i]){
                        bad = i;
                    }
                    else if(isOnView[i]){
                        view = i;
                    }
                }
                if(perfect!=-1){
                    perfect_count++;
                    Log.d("MyTag", "in button2 -- perfect:"+perfect);
                    Log.d("MyTag", "in button2 -- perfect:["+isPerfect[0]+"]["+isPerfect[1]+"]["+isPerfect[2]+"]["+isPerfect[3]+"]["+isPerfect[4]+"]["+isPerfect[5]+"]["+isPerfect[6]+"]["+isPerfect[7]+"]["+isPerfect[8]+"]");
                    Log.d("MyTag", "in button2 -- isOnView:["+isOnView[0]+"]["+isOnView[1]+"]["+isOnView[2]+"]["+isOnView[3]+"]["+isOnView[4]+"]["+isOnView[5]+"]["+isOnView[6]+"]["+isOnView[7]+"]["+isOnView[8]+"]");

                    tv_current.setText(getResources().getString(R.string.result_perfect));
                    end(moveList.get(perfect), perfect);
                    upCombo();
                }
                else if(good!=-1){
                    good_count++;
                    tv_current.setText(getResources().getString(R.string.result_good));
                    end(moveList.get(good), good);
                    upCombo();
                }
                else if(bad!=-1){
                    bad_count++;
                    tv_current.setText(getResources().getString(R.string.result_bad));
                    end(moveList.get(bad), bad);
                    upCombo();
                }
                else if(view!=-1){
                    tv_current.setText("");
                    end(moveList.get(view), view);
                    initCombo();
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.startAnimation(bounce);
                int perfect = -1;
                int good = -1;
                int bad = -1;
                int view = -1;
                for(int i=6; i<9; i++){
                    if(isPerfect[i]){
                        perfect = i;
                    }
                    else if(isGood[i]){
                        good = i;
                    }
                    else if(isBad[i]){
                        bad = i;
                    }
                    else if(isOnView[i]){
                        view = i;
                    }
                }
                if(perfect!=-1){
                    perfect_count++;
                    Log.d("MyTag", "in button3 -- perfect:"+perfect);
                    Log.d("MyTag", "in button3 -- perfect:["+isPerfect[0]+"]["+isPerfect[1]+"]["+isPerfect[2]+"]["+isPerfect[3]+"]["+isPerfect[4]+"]["+isPerfect[5]+"]["+isPerfect[6]+"]["+isPerfect[7]+"]["+isPerfect[8]+"]");
                    Log.d("MyTag", "in button3 -- isOnView:["+isOnView[0]+"]["+isOnView[1]+"]["+isOnView[2]+"]["+isOnView[3]+"]["+isOnView[4]+"]["+isOnView[5]+"]["+isOnView[6]+"]["+isOnView[7]+"]["+isOnView[8]+"]");
                    tv_current.setText(getResources().getString(R.string.result_perfect));
                    end(moveList.get(perfect), perfect);
                    upCombo();
                }
                else if(good!=-1){
                    good_count++;
                    tv_current.setText(getResources().getString(R.string.result_good));
                    end(moveList.get(good), good);
                    upCombo();
                }
                else if(bad!=-1) {
                    bad_count++;
                    tv_current.setText(getResources().getString(R.string.result_bad));
                    end(moveList.get(bad), bad);
                    upCombo();
                }
                else if(view!=-1){
                    tv_current.setText("");
                    end(moveList.get(view), view);
                    initCombo();
                }
            }
        });
    }

    private void initHandler() {
        media_handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                initMediaPlayer();
            }
        };

        end_handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(isOnView[msg.what] == false){
                    return;
                }
                int index = msg.what/3;
                int sub_index = msg.what%3;

                initCombo();

                switch(index){
                    case 0:
                        switch(sub_index){
                            case 0:
                                end(move1_1, msg.what);
                                break;
                            case 1:
                                end(move1_2, msg.what);
                                break;
                            case 2:
                                end(move1_3, msg.what);
                                break;
                            default:
                                break;
                        }
                        break;
                    case 1:
                        switch(sub_index){
                            case 0:
                                end(move2_1, msg.what);
                                break;
                            case 1:
                                end(move2_2, msg.what);
                                break;
                            case 2:
                                end(move2_3, msg.what);
                                break;
                            default:
                                break;
                        }
                        break;
                    case 2:
                        switch(sub_index){
                            case 0:
                                end(move3_1, msg.what);
                                break;
                            case 1:
                                end(move3_2, msg.what);
                                break;
                            case 2:
                                end(move3_3, msg.what);
                                break;
                            default:
                                break;
                        }
                    default:
                        break;
                }
            }
        };

        bad_handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(!isOnView[msg.what]){
                    return;
                }
                isBad[msg.what] = true;
            }
        };

        good_handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(!isOnView[msg.what]){
                    return;
                }
                isGood[msg.what] = true;
            }
        };

        perfect_handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(!isOnView[msg.what]){
                    return;
                }
                isPerfect[msg.what] = true;
            }
        };
    }

    private void startAfter(final int index, final int sub_index, long delay, final ImageView image){
        new Timer().schedule(new TimerTask(){
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(notPlaying){
                            return;
                        }
                        start(image);

                        int var = index*3 + sub_index;
                        isOnView[var] = true;

                        end_handler.sendEmptyMessageDelayed(var, 2100);
                        bad_handler.sendEmptyMessageDelayed(var, 900);
                        good_handler.sendEmptyMessageDelayed(var, 1350);
                        perfect_handler.sendEmptyMessageDelayed(var, 1700);
                    }
                });
            }
        }, delay);
    }

    private void start(final ImageView image){
        image.setVisibility(View.VISIBLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            image.clearAnimation();
            image.setX(0);
            image.animate().translationX(lane_width).setDuration(2000).setInterpolator(AnimationUtils.loadInterpolator(RhythmActivity.this, android.R.anim.linear_interpolator)).start();
        }
    }

    private void end(final ImageView image, int var){
        isOnView[var] = false;
        isPerfect[var] = false;
        isGood[var] = false;
        isBad[var] = false;

        Log.d("MyTag", "in end -- var : "+var);
        Log.d("MyTag", "in end -- isOnView:["+isOnView[0]+"]["+isOnView[1]+"]["+isOnView[2]+"]["+isOnView[3]+"]["+isOnView[4]+"]["+isOnView[5]+"]["+isOnView[6]+"]["+isOnView[7]+"]["+isOnView[8]+"]");

        image.setVisibility(View.INVISIBLE);
    }

    private void initCombo(){
        if(maxCombo<combo){
            maxCombo=combo;
        }
        if(combo>30){
            sound_good_init.stop(track_good_init);
            sound_perfect.stop(track_perfect);
            sound_good.stop(track_good);
            sound_bad.play(track_bad, 1, 1, 1, 0, 1);
        }
        combo=0;
        tv_combo.setText(""+combo);
        tv_current.setText("");
    }

    private void upCombo(){
        combo++;
        tv_combo.setText(""+combo);

        if(combo==30){
            sound_good_init.play(track_good_init, 1, 1, 3, 0, 1);
        }
        if(combo==40){
            sound_good.play(track_good, 1, 1, 2, -1, 1);
        }
        if(combo>=70 && combo%10==0){
            sound_perfect.play(track_perfect, 1, 1, 2, 0, 1);
        }
    }

    private void initView() {
        isOnView = new boolean[9];
        isGood = new boolean[9];
        isBad = new boolean[9];
        isPerfect = new boolean[9];
        bounce = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
        buttonQuit = (LinearLayout) findViewById(R.id.quit);

        move1_1 = (ImageView) findViewById(R.id.move1_1);
        move1_2 = (ImageView) findViewById(R.id.move1_2);
        move1_3 = (ImageView) findViewById(R.id.move1_3);
        move2_1 = (ImageView) findViewById(R.id.move2_1);
        move2_2 = (ImageView) findViewById(R.id.move2_2);
        move2_3 = (ImageView) findViewById(R.id.move2_3);
        move3_1 = (ImageView) findViewById(R.id.move3_1);
        move3_2 = (ImageView) findViewById(R.id.move3_2);
        move3_3 = (ImageView) findViewById(R.id.move3_3);

        moveList = new ArrayList<>();
        moveList.add(move1_1);
        moveList.add(move1_2);
        moveList.add(move1_3);
        moveList.add(move2_1);
        moveList.add(move2_2);
        moveList.add(move2_3);
        moveList.add(move3_1);
        moveList.add(move3_2);
        moveList.add(move3_3);

        button1 = (LinearLayout) findViewById(R.id.button1);
        button2 = (LinearLayout) findViewById(R.id.button2);
        button3 = (LinearLayout) findViewById(R.id.button3);

        tv_quit = (TextView) findViewById(R.id.tv_quit);
        tv_combo = (TextView) findViewById(R.id.tv_combo);
        tv_current = (TextView) findViewById(R.id.tv_current);
        tv = (TextView) findViewById(R.id.tv_rhythm);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);

        timer1 = (ImageView) findViewById(R.id.timer1);
        timer2 = (ImageView) findViewById(R.id.timer2);
        timer3 = (ImageView) findViewById(R.id.timer3);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        lane_width = size.x * 516 / 640;

        setFontToViewBold(tv_combo, tv_quit, tv_current, tv, tv1, tv2, tv3);
    }

    @Override
    public void onkey1() {
        button1.performClick();
    }

    @Override
    public void onkey2() {
        button2.performClick();
    }

    @Override
    public void onkey3() {
        button3.performClick();
    }

    @Override
    public void onkey4() {
        //Intentionally do nothing
    }

    @Override
    public void onkey5() {
        //Intentionally do nothing
    }

    @Override
    public void onkey6() {
        //Intentionally do nothing
    }

    @Override
    public void onkey7() {
        //Intentionally do nothing
    }

    @Override
    public void onkey8() {
        //Intentionally do nothing
    }

    @Override
    public void onkey9() {
        //Intentionally do nothing
    }

    @Override
    public void onkey10() {
        //Intentionally do nothing
    }

    @Override
    public void onKeyBack() {
        goBack();
    }

    public void goBack(){
        for(int i=0; i<9; i++){
            end_handler.removeMessages(i);
            bad_handler.removeMessages(i);
            good_handler.removeMessages(i);
            perfect_handler.removeMessages(i);
        }
        end_handler.removeCallbacksAndMessages(null);
        end_handler = null;
        bad_handler.removeCallbacksAndMessages(null);
        bad_handler = null;
        good_handler.removeCallbacksAndMessages(null);
        good_handler = null;
        perfect_handler.removeCallbacksAndMessages(null);
        perfect_handler = null;
        if(goToNext!=null) {
            goToNext.cancel();
            goToNext = null;
        }

        notPlaying = true;

        sound_good_init.stop(track_good_init);
        sound_perfect.stop(track_perfect);
        sound_good.stop(track_good);
        sound_bad.stop(track_bad);
        Intent serviceIntent = new Intent(RhythmActivity.this, MpPlayer.class);
        stopService(serviceIntent);

        Intent intent = new Intent(getContext(), GateActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        goBack();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        notPlaying = true;
        Intent intent = new Intent(RhythmActivity.this, MpPlayer.class);
        stopService(intent);
    }
}
