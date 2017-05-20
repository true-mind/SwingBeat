package com.truemind.swingbeat.ui;

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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.truemind.swingbeat.BaseActivity;
import com.truemind.swingbeat.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class RhythmActivity extends BaseActivity {

    FrameLayout parentLayout;

    ImageView move1_1;
    ImageView move1_2;
    ImageView move1_3;
    ImageView move2_1;
    ImageView move2_2;
    ImageView move2_3;
    ImageView move3_1;
    ImageView move3_2;
    ImageView move3_3;

    Button button1;
    Button button2;
    Button button3;

    private Handler media_handler;
    private Handler end_handler;
    private Handler good_handler;
    private Handler perfect_handler;

    private boolean[] isOnView;
    private boolean[] isGood;
    private boolean[] isPerfect;

    private ArrayList<ImageView> moveList;

    private TextView tv_combo;
    private TextView tv_timer;

    private int combo;
    private int countdown = 3;
    private long sec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rhythm);

        initView();
        initHandler();
        initListener();
        initCountDown();
    }

    private void initCountDown() {
        new CountDownTimer(3050, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                tv_timer.setText(""+countdown);
                countdown--;
            }

            @Override
            public void onFinish() {
                tv_timer.setVisibility(View.GONE);
                initMoves();
            }
        }.start();
    }

    private void initMoves(){
        isOnView = new boolean[9];
        isGood = new boolean[9];
        isPerfect = new boolean[9];
        media_handler.sendEmptyMessageDelayed(0, 1900);
        sec = 500;
        startAfter(0, 0, sec, move1_1);
        sec += 900;
        startAfter(0, 1, sec, move1_2);
        sec += 900;

        startAfter(1, 0, sec, move2_1);
        sec += 900;
        startAfter(1, 1, sec, move2_2);
        sec += 900;

        startAfter(2, 0, sec, move3_1);
        sec += 900;
        startAfter(2, 1, sec, move3_2);
        sec += 900;
        startAfter(1, 2, sec, move2_3);
        sec += 1700;

        startAfter(2, 0, sec, move3_3);
        sec += 900;
        startAfter(2, 1, sec, move3_2);
        sec += 900;

        startAfter(1, 2, sec, move2_3);
        sec += 900;
        startAfter(1, 1, sec, move2_2);
        sec += 900;

        startAfter(0, 2, sec, move1_3);
        sec += 900;
        startAfter(0, 1, sec, move1_2);
        sec += 900;
        startAfter(2, 0, sec, move3_1);
        sec += 1800;

    }


    private void initMediaPlayer() {
        int resId = R.raw.little_star;
        MediaPlayer mediaPlayer = MediaPlayer.create(this, resId);
        mediaPlayer.start();
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
                    Toast.makeText(RhythmActivity.this, "Perfect", Toast.LENGTH_SHORT).show();
                    end(moveList.get(perfect), perfect);
                    upCombo();
                }
                else if(good!=-1){
                    Toast.makeText(RhythmActivity.this, "Good", Toast.LENGTH_SHORT).show();
                    end(moveList.get(good), good);
                    upCombo();
                }
                else if(bad!=-1){
                    Toast.makeText(RhythmActivity.this, "Bad", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(RhythmActivity.this, "Perfect", Toast.LENGTH_SHORT).show();
                    end(moveList.get(perfect), perfect);
                    upCombo();
                }
                else if(good!=-1){
                    Toast.makeText(RhythmActivity.this, "Good", Toast.LENGTH_SHORT).show();
                    end(moveList.get(good), good);
                    upCombo();
                }
                else if(bad!=-1){
                    Toast.makeText(RhythmActivity.this, "Bad", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(RhythmActivity.this, "Perfect", Toast.LENGTH_SHORT).show();
                    end(moveList.get(perfect), perfect);
                    upCombo();
                }
                else if(good!=-1){
                    Toast.makeText(RhythmActivity.this, "Good", Toast.LENGTH_SHORT).show();
                    end(moveList.get(good), good);
                    upCombo();
                }
                else if(bad!=-1) {
                    Toast.makeText(RhythmActivity.this, "Bad", Toast.LENGTH_SHORT).show();
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
            image.animate().translationY(900).setDuration(2000).setInterpolator(AnimationUtils.loadInterpolator(RhythmActivity.this, android.R.anim.linear_interpolator)).start();
        }
    }

    private void end(final ImageView image, int var){
        isOnView[var] = false;
        isPerfect[var] = false;
        isGood[var] = false;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            image.animate().translationY(-900).setDuration(0).withEndAction(new Runnable() {
                @Override
                public void run() {
                    image.setVisibility(View.INVISIBLE);
                }
            });
        }


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
        parentLayout = (FrameLayout) findViewById(R.id.parentLayout);

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

        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);

        tv_combo = (TextView) findViewById(R.id.tv_combo);
        tv_timer = (TextView) findViewById(R.id.tv_timer);
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

    }

    @Override
    public void onkey5() {

    }

    @Override
    public void onKeyBack() {
        onBackPressed();
    }
}
