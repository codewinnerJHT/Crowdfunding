package com.example.haitao.crowdfunding.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.RadioGroup;

import com.example.haitao.crowdfunding.R;
import com.example.haitao.crowdfunding.bean.UserDetaileBean;
import com.example.haitao.crowdfunding.fragment.AddFragment;
import com.example.haitao.crowdfunding.fragment.BaseFragment;
import com.example.haitao.crowdfunding.fragment.CollectionFragment;
import com.example.haitao.crowdfunding.fragment.HomeFragment;
import com.example.haitao.crowdfunding.fragment.NewsFragment;
import com.example.haitao.crowdfunding.fragment.UserFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private ArrayList<BaseFragment> fragments;
    private RadioGroup rgmain;
    private  int position=0;
    private  Fragment tempFragemnt;//上次显示的Fragment
    public static UserDetaileBean userDetaileBean;
    private  String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
        initListener();
        userDetaileBean=(UserDetaileBean)getIntent().getSerializableExtra("USER");
        Log.d(TAG,userDetaileBean.toString());

    }
    protected void initListener(){
        rgmain=(RadioGroup)findViewById(R.id.rg_main);

        rgmain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i){
                    case R.id.rb_home:
                        position=0;
                        break;
                    case R.id.rb_type:
                        position=1;
                        break;
                    case R.id.rb_community:
                        position=2;
                        break;
                    case R.id.rb_cart:
                        position=3;
                        break;
                    case R.id.rb_user:
                        position=4;
                        break;
                    default:
                        position=0;
                        break;
                }
                //根据位置不同取fragment
                BaseFragment baseFragment=getFragment(position);
                //第一个参数：上次显示的fragment
                //第二个参数：当前将要显示的Fragment

                switchFragment(tempFragemnt,baseFragment);
            }
        });
        rgmain.check(R.id.rb_home);
    }
    private void initFragment(){
        fragments = new ArrayList<>();
        fragments.add(new HomeFragment());
        fragments.add(new NewsFragment());
        fragments.add(new AddFragment());
        fragments.add(new CollectionFragment());
        fragments.add(new UserFragment());
    }
    private BaseFragment getFragment(int position) {
        if (fragments != null && fragments.size() > 0) {
            BaseFragment baseFragment = fragments.get(position);
            return baseFragment;
        }
        return null;
    }

    /**
     * 修改activity
     * @param fromFragment
     * @param nextFragment
     */
    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (tempFragemnt != nextFragment) {
            tempFragemnt = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction =getSupportFragmentManager().beginTransaction();
                //判断 nextFragment 是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    //隐藏当前 Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }
}
