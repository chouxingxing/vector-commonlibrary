package com.vector.common.ui.loader;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;


import androidx.appcompat.app.AppCompatDialog;

import com.vector.common.R;
import com.vector.common.utils.display.DimenUtil;
import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.indicators.BallPulseIndicator;

import java.util.ArrayList;

/**
 * Created by 瓮少波 on 2018/4/25.
 */

public class LoaderManager {

    private static final int LOADER_SIZE_SCALE = 15;
    private static final int LOADER_OFFSET_SCALE = 15;

    private static final ArrayList<AppCompatDialog> LOADERS = new ArrayList<>();

    private static final String DEFAULT_LOADER = LoaderStyle.BallSpinFadeLoaderIndicator.name();

    public static void showLoading(Context context, String type) {

        final AppCompatDialog dialog = new AppCompatDialog(context, R.style.loader_dialog);

        final AVLoadingIndicatorView avLoadingIndicatorView = LoaderCreator.create(type, context);
        dialog.setContentView(avLoadingIndicatorView);

        int deviceWidth = DimenUtil.getScreenWidth();
        int deviceHeight = DimenUtil.getScreenHeight();
        final Window dialogWindow = dialog.getWindow();

        if (dialogWindow != null) {
            WindowManager.LayoutParams lp = dialogWindow.getAttributes();
            lp.width = deviceWidth / LOADER_SIZE_SCALE;
            lp.height = deviceHeight / LOADER_SIZE_SCALE + deviceHeight / LOADER_OFFSET_SCALE;
            lp.gravity = Gravity.CENTER;
        }
        LOADERS.add(dialog);
        dialog.show();

    }

    public static void showLoading(Context context, LoaderStyle loaderStyle) {
        showLoading(context, loaderStyle.name());
    }

    public static void showLoading(Context context) {
        showLoading(context, DEFAULT_LOADER);
    }

    public static void stopLoading() {
        for (AppCompatDialog dialog : LOADERS) {
            if (dialog != null) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
            }
        }
    }
}
