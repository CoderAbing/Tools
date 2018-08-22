package com.abing.tools.http;


import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
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

    private final static int TIMEOUT = 30;

    private final static String BASE_URL = "http://v.juhe.cn";


    private static Retrofit mRetrofit;

    private static OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
//            .addInterceptor(new Interceptor() {
//                @Override
//                public Response intercept(Chain chain) throws IOException {
//                    Request.Builder builder = chain.request().newBuilder();
//                    builder.addHeader("token", "123");
//                    return chain.proceed(builder.build());
//                }
//            })
            //网络日志拦截器
            .addInterceptor(new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    Log.i("------>", message);
                }
            }).setLevel(HttpLoggingInterceptor.Level.BASIC))
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build();


    public static Retrofit getInstence() {
        if (null == mRetrofit) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//支持RxJava
                    .client(mOkHttpClient)
                    .build();
        }
        return mRetrofit;
    }


}
