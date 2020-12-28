package com.vector.common.app;

import android.content.Context;
import android.os.Handler;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

/**
 * @author Vector Weng
 * @description: App initialization general configuration class
 * @date :2020/12/9 17:16
 */
public class Configurator {

    private static final HashMap<ConfigType, Object> VECTOR_CONFIGS = new HashMap<>();
    private static final Handler HANDLER = new Handler();

    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

    private Configurator() {
        VECTOR_CONFIGS.put(ConfigType.CONFIG_READY, false);
        VECTOR_CONFIGS.put(ConfigType.HANDLER, HANDLER);

    }

    private static final class Holder {
        private static final Configurator INSTANCE = new Configurator();
    }

    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    public final void configure() {
        initIcons();
        VECTOR_CONFIGS.put(ConfigType.CONFIG_READY, true);
    }

    final HashMap<ConfigType, Object> getConfigs() {
        return VECTOR_CONFIGS;
    }

    public final Configurator initLogger(int level) {
        initLogger(level, null);
        return this;
    }

    public final Configurator initLogger(int level, FormatStrategy formatStrategy) {
        if (formatStrategy != null) {
            Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
        } else {
            Logger.addLogAdapter(new AndroidLogAdapter());
        }
        VECTOR_CONFIGS.put(ConfigType.LOGGER_LEVEL, level);
        return this;
    }

    public final Configurator withContext(Context context) {
        VECTOR_CONFIGS.put(ConfigType.APPLICATION_CONTEXT, context);
        return this;
    }

    public final Configurator withApiHost(String host) {
        VECTOR_CONFIGS.put(ConfigType.API_HOST, host);
        return this;
    }

    public final Configurator withLoaderDelayed(long delayed) {
        VECTOR_CONFIGS.put(ConfigType.LOADER_DELAYED, delayed);
        return this;
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        VECTOR_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptor(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        VECTOR_CONFIGS.put(ConfigType.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    private void checkConfiguration() {
        final boolean isReady = (boolean) VECTOR_CONFIGS.get(ConfigType.CONFIG_READY);
        if (!isReady)
            throw new RuntimeException("Configuration is not ready,call configure");
    }

    final <T> T getConfiguration(ConfigType key) {
        checkConfiguration();
        return (T) VECTOR_CONFIGS.get(key);
    }


}
