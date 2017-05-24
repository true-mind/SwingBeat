package com.truemind.swingbeat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.truemind.swingbeat.BaseActivity;
import com.truemind.swingbeat.R;
import com.truemind.swingbeat.util.OnSingleClickListener;

/**
 * Created by 현석 on 2017-05-24.
 */

public class TestResult extends BaseActivity {

    private int score;
    private TextView scoreView;
    private TextView grade;
    private LinearLayout btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_result);
        score = getIntent().getIntExtra("score", 0);

        initView();
        initListener();

    }

    public void initView() {
        TextView scoreTitle = (TextView) findViewById(R.id.scoreTitle);
        TextView scoreBy25 = (TextView) findViewById(R.id.scoreBy25);
        TextView title = (TextView)findViewById(R.id.title);
        TextView textTable = (TextView)findViewById(R.id.textTable);
        scoreView = (TextView) findViewById(R.id.score);
        TextView gradeTitle = (TextView) findViewById(R.id.gradeTitle);
        grade = (TextView) findViewById(R.id.grade);
        TextView btnText = (TextView) findViewById(R.id.btnText);
        btnBack = (LinearLayout) findViewById(R.id.btnBack);

        setFontToViewBold(scoreTitle, scoreBy25, scoreView, grade, gradeTitle, btnText, title, textTable);
        gradeCutter(score);
        scoreView.setText(Integer.toString(score));
    }

    public void initListener() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TestIntro.class));
                finish();
            }
        });
    }

    public void gradeCutter(int score) {

        if (score < 6) {
            grade.setText("아주 나쁨");
        } else if (score < 13) {
            grade.setText("나쁨");
        } else if (score < 20) {
            grade.setText("보통");
        } else {
            grade.setText("우수");
        }

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
