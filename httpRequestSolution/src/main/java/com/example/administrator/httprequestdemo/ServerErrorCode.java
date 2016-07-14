package com.example.administrator.httprequestdemo;

import com.android.volley.center.AsyncRequestCenter;

/**
 * Created by Administrator on 2016/7/14.
 */
public class ServerErrorCode implements AsyncRequestCenter.ErrorCode {
    public static final String TAG="ServerErrorCode";

    /**
     * 返回成功
     */
    public static final int STATUS_SUCCESS = 0;

    /**
     * 返回失败
     */
    public static final int STATUS_EMPTY = 1;

    /**
     * token失效
     */
    public static final int STATUS_INVALID = 2;

    /**
     * token超时
     */
    public static final int STATUS_TIMEOUT = 6;
    /**
     *系统维护
     */
    public static final int STATUS_FIX= 7;
}
