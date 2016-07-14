package com.example.administrator.httprequestdemo.parser;

import com.example.administrator.httprequestdemo.ServerErrorCode;
import com.example.administrator.httprequestdemo.exception.HttpClientApiException;
import com.example.administrator.httprequestdemo.model.AbstractBaseModel;
import com.google.gson.Gson;

import org.json.JSONException;

import java.io.IOException;

/**
* 数据解析工厂
* @date: 2016/7/14 15:05
* @author: KJL
* @param:
* @return:
*/
public class JsonParserFactory {
    /**
     * TAG
     */
    private static final String TAG = "JsonParserFactory";

    public static <T extends AbstractBaseModel> T parserStringJson(Class<T> cls,Object context)
            throws JSONException,HttpClientApiException,IOException{
        final T response;
        response=new Gson().fromJson((String)context,cls);
        if (response==null){
            throw new JSONException(TAG+" JsonParser is null.");
        }

        if (response.getCode()!= ServerErrorCode.STATUS_SUCCESS){
            throw new HttpClientApiException(response.getCode(), response.getMsg());
        }
        return response;
    }

    public static <T extends AbstractBaseModel> IResultDataParser<T> parseBaseModel(final Class<T> cls){
        return new IResultDataParser<T>() {
            @Override
            public T parse(Object response) throws JSONException, HttpClientApiException, IOException {
                return parserStringJson(cls,(String)response);
            }
        };
    }

}
