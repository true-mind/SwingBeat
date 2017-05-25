package com.truemind.swingbeat.ui;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.truemind.swingbeat.BaseActivity;
import com.truemind.swingbeat.R;
import com.truemind.swingbeat.service.MpPlayer;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class RhythmActivity extends BaseActivity {

    ImageView move1_1;
    ImageView move1_2;
    ImageView move1_3;
    ImageView move2_1;
    ImageView move2_2;
    ImageView move2_3;
    ImageView move3_1;
    ImageView move3_2;
    ImageView move3_3;

    LinearLayout button1;
    LinearLayout button2;
    LinearLayout button3;

    private Handler media_handler;
    private Handler end_handler;
    private Handler good_handler;
    private Handler perfect_handler;

    private boolean[] isOnView;
    private boolean[] isGood;
    private boolean[] isPerfect;

    private ArrayList<ImageView> moveList;

    private TextView tv;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv_combo;
    private TextView tv_current;
    private TextView tv_quit;
    private ImageView timer1;
    private ImageView timer2;
    private ImageView timer3;

    private int combo;
    private int countdown = 3;
    private long sec;
    private int beat = 1000;

    private int mIndex1;
    private int mIndex2;
    private int mIndex3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rhythm_new);

        initView();
        initHandler();
        initListener();
        initCountDown();
    }

    private void initCountDown() {
        new CountDownTimer(3050, 1000){
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
        sec = 1300;

        controlMoves(1, sec);
        sec+=500;
        controlMoves(1, sec);
        sec+=500;

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
        sec+=beat/4;
        controlMoves(1, sec);
        sec+=beat/4;

        sec+=beat;
        controlMoves(1, sec);
        sec+=beat/4;
        controlMoves(2, sec);
        sec+=beat/4;
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
        controlMoves(2, sec);
        sec+=beat/4;
        controlMoves(3, sec);
        sec+=beat/2;
        controlMoves(1, sec);
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

        sec+=beat/4;
        controlMoves(1, sec);
        sec+=beat/4;
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
        sec+=beat/4;
        controlMoves(1, sec);
        sec+=beat/4;

        sec+=beat;
        controlMoves(1, sec);
        sec+=beat/4;
        controlMoves(2, sec);
        sec+=beat/4;
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
        controlMoves(2, sec);
        sec+=beat/4;
        controlMoves(3, sec);
        sec+=beat/2;
        controlMoves(1, sec);
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
    }


    private void initMediaPlayer() {
        Intent intent = new Intent(RhythmActivity.this, MpPlayer.class);
        startService(intent);
    }

    private void initListener() {
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int perfect = -1;
                int good = -1;
                int bad = -1;
                for(int i=0; i<3; i++){
                    if(isPerfect[i]){
                        perfect = i;
                    }
                    if(isGood[i]){
                        good = i;
                    }
                    if(isOnView[i]){
                        bad = i;
                    }
                }
                if(perfect!=-1){
                    tv_current.setText("Perfect");
                    end(moveList.get(perfect), perfect);
                    upCombo();
                }
                else if(good!=-1){
                    tv_current.setText("Good");
                    end(moveList.get(good), good);
                    upCombo();
                }
                else if(bad!=-1){
                    tv_current.setText("Bad");
                    end(moveList.get(bad), bad);
                    upCombo();
                }
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int perfect = -1;
                int good = -1;
                int bad = -1;
                for(int i=3; i<6; i++){
                    if(isPerfect[i]){
                        perfect = i;
                    }
                    if(isGood[i]){
                        good = i;
                    }
                    if(isOnView[i]){
                        bad = i;
                    }
                }
                if(perfect!=-1){
                    tv_current.setText("Perfect");
                    end(moveList.get(perfect), perfect);
                    upCombo();
                }
                else if(good!=-1){
                    tv_current.setText("Good");
                    end(moveList.get(good), good);
                    upCombo();
                }
                else if(bad!=-1){
                    tv_current.setText("Bad");
                    end(moveList.get(bad), bad);
                    upCombo();
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int perfect = -1;
                int good = -1;
                int bad = -1;
                for(int i=6; i<9; i++){
                    if(isPerfect[i]){
                        perfect = i;
                    }
                    if(isGood[i]){
                        good = i;
                    }
                    if(isOnView[i]){
                        bad = i;
                    }
                }
                if(perfect!=-1){
                    tv_current.setText("Perfect");
                    end(moveList.get(perfect), perfect);
                    upCombo();
                }
                else if(good!=-1){
                    tv_current.setText("Good");
                    end(moveList.get(good), good);
                    upCombo();
                }
                else if(bad!=-1) {
                    tv_current.setText("Bad");
                    end(moveList.get(bad), bad);
                    upCombo();
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

        good_handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                isGood[msg.what] = true;
            }
        };

        perfect_handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
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
                        start(index, sub_index, image);
                        int var = index*3 + sub_index;
                        end_handler.sendEmptyMessageDelayed(var, 2100);
                        good_handler.sendEmptyMessageDelayed(var, 1200);
                        perfect_handler.sendEmptyMessageDelayed(var, 1700);
                    }
                });
            }
        }, delay);
    }

    private void start(int index, int sub_index, final ImageView image){
        image.setVisibility(View.VISIBLE);
        int var = index * 3 + sub_index;
        isOnView[var] = true;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            image.clearAnimation();
            image.setX(0);
            image.animate().translationX(1500).setDuration(2000).setInterpolator(AnimationUtils.loadInterpolator(RhythmActivity.this, android.R.anim.linear_interpolator)).start();
        }
    }

    private void end(final ImageView image, int var){
        isOnView[var] = false;
        isPerfect[var] = false;
        isGood[var] = false;

        image.setVisibility(View.INVISIBLE);
    }

    private void initCombo(){
        combo=0;
        tv_combo.setText(""+combo);
    }

    private void upCombo(){
        combo++;
        tv_combo.setText(""+combo);
    }

    private void initView() {
        isOnView = new boolean[9];
        isGood = new boolean[9];
        isPerfect = new boolean[9];

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

        tv_quit = (TextView) findViewById(R.id.quit);
        tv_combo = (TextView) findViewById(R.id.tv_combo);
        tv_current = (TextView) findViewById(R.id.tv_current);
        tv = (TextView) findViewById(R.id.tv_rhythm);
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);

        timer1 = (ImageView) findViewById(R.id.timer1);
        timer2 = (ImageView) findViewById(R.id.timer2);
        timer3 = (ImageView) findViewById(R.id.timer3);

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
        onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(RhythmActivity.this, MpPlayer.class);
        stopService(intent);
    }
}
