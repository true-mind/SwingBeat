package com.truemind.swingbeat.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.truemind.swingbeat.BaseActivity;
import com.truemind.swingbeat.Constants;
import com.truemind.swingbeat.R;
import com.truemind.swingbeat.ui.drum.DrumActivity;
import com.truemind.swingbeat.ui.rhythm.RhythmActivity;
import com.truemind.swingbeat.ui.test.TestIntro;
import com.truemind.swingbeat.util.CommonDialog;

public class GateActivity extends BaseActivity {

    private TextView txtGoHomePage;
    private ImageButton btnDrum;
    private ImageButton btnGame;
    private ImageButton btnTest;
    private ImageButton btnBT;
    private Button btnManual;
    private ImageView gateImage;

    private int i = 0;

    /**for onBackPress*/
    public final long FINISH_INTERVAL_TIME = 2000;
    public long backPressedTime = 0;

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
        btnManual = (Button)findViewById(R.id.btnManual);
        gateImage = (ImageView)findViewById(R.id.gateImage);

        setFontToViewBold(txtDrumTitle, txtDrum1, txtDrum2, txtGameTitle, txtGame1,
                txtGame2, txtTestTitle, txtTestTitle, txtTest1, txtTest2, txtGoHomePage, btnManual);

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
                startActivity(new Intent(getContext(), DrumActivity.class));
                finish();
            }
        });

        btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), RhythmActivity.class));
                finish();
            }
        });

        btnTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), TestIntro.class));
                finish();
            }
        });

        btnManual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommonDialog dialog = new CommonDialog();
                dialog.showDialog(getContext(), "Key Map", getResources().getString(R.string.KeyMap), true, "확인 (Enter)");
            }
        });

        gateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                if(i>4){
                    /** Easter Egg!*/
                    Toast.makeText(getContext(), "Hi Sally!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getContext(), Easter.class);
                    startActivity(intent);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        i = 0;
                    }
                }, 500);
            }
        });

    }

    @Override
    public void onBackPressed() {
        goBack();
    }

    public void goBack(){
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if(0<=intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
        }
        else
        {
            backPressedTime = tempTime;
            Toast.makeText(getContext(), R.string.exitMessage,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onkey1() {
        startActivity(new Intent(getContext(), DrumActivity.class));
        finish();
    }

    @Override
    public void onkey2() {
        startActivity(new Intent(getContext(), RhythmActivity.class));
        finish();
    }

    @Override
    public void onkey3() {
        startActivity(new Intent(getContext(), TestIntro.class));
        finish();
    }

    @Override
    public void onkey4() {
        goBTSetting();
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
        goBack();
    }
}
