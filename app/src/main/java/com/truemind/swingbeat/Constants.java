package com.truemind.swingbeat;

/**
 * Created by 현석 on 2017-05-19.
 */

public class Constants {

    public final static String MINTO_WEB_LINK = "http://www.mintosys.com/blogPost/swingair";

    public static final int KICK = 1;
    public static final int SNARE = 2;
    public static final int HIHAT = 3;
    public static final int HIHAT_OPEN = 4;
    public static final int SMALL_TOM = 5;
    public static final int MIDDLE_TOM = 6;
    public static final int FLOOR_TOM = 7;
    public static final int CRASH_16 = 8;
    public static final int CRASH_18 = 9;
    public static final int RIDE = 10;
    public static final int RIMSHOT = 11;
    public static final int BRUSH = 12;

    public static int DRUM_KEY1 = 3;
    public static int DRUM_KEY2 = 11;
    public static int DRUM_KEY3 = 9;
    public static int DRUM_KEY4 = 10;
    public static int DRUM_KEY5 = 1;
    public static int DRUM_KEY6 = 2;
    public static int DRUM_KEY7 = 5;
    public static int DRUM_KEY8 = 6;
    public static int DRUM_KEY9 = 7;
    public static int DRUM_KEY10 = 12;

    public static int DRUM_SOUND_TRACK = 6;

    public static void initDrumSound(int key1, int key2, int key3, int key4, int key5){
        DRUM_KEY1 = key1;
        DRUM_KEY2 = key2;
        DRUM_KEY3 = key3;
        DRUM_KEY4 = key4;
        DRUM_KEY5 = key5;
    }

    public static int TIMER = 1000;
    public static int TIMER_SPINNER_POSITION = 2;

    public static int RHYTHM_MAX_COMBO = 0;
    public static int RHYTHM_PERFECT = 0;
    public static int RHYTHM_GOOD = 0;
    public static int RHYTHM_BAD = 0;
}
