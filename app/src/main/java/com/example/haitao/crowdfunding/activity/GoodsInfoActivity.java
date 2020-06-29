package com.example.haitao.crowdfunding.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.haitao.crowdfunding.R;
import com.example.haitao.crowdfunding.bean.GoodsInfoBean;
import com.example.haitao.crowdfunding.util.Constant;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by haitao on 2020/6/22.
 */

public class GoodsInfoActivity extends Activity {
    private GoodsInfoBean goodInfoBean;
    private ImageView ivgoodinfoimage,ib_Goods_back;
    private TextView tvgoodinfodesc,tv_good_info_callcenter,tvgoodinfoname,proportion,tvgoodinfoprice;
    private  TextView details,tv_good_info_collection,tvsurplusday,tv_status;
    private ProgressBar progressbar_money;
    private  int proprotion1=0;
    private  String supportmoney;
    private Integer uid,pid;
    private  String TAG="GoodsInfoActivity";
    private  String COLLECTIONURl;
    private String FOLLOWURL;
    private Button btn_follow_casting,Btn_lead;
    private String price,status;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_info);
        initView();
        goodInfoBean= (GoodsInfoBean)getIntent().getSerializableExtra("GOODSBEAN");
        if (goodInfoBean!=null){
            setDataForGoods(goodInfoBean);
            tv_good_info_collection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pid= goodInfoBean.getId();
                    /*uid=goodInfoBean.getUid();*/
                    uid=Constant.userBean.getId();
                    Log.d(TAG,"uid="+uid.toString());
                    Log.d(TAG,"pid="+pid.toString());
                    COLLECTIONURl=Constant.ROOTURL+"collect?uid="+uid+"&pid="+pid;
                    //地址：http://10.10.81.189:8080/crowdfunding/collect?uid=1&pid=1
                    AlertDialog.Builder dialog=new AlertDialog.Builder(GoodsInfoActivity.this);
                    dialog.setTitle("确认收藏");
                    dialog.setMessage("你确认收藏吗？");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            setDataFormatNet(COLLECTIONURl);
                        }
                    });
                    dialog.setNegativeButton("取消",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    dialog.show();
                }
            });

            btn_follow_casting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder dialog=new AlertDialog.Builder(GoodsInfoActivity.this);
                    dialog.setTitle("确认跟投");
                    dialog.setMessage("你确认跟投吗？");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            pid= goodInfoBean.getId();
                            /*uid=goodInfoBean.getUid();*/
                            uid=Constant.userBean.getId();
                            price=goodInfoBean.getPrice().toString();
                            status=goodInfoBean.getStatus();
                            FOLLOWURL=Constant.ROOTURL+"follow?uid="+uid+"&pid="+pid+"&price="+price+"&status="+status;
                            //http://10.10.81.189:8080/crowdfunding/follow?uid=1&pid=2&price=2000&status=0
                            setfollowData(FOLLOWURL);
                        }
                    });
                    dialog.setNegativeButton("取消",new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    dialog.show();
                }
            });

        }

        ib_Goods_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        tv_good_info_callcenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0826-7629085"));
                startActivity(intent);
            }
        });
        Btn_lead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(GoodsInfoActivity.this);
                dialog.setTitle("确认领头");
                dialog.setMessage("你确认领头吗？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(GoodsInfoActivity.this,"恭喜你领头成功",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("取消",new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(GoodsInfoActivity.this,"你取消了领头",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
            }
        });
    }
    private void setDataFormatNet(String REGISTERURL) {
        OkHttpUtils
                .get()
                .url(REGISTERURL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d(TAG, "收藏请求失败：" + e.toString());
                        Toast.makeText(GoodsInfoActivity.this, "收藏请求失败，请稍后重试", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d(TAG, "收藏请求成功：" + response);
                        Toast.makeText(GoodsInfoActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setfollowData(String FOLLOWURL) {
        OkHttpUtils
                .get()
                .url(FOLLOWURL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d(TAG, "跟投请求失败：" + e.toString());
                        Toast.makeText(GoodsInfoActivity.this, "跟投请求失败，请稍后重试", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.d(TAG, "跟投请求成功：" + response);
                        Toast.makeText(GoodsInfoActivity.this, "跟投成功", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void initView() {
        ivgoodinfoimage=(ImageView)findViewById(R.id.iv_good_info_image);
        tvgoodinfoname=(TextView)findViewById(R.id.tv_good_info_name);
        tvgoodinfodesc=(TextView)findViewById(R.id.tv_good_info_desc);
        proportion=(TextView)findViewById(R.id.tv_proportion);
        progressbar_money=(ProgressBar)findViewById(R.id.progressbar_money);
        tvgoodinfoprice=(TextView)findViewById(R.id.tv_good_info_price);
        tvsurplusday=(TextView)findViewById(R.id.tv_surplus_day);
        tv_status=(TextView)findViewById(R.id.tv_status);
        details=(TextView)findViewById(R.id.details);
        tv_good_info_collection=(TextView)findViewById(R.id.tv_good_info_collection);
        btn_follow_casting=(Button)findViewById(R.id.btn_follow_casting);
        ib_Goods_back=(ImageView)findViewById(R.id.ib_Goods_back);
        tv_good_info_callcenter=(TextView)findViewById(R.id.tv_good_info_callcenter);
        Btn_lead=(Button)findViewById(R.id.Btn_lead);
    }

    private void setDataForGoods(GoodsInfoBean goodInfoBean) {
        Glide.with(this).load(Constant.IMAGE_URL+goodInfoBean.getImageurl()).into(ivgoodinfoimage);
        tvgoodinfoname.setText(goodInfoBean.getName());
        tvgoodinfodesc.setText(goodInfoBean.getInformation());
        double proprotion=(goodInfoBean.getSuppormoney()/goodInfoBean.getMoney())*100;
        proprotion1=(int)proprotion;
        proportion.setText(proprotion1+"%");
        progressbar_money.setProgress(proprotion1);
        supportmoney=String.valueOf(goodInfoBean.getSuppormoney());
        tvgoodinfoprice.setText("已获得意向投资：￥"+supportmoney);
        tvsurplusday.setText("剩余天数:"+goodInfoBean.getDaysrmaining());
        if ("0".equals(goodInfoBean.getStatus())){
            tv_status.setText("进行中");
        }else {
            tv_status.setText("已完成");
        }
        details.setText(goodInfoBean.getBriefintroduction());
    }
}
