package com.truemind.swingbeat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.truemind.swingbeat.BaseActivity;
import com.truemind.swingbeat.Constants;
import com.truemind.swingbeat.R;
import com.truemind.swingbeat.util.AdapterSpinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 현석 on 2017-05-24.
 */

public class TestIntro extends BaseActivity {

    private LinearLayout btnStart;
    private Spinner timerSet;

    AdapterSpinner adapterSpinner;
    List<String> timeData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_intro);

        initView();
        initListener();

    }

    public void initView() {
        timeData.add("600");
        timeData.add("800");
        timeData.add("1000");
        timeData.add("1200");
        timeData.add("1400");
        timeData.add("1600");
        timeData.add("1800");
        timeData.add("2000");
        timeData.add("2500");
        timeData.add("3000");
        adapterSpinner = new AdapterSpinner(getContext(), timeData);

        TextView title = (TextView) findViewById(R.id.title);
        TextView text1 = (TextView) findViewById(R.id.text1);
        TextView text2 = (TextView) findViewById(R.id.text2);
        TextView text3 = (TextView) findViewById(R.id.text3);
        TextView text4 = (TextView) findViewById(R.id.text4);
        TextView text5 = (TextView) findViewById(R.id.text5);
        TextView text6 = (TextView) findViewById(R.id.text6);
        TextView text7 = (TextView) findViewById(R.id.text7);
        TextView timer = (TextView) findViewById(R.id.timer);
        TextView btnText = (TextView) findViewById(R.id.btnText);
        timerSet = (Spinner) findViewById(R.id.timerSet);
        timerSet.setAdapter(adapterSpinner);
        timerSet.setSelection(Constants.TIMER_SPINNER_POSITION);

        btnStart = (LinearLayout) findViewById(R.id.btnStart);

        setFontToViewBold(text1, text2, text3, text4, text5, text6, text7, btnText, timer);
        setFontToViewBold2(title);
    }

    public void initListener() {
        timerSet.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Constants.TIMER = Integer.parseInt(timeData.get(position));
                Constants.TIMER_SPINNER_POSITION = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TestActivity.class));
                finish();
            }
        });
    }

    private void goBack() {
        startActivity(new Intent(getContext(), GateActivity.class));
        finish();
    }


    @Override
    public void onkey1() {
        goBack();
    }

    @Override
    public void onkey2() {
        goBack();
    }

    @Override
    public void onkey3() {
        timerSet.performClick();
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
        goBack();
    }
}
