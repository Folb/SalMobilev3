package com.example.folb.salmobile.supportClasses;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;

import com.example.folb.salmobile.R;

import java.util.ArrayList;

/**
 * Created by folb on 29.03.17.
 */

public class UI {

    private static String ret;

    private static final String TAG = "UI";
    private static int[] louseButtonColor = {
            R.color.blue,
            R.color.orange_red,
            R.color.yellow,
            R.color.green,
            R.color.orange,
            R.color.grey
    };

    public static int[] getLouseButtonColor() {
        return louseButtonColor;
    }


    public static ArrayList<Integer> getDims(GridLayout grid) {
        ArrayList<Integer> dims = new ArrayList<>();
        float width = grid.getWidth();
        float height = grid.getHeight();

        int rows = grid.getRowCount();
        int columns = grid.getColumnCount();
        double mp = 0.025;
        double w, h, vm, hm;
        vm = height*mp;
        hm = width*mp;
        w = (width - ((columns + 1) * (mp * width)))/columns;
        h = (height - ((rows + 1) * (mp * height)))/rows;

        dims.add((int) w);
        dims.add((int) h);
        dims.add((int) hm);
        dims.add((int) vm);

        return dims;
    }

    private static void setReturnString(String s) {
        ret = s;
    }
}
