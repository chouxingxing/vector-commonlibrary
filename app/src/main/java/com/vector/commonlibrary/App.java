package com.vector.commonlibrary;

import android.app.Application;

import androidx.annotation.Nullable;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.DiskLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
import com.vector.common.app.Vector;
import com.vector.common.utils.log.LoggerUtil;

/**
 * @author Vector Weng
 * @description:
 * @date :2020/12/21 16:49
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Vector.init(this)
                .withLoaderDelayed(2000)
                .initLogger(LoggerUtil.LoggerLevel.VERBOSE)
                .withApiHost("http://192.168.31.80:20002/api/")
                .configure();
    }


}
