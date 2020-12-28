package com.vector.common.app;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

/**
 * @author Vector Weng
 * @description:
 * @date :2020/12/9 17:16
 */
public class Vector {

    public static final Configurator init(Context context) {
        getConfigurator()
                .getConfigs()
                .put(ConfigType.APPLICATION_CONTEXT, context.getApplicationContext());
        return Configurator.getInstance();
    }

    public static Configurator getConfigurator() {
        return Configurator.getInstance();
    }

    public static <T> T getConfiguration(ConfigType key) {
        return getConfigurator().getConfiguration(key);
    }

    public static Application getApplicationContext() {
        return getConfiguration(ConfigType.APPLICATION_CONTEXT);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigType.HANDLER);
    }


}
