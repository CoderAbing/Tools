package com.abing.tools.news;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.abing.AppContext;
import com.abing.tools.base.BaseActivity;
import com.abing.tools.R;
import com.abing.tools.base.HttpObserver;
import com.abing.tools.http.RetrofitUtils;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Callback;
import retrofit2.Call;

/**
 * @author Abing
 * @package com.abing.testrxjava.news
 * @date 2018/8/17 10:10
 * @org 北京云玺科技有限责任公司
 * @email Vincent_0728@outlook.com
 * @describe TODO:
 */
@Route(path = NewsActivity.AROUTER_PATH)
public class NewsActivity extends BaseActivity {
    public static final String AROUTER_PATH = "/news/NewsActivity";
    private NewsAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<NewsMode.ResultBean.DataBean> mData;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);
        init();
        onRequestNewsRx();
    }

    private void init() {
        mRecyclerView = findViewById(R.id.rv_news_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mData = new ArrayList<>();
        mAdapter = new NewsAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
    }


    private void onRequestNewsRx() {
        INewsRetrofit iNewsRetrofit = RetrofitUtils.getInstence().create(INewsRetrofit.class);
        Observable<NewsMode> news = iNewsRetrofit.onRequestNews("keji", AppContext.APP_KEY);
        news.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<NewsMode>() {
                    @Override
                    public void onNext(NewsMode newsMode) {
                        Log.e("------>", newsMode.toString());
                        mAdapter.setNewData(newsMode.getResult().getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("------>", "Error");
                    }

                });
    }

}

