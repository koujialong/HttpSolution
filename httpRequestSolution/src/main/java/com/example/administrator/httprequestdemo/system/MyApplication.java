package com.example.administrator.httprequestdemo.system;

import android.app.Application;

import com.android.volley.center.AsyncRequestCenter;

/**
 * Created by Administrator on 2016/7/14.
 */
public class MyApplication extends Application{

    private static MyApplication instance=null;

    public static MyApplication getInstance(){
        return instance;
    }

    public MyApplication() {
        instance=this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AsyncRequestCenter.getInstance().initiate(this);
    }
}
