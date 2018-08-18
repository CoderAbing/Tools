package com.abing;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;

import com.alibaba.android.arouter.launcher.ARouter;


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
        if (isApkInDebug()) {           // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this); // 尽可能早，推荐在Application中初始化
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