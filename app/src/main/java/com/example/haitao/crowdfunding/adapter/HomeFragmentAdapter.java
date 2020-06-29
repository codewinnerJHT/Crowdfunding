package com.example.haitao.crowdfunding.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.haitao.crowdfunding.R;
import com.example.haitao.crowdfunding.activity.GoodsInfoActivity;
import com.example.haitao.crowdfunding.activity.MainActivity;
import com.example.haitao.crowdfunding.bean.GoodsInfoBean;
import com.example.haitao.crowdfunding.bean.ProjectBeanData;
import com.example.haitao.crowdfunding.bean.UserDetaileBean;
import com.example.haitao.crowdfunding.util.Constant;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnLoadImageListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by haitao on 2020/6/20.
 */

public class HomeFragmentAdapter extends RecyclerView.Adapter{

    private String TAG="HomeFragmentAdapter";

    public static final int BANNER = 0;//横幅广告

    public static final int CHANNEL = 1;//频道

    public static final int ACT = 2;//活动

    public static final int SECKILL = 3;//秒杀

    public static final int RECOMMEND = 4;//推荐

    public static final int HOT = 5;//热卖

    private LayoutInflater mLayoutInflater;//初始化布局

    private  Context mContext;

    private  ProjectBeanData projectBeanData;//数据

    public int currentType = BANNER;//当前类型

    public UserDetaileBean userDetaileBean;

    public HomeFragmentAdapter(Context mContext, ProjectBeanData projectBeanData) {
            this.mContext=mContext;
            this.projectBeanData=projectBeanData;
            mLayoutInflater=LayoutInflater.from(mContext);
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case BANNER:
                currentType = BANNER;
                break;
            case CHANNEL:
                currentType = CHANNEL;
                break;
            case ACT:
                currentType = ACT;
                break;
            case SECKILL:
                currentType = SECKILL;
                break;
            case RECOMMEND:
                currentType = RECOMMEND;
                break;
            case HOT:
                currentType = HOT;
                break;
        }
        return currentType;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType==BANNER){
            return new  BannerViewHolder(mContext,mLayoutInflater.inflate(R.layout.banner_viewpager,null));
        }
        return new  HotViewHolder(mContext,mLayoutInflater.inflate(R.layout.hot_item,null));


    }
    class HotViewHolder extends RecyclerView.ViewHolder{
        private final Context mContext;
        private TextView tv_more_hot;
        private GridView gv_hot;
        private HotAdapter adapter;
        public HotViewHolder(final Context mContext, View itemView){
            super(itemView);
            this.mContext=mContext;
            tv_more_hot=(TextView)itemView.findViewById(R.id.tv_more_hot);
            gv_hot=(GridView)itemView.findViewById(R.id.gv_hot);

        }

        public void setData(final List<ProjectBeanData.GethostBean> gethost) {
            Log.d(TAG,"gethost的数据"+gethost.toString());
            adapter=new HotAdapter(mContext,gethost);
            gv_hot.setAdapter(adapter);

            gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                   // Toast.makeText(mContext,"position"+i,Toast.LENGTH_SHORT).show();
                    ProjectBeanData.GethostBean hostBean=gethost.get(i);
                    GoodsInfoBean goodsinfobean=new GoodsInfoBean();
                    goodsinfobean.setImageurl(hostBean.getImageurl());
                    goodsinfobean.setName(hostBean.getName());
                    goodsinfobean.setPrice(hostBean.getPrice());
                    goodsinfobean.setInformation(hostBean.getInformation());
                    goodsinfobean.setStatus(hostBean.getStatus());
                    goodsinfobean.setDaysrmaining(hostBean.getDaysremaining());
                    goodsinfobean.setMoney(hostBean.getMoney());
                    goodsinfobean.setSuppormoney(hostBean.getSupportmoney());
                    goodsinfobean.setId(hostBean.getId());
                    goodsinfobean.setPhone(hostBean.getPhone());
                    goodsinfobean.setBriefintroduction(hostBean.getBriefintroduction());
                    MainActivity mainActivity=new MainActivity();
                    userDetaileBean= mainActivity.userDetaileBean;
                    Log.d(TAG,userDetaileBean.toString());
                    goodsinfobean.setUid(userDetaileBean.getId());
                    Log.d(TAG,goodsinfobean.toString());
                    startGoodsinfo(goodsinfobean);
                }
            });
        }
    }
private  void startGoodsinfo(GoodsInfoBean goodInfoBean){
    Intent intent= new Intent(mContext, GoodsInfoActivity.class);
    intent.putExtra("GOODSBEAN",goodInfoBean);
    mContext.startActivity(intent);
}


    class BannerViewHolder extends RecyclerView.ViewHolder{
        private final Context mContext;
        private Banner banner;
        public  BannerViewHolder(Context mContext, View itemView){
            super(itemView);
            this.mContext=mContext;
            this.banner=(Banner)itemView.findViewById(R.id.banner);
        }
        //设置banner的数据
        public void setData(List<ProjectBeanData.BannerurlBean> banner_info) {
            //得到图片地址
            List<String> imagesUrl=new ArrayList<>();
            for (int i = 0; i <banner_info.size() ; i++) {
                String imageUrl=banner_info.get(i).getBannerurl();
                imagesUrl.add(imageUrl);
            }
            banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            banner.setBannerAnimation(Transformer.DepthPage);
            banner.setImages(imagesUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    Log.d(TAG,url.toString());
                    Glide.with(mContext).load(Constant.IMAGE_URL+url).into(view);
                }
            });
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position)==BANNER){
            BannerViewHolder bannerViewHoilder=(BannerViewHolder)holder;
            bannerViewHoilder.setData(projectBeanData.getBannerurl());
        }else {
            Log.d(TAG,"数据"+projectBeanData.getGethost());
            HotViewHolder hotViewHolder=(HotViewHolder)holder;
            hotViewHolder.setData(projectBeanData.getGethost());
        }

        }


    @Override
    public int getItemCount() {
        return 2;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }


}
