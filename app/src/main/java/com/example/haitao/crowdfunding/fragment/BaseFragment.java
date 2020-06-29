package com.example.haitao.crowdfunding.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.example.haitao.crowdfunding.CrowdfundingApplication;

import java.util.ArrayList;

/**
 * Created by haitao on 2020/6/19.
 */

public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    private ArrayList<BaseFragment> fragments;
    public RequestQueue requestQueue = null;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CrowdfundingApplication app=new CrowdfundingApplication();
        requestQueue= app.requestQueue;
        mContext=getActivity();
    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return initView();
    }
    public abstract View initView();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    public  void initData(){

    }

}
