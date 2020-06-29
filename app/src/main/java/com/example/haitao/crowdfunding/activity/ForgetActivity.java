package com.example.haitao.crowdfunding.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.haitao.crowdfunding.R;
import com.example.haitao.crowdfunding.util.Constant;
import com.example.haitao.crowdfunding.util.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

public class ForgetActivity extends Activity {
    private ImageView forget_back;
    private EditText user_acct_foget,user_code_foget,foget_password_edit,forget_confirm_password_edit;
    private Button get_code;
    private Button reset_button;
    private TextView forget_user_acct_error,foget_code_error,forget_password_error,forget_confirm_password_error;
    private String regex="0\\d{2,3}[-]?\\d{7,8}|0\\d{2,3}\\s?\\d{7,8}|13[0-9]\\d{8}|15[1089]\\d{8}";
    private String TAG="ForgetActivity";
    private String UPDATEPSWDURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        initview();
        forget_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        get_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog=new AlertDialog.Builder(ForgetActivity.this);
                dialog.setTitle("请记住验证码");
                dialog.setMessage("验证码【369878】");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
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
        reset_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if("369878".equals(user_code_foget.getText().toString().trim())){
                    if (!user_acct_foget.getText().toString().matches(regex)){
                        forget_user_acct_error.setVisibility(View.VISIBLE);
                    }else if(foget_password_edit.getText().toString().trim().equals("")
                            || foget_password_edit.getText().toString().trim().length() > 16
                            || foget_password_edit.getText().toString().trim().length() < 6){
                        forget_password_error.setVisibility(View.VISIBLE);
                    }else if(!forget_confirm_password_edit.getText().toString().trim()
                            .equals(foget_password_edit.getText().toString().trim())){
                        forget_confirm_password_error.setVisibility(View.VISIBLE);
                    }else{
                      String  newsloginacct=user_acct_foget.getText().toString();
                      String   newpassword= MD5Util.digest(foget_password_edit.getText().toString()).trim();
//                        Toast.makeText(ForgetActivity.this,"zhanghu:"+newsloginacct,Toast.LENGTH_LONG).show();
//                        Toast.makeText(ForgetActivity.this,"mima:"+newpassword,Toast.LENGTH_LONG).show();
                        UPDATEPSWDURL= Constant.ROOTURL+"updatepswd?newloginacct="+newsloginacct+"&newpassword="+newpassword;
                        //http://10.10.81.189:8080/crowdfunding/updatepswd?newloginacct=12&newpassword=33
                        updatepswd(UPDATEPSWDURL);

                    }
                }else {
                    foget_code_error.setVisibility(View.VISIBLE);
                }
            }
        });
        user_acct_foget.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    forget_user_acct_error.setVisibility(View.VISIBLE);
                }else {
                    forget_user_acct_error.setVisibility(View.GONE);
                }
            }
        });
        foget_password_edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    forget_password_error.setVisibility(View.VISIBLE);
                }else {
                    forget_password_error.setVisibility(View.GONE);
                }
            }
        });
        forget_confirm_password_edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b){
                    forget_confirm_password_error.setVisibility(View.VISIBLE);
                }else {
                    forget_confirm_password_error.setVisibility(View.GONE);
                }
            }
        });

    }

    private void initview() {
        forget_back=(ImageView)findViewById(R.id.forget_back);
        user_acct_foget=(EditText)findViewById(R.id.user_acct_foget);
        user_code_foget=(EditText)findViewById(R.id.user_code_foget);
        foget_password_edit=(EditText)findViewById(R.id.foget_password_edit);
        forget_confirm_password_edit=(EditText)findViewById(R.id.forget_confirm_password_edit);
        get_code=(Button)findViewById(R.id.get_code);
        reset_button=(Button)findViewById(R.id.reset_button);
        forget_user_acct_error=(TextView)findViewById(R.id.forget_user_acct_error);
        foget_code_error=(TextView)findViewById(R.id.foget_code_error);
        forget_password_error=(TextView)findViewById(R.id.forget_password_error);
        forget_confirm_password_error=(TextView)findViewById(R.id.forget_confirm_password_error);
    }
private void updatepswd(String url){
    OkHttpUtils
            .get()
            .url(url)
            .build()
            .execute(new StringCallback()
            {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Log.d(TAG, "找回密码请求失败："+e.toString());
                    Toast.makeText(ForgetActivity.this,"网络连接失败",Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onResponse(String response, int id) {
                    Log.d(TAG, "找回密码请求成功："+response);
                    JSONObject ob= JSON.parseObject(response);
                    String issuccess=ob.getString("num");
                    if ("1".equals(issuccess)){
                        Toast.makeText(ForgetActivity.this,"密码重置成功，请登录",Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(ForgetActivity.this,"网络错误",Toast.LENGTH_SHORT).show();
                    }
                }
            });
}
}

