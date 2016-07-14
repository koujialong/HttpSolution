package com.example.administrator.httprequestdemo;

import android.os.Handler;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.center.AsyncRequestCenter;
import com.android.volley.center.RequestParams;
import com.example.administrator.httprequestdemo.connect.DeviceBandwidthSampler;
import com.example.administrator.httprequestdemo.exception.HttpClientApiException;
import com.example.administrator.httprequestdemo.parser.IResultDataParser;
import com.example.administrator.httprequestdemo.parser.IResultReceiver;
import com.example.administrator.httprequestdemo.system.MyApplication;
import com.example.administrator.httprequestdemo.utils.LogUtils;
import com.example.administrator.httprequestdemo.utils.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;


/**
 * Created by Administrator on 2016/7/14.
 */
public class HttpTaskManager {
    /**
    * TAG
    * */
    private static final  String TAG=HttpTaskManager.class.getSimpleName();

    private static Handler mHandler;

    private static long mStartTime;
    private static long mEndTime;

    static {
        mHandler=new Handler();
    }

    public static <T> void startStringRequest(Request.Priority mPriority, final RequestParams requestParams,
                                              final IResultDataParser<T> parserObj,
                                              final IResultReceiver resultReceiver){
        mStartTime =System.currentTimeMillis();
        if (requestParams == null){
            LogUtils.e(TAG, "startStringRequest error!!! requestParams is null!");
            if (resultReceiver!=null){
                resultReceiver.onReceiveResult(ServerErrorCode.NO_CONNECTION_ERROR,null);
            }
            return;
        }

        //开始采样
        DeviceBandwidthSampler.getInstance().startSampling();

        boolean isNetAvaulable = NetworkUtils.isOnline(MyApplication.getInstance());

        if (mPriority == null){
            mPriority=Request.Priority.NORMAL;
        }
            //开启网络请求
            AsyncRequestCenter.getInstance().startStringRequest(mPriority, requestParams, new AsyncRequestCenter.RequestListener<String>() {
                @Override
                public void onSuccess(String result) {
                    //停止采样
                    DeviceBandwidthSampler.getInstance().stopSampling();

                    //解析器或者结果回调器为空择返回，不进行任何网络请求操作
                    if ((parserObj == null) || (resultReceiver == null)) {
                        return;
                    }
                    //判断数据返回结果是否为空
                    if (TextUtils.isEmpty(result)) {
                        resultReceiver.onReceiveResult(ServerErrorCode.STATUS_EMPTY, null);
                        return;
                    }

                    //数据处理过程
                    onContentProviderProcess(result, parserObj, resultReceiver);
                }

                @Override
                public void onFailure(int errorCode, String errorMsg) {
                    LogUtils.d(TAG, "onFailure =====errorCode==== " + errorCode + " ==== errorMsg === " + errorMsg);
                    //停止采样
                    DeviceBandwidthSampler.getInstance().stopSampling();

                    //数据逻辑处理过程
                    onContentProviderProcess(errorMsg, parserObj, resultReceiver);
                }
            }, isNetAvaulable);
    }

    private static <T> void onContentProviderProcess(final Object result,final IResultDataParser<T> parserObj,
                                                     final  IResultReceiver resultReceiver){
        //进行解析
        T resultObj=null;
        int statusCode=ServerErrorCode.STATUS_SUCCESS;

        try {
            resultObj=parserObj.parse(result);
        } catch (JSONException e) {
            LogUtils.e(TAG, "JSONException break out", e);
            statusCode = ServerErrorCode.PARSE_ERROR;
        } catch (HttpClientApiException e) {
            LogUtils.e(TAG, "HttpClientApiException break out", e);
            statusCode=e.getCode();
            resultObj= (T) e.getMessage();
        } catch (IOException e) {
            LogUtils.e(TAG, "IOException break out", e);
            statusCode = ServerErrorCode.PARSE_ERROR;
        } finally {
            //切换至主线程
            runUiThread(resultReceiver,statusCode,resultObj);
        }
    }

    /**
    * 回调结果至主线程
    * @date: 2016/7/14 14:48
    * @author: KJL
    * @param:
    * @return:
    */
    private static <T> void runUiThread(final IResultReceiver resultReceiver,final int resultCode,final T resultObj){
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                mEndTime=System.currentTimeMillis();
                LogUtils.d(TAG, "网络请求耗时:   " + (mEndTime - mStartTime));
                //回调输出
                resultReceiver.onReceiveResult(resultCode,resultObj);
            }
        });
    }

    /**
    * 根据TAG，关闭对应http任务
    * @date: 2016/7/14 14:56
    * @author: KJL
    * @param:
    * @return:
    */
    public static void cancelAllRequest(Object tag){
        AsyncRequestCenter.getInstance().cancelAll(tag);
    }

    /**
    * 暴漏请求接口
    * @date: 2016/7/14 14:58
    * @author: KJL
    * @param:
    * @return:
    */
    public static <T> void startStringRequest(final RequestParams requestParams,final IResultDataParser<T> parserObj,
                                              final IResultReceiver resultReceiver){
        startStringRequest(Request.Priority.NORMAL,requestParams,parserObj,resultReceiver);
    }
}