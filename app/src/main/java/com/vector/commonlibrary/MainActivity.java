package com.vector.commonlibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.vector.common.app.ConfigType;
import com.vector.common.app.Vector;
import com.vector.common.net.RestClient;
import com.vector.common.net.callback.IError;
import com.vector.common.net.callback.IFailure;
import com.vector.common.net.callback.ISuccess;
import com.vector.common.utils.log.LoggerUtil;

import java.util.logging.Logger;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RestClient.bulider()
                .loader(MainActivity.this)
                .url("https://www.baidu.com")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.i("MainActivity", response);
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {
                        Log.i("MainActivity", code + "");

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.i("MainActivity", "失败");

                    }
                })
                .bulid()
                .get();

    }
}