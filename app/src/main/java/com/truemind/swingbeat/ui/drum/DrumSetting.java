package com.truemind.swingbeat.ui.drum;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.truemind.swingbeat.BaseActivity;
import com.truemind.swingbeat.Constants;
import com.truemind.swingbeat.R;
import com.truemind.swingbeat.util.AdapterSpinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 현석 on 2017-05-19.
 */

public class DrumSetting extends BaseActivity{

    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;
    Spinner spinner4;
    Spinner spinner5;
    Spinner spinner6;
    Spinner spinner7;
    Spinner spinner8;
    Spinner spinner9;
    Spinner spinner10;
    Spinner spinner11;

    AdapterSpinner adapterSpinner;
    AdapterSpinner adapterSpinner2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drum_setting);

        initView();
        initListener();

    }

    public void initView(){

        List<String> midiData = new ArrayList<>();
        midiData.add("Kick"); midiData.add("Snare"); midiData.add("Hi-hat"); midiData.add("Small Tom"); midiData.add("Middle Tom");
        midiData.add("Floor Tom"); midiData.add("16-inches Crash"); midiData.add("18-inches Crash"); midiData.add("Ride"); midiData.add("Stick");

        List<String> soundTrackData = new ArrayList<>();
        soundTrackData.add("Funk1");
        soundTrackData.add("Jazz1");
        soundTrackData.add("Rock1");
        soundTrackData.add("Slow Rock1");
        soundTrackData.add("BossaNova1");
        soundTrackData.add("BossaNova2");

        spinner1 = (Spinner)findViewById(R.id.spinner1);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner3 = (Spinner)findViewById(R.id.spinner3);
        spinner4 = (Spinner)findViewById(R.id.spinner4);
        spinner5 = (Spinner)findViewById(R.id.spinner5);
        spinner6 = (Spinner)findViewById(R.id.spinner6);
        spinner7 = (Spinner)findViewById(R.id.spinner7);
        spinner8 = (Spinner)findViewById(R.id.spinner8);
        spinner9 = (Spinner)findViewById(R.id.spinner9);
        spinner10 = (Spinner)findViewById(R.id.spinner10);
        spinner11 = (Spinner)findViewById(R.id.spinner11);

        TextView settingTitle = (TextView) findViewById(R.id.settingTitle);
        TextView settingItem1 = (TextView) findViewById(R.id.settingItem1);
        TextView settingItem2 = (TextView) findViewById(R.id.settingItem2);
        TextView settingItem3 = (TextView) findViewById(R.id.settingItem3);
        TextView settingItem4 = (TextView) findViewById(R.id.settingItem4);
        TextView settingItem5 = (TextView) findViewById(R.id.settingItem5);
        TextView settingItem6 = (TextView) findViewById(R.id.settingItem6);
        TextView settingItem7 = (TextView) findViewById(R.id.settingItem7);
        TextView settingItem8 = (TextView) findViewById(R.id.settingItem8);
        TextView settingItem9 = (TextView) findViewById(R.id.settingItem9);
        TextView settingItem10 = (TextView) findViewById(R.id.settingItem10);
        TextView settingItem11 = (TextView) findViewById(R.id.settingItem11);
        TextView additional = (TextView) findViewById(R.id.additional);


        setFontToViewBold(settingTitle, settingItem1, settingItem2, settingItem3, settingItem4, settingItem5, settingItem6
                , settingItem7, settingItem8, settingItem9, settingItem10, settingItem11, additional);

        adapterSpinner = new AdapterSpinner(getContext(), midiData);
        adapterSpinner2 = new AdapterSpinner(getContext(), soundTrackData);

        spinner1.setAdapter(adapterSpinner);
        spinner1.setSelection(Constants.DRUM_KEY1-1);
        spinner2.setAdapter(adapterSpinner);
        spinner2.setSelection(Constants.DRUM_KEY2-1);
        spinner3.setAdapter(adapterSpinner);
        spinner3.setSelection(Constants.DRUM_KEY3-1);
        spinner4.setAdapter(adapterSpinner);
        spinner4.setSelection(Constants.DRUM_KEY4-1);
        spinner5.setAdapter(adapterSpinner);
        spinner5.setSelection(Constants.DRUM_KEY5-1);

        spinner6.setAdapter(adapterSpinner2);
        spinner6.setSelection(Constants.DRUM_SOUND_TRACK-1);

        spinner7.setAdapter(adapterSpinner);
        spinner7.setSelection(Constants.DRUM_KEY6-1);
        spinner8.setAdapter(adapterSpinner);
        spinner8.setSelection(Constants.DRUM_KEY7-1);
        spinner9.setAdapter(adapterSpinner);
        spinner9.setSelection(Constants.DRUM_KEY8-1);
        spinner10.setAdapter(adapterSpinner);
        spinner10.setSelection(Constants.DRUM_KEY9-1);
        spinner11.setAdapter(adapterSpinner);
        spinner11.setSelection(Constants.DRUM_KEY10-1);

    }

    public void initListener(){

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Constants.DRUM_KEY1 = (position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Constants.DRUM_KEY2 = (position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Constants.DRUM_KEY3 = (position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Constants.DRUM_KEY4 = (position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Constants.DRUM_KEY5 = (position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Constants.DRUM_SOUND_TRACK = (position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Constants.DRUM_KEY6 = (position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner8.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Constants.DRUM_KEY7 = (position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner9.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Constants.DRUM_KEY8 = (position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner10.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Constants.DRUM_KEY9 = (position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner11.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Constants.DRUM_KEY10 = (position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void goBack(){
        Intent intent = new Intent(getContext(), DrumActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        goBack();
    }

    @Override
    public void onkey1() {
        spinner1.performClick();
    }

    @Override
    public void onkey2() {
        spinner2.performClick();
    }

    @Override
    public void onkey3() {
        spinner3.performClick();
    }

    @Override
    public void onkey4() {
        spinner4.performClick();
    }

    @Override
    public void onkey5() {
        spinner5.performClick();
    }

    @Override
    public void onkey6() {
        spinner7.performClick();
    }

    @Override
    public void onkey7() {
        spinner8.performClick();
    }

    @Override
    public void onkey8() {
        spinner9.performClick();
    }

    @Override
    public void onkey9() {
        spinner10.performClick();
    }

    @Override
    public void onkey10() {
        spinner11.performClick();
    }

    @Override
    public void onKeyBack() {
        goBack();
    }
}
