package com.abing;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;


/**
 * @author Abing
 * @package com.abing
 * @date 2018/8/17 11:31
 * @org 北京云玺科技有限责任公司
 * @email Vincent_0728@outlook.com
 * @describe TODO:
 */
public class AppContext extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }


    //判断当前应用是否是debug状态
    public boolean isApkInDebug() {
        try {
            ApplicationInfo info = getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }

    }
}