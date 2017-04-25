package com.example.folb.salmobile.UIUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.percent.PercentRelativeLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.folb.salmobile.R;
import com.example.folb.salmobile.supportClasses.FontChanger;

import org.w3c.dom.Text;

import static android.content.ContentValues.TAG;

/**
 * Created by folb on 06.04.17.
 */

public class MenuButton {

    private int color;
    private int upperText, lowerText;
    private Class linkedToActivity;
    private Activity activity;

    public MenuButton(Activity activity, int color, int upperText, int lowerText, Class linkedToActivity) {
        this.activity = activity;
        this.color = color;
        this.upperText = upperText;
        this.lowerText = lowerText;
        this.linkedToActivity = linkedToActivity;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getUpperText() {
        return upperText;
    }

    public Class getLinkedToActivity() {
        return linkedToActivity;
    }

    public void setLinkedToActivity(Class linkedToActivity) {
        this.linkedToActivity = linkedToActivity;
    }

    public int getLowerText() {
        return lowerText;
    }
}
