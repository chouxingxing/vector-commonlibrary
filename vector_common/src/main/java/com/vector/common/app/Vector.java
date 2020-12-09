package com.vector.common.app;

import android.content.Context;

import java.util.HashMap;

/**
 * @author Vector Weng
 * @description:
 * @date :2020/12/9 17:16
 */
public class Vector {

    public static final Configurator init(Context context) {
        return Configurator.getInstance();
    }

    public static HashMap<String, Object> getConfigs() {
        return Configurator.getInstance().getConfigs();
    }

    public static Context getApplicationContext() {
        return (Context) getConfigs().get(ConfigType.APPLICATION_CONTEXT);

    }

}
