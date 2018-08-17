package com.abing.tools.news;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.abing.tools.base.BaseActivity;
import com.abing.tools.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author Abing
 * @package com.abing.testrxjava.news
 * @date 2018/8/17 10:10
 * @org 北京云玺科技有限责任公司
 * @email Vincent_0728@outlook.com
 * @describe TODO:
 */

public class NewsActivity extends BaseActivity {

    private NewsAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<NewsMode.ResultBean.DataBean> mData;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);
        init();
        onRequestNews();
    }

    private void init() {
        mRecyclerView = findViewById(R.id.rv_news_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mData = new ArrayList<>();
        mAdapter = new NewsAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
    }


    @SuppressLint("CheckResult")
    private void onRequestNews() {
        Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(ObservableEmitter<Response> emitter) throws Exception {
                //开始网络请求
                Request request = new Request.Builder()
                        .get()
                        .url("http://v.juhe.cn/toutiao/index?type=keji&key=a8d4234a11ddf267c246781f1d1a193b")
                        .build();
                Call call = new OkHttpClient().newCall(request);
                Response response = call.execute();
                //请求结束发射请求结果
                emitter.onNext(response);
                Log.i("1------->", Thread.currentThread().getName());
            }
        }).subscribeOn(Schedulers.newThread())
                .map(new Function<Response, List<NewsMode.ResultBean.DataBean>>() {
                    @Override
                    public List<NewsMode.ResultBean.DataBean> apply(Response response) throws Exception {
                        Log.i("3------->", Thread.currentThread().getName());

                        //提取结果转换成集合
                        if (null != response && response.isSuccessful()) {
                            NewsMode newsMode = new Gson().fromJson(response.body().string(), NewsMode.class);
                            if (null != newsMode) return newsMode.getResult().getData();
                        }
                        return null;
                    }
                }).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<NewsMode.ResultBean.DataBean>>() {
                    @Override
                    public void accept(List<NewsMode.ResultBean.DataBean> dataBeans) {
                        Log.i("4------->", Thread.currentThread().getName());

                        //加载数据到列表
                        if (null == dataBeans) return;
                        mAdapter.setNewData(dataBeans);
                    }
                })
        ;
    }


}

