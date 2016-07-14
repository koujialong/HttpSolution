package com.example.administrator.httprequestdemo.url;

/**
 * Created by xiangyutian on 16/1/28.
 */
public class Domains {

    // api地址
    public static final String FORMAL_API_HOST = "http://www.qilin99.com";
    public static final String TEST_API_HOST = "http://wp.500win.cn";

    private static String API_QILIN_DOMAIN = "";

    static {
        setQilinApiDomain(FORMAL_API_HOST);
    }

    public static void setQilinApiDomain(String api) {
        API_QILIN_DOMAIN = api;
    }

    public static String getQilinApiomain() {
        return API_QILIN_DOMAIN;
    }

    // 通用api测试
    public static void initApiDomian(boolean api) {
        if (api) {
            Domains.setQilinApiDomain(TEST_API_HOST);
        } else {
            Domains.setQilinApiDomain(FORMAL_API_HOST);
        }
    }
}
