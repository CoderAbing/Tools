package com.abing.tools.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.abing.tools.base.BaseActivity;
import com.abing.tools.R;
import com.abing.tools.news.NewsActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button btnStart, btnTime, btnNetwork;
    private TextView tvNetWork;
    private Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.button);
        btnTime = findViewById(R.id.button1);
        btnNetwork = findViewById(R.id.button3);
        tvNetWork = findViewById(R.id.textview3);
        btnStart.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        btnNetwork.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                button1();
                break;
            case R.id.button1:
                button2();
            case R.id.button3:
                startActivity(new Intent(this, NewsActivity.class));
                break;
        }
    }

    public void button2() {
        if (null != mDisposable && !mDisposable.isDisposed()) return;
        mDisposable = Flowable.interval(4, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        btnTime.setText(String.valueOf(aLong));
                        //在页面销毁的时候要停止计时
                        Log.e("accept------>", "accept: doOnNext : " + aLong);
                    }
                })
        ;
    }


    private void button1() {

        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("零一二三四五六七八九十");
            }
        }).subscribe(new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.i("onSubscribe------>", Thread.currentThread().getName());
            }

            @Override
            public void onNext(String s) {
                Log.i("onNext------>", Thread.currentThread().getName());
                Log.i("onNext------>", s);
            }


            @Override
            public void onError(Throwable e) {
                Log.i("onError------>", Thread.currentThread().getName());
            }

            @Override
            public void onComplete() {
                Log.i("onComplete------>", Thread.currentThread().getName());
            }
        });

    }


}
