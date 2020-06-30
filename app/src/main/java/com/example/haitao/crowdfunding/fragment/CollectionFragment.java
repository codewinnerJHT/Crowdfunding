package com.example.haitao.crowdfunding.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.haitao.crowdfunding.R;
import com.example.haitao.crowdfunding.activity.GoodsInfoActivity;
import com.example.haitao.crowdfunding.adapter.CollectionAdapter;
import com.example.haitao.crowdfunding.bean.CollectionInfoBean;
import com.example.haitao.crowdfunding.bean.GoodsInfoBean;
import com.example.haitao.crowdfunding.bean.UserDetaileBean;
import com.example.haitao.crowdfunding.util.Constant;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by haitao on 2020/6/19.
 */

public class CollectionFragment extends BaseFragment {
    private TextView textView;
    private UserDetaileBean userDetaileBean;
    private Integer uid;
    private  String COLLECTIONURL;
    private  CollectionAdapter adapter;
    private String TAG="CollFragmentActivity";
    private ListView collection_list;
    private String DELURL;
    private SwipeRefreshLayout swipe_refresh;
    @Override
    public View initView() {
        View view=View.inflate(mContext, R.layout.fragment_collection,null);
       // collection_list=(ListView)view.findViewById(R.id.collection_list);

        collection_list=(ListView)view.findViewById(R.id.collection_list);
        swipe_refresh=(SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh);
        swipe_refresh.setOnRefreshListener(listener);
        return view;
    }
    @Override
    public void initData() {
        super.initData();
        Log.d(TAG,"收藏页面的数据初始化了");
        userDetaileBean= (UserDetaileBean) getActivity().getIntent().getSerializableExtra("USER");
        uid=userDetaileBean.getId();
        COLLECTIONURL= Constant.ROOTURL+"selectcollect?uid="+uid;
        //http://10.10.81.189:8080/crowdfunding/selectcollect?uid=1
        getDataFromNet(COLLECTIONURL);
        Log.d(TAG,userDetaileBean.toString());



    }

    private SwipeRefreshLayout.OnRefreshListener listener=new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
         new Handler().postDelayed(new Runnable() {
             @Override
             public void run() {
                 Toast.makeText(mContext,"刷新成功",Toast.LENGTH_SHORT).show();
                 initData();
                 swipe_refresh.setRefreshing(false);
             }
         },1500);
        }
    };

    private void delcollection(String DELURL) {
        OkHttpUtils
                .get()
                .url(DELURL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d(TAG, "删除收藏请求失败：" + e.toString());
                        Toast.makeText(mContext, "删除收藏请求失败，请稍后重试", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d(TAG, "删除收藏请求成功：" + response);
                        Toast.makeText(mContext, "删除成功", Toast.LENGTH_SHORT).show();
                        initData();
                    }
                });
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
                        Log.d(TAG, "收藏请求失败："+e.toString());
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.d(TAG, "收藏请求成功："+response);
                        processData(response);
                    }
                });
    }
    private  void processData(String json){
     final  CollectionInfoBean collectionInfoBean= JSON.parseObject(json,CollectionInfoBean.class);
        //Log.d(TAG,"解析成功="+collectionInfoBean.getProjects().get(0).getName());
        if (collectionInfoBean!=null){
            adapter=new CollectionAdapter(mContext,collectionInfoBean.getProjects());
            collection_list.setAdapter(adapter);
            //rv_collect.setLayoutManager(new GridLayoutManager(mContext,1));
            collection_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Toast.makeText(mContext,"position"+i,Toast.LENGTH_SHORT).show();
                    CollectionInfoBean.ProjectsBean projectsBean=collectionInfoBean.getProjects().get(i);
                    GoodsInfoBean goodsinfobean=new GoodsInfoBean();
                    goodsinfobean.setImageurl(projectsBean.getImageurl());
                    goodsinfobean.setName(projectsBean.getName());
                    goodsinfobean.setPrice(projectsBean.getPrice());
                    goodsinfobean.setInformation(projectsBean.getInformation());
                    goodsinfobean.setStatus(projectsBean.getStatus());
                    goodsinfobean.setDaysrmaining(projectsBean.getDaysremaining());
                    goodsinfobean.setMoney(projectsBean.getMoney());
                    goodsinfobean.setSuppormoney(projectsBean.getSupportmoney());
                    goodsinfobean.setId(projectsBean.getId());
                    goodsinfobean.setBriefintroduction(projectsBean.getBriefintroduction());
                    Log.d(TAG,userDetaileBean.toString());
                    Log.d(TAG,goodsinfobean.toString());
                    startGoodsinfo(goodsinfobean);
                }
            });



          List<Map<String, Object>> listItems;
            Map<String, Object> map;
            listItems = new ArrayList<Map<String, Object>>();
            map = new HashMap<String, Object>();
            map.put("del", "删除");
           // map.put("personName", names[i]);
           // map.put("desc", desc[i]);*//*
            //把列表项加进列表集合
            listItems.add(map);

            //SimpleAdapter simpleAdapter=new SimpleAdapter(mContext, listItems, R.layout.collection_item, new String[]{"del"}, new int[]{R.id.about});
            //collection_list.setAdapter(simpleAdapter);
            collection_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int i, long l) {
                    AlertDialog.Builder dialog=new AlertDialog.Builder(mContext);
                    dialog.setTitle("确认删除");
                    dialog.setMessage("你确认删除吗？");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int o) {
                            Toast.makeText(mContext,"position"+i,Toast.LENGTH_SHORT).show();

                            CollectionInfoBean.ProjectsBean projectsBean=collectionInfoBean.getProjects().get(i);
                            int pid=projectsBean.getId();
                            DELURL=Constant.ROOTURL+"delcollect?uid="+Constant.userBean.getId()+"&pid="+pid;
                            //http://10.10.81.189:8080/crowdfunding/delcollect?uid=1&pid=1
                            delcollection(DELURL);
                        }
                    });
                    dialog.setNegativeButton("取消",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    dialog.show();
                    return true;
                }
            });

        }else {

        }
    }


    private  void startGoodsinfo(GoodsInfoBean goodInfoBean){
        Intent intent= new Intent(mContext, GoodsInfoActivity.class);
        intent.putExtra("GOODSBEAN",goodInfoBean);
        mContext.startActivity(intent);
    }

}
