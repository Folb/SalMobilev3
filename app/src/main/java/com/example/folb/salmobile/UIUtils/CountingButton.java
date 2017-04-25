package com.example.folb.salmobile.UIUtils;

/**
 * Created by folb on 13.04.17.
 */

public class CountingButton extends AButton {

    private int tracker;
    private int lowerText;
    private int color;

    public CountingButton(int color, int lowerText) {
        this.color = color;
        this.lowerText = lowerText;
        tracker = 0;
    }

    public int addToTracker() {
        return ++tracker;
    }

    public int removeToTracker() {
        return --tracker;
    }

    public int getTracker() {
        return tracker;
    }

    public void setTracker(int tracker) {
        this.tracker = tracker;
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
