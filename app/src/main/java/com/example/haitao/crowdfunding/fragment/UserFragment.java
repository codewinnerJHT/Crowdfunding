package com.example.haitao.crowdfunding.fragment;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.haitao.crowdfunding.R;
import com.example.haitao.crowdfunding.util.Constant;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by haitao on 2020/6/19.
 */

public class UserFragment extends BaseFragment {

    private String TAG="UserFragmentActivity";
    private TextView user_name,user_acct,lianxi,user_count,user_sum;
    private Button exit;
    private String USERCOUNT;
    private String USERSUM;
    @Override
    public View initView() {
        View view=View.inflate(mContext, R.layout.fragment_user,null);
        user_name=(TextView)view.findViewById(R.id.user_name);
        user_acct=(TextView)view.findViewById(R.id.user_acct);
        lianxi=(TextView)view.findViewById(R.id.lianxi);
        user_count=(TextView)view.findViewById(R.id.user_count);
        user_name.setText("账户名："+Constant.userBean.getUsername());
        exit=(Button)view.findViewById(R.id.exit);
        user_sum=(TextView)view.findViewById(R.id.user_sum);
        if ("0".equals(Constant.userBean.getAccttype())){
            user_acct.setText("个人账户");
        }else {
            user_acct.setText("公司账户");
        }
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
            }
        });
        lianxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0866-7894623"));
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.d(TAG,"我的页面的数据初始化了");
        USERCOUNT=Constant.ROOTURL+"selectCount?uid="+Constant.userBean.getId();
        //http://10.10.81.189:8080/crowdfunding/selectCount?uid=1
        getCount(USERCOUNT);
        USERSUM=Constant.ROOTURL+"Summoney?uid="+Constant.userBean.getId();
        //http://10.10.81.189:8080/crowdfunding/Summoney?uid=2
        getSum(USERSUM);

    }

    private void getSum(String USERSUM) {
        OkHttpUtils
                .get()
                .url(USERSUM)
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d(TAG, "我的请求失败："+e.toString());
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.d(TAG, "我的请求成功："+response);
                        JSONObject ob= JSON.parseObject(response);
                        String count=ob.getString("num");
                        user_sum.setText("已投资金额："+count+"元");
                    }
                });

    }

    private void getCount(String USERCOUNT) {
        OkHttpUtils
                .get()
                .url(USERCOUNT)
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d(TAG, "我的请求失败："+e.toString());
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.d(TAG, "我的请求成功："+response);
                        JSONObject ob= JSON.parseObject(response);
                        String count=ob.getString("num");
                        user_count.setText("已投资数："+count+"笔");
                    }
                });
    }
}
