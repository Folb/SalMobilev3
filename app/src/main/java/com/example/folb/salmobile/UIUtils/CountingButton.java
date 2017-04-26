package com.example.folb.salmobile.UIUtils;

/**
 * Created by folb on 13.04.17.
 */

public class CountingButton extends AButton {

    private int lowerText;
    private int color;

    public CountingButton(int color, int lowerText) {
        this.color = color;
        this.lowerText = lowerText;
    }

    public int getLowerText() {
        return lowerText;
    }

    public void setLowerText(int lowerText) {
        this.lowerText = lowerText;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
