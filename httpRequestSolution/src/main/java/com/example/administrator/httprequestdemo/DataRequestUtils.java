package com.example.administrator.httprequestdemo;

import com.android.volley.Request;
import com.android.volley.center.RequestParams;
import com.example.administrator.httprequestdemo.url.Domains;

/**
 * Created by Administrator on 2016/7/14.
 */
public class DataRequestUtils {
    private static final String TAG = "DataRequestUtils";
    // 省份接口
    private static final String PROVINCE_LIST = "/ctrade/member/getProvinceList.do";

    private static String combineRequestUrl(String domain) {
        String url = Domains.FORMAL_API_HOST + domain;
        return url;
    }

//    private static void addBaseParams(RequestParams requestParams) {
//        //requestParams.addQueryParam("deviceid", DeviceConstants.getInstance().getDeviceId());
////        requestParams.addQueryParam("ua", DeviceConstants.getInstance().getPlatform());
////        requestParams.addQueryParam("appver",
////                DeviceConstants.getInstance().getAppVersion(MyApplication.getInstance().getApplicationContext()));
//        //requestParams.addQueryParam("sysver", DeviceUtils.getVersionRelease());
//    }

    /**
     * 获取省份列表
     *
     * @param tag
     * @return
     */
    public static RequestParams getProvinceList(String tag, String environmentCode) {
        String url = combineRequestUrl(PROVINCE_LIST);
        RequestParams requestParams=new RequestParams(url, Request.Method.GET);
        requestParams.setTag(tag);
        requestParams.addQueryParam("environmentCode", environmentCode);
        return requestParams;
    }
}
