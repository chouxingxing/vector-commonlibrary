package com.vector.common.utils.display;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import com.vector.common.app.Vector;

/**
 * Created by 瓮少波 on 2018/4/25.
 */

public class DimenUtil {

    public static int getScreenWidth() {
        final Resources resources = Vector.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }


    public static int getScreenHeight() {
        final Resources resources = Vector.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }

    ;

}
