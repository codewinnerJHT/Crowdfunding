package com.example.haitao.crowdfunding.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.haitao.crowdfunding.R;
import com.example.haitao.crowdfunding.adapter.CollectionAdapter;
import com.example.haitao.crowdfunding.bean.CollectionInfoBean;
import com.example.haitao.crowdfunding.bean.GoodsInfoBean;
import com.example.haitao.crowdfunding.bean.UserDetaileBean;
import com.example.haitao.crowdfunding.util.Constant;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class SearchActivity extends Activity implements View.OnClickListener{
private UserDetaileBean userDetaileBean;
    private String TAG="SearchActivity";
    private ImageView search_back;
    private EditText et_search;
    private Button b_serch;
    private ListView lv_search;
    private  String SEARCHURL;
    private String keyword;
    private Context mContext;
    private CollectionAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        userDetaileBean= (UserDetaileBean)  getIntent().getSerializableExtra("USER");
        Log.d(TAG,"用户信息"+userDetaileBean.toString());
        mContext=getApplicationContext();
        initview();
        Log.d(TAG,"静态用户信息"+Constant.userBean.toString());

    }

    private void initview() {
        search_back=(ImageView)findViewById(R.id.search_back);
        search_back.setOnClickListener(this);
        et_search=(EditText)findViewById(R.id.et_search);
        b_serch=(Button)findViewById(R.id.b_serch);
        b_serch.setOnClickListener(this);
        lv_search=(ListView)findViewById(R.id.lv_search);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.b_serch:
                keyword=et_search.getText().toString();
                SEARCHURL= Constant.ROOTURL+"selectsearch?name="+keyword;
                //http://10.10.81.189:8080/crowdfunding/selectsearch?name=科创
                search();
                break;
            case R.id.search_back:
                finish();
                break;

        }
    }

    private void search() {
        OkHttpUtils
                .get()
                .url(SEARCHURL)
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d(TAG, "搜索请求失败："+e.toString());
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.d(TAG, "搜索请求成功："+response);
                        processData(response);
                    }
                });
    }
    private  void processData(String json){
        final   CollectionInfoBean collectionInfoBean= JSON.parseObject(json,CollectionInfoBean.class);
        if (collectionInfoBean.getProjects()!=null){
            Toast.makeText(mContext,"数据"+collectionInfoBean.toString(),Toast.LENGTH_SHORT).show();
            adapter=new CollectionAdapter(mContext,collectionInfoBean.getProjects());
            lv_search.setAdapter(adapter);
            lv_search.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                    //goodsinfobean.setPhone(projectsBean.getPhone());
                    MainActivity mainActivity=new MainActivity();
                    //userDetaileBean=(UserDetaileBean)mainActivity.getIntent().getSerializableExtra("USER");
                    // userDetaileBean= mainActivity.userDetaileBean;
                    Log.d(TAG,userDetaileBean.toString());
                    goodsinfobean.setUid(userDetaileBean.getId());
                    Log.d(TAG,goodsinfobean.toString());
                    startGoodsinfo(goodsinfobean);
                }
            });


        }else {
            Toast.makeText(mContext,"无数据",Toast.LENGTH_SHORT).show();
        }
    }
    private  void startGoodsinfo(GoodsInfoBean goodInfoBean){
        Intent intent= new Intent(mContext, GoodsInfoActivity.class);
        intent.putExtra("GOODSBEAN",goodInfoBean);
        mContext.startActivity(intent);
    }
}
