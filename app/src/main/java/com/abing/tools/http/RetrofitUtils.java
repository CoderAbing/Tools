package com.abing.tools.http;


import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Abing
 * @package com.abing.tools.http
 * @fileName RetrofitUtils
 * @date 2018/8/18 15:09
 * @org 北京云玺科技有限责任公司
 * @email Vincent_0728@outlook.com
 * @describe TODO   网络工具类
 */


public class RetrofitUtils {

    private final static String BASE_URL = "http://v.juhe.cn";

    private static Retrofit mRetrofit;


    public static Retrofit getInstence() {
        if (null == mRetrofit) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().create()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//支持RxJava
                    .build();

        }
        return mRetrofit;
    }
}
