package com.example.haitao.crowdfunding.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.example.haitao.crowdfunding.R;
import com.example.haitao.crowdfunding.util.CacheUtils;

public class WelcomeActivity extends Activity {
    public static final String START_MAIN = "start_main";
private RelativeLayout rl_splahs_root;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        rl_splahs_root=(RelativeLayout)findViewById(R.id.rl_splahs_root);
        AlphaAnimation aa=new AlphaAnimation(0,1);
        aa.setFillAfter(true);

        ScaleAnimation sa=new ScaleAnimation(0,1,0,1,ScaleAnimation.RELATIVE_TO_SELF,0.5f,ScaleAnimation.RELATIVE_TO_SELF,0.5f);
        sa.setFillAfter(true);

        RotateAnimation ra=new RotateAnimation(0,360,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        ra.setFillAfter(true);

        AnimationSet set=new AnimationSet(false);
        set.addAnimation(ra);
        set.addAnimation(aa);
        set.addAnimation(sa);
        set.setDuration(2000);

        rl_splahs_root.startAnimation(set);

        set.setAnimationListener(new MyAnimationListener());
    }
class MyAnimationListener implements Animation.AnimationListener{


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        boolean isStartMain= CacheUtils.getBoolean(WelcomeActivity.this,START_MAIN);
        Intent intent;
        if (isStartMain){
            intent=new Intent(WelcomeActivity.this,LoginActivity.class);

        }else {
            intent=new Intent(WelcomeActivity.this,GuideActivity.class);

        }
        startActivity(intent);
            finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
}
