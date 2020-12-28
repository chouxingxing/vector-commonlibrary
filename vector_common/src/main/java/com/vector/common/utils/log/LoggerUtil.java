package com.vector.common.utils.log;

import android.util.Log;

import com.orhanobut.logger.Logger;
import com.vector.common.BuildConfig;
import com.vector.common.app.ConfigType;
import com.vector.common.app.Vector;

/**
 * @author Vector Weng
 * @description: 日志工具类
 * @date :2020/12/22 18:41
 */
public class LoggerUtil {

    private static final String TAG = LoggerUtil.class.getSimpleName();

    public static class LoggerLevel {
        public static final int VERBOSE = 1;
        public static final int DEBUG = 2;
        public static final int INFO = 3;
        public static final int WARN = 4;
        public static final int ERROR = 5;
        public static final int NOTHING = 6;
    }

    private static Integer LOG_LEVEL = Vector.getConfiguration(ConfigType.LOGGER_LEVEL);

    private static Integer checkLoggerLevel() {
        if (LOG_LEVEL == null) {
            Log.e(TAG, "Vector_CommonLibrary----------Logger is not init");
        }
        return LOG_LEVEL;
    }

    public static void v(String tag, String message) {
        if (checkLoggerLevel() != null && checkLoggerLevel() <= LoggerLevel.VERBOSE) {
            Logger.t(tag).v(message);
        }
    }

    public static void d(String tag, Object message) {
        if (checkLoggerLevel() != null && checkLoggerLevel() <= LoggerLevel.DEBUG) {
            Logger.t(tag).d(message);
        }
    }

    public static void d(Object message) {
        if (checkLoggerLevel() != null && checkLoggerLevel() <= LoggerLevel.DEBUG) {
            Logger.d(message);
        }
    }

    public static void i(String tag, String message) {
        if (checkLoggerLevel() != null && checkLoggerLevel() <= LoggerLevel.INFO) {
            Logger.t(tag).i(message);
        }
    }

    public static void w(String tag, String message) {
        if (checkLoggerLevel() != null && checkLoggerLevel() <= LoggerLevel.WARN) {
            Logger.t(tag).w(message);
        }
    }

    public static void json(String tag, String message) {
        if (checkLoggerLevel() != null && checkLoggerLevel() <= LoggerLevel.WARN) {
            Logger.t(tag).json(message);
        }
    }

    public static void e(String tag, String message) {
        if (checkLoggerLevel() != null && checkLoggerLevel() <= LoggerLevel.ERROR) {
            Logger.t(tag).e(message);
        }
    }

}
