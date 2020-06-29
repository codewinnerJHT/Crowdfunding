package com.example.haitao.crowdfunding.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.haitao.crowdfunding.R;
import com.example.haitao.crowdfunding.bean.CollectionInfoBean;
import com.example.haitao.crowdfunding.util.Constant;

import java.util.List;

/**
 * Created by haitao on 2020/6/26.
 */

public class CollectionAdapter extends BaseAdapter{
private Context mContext;
    private String TAG="CollectionAdapter";
    private final List<CollectionInfoBean.ProjectsBean> datas;
    public CollectionAdapter(Context mContext, List<CollectionInfoBean.ProjectsBean> collect_projecti){
        this.mContext=mContext;
        this.datas=collect_projecti;
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
        if(converView==null){
            converView=View.inflate(mContext, R.layout.collection_item,null);
            viewHolder =new ViewHolder();
            viewHolder.iv_collection_image=(ImageView)converView.findViewById(R.id.iv_collection_image);
            viewHolder.pb_collection=(ProgressBar) converView.findViewById(R.id.pb_collection);
            viewHolder.tv_collection_status=(TextView) converView.findViewById(R.id.tv_collection_status);
            viewHolder.tv_collection_title=(TextView) converView.findViewById(R.id.tv_collection_title);
            viewHolder.project_id=(TextView) converView.findViewById(R.id.project_id);
            viewHolder.tv_baifen=(TextView)converView.findViewById(R.id.tv_baifen);
            converView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)converView.getTag();
        }
        CollectionInfoBean.ProjectsBean projectsBean=datas.get(position);
        Glide.with(mContext).load(Constant.IMAGE_URL+projectsBean.getImageurl()).into(viewHolder.iv_collection_image);
        viewHolder.tv_collection_title.setText(projectsBean.getName());
        if ("0".equals(projectsBean.getStatus())){
            viewHolder.tv_collection_status.setText("进行中");
        }else {
            viewHolder.tv_collection_status.setText("已完成");
        }
        double proprotion=(projectsBean.getSupportmoney()/projectsBean.getMoney())*100;
        viewHolder.pb_collection.setProgress((int)proprotion);
        viewHolder.tv_baifen.setText(proprotion+"%");
        Log.d(TAG,"项目数据："+projectsBean.toString());
        return converView;
    }

    static  class ViewHolder{
        ImageView iv_collection_image;
        ProgressBar pb_collection;
        TextView tv_collection_status,tv_collection_title,project_id,tv_baifen;
    }
}
