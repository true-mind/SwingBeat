package com.truemind.swingbeat.ui.rhythm;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.Display;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.truemind.swingbeat.BaseActivity;
import com.truemind.swingbeat.R;

import java.util.ArrayList;

/**
 * Created by 현석 on 2017-05-25.
 */

public class NewRhythmActivity extends BaseActivity{


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

    private TextView tv;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv_combo;
    private TextView tv_current;
    private TextView tv_quit;

    private Handler media_handler;
    private Handler end_handler;
    private Handler good_handler;
    private Handler perfect_handler;

    private boolean[] isOnView;
    private boolean[] isGood;
    private boolean[] isPerfect;

    private ArrayList<ImageView> moveList;

    private int combo;
    private int countdown = 3;
    private long sec;
    private int beat = 1000;

    private int mIndex1;
    private int mIndex2;
    private int mIndex3;

    private int maxCombo;
    private int perfect_count;
    private int good_count;
    private int bad_count;

    private int lane_width;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rhythm_new);

    }

    public void initView(){
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

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        lane_width = size.x * 516 / 640;

        setFontToViewBold(tv_combo, tv_quit, tv_current, tv, tv1, tv2, tv3);
    }

    public void initListener(){

    }

    @Override
    public void onkey1() {

    }

    @Override
    public void onkey2() {

    }

    @Override
    public void onkey3() {

    }

    @Override
    public void onkey4() {

    }

    @Override
    public void onkey5() {

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

    }
}
