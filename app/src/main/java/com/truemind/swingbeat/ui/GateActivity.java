package com.truemind.swingbeat.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.truemind.swingbeat.BaseActivity;
import com.truemind.swingbeat.Constants;
import com.truemind.swingbeat.R;

public class GateActivity extends BaseActivity {

    private TextView txtGoHomePage;
    private ImageButton btnDrum;
    private ImageButton btnGame;
    private ImageButton btnTest;
    private ImageButton btnBT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate);

        initView();
        initListener();
    }

    public void initView(){

        TextView txtDrumTitle = (TextView)findViewById(R.id.textDrumTitle);
        TextView txtDrum1 = (TextView)findViewById(R.id.textDrumText1);
        TextView txtDrum2 = (TextView)findViewById(R.id.textDrumText2);
        TextView txtGameTitle = (TextView)findViewById(R.id.textGameTitle);
        TextView txtGame1 = (TextView)findViewById(R.id.textGameText1);
        TextView txtGame2 = (TextView)findViewById(R.id.textGameText2);
        TextView txtTestTitle = (TextView)findViewById(R.id.textTestTitle);
        TextView txtTest1 = (TextView)findViewById(R.id.textTestText1);
        TextView txtTest2 = (TextView)findViewById(R.id.textTestText2);

        txtGoHomePage = (TextView)findViewById(R.id.txtGoHomePage);
        btnDrum = (ImageButton)findViewById(R.id.btnDrum);
        btnGame = (ImageButton)findViewById(R.id.btnGame);
        btnTest = (ImageButton)findViewById(R.id.btnTest);
        btnBT = (ImageButton)findViewById(R.id.btnBT);

        setFontToViewBold(txtDrumTitle, txtDrum1, txtDrum2, txtGameTitle, txtGame1,
                txtGame2, txtTestTitle, txtTestTitle, txtTest1, txtTest2, txtGoHomePage);

    }

    public void initListener(){

        txtGoHomePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Constants.MINTO_WEB_LINK)));
            }
        });

        btnBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBTSetting();
            }
        });

        btnDrum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    onkey
}
