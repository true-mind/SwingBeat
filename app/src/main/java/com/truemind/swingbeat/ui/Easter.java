package com.truemind.swingbeat.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.truemind.swingbeat.BaseActivity;
import com.truemind.swingbeat.R;

/**
 * Created by 현석 on 2017-05-25.
 */

public class Easter extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easter);

        TextView easter_foot = (TextView)findViewById(R.id.easter_foot);
        setFontToViewBold(easter_foot);
    }

    @Override
    public void onkey1() {
        finish();
    }

    @Override
    public void onkey2() {
        finish();
    }

    @Override
    public void onkey3() {
        finish();
    }

    @Override
    public void onkey4() {
        finish();
    }

    @Override
    public void onkey5() {
        finish();
    }

    @Override
    public void onkey6() {
        finish();
    }

    @Override
    public void onkey7() {
        finish();
    }

    @Override
    public void onkey8() {
        finish();
    }

    @Override
    public void onkey9() {
        finish();
    }

    @Override
    public void onkey10() {
        finish();
    }

    @Override
    public void onKeyBack() {
        finish();
    }
}
