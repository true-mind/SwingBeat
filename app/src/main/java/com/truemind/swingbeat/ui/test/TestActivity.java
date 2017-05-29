package com.truemind.swingbeat.ui.test;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.truemind.swingbeat.BaseActivity;
import com.truemind.swingbeat.Constants;
import com.truemind.swingbeat.R;
import com.truemind.swingbeat.util.OnSingleClickListener;

import java.util.Random;

/**
 * Created by 현석 on 2017-05-24.
 */

public class TestActivity extends BaseActivity {

    public static final String TAG = "MyTag";

    public static final int COLOR_BLUE = 0;
    public static final int COLOR_RED = 1;
    public static final int COLOR_GREEN = 2;
    public static final int COLOR_YELLOW = 3;

    public static final int MAX_POSITION = 25;
    public int TIMER_INTERVAL = Constants.TIMER;

    private LinearLayout btnQuit;
    private ImageButton btn1;
    private ImageButton btn2;
    private ImageButton btn3;
    private ImageButton btn4;
    private TextView textColor;
    private TextView score;
    private TextView number;
    private TextView yeah;
    Random randomText = new Random();
    Random randomColor = new Random();
    private int text;
    public int currentPos = 0;
    public int numberRight = 0;

    private Runnable r;
    Handler handler = new Handler();
    Animation bounce;
    Animation show;

    SoundPool startHornPool;
    SoundPool beepPool;
    SoundPool beepPingPool;

    int startHorn;
    int beep;
    int beepPing;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        initView();
        initListener();

    }

    public void initView() {

        /** Sound Effect Initialize*/
        startHornPool = build(1, AudioManager.STREAM_MUSIC, 0);
        startHorn = startHornPool.load(getContext(), R.raw.horn, 1);
        beepPool = build(1, AudioManager.STREAM_MUSIC, 0);
        beep = beepPool.load(getContext(), R.raw.beep, 1);
        beepPingPool = build(1, AudioManager.STREAM_MUSIC, 0);
        beepPing = beepPingPool.load(getContext(), R.raw.bleep, 1);

        /**Animation Effect Initialize*/
        bounce = AnimationUtils.loadAnimation(getContext(), R.anim.bounce);
        show = AnimationUtils.loadAnimation(getContext(), R.anim.show_feed);

        /** View Initialize*/
        TextView scoreTitle = (TextView) findViewById(R.id.scoreTitle);
        yeah = (TextView) findViewById(R.id.yeah);
        TextView scoreBy25 = (TextView) findViewById(R.id.scoreBy25);
        TextView numberTitle = (TextView) findViewById(R.id.numberTitle);
        score = (TextView) findViewById(R.id.score);
        number = (TextView) findViewById(R.id.number);
        textColor = (TextView) findViewById(R.id.textColor);
        btn1 = (ImageButton) findViewById(R.id.btn1);
        btn2 = (ImageButton) findViewById(R.id.btn2);
        btn3 = (ImageButton) findViewById(R.id.btn3);
        btn4 = (ImageButton) findViewById(R.id.btn4);
        btnQuit = (LinearLayout) findViewById(R.id.btnQuit);
        TextView btn5Text = (TextView) findViewById(R.id.btn5Text);
        setFontToViewBold(textColor, btn5Text, scoreBy25, score, scoreTitle, number, numberTitle, yeah);

        /** Start After 1000ms*/
        mHandler.sendEmptyMessageDelayed(0, 1000);
    }

    /**SoundPool build
     * @SoundPool - Deprecated after Lollipop
     *
     * @param para1 Max Stream
     * @param para2 Stream type
     * @param para3 Source quality
     *
     * @return SoundPool
     * */
    public SoundPool build(int para1, int para2, int para3) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return new SoundPool.Builder()
                    .build();
        } else {
            return new SoundPool(para1, para2, para3);
        }
    }

    /**
     * Initialize buttons, if text equals button id, play beepPing & show feed, increase numberRight
     *
     * */
    public void initListener() {

        btn1.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                Log.d(TAG, "btn1");
                btn1.startAnimation(bounce);
                if (text == COLOR_BLUE) {
                    numberRight++;
                    showFeed();
                    beepPingPool.play(beepPing, 1, 1, 1, 0, 1);
                }else{
                    beepPool.play(beepPing, 1, 1, 1, 0, 1);
                }
            }
        });

        btn2.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                Log.d(TAG, "btn2");
                btn2.startAnimation(bounce);
                if (text == COLOR_RED) {
                    numberRight++;
                    showFeed();
                    beepPingPool.play(beepPing, 1, 1, 1, 0, 1);
                }else{
                    beepPool.play(beepPing, 1, 1, 1, 0, 1);
                }
            }
        });

        btn3.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                Log.d(TAG, "btn3");
                btn3.startAnimation(bounce);
                if (text == COLOR_GREEN) {
                    numberRight++;
                    showFeed();
                    beepPingPool.play(beepPing, 1, 1, 1, 0, 1);
                }else{
                    beepPool.play(beepPing, 1, 1, 1, 0, 1);
                }
            }
        });

        btn4.setOnClickListener(new OnSingleClickListener() {
            @Override
            public void onSingleClick(View v) {
                Log.d(TAG, "btn4");
                btn4.startAnimation(bounce);
                if (text == COLOR_YELLOW) {
                    numberRight++;
                    showFeed();
                    beepPingPool.play(beepPing, 1, 1, 1, 0, 1);
                }else{
                    beepPool.play(beepPing, 1, 1, 1, 0, 1);
                }
            }
        });

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
                btnQuit.startAnimation(bounce);
            }
        });

    }

    Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            currentPos++;
            text = randomText.nextInt(4);
            makeNext(randomColor.nextInt(4), text);
            score.setText(Integer.toString(numberRight));
            number.setText(Integer.toString(currentPos));

            if(currentPos==1){
                startHornPool.play(startHorn, 1, 1, 1, 0, 1);
            }

            if (currentPos <= MAX_POSITION) {
                mHandler.sendEmptyMessageDelayed(0, TIMER_INTERVAL);
            } else {
                mHandler.removeCallbacksAndMessages(null);
                Intent intent = new Intent(getContext(), TestResult.class);
                intent.putExtra("score", numberRight);
                startActivity(intent);
                finish();
            }
        }
    };

    public void showFeed() {
        yeah.setVisibility(View.VISIBLE);
        yeah.startAnimation(show);
        /*
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                yeah.setVisibility(View.GONE);
            }
        }, 1500);
        */

    }

    public void makeNext(int color, int text) {
        switch (color) {
            case COLOR_BLUE:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    textColor.setTextColor(getResources().getColor(R.color.color_blue, null));
                } else {
                    textColor.setTextColor(getResources().getColor(R.color.color_blue));
                }
                break;
            case COLOR_RED:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    textColor.setTextColor(getResources().getColor(R.color.color_red, null));
                } else {
                    textColor.setTextColor(getResources().getColor(R.color.color_red));
                }
                break;
            case COLOR_GREEN:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    textColor.setTextColor(getResources().getColor(R.color.color_green, null));
                } else {
                    textColor.setTextColor(getResources().getColor(R.color.color_green));
                }
                break;
            case COLOR_YELLOW:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    textColor.setTextColor(getResources().getColor(R.color.color_yellow, null));
                } else {
                    textColor.setTextColor(getResources().getColor(R.color.color_yellow));
                }
                break;
        }

        switch (text) {
            case COLOR_BLUE:
                textColor.setText(getResources().getString(R.string.blue));
                break;
            case COLOR_RED:
                textColor.setText(getResources().getString(R.string.red));
                break;
            case COLOR_GREEN:
                textColor.setText(getResources().getString(R.string.green));
                break;
            case COLOR_YELLOW:
                textColor.setText(getResources().getString(R.string.yellow));
                break;

        }

    }

    private void goBack() {
        mHandler.removeCallbacksAndMessages(null);
        startActivity(new Intent(getContext(), TestIntro.class));
        finish();
    }

    @Override
    public void onkey1() {
        btn1.performClick();
    }

    @Override
    public void onkey2() {
        btn2.performClick();
    }

    @Override
    public void onkey3() {
        btn3.performClick();
    }

    @Override
    public void onkey4() {
        btn4.performClick();
    }

    @Override
    public void onkey5() {
        goBack();
    }

    @Override
    public void onkey6() {

    }

    @Override
    public void onkey7() {

    }

    @Override
    public void onkey8() {

    }

    @Override
    public void onkey9() {

    }

    @Override
    public void onkey10() {

    }

    @Override
    public void onKeyBack() {
        goBack();
    }
}
