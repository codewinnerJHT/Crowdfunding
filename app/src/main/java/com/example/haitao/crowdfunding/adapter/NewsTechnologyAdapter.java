package com.example.haitao.crowdfunding.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.haitao.crowdfunding.R;
import com.example.haitao.crowdfunding.bean.NewsInfoBean;
import com.example.haitao.crowdfunding.util.Constant;

import java.util.List;

/**
 * Created by haitao on 2020/6/25.
 */

public class NewsTechnologyAdapter  extends BaseAdapter{
    private  final Context mContext;
    private  String TAG="NewsTechnologyAdapter";
    private  final List<NewsInfoBean.NewsBean> datas;
    public  NewsTechnologyAdapter(Context mContext, List<NewsInfoBean.NewsBean> news_info){
        this.mContext=mContext;
        this.datas=news_info;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View converView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (converView==null){
            converView=View.inflate(mContext, R.layout.news_item,null);
            viewHolder =new ViewHolder();
            viewHolder.iv_news_image=(ImageView)converView.findViewById(R.id.iv_news_image);
            viewHolder.tv_news_title=(TextView) converView.findViewById(R.id.tv_news_title);
            viewHolder.tv_news_publisher=(TextView) converView.findViewById(R.id.tv_news_publisher);
            viewHolder.tv_news_time=(TextView) converView.findViewById(R.id.tv_news_time);
            converView.setTag(viewHolder);

        }else {
            viewHolder=(ViewHolder)converView.getTag();
        }
        Log.d(TAG,"新闻的数据"+datas.toString());
        NewsInfoBean.NewsBean newsBean=datas.get(position);
        Glide.with(mContext).load(Constant.IMAGE_URL+newsBean.getImageurlone()).into(viewHolder.iv_news_image);
        viewHolder.tv_news_title.setText(newsBean.getTitle());
        viewHolder.tv_news_publisher.setText(newsBean.getPublisher());
        viewHolder.tv_news_time.setText(newsBean.getTime());
        return converView;
    }

    static  class ViewHolder{
        ImageView iv_news_image;
        TextView tv_news_title,tv_news_publisher,tv_news_time;
    }
}
