package com.example.folb.salmobile.supportClasses;

import android.os.Bundle;
import android.util.Log;

import com.example.folb.salmobile.R;
import com.example.folb.salmobile.models.Fish;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import static com.example.folb.salmobile.fragments.ThreeButtonNavbarFragment.TAG;

/**
 * Created by folb on 29.03.17.
 */

public class Support {

    private static int[] allLiceTypes = {100, 103, 106, 109, 112};
    private static String[] liceTypeNames = {"fs", "lb", "sb", "kh", "sl"};
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

    public static String getLiceTypeName(int id) {
        for (int i = 0; i < allLiceTypes.length; i++) {
            if (allLiceTypes[i] == id) {
                return liceTypeNames[i];
            }
        }
        return null;
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

    public static ArrayList<Integer> calculateTotal(ArrayList<Fish> fishes) {
        ArrayList<Integer> ret = new ArrayList<>();
        for (int id : Support.getAllLiceTypes()) {
            int tot = 0;
            for (int i = 0; i < fishes.size(); i++) {
                tot += fishes.get(i).getLice().get(id);
            }
            ret.add(tot);
        }
        return ret;
    }

    public static ArrayList<Double> calculateAverage(ArrayList<Integer> total, int amount) {
        ArrayList<Double> ret = new ArrayList<>();
        double a = amount;
        for (int i : total) {
            double in = i;
            double d = in/a;
            ret.add(d);
        }
        return ret;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
