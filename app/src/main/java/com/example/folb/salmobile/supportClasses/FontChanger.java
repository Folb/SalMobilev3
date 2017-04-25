package com.example.folb.salmobile.supportClasses;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;

/**
 * Created by Follo on 1/7/2017.
 */

public class FontChanger {

    public static final int FONT_NAME_1 = 0;
    public static final int FONT_NAME_2 = 1;
    public static final int FONT_NAME_3 = 2;

    private static final int NUM_OF_CUSTOM_FONTS = 3;

    private static boolean fontsLoaded = false;

    private static Typeface[] fonts = new Typeface[NUM_OF_CUSTOM_FONTS];

    private static String[] fontPath = {
            "fonts/Lato-Heavy.ttf",
            "fonts/Lato-Regular.ttf",
            "fonts/fontawesome-webfont.ttf"
    };

    public static Typeface getTypeface(Activity activity, int fontIdentifier) {
        if (!fontsLoaded) {
            loadFonts(activity);
        }
        return fonts[fontIdentifier];
    }

    private static void loadFonts(Activity activity) {
        for (int i = 0; i < NUM_OF_CUSTOM_FONTS; i++) {
            fonts[i] = Typeface.createFromAsset(activity.getAssets(), fontPath[i]);
        }
        fontsLoaded = true;

    }

}
