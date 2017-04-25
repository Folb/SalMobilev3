package com.example.folb.salmobile.supportClasses;

import android.os.Bundle;

import com.example.folb.salmobile.R;

/**
 * Created by folb on 29.03.17.
 */

public class Support {

    private static int[] allLiceTypes = {100, 103, 106, 109, 112};
    private static int[] taskIcons = {
            R.string.dead_fish_icon,
            R.string.enviroment_icon,
            R.string.agd_icon,
            R.string.louse_counting_icon,
            R.string.own_def_icon,
            R.string.settings_icon};

    public static int[] getAllLiceTypes() {
        return allLiceTypes;
    }

    public static int[] getTaskIcons() {
        return taskIcons;
    }

    public static Bundle getNavbarBundle(int fst, int snd, int thr, boolean inCounting) {
        Bundle b = new Bundle();
        b.putInt("fst", fst);
        b.putInt("snd", snd);
        b.putInt("thr", thr);
        b.putBoolean("inCounting", inCounting);
        return b;
    }
}
