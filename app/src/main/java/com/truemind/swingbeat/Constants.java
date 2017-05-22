package com.truemind.swingbeat;

/**
 * Created by 현석 on 2017-05-19.
 */

public class Constants {

    public final static String MINTO_WEB_LINK = "http://mintosys.com/";

    public static final int KICK = 1;
    public static final int SNARE = 2;
    public static final int HIHAT = 3;
    public static final int SMALL_TOM = 4;
    public static final int MIDDLE_TOM = 5;
    public static final int FLOOR_TOM = 6;
    public static final int CRASH_16 = 7;
    public static final int CRASH_18 = 8;
    public static final int RIDE = 9;
    public static final int RIMSHOT = 10;

    public static int DRUM_KEY1 = 1;
    public static int DRUM_KEY2 = 2;
    public static int DRUM_KEY3 = 3;
    public static int DRUM_KEY4 = 8;
    public static int DRUM_KEY5 = 9;
    public static int DRUM_SOUND_TRACK = 1;

    public static void initDrumSound(int key1, int key2, int key3, int key4, int key5){
        DRUM_KEY1 = key1;
        DRUM_KEY2 = key2;
        DRUM_KEY3 = key3;
        DRUM_KEY4 = key4;
        DRUM_KEY5 = key5;
    }

}
