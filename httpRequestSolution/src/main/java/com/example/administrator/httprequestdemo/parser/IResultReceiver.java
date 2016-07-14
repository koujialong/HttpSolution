package com.example.administrator.httprequestdemo.parser;

/**
 * Created by Administrator on 2016/7/14.
 */
public interface IResultReceiver {
    /**
    * 返回数据接口
    * @date: 2016/7/14 13:02
    * @author: KJL
    * @param: resultCode 网络请求返回的错误代码
    * @param: resultData 解析封装好的数据模型对象
    */
    public void onReceiveResult(int resultCode,Object resultData);
}
