package com.joint.turman.customwidget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

/**
 * Created by dqf on 2016/3/3.
 */
public class FontAwesomeUtils {

    private static Typeface font;

    public static Typeface getFont(Context context) {
        if (font == null) {
            try {
                font = Typeface.createFromAsset(context.getAssets(), "fontawesome-webfont.ttf");
            } catch (Exception e) {
                Log.e("BButton", "Could not get typeface because " + e.getMessage());
                font = Typeface.DEFAULT;
            }
        }
        return font;
    }

}