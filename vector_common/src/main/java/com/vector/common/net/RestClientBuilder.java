package com.vector.common.net;

import android.content.Context;


import com.vector.common.net.callback.IError;
import com.vector.common.net.callback.IFailure;
import com.vector.common.net.callback.IRequest;
import com.vector.common.net.callback.ISuccess;
import com.vector.common.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by 瓮少波 on 2018/4/24.
 */

public class RestClientBuilder {

    private String mUrl = null;
    private static final Map<String, Object> PARAMS = RestCreator.getParams();
    private IRequest mRequest = null;
    private IError mError = null;
    private ISuccess mSuccess = null;
    private IFailure mFailure = null;
    private RequestBody mBoby = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;
    private File mFile = null;
    private String mName = null;
    private String mDownload_dir = null;
    private String mExtension = null;

    RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {

        this.mUrl = url;
        return this;

    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {

        PARAMS.putAll(params);
        return this;

    }

    public final RestClientBuilder params(String key, Object value) {


        PARAMS.put(key, value);
        return this;

    }

    public final RestClientBuilder file(File file) {

        this.mFile = file;
        return this;

    }

    public final RestClientBuilder file(String path) {

        this.mFile = new File(path);
        return this;

    }

    public final RestClientBuilder name(String name) {

        this.mName = name;
        return this;

    }

    public final RestClientBuilder dir(String dir) {

        this.mDownload_dir = dir;
        return this;

    }

    public final RestClientBuilder extension(String extension) {

        this.mExtension = extension;
        return this;

    }

    public final RestClientBuilder raw(String raw) {

        this.mBoby = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;

    }

    public final RestClientBuilder onRequest(IRequest iRequest) {

        this.mRequest = iRequest;
        return this;

    }

    public final RestClientBuilder success(ISuccess iSuccess) {

        this.mSuccess = iSuccess;
        return this;

    }

    public final RestClientBuilder failure(IFailure iFailure) {

        this.mFailure = iFailure;
        return this;

    }

    public final RestClientBuilder error(IError iError) {

        this.mError = iError;
        return this;

    }

    public final RestClientBuilder loader(Context context, LoaderStyle loaderStyle) {
        this.mContext = context;
        this.mLoaderStyle = loaderStyle;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallSpinFadeLoaderIndicator;
        return this;
    }

    public final RestClient bulid() {
        return new RestClient(
                mUrl,
                PARAMS,
                mDownload_dir,
                mExtension,
                mName,
                mRequest,
                mError,
                mSuccess,
                mFailure,
                mBoby,
                mLoaderStyle,
                mFile,
                mContext);

    }

}


