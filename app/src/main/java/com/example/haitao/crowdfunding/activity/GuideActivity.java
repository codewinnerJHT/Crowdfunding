package com.example.haitao.crowdfunding.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.haitao.crowdfunding.R;
import com.example.haitao.crowdfunding.util.CacheUtils;
import com.example.haitao.crowdfunding.util.DensityUtil;

import java.util.ArrayList;

public class GuideActivity extends Activity {

    private ViewPager viewpager;
    private Button btn_start_main;
    private LinearLayout ll_point_group;
    private ArrayList<ImageView> imageViews;
    private ImageView iv_red_point;
    private int widthdpi ;
    private int leftmax;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        viewpager= (ViewPager)findViewById(R.id.viewpager);
        btn_start_main=(Button)findViewById(R.id.btn_start_main);
        ll_point_group=(LinearLayout)findViewById(R.id.ll_point_group);
        iv_red_point=(ImageView) findViewById(R.id.iv_red_point);


        //准备数据
        int[] ids=new int[]{
                R.drawable.guide_1,
                R.drawable.guide_2,
                R.drawable.guide_3
        };
        widthdpi = DensityUtil.dip2px(this,10);
        imageViews=new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            ImageView imageview=new ImageView(this);
            //设置背景
            imageview.setBackgroundResource(ids[i]);
            imageViews.add(imageview);

            //创建点
            ImageView point=new ImageView(this);
            point.setBackgroundResource(R.drawable.point_normal);

            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(widthdpi,widthdpi);
            if (i !=0){
                //不包括第一个，相聚10点
                params.leftMargin=35;
            }
            point.setLayoutParams(params);
            //添加到线性布局
            ll_point_group.addView(point);

        }
        //设置viewpager的适配器
        viewpager.setAdapter(new MyPagerAdapter());
        iv_red_point.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener());
        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());


        btn_start_main.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                CacheUtils.putBoolean(GuideActivity.this,WelcomeActivity.START_MAIN,true);
                Intent intent=new Intent(GuideActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        /**
         * 当页面回调了会回调这个方法
         * @param position 当前滑动页面的位置
         * @param positionOffset 页面滑动的百分比
         * @param positionOffsetPixels 滑动的像数
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {



        }

        /**
         * 当页面被选中的时候，回调这个方法
         * @param position 被选中页面的对应的位置
         */
        @Override
        public void onPageSelected(int position) {
            if(position==imageViews.size()-1){
                //最后一个页面
                btn_start_main.setVisibility(View.VISIBLE);
            }else{
                //其他页面
                btn_start_main.setVisibility(View.GONE);
            }

        }

        /**
         * 当ViewPager页面滑动状态发生变化的时候
         * @param state
         */
        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener{
        @Override
        public void onGlobalLayout() {
            //执行不只一次
            iv_red_point.getViewTreeObserver().removeGlobalOnLayoutListener(MyOnGlobalLayoutListener.this);

//            间距  = 第1个点距离左边的距离 - 第0个点距离左边的距离
            leftmax = ll_point_group.getChildAt(1).getLeft() - ll_point_group.getChildAt(0).getLeft();
        }
    }


class MyPagerAdapter extends PagerAdapter{
    @Override
    public int getCount() {
        return imageViews.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        //return view==imageViews.get(Integer.parseInt((String) object));
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=imageViews.get(position);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        container.removeView((View) object);
    }
}
}
