package com.vector.common.net;

import android.content.Context;

import com.vector.common.net.callback.IError;
import com.vector.common.net.callback.IFailure;
import com.vector.common.net.callback.IRequest;
import com.vector.common.net.callback.ISuccess;
import com.vector.common.net.callback.RequestCallbacks;
import com.vector.common.net.download.DownloadHandler;
import com.vector.common.ui.loader.LoaderManager;
import com.vector.common.ui.loader.LoaderStyle;

import java.io.File;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;

/**
 * @author Vector Weng
 * @description:
 * @date :2020/12/9 18:46
 */
public class RestClient {

    private final String URL;
    private final Map<String, Object> PARAMS;
    private final IRequest REQUEST;
    private final String DOWNLOAD_DIR;
    private final String EXTENSION;
    private final String NAME;
    private final IError ERROR;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final RequestBody BOBY;
    private final LoaderStyle LOADER_STYLE;
    private final File FILE;
    private final Context CONTEXT;

    RestClient(String URL,
               Map<String, Object> PARAMS,
               String DOWNLOAD_DIR,
               String EXTENSION,
               String NAME,
               IRequest REQUEST,
               IError ERROR,
               ISuccess SUCCESS,
               IFailure FAILURE,
               RequestBody BOBY,
               LoaderStyle LOADERSTYLE,
               File FILE,
               Context CONTEXT) {
        this.URL = URL;
        this.PARAMS = PARAMS;
        this.DOWNLOAD_DIR = DOWNLOAD_DIR;
        this.EXTENSION = EXTENSION;
        this.NAME = NAME;
        this.REQUEST = REQUEST;
        this.ERROR = ERROR;
        this.SUCCESS = SUCCESS;
        this.FAILURE = FAILURE;
        this.BOBY = BOBY;
        this.LOADER_STYLE = LOADERSTYLE;
        this.FILE = FILE;
        this.CONTEXT = CONTEXT;
    }

    public static RestClientBuilder bulider() {
        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {

        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if (LOADER_STYLE != null) {
            LoaderManager.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                call = service.postRaw(URL, BOBY);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BOBY);
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body = MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                call = service.upload(URL, body);
                break;
            default:
                break;
        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }

    }

    private RequestCallbacks getRequestCallback() {
        return new RequestCallbacks(REQUEST, SUCCESS, FAILURE, ERROR, LOADER_STYLE);
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void put() {

        if (BOBY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.PUT_RAW);
        }

    }

    public final void post() {

        if (BOBY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.POST_RAW);
        }

    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }

    public final void upload() {
        request(HttpMethod.UPLOAD);
    }

    public final void download() {
        new DownloadHandler(URL, PARAMS, REQUEST, DOWNLOAD_DIR, EXTENSION, NAME, ERROR, SUCCESS, FAILURE).handlerDownload();
    }


}
