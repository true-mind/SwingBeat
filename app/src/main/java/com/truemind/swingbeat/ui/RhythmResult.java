package com.truemind.swingbeat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.truemind.swingbeat.BaseActivity;
import com.truemind.swingbeat.Constants;
import com.truemind.swingbeat.R;

/**
 * Created by 현석 on 2017-05-25.
 */

public class RhythmResult extends BaseActivity {

    private TextView title;
    private TextView comboTitle;
    private TextView combo;
    private TextView perfect;
    private TextView perfectTitle;
    private TextView good;
    private TextView goodTitle;
    private TextView bad;
    private TextView badTitle;
    private TextView total;
    private TextView totalTitle;
    private LinearLayout btnMain;
    private TextView btnText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rhythm_result);

        initView();
        initListener();

    }

    public void initView(){

        title = (TextView)findViewById(R.id.title);
        combo = (TextView)findViewById(R.id.combo);
        comboTitle = (TextView)findViewById(R.id.comboTitle);
        perfect = (TextView)findViewById(R.id.perfect);
        perfectTitle = (TextView)findViewById(R.id.perfectTitle);
        good = (TextView)findViewById(R.id.good);
        goodTitle = (TextView)findViewById(R.id.goodTitle);
        bad = (TextView)findViewById(R.id.bad);
        badTitle = (TextView)findViewById(R.id.badTitle);
        total = (TextView)findViewById(R.id.total);
        totalTitle = (TextView)findViewById(R.id.totalTitle);
        btnMain = (LinearLayout)findViewById(R.id.btnMain);
        btnText = (TextView)findViewById(R.id.btnText);


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
        int totalScore = Constants.RHYTHM_PERFECT + Constants.RHYTHM_GOOD;
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
