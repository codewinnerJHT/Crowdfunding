package com.example.haitao.crowdfunding;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by haitao on 2020/6/18.
 */

public class CrowdfundingApplication extends Application{
    public RequestQueue requestQueue = null;
    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(this.getApplicationContext());
        initOkhttpclient();
    }
private  void initOkhttpclient(){
                 OkHttpClient okHttpClient = new OkHttpClient.Builder()
                         .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                         .readTimeout(10000L, TimeUnit.MILLISECONDS)
                         .build();
                  OkHttpUtils.initClient(okHttpClient);
                    }
                }
