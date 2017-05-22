package com.truemind.swingbeat.ui;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import com.truemind.swingbeat.BaseActivity;
import com.truemind.swingbeat.Constants;
import com.truemind.swingbeat.R;

/**
 * Created by 현석 on 2017-05-19.
 */

public class DrumActivity extends BaseActivity {

    private static final String TAG = "MyTag";

    private ImageButton kick;
    private ImageButton snare;
    private ImageButton hihat;
    private ImageButton tomS;
    private ImageButton tomM;
    private ImageButton tomF;
    private ImageButton crash16;
    private ImageButton crash18;
    private ImageButton ride;
    private ImageButton setting;
    private ImageButton play;

    SoundPool kickPool;
    SoundPool snarePool;
    SoundPool hihatPool;
    SoundPool tomSPool;
    SoundPool tomMPool;
    SoundPool tomFPool;
    SoundPool crash16Pool;
    SoundPool crash18Pool;
    SoundPool ridePool;

    int kickTrack;
    int snareTrack;
    int hihatTrack;
    int tomSTrack;
    int tomMTrack;
    int tomFTrack;
    int crash16Track;
    int crash18Track;
    int rideTrack;

    MediaPlayer mediaPlayer;
    Animation bounce;

    private boolean isPlay = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drum);

        initView();
        initListener();
    }


    public void initView() {
        bounce = AnimationUtils.loadAnimation(getContext(),R.anim.bounce);
        kick = (ImageButton) findViewById(R.id.kick);
        snare = (ImageButton) findViewById(R.id.snare);
        hihat = (ImageButton) findViewById(R.id.hihat);
        tomS = (ImageButton) findViewById(R.id.tomSmall);
        tomM = (ImageButton) findViewById(R.id.tomMiddle);
        tomF = (ImageButton) findViewById(R.id.tomFloor);
        crash16 = (ImageButton) findViewById(R.id.crash16);
        crash18 = (ImageButton) findViewById(R.id.crash18);
        ride = (ImageButton) findViewById(R.id.ride);
        setting = (ImageButton)findViewById(R.id.setting);
        play = (ImageButton) findViewById(R.id.play);
        initSound();
    }

    public void initSound() {
        kickPool = build(1, AudioManager.STREAM_MUSIC, 0);
        snarePool = build(1, AudioManager.STREAM_MUSIC, 0);
        hihatPool = build(1, AudioManager.STREAM_MUSIC, 0);
        tomSPool = build(1, AudioManager.STREAM_MUSIC, 0);
        tomMPool = build(1, AudioManager.STREAM_MUSIC, 0);
        tomFPool = build(1, AudioManager.STREAM_MUSIC, 0);
        crash16Pool = build(1, AudioManager.STREAM_MUSIC, 0);
        crash18Pool = build(1, AudioManager.STREAM_MUSIC, 0);
        ridePool = build(1, AudioManager.STREAM_MUSIC, 0);

        kickTrack = kickPool.load(getContext(), R.raw.kick, 1);
        snareTrack = snarePool.load(getContext(), R.raw.snare, 1);
        hihatTrack = hihatPool.load(getContext(), R.raw.hihat, 1);
        tomSTrack = tomSPool.load(getContext(), R.raw.tom_s, 1);
        tomMTrack = tomMPool.load(getContext(), R.raw.tom_m, 1);
        tomFTrack = tomFPool.load(getContext(), R.raw.tom_f, 1);
        crash16Track = crash16Pool.load(getContext(), R.raw.crash16, 1);
        crash18Track = crash18Pool.load(getContext(), R.raw.crash18, 1);
        rideTrack = ridePool.load(getContext(), R.raw.ride, 1);

        if(Constants.DRUM_SOUND_TRACK == 1){
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.funk1);
        }else if(Constants.DRUM_SOUND_TRACK == 2){
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.jazz1);
        }else{
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.jazz1);
        }

    }

    public SoundPool build(int para1, int para2, int para3) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return new SoundPool.Builder()
                    .build();
        } else {
            return new SoundPool(para1, para2, para3);
        }
    }

    public void initListener() {
        kick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kickPool.play(kickTrack, 1, 1, 1, 0, 1);
                kick.startAnimation(bounce);
            }
        });
        kick.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==0)
                kickPool.play(kickTrack, 1, 1, 1, 0, 1);
                kick.startAnimation(bounce);
                return true;
            }
        });

        snare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snarePool.play(snareTrack, 1, 1, 2, 0, 1);
                snare.startAnimation(bounce);
            }
        });
        snare.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==0)
                snarePool.play(snareTrack, 1, 1, 2, 0, 1);
                snare.startAnimation(bounce);
                return true;
            }
        });

        hihat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hihatPool.play(hihatTrack, 1, 1, 2, 0, 1);
                hihat.startAnimation(bounce);
            }
        });
        hihat.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==0)
                hihatPool.play(hihatTrack, 1, 1, 2, 0, 1);
                hihat.startAnimation(bounce);
                return true;
            }
        });

        tomS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomSPool.play(tomSTrack, 1, 1, 0, 0, 1);
                tomS.startAnimation(bounce);
            }
        });
        tomS.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==0)
                tomSPool.play(tomSTrack, 1, 1, 0, 0, 1);
                tomS.startAnimation(bounce);
                return true;
            }
        });

        tomM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomMPool.play(tomMTrack, 1, 1, 0, 0, 1);
                tomM.startAnimation(bounce);
            }
        });
        tomM.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==0)
                tomMPool.play(tomMTrack, 1, 1, 0, 0, 1);
                tomM.startAnimation(bounce);
                return true;
            }
        });

        tomF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tomFPool.play(tomFTrack, 1, 1, 0, 0, 1);
                tomF.startAnimation(bounce);
            }
        });
        tomF.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==0)
                tomFPool.play(tomFTrack, 1, 1, 0, 0, 1);
                tomF.startAnimation(bounce);
                return true;
            }
        });

        crash16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crash16Pool.play(crash16Track, 1, 1, 0, 0, 1);
                crash16.startAnimation(bounce);
            }
        });
        crash16.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==0)
                crash16Pool.play(crash16Track, 1, 1, 0, 0, 1);
                crash16.startAnimation(bounce);
                return true;
            }
        });

        crash18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crash18Pool.play(crash18Track, 1, 1, 2, 0, 1);
                crash18.startAnimation(bounce);
            }
        });
        crash18.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==0)
                crash18Pool.play(crash18Track, 1, 1, 2, 0, 1);
                crash18.startAnimation(bounce);
                return true;
            }
        });

        ride.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ridePool.play(rideTrack, 1, 1, 0, 0, 1);
                ride.startAnimation(bounce);
            }
        });
        ride.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==0)
                ridePool.play(rideTrack, 1, 1, 0, 0, 1);
                ride.startAnimation(bounce);
                return true;
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                Intent intent = new Intent(getContext(), DrumSetting.class);
                startActivity(intent);
                finish();
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPlay = backTrack(isPlay);
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.stop();
                play.setSelected(false);
            }
        });
    }

    public boolean backTrack(boolean isPlay){
        if(isPlay){
            mediaPlayer.stop();
            play.setSelected(false);
            isPlay = false;
        }else{
            if(Constants.DRUM_SOUND_TRACK == 1){
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.funk1);
            }else if(Constants.DRUM_SOUND_TRACK == 2){
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.jazz1);
            }else{
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.jazz1);
            }
            mediaPlayer.start();
            play.setSelected(true);
            isPlay = true;
        }
        return isPlay;
    }

    public void goBack() {
        mediaPlayer.stop();
        mediaPlayer.release();
        Intent intent = new Intent(getContext(), GateActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        goBack();
    }

    @Override
    public void onkey1() {
        performDrumClick(Constants.DRUM_KEY1);
    }

    @Override
    public void onkey2() {
        performDrumClick(Constants.DRUM_KEY2);
    }

    @Override
    public void onkey3() {
        performDrumClick(Constants.DRUM_KEY3);
    }

    @Override
    public void onkey4() {
        performDrumClick(Constants.DRUM_KEY4);
    }

    @Override
    public void onkey5() {
        performDrumClick(Constants.DRUM_KEY5);
    }

    @Override
    public void onKeyBack() {
        goBack();
    }

    public void performDrumClick(int seq) {
        switch (seq) {
            case Constants.KICK:
                kick.performClick();
                break;
            case Constants.SNARE:
                snare.performClick();
                break;
            case Constants.HIHAT:
                hihat.performClick();
                break;
            case Constants.SMALL_TOM:
                tomS.performClick();
                break;
            case Constants.MIDDLE_TOM:
                tomM.performClick();
                break;
            case Constants.FLOOR_TOM:
                tomF.performClick();
                break;
            case Constants.CRASH_16:
                crash16.performClick();
                break;
            case Constants.CRASH_18:
                crash18.performClick();
                break;
            case Constants.RIDE:
                ride.performClick();
                break;
        }
    }

}
