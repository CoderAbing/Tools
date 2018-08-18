package com.abing.tools.base;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * @author Abing
 * @package com.abing.tools.base
 * @fileName ARouterHelper
 * @date 2018/8/18 13:47
 * @org 北京云玺科技有限责任公司
 * @email Vincent_0728@outlook.com
 * @describe TODO   封装阿里路由器
 */


public class ARouterHelper {
    private static ARouter mARouter;

    public synchronized static ARouter getInstence() {
        if (null == mARouter) {
            mARouter = ARouter.getInstance();
        }
        return mARouter;
    }

}
