package com.example.haitao.crowdfunding.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.example.haitao.crowdfunding.R;
import com.example.haitao.crowdfunding.activity.ForgetActivity;
import com.example.haitao.crowdfunding.activity.LoginActivity;
import com.example.haitao.crowdfunding.activity.SearchActivity;
import com.example.haitao.crowdfunding.adapter.HomeFragmentAdapter;
import com.example.haitao.crowdfunding.bean.ProjectBeanData;
import com.example.haitao.crowdfunding.bean.UserDetaileBean;
import com.example.haitao.crowdfunding.util.Constant;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by haitao on 2020/6/19.
 */

public class HomeFragment extends BaseFragment implements View.OnClickListener{
    private RecyclerView rvHome;
    private ImageView ib_top;
    private TextView tv_search_home, tv_message_home,login_acct,tv_username;
    private String TAG = "HomeFragmentActivity";
    private HomeFragmentAdapter adapter;
    private String HOME_URL;
    private UserDetaileBean userDetaileBean;
    private TextView home_menu;
    private DrawerLayout mDrawerLayout;
    private NavigationView navView;
    @Override
    public View initView() {
        Log.d(TAG, "主页面UI被初始化");
        View view = View.inflate(mContext, R.layout.fragment_home, null);
        rvHome = (RecyclerView) view.findViewById(R.id.rv_home);
        ib_top = (ImageView) view.findViewById(R.id.ib_top);
        tv_search_home = (TextView) view.findViewById(R.id.tv_search_home);
        tv_message_home = (TextView) view.findViewById(R.id.tv_message_home);
        home_menu=(TextView)view.findViewById(R.id.home_menu);
        home_menu.setOnClickListener(this);
        mDrawerLayout=(DrawerLayout)view.findViewById(R.id.drawer_layout);
        navView=(NavigationView)view.findViewById(R.id.nav_view);

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.update_pswd:
                        Toast.makeText(mContext,"你点击了修改密码",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(mContext, ForgetActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                        break;
                    case R.id.update_acct:
                        Toast.makeText(mContext,"你点击了修改账户",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.login_out:
                        Intent intent1=new Intent(mContext, LoginActivity.class);
                        startActivity(intent1);
                        getActivity().finish();
                        break;
                    case R.id.out:
                        getActivity().finish();
                        break;
                    case R.id.about:
                        Toast.makeText(mContext,"你点击了关于我们",Toast.LENGTH_SHORT).show();
                        break;

                }
                return false;
            }
        });
        initListener();
        userDetaileBean= (UserDetaileBean) getActivity().getIntent().getSerializableExtra("USER");
        Log.d(TAG,userDetaileBean.toString());
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //联网请求数据
        HOME_URL= Constant.ROOTURL+"/hot?hotstatus=1";
        getDataFromNet();
    }

    private void initListener() {
        //置顶的监听
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回到顶部
                Toast.makeText(mContext, "回到顶部", Toast.LENGTH_SHORT).show();
                rvHome.scrollToPosition(0);
            }
        });
        //搜素的监听
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(HomeFragment.this.getActivity(), SearchActivity.class);
                intent.putExtra("USER",userDetaileBean);
                startActivity(intent);
            }
        });
        //消息的监听
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "进入消息中心", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
        case R.id.home_menu:
            mDrawerLayout.openDrawer(GravityCompat.START);
            break;
         default:
        }
        return true;
    }

    private  void getDataFromNet(){
            OkHttpUtils
                    .get()
                    .url(HOME_URL)
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
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.home_menu:
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
        }
    }
    private  void processData(String json){
            ProjectBeanData projectBeanData= JSON.parseObject(json,ProjectBeanData.class);
            if (projectBeanData!=null){
                adapter=new HomeFragmentAdapter(mContext,projectBeanData);
                rvHome.setAdapter(adapter);
                rvHome.setLayoutManager(new GridLayoutManager(mContext,1));
            }else {
            }
        }
}
