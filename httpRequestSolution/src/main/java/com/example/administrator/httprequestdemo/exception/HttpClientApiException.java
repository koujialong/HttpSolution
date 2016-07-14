package com.example.administrator.httprequestdemo.exception;

/**
 * Created by Administrator on 2016/7/14.
 */
public class HttpClientApiException extends Exception {
    private static final long seriaVersionUID = -1460894893738016580L;
    /**
    * 错误代码
    */
    private int code;
    private String message;

    public HttpClientApiException(int errorCode,String errorMessage) {
        super(errorMessage);
        code = errorCode;
        message=errorMessage;
    }

    public HttpClientApiException(int responseCode,Throwable throwable) {
        super(throwable);
        code = responseCode;
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append("code = ");
        sb.append(code);
        sb.append(" ");
        sb.append("message = ");
        sb.append(message);
        sb.append(super.getMessage());
        return sb.toString();
    }

    /**
    * @return int 服务器返回错误代码
    */
    public int getCode(){
        return code;
    }

    /**
     * @return string 返回错误内容
     */
    public String getMessage(){
        return message;
    }

}
