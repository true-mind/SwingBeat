package com.truemind.swingbeat.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
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
 * Created by 현석 on 2017-05-19.
 */

public class DrumSetting extends BaseActivity{

    private TextView settingTitle;
    private TextView settingItem1;
    private TextView settingItem2;
    private TextView settingItem3;
    private TextView settingItem4;
    private TextView settingItem5;
    private TextView settingItem6;

    Spinner spinner1;
    Spinner spinner2;
    Spinner spinner3;
    Spinner spinner4;
    Spinner spinner5;
    Spinner spinner6;

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
        soundTrackData.add("Metronome");

        spinner1 = (Spinner)findViewById(R.id.spinner1);
        spinner2 = (Spinner)findViewById(R.id.spinner2);
        spinner3 = (Spinner)findViewById(R.id.spinner3);
        spinner4 = (Spinner)findViewById(R.id.spinner4);
        spinner5 = (Spinner)findViewById(R.id.spinner5);
        spinner6 = (Spinner)findViewById(R.id.spinner6);

        settingTitle = (TextView)findViewById(R.id.settingTitle);
        settingItem1 = (TextView)findViewById(R.id.settingItem1);
        settingItem2 = (TextView)findViewById(R.id.settingItem2);
        settingItem3 = (TextView)findViewById(R.id.settingItem3);
        settingItem4 = (TextView)findViewById(R.id.settingItem4);
        settingItem5 = (TextView)findViewById(R.id.settingItem5);
        settingItem6 = (TextView)findViewById(R.id.settingItem6);

        setFontToViewBold(settingTitle, settingItem1, settingItem2, settingItem3, settingItem4, settingItem5, settingItem6);

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
    public void onKeyBack() {
        goBack();
    }
}
