package com.abing.tools.news;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.abing.tools.R;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * @author Abing
 * @package com.abing.testrxjava.news
 * @date 2018/8/17 10:29
 * @org 北京云玺科技有限责任公司
 * @email Vincent_0728@outlook.com
 * @describe TODO:
 */
public class NewsAdapter extends BaseQuickAdapter<NewsMode.ResultBean.DataBean, BaseViewHolder> {
    public NewsAdapter(@Nullable List<NewsMode.ResultBean.DataBean> data) {
        super(R.layout.item_news, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewsMode.ResultBean.DataBean item) {
        helper.setText(R.id.tv_item_news_title, item.getTitle())
                .setText(R.id.tv_item_news_time, item.getDate())
                .setText(R.id.tv_item_news_author, item.getAuthor_name());
        ImageView imageView = helper.getView(R.id.iv_item_news_pic);
        Glide.with(mContext).load(item.getThumbnail_pic_s()).into(imageView);
        Log.i("------->", item.getThumbnail_pic_s());

    }
}
