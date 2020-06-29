package com.example.haitao.crowdfunding.fragment;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;

import com.alibaba.fastjson.JSON;
import com.example.haitao.crowdfunding.R;
import com.example.haitao.crowdfunding.activity.NewsDetailsActivity;
import com.example.haitao.crowdfunding.adapter.NewsTechnologyAdapter;
import com.example.haitao.crowdfunding.bean.NewsDetailsBean;
import com.example.haitao.crowdfunding.bean.NewsInfoBean;
import com.example.haitao.crowdfunding.util.Constant;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by haitao on 2020/6/19.
 */

public class NewsFragment extends BaseFragment {

    private String TAG="NewsFragmentActivity";
    private RadioButton ra_shop_bt;
    private RadioButton ra_food_bt;
    private ListView news_list;
    private NewsTechnologyAdapter adapter;
    private  String NewsURL;
    private  String NewsURL1;
    private String NewsURL2;
    @Override
    public View initView() {
        Log.d(TAG,"新闻页面UI被初始化");
        View view=View.inflate(mContext, R.layout.fragment_news,null);
        ra_shop_bt = (RadioButton) view.findViewById(R.id.ra_shop_bt);
        ra_food_bt = (RadioButton) view.findViewById(R.id.ra_food_bt);
        news_list=(ListView)view.findViewById(R.id.news_list);

        ra_shop_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ra_shop_bt.setBackgroundResource(R.color.Lightgreen);
                ra_food_bt.setBackgroundResource(R.color.gray);
                NewsURL1=Constant.ROOTURL+"news?type=1";
                getDataFromNet(NewsURL1);
            }
        });
        ra_food_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ra_food_bt.setBackgroundResource(R.color.Lightgreen);
                ra_shop_bt.setBackgroundResource(R.color.gray);
                NewsURL2=Constant.ROOTURL+"news?type=2";
                getDataFromNet(NewsURL2);
            }
        });


        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.d(TAG,"新闻页面的数据初始化了");
        NewsURL=Constant.ROOTURL+"news?type=1";
        getDataFromNet(NewsURL);

    }
    private  void getDataFromNet(String URL){
        OkHttpUtils
                .get()
                .url(URL)
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d(TAG, "首页请求失败："+e.toString());
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.d(TAG, "首页请求成功："+response);
                        processData(response);
                    }
                });
    }

    private  void processData(String json){
        final NewsInfoBean newsInfoBean= JSON.parseObject(json,NewsInfoBean.class);
        //Log.d(TAG,"解析成功="+newsInfoBean.getNews().get(0).getTitle());
        if (newsInfoBean!=null){
            adapter=new NewsTechnologyAdapter(mContext,newsInfoBean.getNews());
            news_list.setAdapter(adapter);
            //news_list.setLayoutManager(new GridLayoutManager(mContext,1));
            news_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    //Toast.makeText(mContext,"position"+i,Toast.LENGTH_SHORT).show();
                    NewsInfoBean.NewsBean newsBean=newsInfoBean.getNews().get(i);
                    NewsDetailsBean newsDetailsBean=new NewsDetailsBean();
                    newsDetailsBean.setTitle(newsBean.getTitle());
                    newsDetailsBean.setPublisher(newsBean.getPublisher());
                    newsDetailsBean.setTime(newsBean.getTime());
                    newsDetailsBean.setContentone(newsBean.getContentone());
                    newsDetailsBean.setContenttwo(newsBean.getContenttwo());
                    newsDetailsBean.setContentthree(newsBean.getContentthree());
                    newsDetailsBean.setImageurlone(newsBean.getImageurlone());
                    newsDetailsBean.setImageurltwo(newsBean.getImageurltwo());
                    newsDetailsBean.setImageurlthree(newsBean.getImageurlthree());
                    startNewsDetails(newsDetailsBean);
                }
            });
        }else {

        }
    }

    private  void startNewsDetails(NewsDetailsBean newsDetailsBean){
        Intent intent= new Intent(mContext, NewsDetailsActivity.class);
        intent.putExtra("NEWSBEAN",newsDetailsBean);
        mContext.startActivity(intent);
    }
}
