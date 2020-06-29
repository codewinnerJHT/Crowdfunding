package com.example.haitao.crowdfunding.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.haitao.crowdfunding.CrowdfundingApplication;
import com.example.haitao.crowdfunding.R;
import com.example.haitao.crowdfunding.bean.UserDetaileBean;
import com.example.haitao.crowdfunding.bean.UserInfoBean;
import com.example.haitao.crowdfunding.util.Constant;
import com.example.haitao.crowdfunding.util.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private  TextView registertextview,login_cancle;
    private EditText LoginNameEditText,LoginPasswordEditText;
    private Button login;
    int REQUEST_CODE=0;
    public RequestQueue requestQueue = null;
    private Context context;
    private CrowdfundingApplication app;
    String TAG="LoginActivity";
    private SharedPreferences pref;
    private CheckBox rememberPass;
    private SharedPreferences.Editor editor;
    private TextView recode_textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        requestQueue= Volley.newRequestQueue(getApplicationContext());
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        InitView();
        rememberPass = (CheckBox) findViewById(R.id.remember_pass);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemember=pref.getBoolean("remember_password",false);

        if (isRemember){
            String account=pref.getString("account","");
            String password=pref.getString("password","");
            LoginNameEditText.setText(account);
            LoginPasswordEditText.setText(password);
            rememberPass.setChecked(true);
        }
    }

    public void InitView(){
        registertextview=(TextView)findViewById(R.id.register_text);
        registertextview.setOnClickListener(this);

        LoginNameEditText=(EditText) findViewById(R.id.LoginName);
        LoginPasswordEditText=(EditText)findViewById(R.id.password);

        login=(Button)findViewById(R.id.Btnlogin);
        login.setOnClickListener(this);

        login_cancle=(TextView)findViewById(R.id.login_cancle);
        login_cancle.setOnClickListener(this);
        recode_textview=(TextView)findViewById(R.id.recode_textview);
        recode_textview.setOnClickListener(this);


    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_text:
                Intent intent_register = new Intent(LoginActivity.this, RegisterActivity.class);
                Log.d(TAG,"你点击了注册按钮");
                startActivity(intent_register);
                finish();
                break;
            case R.id.Btnlogin:
                Log.d(TAG,"你点击了登录按钮");
                loginAction();
                break;
            case R.id.login_cancle:
                Log.d(TAG,"你点击退出按钮");
                this.finish();
                break;
            case R.id.recode_textview:
                Intent intent=new Intent(LoginActivity.this,ForgetActivity.class);
                startActivity(intent);
        }
    }
    public void loginAction(){
        String loginname=LoginNameEditText.getText().toString().trim();
        String loginpswd=MD5Util.digest(LoginPasswordEditText.getText().toString()).trim();
        String LOGINURL= Constant.ROOTURL+"login?username="+loginname+"&userpswd="+loginpswd;
        //String url= "http://192.168.43.18:8080/crowdfunding/login?username=11&userpswd=e10adc3949ba59abbe56e057f20f883e";
        Log.d(TAG,loginname);
        Log.d(TAG,loginpswd);
        OkHttpUtils
                .get()
                .url(LOGINURL)
                .build()
                .execute(new StringCallback()
                {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d(TAG, "首页请求失败："+e.toString());
                        Toast.makeText(LoginActivity.this,"网络连接失败",Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void onResponse(String response, int id) {
                        Log.d(TAG, "首页请求成功："+response);
                        processData(response);
                    }
                });
    }
    private  void processData(String json){
        UserInfoBean userInfoBean= JSON.parseObject(json,UserInfoBean.class);
        //Log.d(TAG,"解析成功="+userInfoBean.getUser().get(0).getUsername());
        if (!userInfoBean.getUser().isEmpty()){
            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
            UserDetaileBean userDetaileBean=new UserDetaileBean();

            userDetaileBean.setLoginacct(userInfoBean.getUser().get(0).getLoginacct());
            userDetaileBean.setUsername(userInfoBean.getUser().get(0).getUsername());
            userDetaileBean.setAccttype(userInfoBean.getUser().get(0).getAccttype());
            userDetaileBean.setAddress(userInfoBean.getUser().get(0).getAddress());
            userDetaileBean.setId(userInfoBean.getUser().get(0).getId());
            Constant.userBean=new UserInfoBean.UserBean();
            Constant.userBean.setLoginacct(userInfoBean.getUser().get(0).getLoginacct());
            Constant.userBean.setUsername(userInfoBean.getUser().get(0).getUsername());
            Constant.userBean.setAccttype(userInfoBean.getUser().get(0).getAccttype());
            Constant.userBean.setAddress(userInfoBean.getUser().get(0).getAddress());
            Constant.userBean.setId(userInfoBean.getUser().get(0).getId());

            String account = LoginNameEditText.getText().toString();
            String password = LoginPasswordEditText.getText().toString();
            editor=pref.edit();
            if (rememberPass.isChecked()){
                editor.putBoolean("remember_password",true);
                editor.putString("account",account);
                editor.putString("password",password);
            } else {
                editor.clear();
            }
            editor.apply();

            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            intent.putExtra("USER",userDetaileBean);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(LoginActivity.this,"账号或密码不正确",Toast.LENGTH_SHORT).show();
        }
    }
}
