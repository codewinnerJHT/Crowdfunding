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
import com.example.haitao.crowdfunding.bean.ProjectBeanData;
import com.example.haitao.crowdfunding.util.Constant;

import java.util.List;

/**
 * Created by haitao on 2020/6/21.
 */

public class HotAdapter  extends BaseAdapter{
    private final Context mContext;
    private String TAG="HotAdapter";
    private final List<ProjectBeanData.GethostBean> datas;
    public HotAdapter(Context mContext,List<ProjectBeanData.GethostBean> hot_info){
        this.mContext=mContext;
        this.datas=hot_info;
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
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView =View.inflate(mContext, R.layout.item_hot_grid_view,null);
            viewHolder=new ViewHolder();
            viewHolder.iv_hot=(ImageView)convertView.findViewById(R.id.iv_hot);
            viewHolder.tv_name=(TextView)convertView.findViewById(R.id.tv_name);
            viewHolder.tv_surplus_day=(TextView)convertView.findViewById(R.id.tv_surplus_day);
            viewHolder.tv_speed=(TextView)convertView.findViewById(R.id.tv_speed);
            viewHolder.tv_money=(TextView)convertView.findViewById(R.id.tv_money);
            viewHolder.tv_price=(TextView)convertView.findViewById(R.id.tv_price);
            viewHolder.progressbar_money=(ProgressBar)convertView.findViewById(R.id.progressbar_money);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder)convertView.getTag();
        }
        //根据位置得到对应的数据
        Log.d(TAG,"热卖的数据"+datas.toString());
        ProjectBeanData.GethostBean hotBean=datas.get(position);
        Glide.with(mContext).load(Constant.IMAGE_URL+hotBean.getImageurl()).into(viewHolder.iv_hot);
        viewHolder.tv_name.setText(hotBean.getName());
        viewHolder.tv_surplus_day.setText("剩余"+hotBean.getDaysremaining()+"天");
        double speed=hotBean.getSupportmoney()/hotBean.getMoney()*100;
        viewHolder.tv_speed.setText("意向进度"+speed+"%");
        viewHolder.progressbar_money.setProgress((int)speed);
        viewHolder.tv_money.setText("1000元起投");
        viewHolder.tv_price.setText("融资"+hotBean.getMoney()+"万");
        return convertView;
    }

    static class ViewHolder{
        ImageView iv_hot;
        TextView tv_name,tv_surplus_day,tv_speed,tv_money,tv_price;
        ProgressBar progressbar_money;
    }
}
