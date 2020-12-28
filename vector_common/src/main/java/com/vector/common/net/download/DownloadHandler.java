package com.vector.common.net.download;

import android.os.AsyncTask;

import com.vector.common.net.RestCreator;
import com.vector.common.net.callback.IError;
import com.vector.common.net.callback.IFailure;
import com.vector.common.net.callback.IRequest;
import com.vector.common.net.callback.ISuccess;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 瓮少波 on 2018/4/25.
 */

public class DownloadHandler {


    private final String URL;
    private final Map<String, Object> PARAMS;
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final IError ERROR;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;

    public DownloadHandler(String URL,
                           Map<String, Object> PARAMS,
                           IRequest REQUEST,
                           String DOWNLOAD_DIR,
                           String EXTENSION,
                           String NAME,
                           IError ERROR,
                           ISuccess SUCCESS,
                           IFailure FAILURE) {
        this.URL = URL;
        this.PARAMS = PARAMS;
        this.REQUEST = REQUEST;
        this.DOWNLOAD_DIR = DOWNLOAD_DIR;
        this.EXTENSION = EXTENSION;
        this.NAME = NAME;
        this.ERROR = ERROR;
        this.SUCCESS = SUCCESS;
        this.FAILURE = FAILURE;
    }

    public final void handlerDownload() {

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        RestCreator.getRestService().download(URL, PARAMS).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    final ResponseBody responseBody = response.body();
                    final SaveFileTask task = new SaveFileTask(REQUEST, SUCCESS);
                    task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, DOWNLOAD_DIR, EXTENSION, response, NAME);
                    if (task.isCancelled()) {
                        if (REQUEST != null) {
                            REQUEST.onRequestEnd();
                        }
                    }
                } else {
                    if (ERROR != null) {
                        ERROR.onError(response.code(), response.message());
                    }
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                if (FAILURE != null) {
                    FAILURE.onFailure();
                }

            }
        });

    }

}
