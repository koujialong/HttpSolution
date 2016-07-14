package com.example.administrator.httprequestdemo.parser;

import com.example.administrator.httprequestdemo.exception.HttpClientApiException;

import org.json.JSONException;

import java.io.IOException;

/**
* 解析器基类
* @date: 2016/7/14 12:35
* @author: KJL
* @param:
* @return:
*/
public interface IResultDataParser<T> {
    /**
     * 解析服务器响应
     *
     * @date: 2016/7/14 12:35
     * @author: KJL
     * @return: T 解析respons后得到的数据类型
     * @throws JSONException 解析服务器响应的JSON
     * @throws HttpClientApiException 服务器返回的错误代码
     * @throws IOException 获取服务器响应异常
     */
    T parse(Object response) throws JSONException,HttpClientApiException, IOException;
}
