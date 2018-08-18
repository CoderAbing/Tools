package com.abing.tools.news;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Abing
 * @package com.abing.tools.news
 * @fileName INewsRetrofit
 * @date 2018/8/18 14:41
 * @org 北京云玺科技有限责任公司
 * @email Vincent_0728@outlook.com
 * @describe TODO
 */


public interface INewsRetrofit {

    @GET("/toutiao/index")
    Observable<NewsMode> onRequestNews(@Query("top") String top, @Query("key") String key);
}
