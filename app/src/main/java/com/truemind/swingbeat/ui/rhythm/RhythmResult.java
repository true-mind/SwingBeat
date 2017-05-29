package com.truemind.swingbeat.ui.rhythm;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.truemind.swingbeat.BaseActivity;
import com.truemind.swingbeat.Constants;
import com.truemind.swingbeat.R;
import com.truemind.swingbeat.ui.GateActivity;

/**
 * Created by 현석 on 2017-05-25.
 */

public class RhythmResult extends BaseActivity {
    private SoundPool soundPool;
    private TextView combo;
    private TextView perfect;
    private TextView good;
    private TextView bad;
    private TextView total;
    private LinearLayout btnMain;

    private static int track;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rhythm_result);

        initView();
        initListener();
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
        soundPool = build(1, AudioManager.STREAM_MUSIC, 0);
        track = soundPool.load(getContext(), R.raw.applause, 1);
        soundPool.play(track, 1, 1, 1, 0, 1);
    }

    public void initView(){

        TextView title = (TextView) findViewById(R.id.title);
        TextView comboTitle = (TextView) findViewById(R.id.comboTitle);
        TextView perfectTitle = (TextView) findViewById(R.id.perfectTitle);
        TextView goodTitle = (TextView) findViewById(R.id.goodTitle);
        TextView badTitle = (TextView) findViewById(R.id.badTitle);
        TextView totalTitle = (TextView) findViewById(R.id.totalTitle);
        TextView btnText = (TextView) findViewById(R.id.btnText);

        combo = (TextView)findViewById(R.id.combo);
        perfect = (TextView)findViewById(R.id.perfect);
        good = (TextView)findViewById(R.id.good);
        bad = (TextView)findViewById(R.id.bad);
        total = (TextView)findViewById(R.id.total);
        btnMain = (LinearLayout)findViewById(R.id.btnMain);


        setFontToViewBold(combo, comboTitle, perfect, perfectTitle, good, goodTitle,
                bad, badTitle, total, totalTitle, btnText);
        setFontToViewBold2(title);

        initScoreBoard();
    }

    public void initScoreBoard(){

        combo.setText(Integer.toString(Constants.RHYTHM_MAX_COMBO));
        perfect.setText(Integer.toString(Constants.RHYTHM_PERFECT));
        good.setText(Integer.toString(Constants.RHYTHM_GOOD));
        bad.setText(Integer.toString(Constants.RHYTHM_BAD));
        int totalScore = Constants.RHYTHM_PERFECT + Constants.RHYTHM_GOOD + Constants.RHYTHM_BAD;
        total.setText(Integer.toString(totalScore));

    }

    public void initListener(){

        btnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

    }

    public void goBack(){
        startActivity(new Intent(getContext(), GateActivity.class));
        finish();
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
