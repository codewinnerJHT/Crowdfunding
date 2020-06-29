package com.example.haitao.crowdfunding.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.haitao.crowdfunding.R;
import com.example.haitao.crowdfunding.util.Constant;
import com.example.haitao.crowdfunding.util.MD5Util;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;

import static com.example.haitao.crowdfunding.R.id.register_button;

public class RegisterActivity extends Activity implements View.OnClickListener{


    private Button registerbutton;
    private TextView user_name_edit,user_name_error,user_acct_edit,user_acct_error,password_edit,password_error,confirm_password_edit,confirm_password_error;
    private ImageView register_back,user_name_clear,user_acct_clear,password_clear,confirm_password_clear;
    private String regex="0\\d{2,3}[-]?\\d{7,8}|0\\d{2,3}\\s?\\d{7,8}|13[0-9]\\d{8}|15[1089]\\d{8}";;
    private String phone;
    Pattern pattern= Pattern.compile(regex);
    private String TAG="RegisterActivity";
    private String newsloginacct;
    private String newusername;
    private String newpassword1;
    private String newpassword2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();


    }
    @Override
    public void onClick(View view){
        switch (view.getId()) {
            case R.id.register_back:
                this.finish();
                break;
            case R.id.register_button:
                try {
                    RegisterUser();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.user_name_clear:
                user_name_edit.setText("");
                break;
            case R.id.user_acct_clear:
                user_acct_edit.setText("");
                break;
            case R.id.password_clear:
                password_edit.setText("");
                break;
            case R.id.confirm_password_clear:
                confirm_password_edit.setText("");
                break;
/*          case R.id.user_name_edit:
                user_name_error.setVisibility(View.GONE);
                user_name_clear.setVisibility(View.VISIBLE);
                password_clear.setVisibility(View.GONE);
               *//* confirm_password_clear.setVisibility(View.GONE);*//*
                break;
              case R.id.password_edit:
                password_error.setVisibility(View.GONE);
                user_name_clear.setVisibility(View.GONE);
                password_clear.setVisibility(View.VISIBLE);
                confirm_password_clear.setVisibility(View.GONE);

                break;*/
        }
    }
    public void initView(){

        user_name_clear=(ImageView)findViewById(R.id.user_name_clear);
        user_name_clear.setOnClickListener(this);
        user_name_clear.setVisibility(View.VISIBLE);

        user_acct_clear=(ImageView)findViewById(R.id.user_acct_clear);
        user_acct_clear.setOnClickListener(this);
        user_acct_clear.setVisibility(View.VISIBLE);

        password_clear=(ImageView)findViewById(R.id.password_clear);
        password_clear.setOnClickListener(this);
        password_clear.setVisibility(View.VISIBLE);

        confirm_password_clear=(ImageView)findViewById(R.id.confirm_password_clear);
        confirm_password_clear.setOnClickListener(this);
        confirm_password_clear.setVisibility(View.VISIBLE);

        registerbutton=(Button) findViewById(register_button);
        registerbutton.setOnClickListener(this);

        user_name_edit=(TextView)findViewById(R.id.user_name_edit);
        user_name_edit.setOnClickListener(this);
        user_name_error=(TextView) findViewById(R.id.user_name_error);

        user_acct_edit=(TextView)findViewById(R.id.user_acct_edit);
        user_acct_edit.setOnClickListener(this);
        user_acct_error=(TextView)findViewById(R.id.user_acct_error);

        password_edit=(TextView) findViewById(R.id.password_edit);
        password_edit.setOnClickListener(this);
        password_error=(TextView)findViewById(R.id.password_error);

        confirm_password_edit=(TextView) findViewById(R.id.confirm_password_edit);
        confirm_password_edit.setOnClickListener(this);
        confirm_password_error=(TextView) findViewById(R.id.confirm_password_error);

        register_back=(ImageView)findViewById(R.id.register_back);
        register_back.setOnClickListener(this);



    }
public  void RegisterUser() throws InterruptedException {
    phone=user_acct_edit.getText().toString();
    Matcher matcher= pattern.matcher(phone);
   if (user_name_edit.getText().toString().trim().equals("")
            || user_name_edit.getText().toString().trim().length() > 20
            || user_name_edit.getText().toString().trim().length() < 4) {
        user_name_error.setVisibility(View.VISIBLE);
    }else if(!matcher.matches()){
       user_acct_error.setVisibility(View.VISIBLE);
    } else if (password_edit.getText().toString().trim().equals("")
            || password_edit.getText().toString().trim().length() > 16
            || password_edit.getText().toString().trim().length() < 6) {
        password_error.setVisibility(View.VISIBLE);
    } else if (!confirm_password_edit.getText().toString().trim()
            .equals(password_edit.getText().toString().trim())) {
        confirm_password_error.setVisibility(View.VISIBLE);
    } else {
        newsloginacct=user_acct_edit.getText().toString();
        newusername = user_name_edit.getText().toString();
       newpassword1= MD5Util.digest(password_edit.getText().toString()).trim();
       newpassword2=password_edit.getText().toString();
       final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("注册中，请稍后...");
        progressDialog.show();
       String REGISTERURL= Constant.ROOTURL+"register?username="+newusername+"&userpswd="+newpassword1+"&loginacct="+newsloginacct;
       //http://10.10.81.189:8080/crowdfunding/register?username=22&userpswd=22&loginacct=1234
       setDataFormatNet(REGISTERURL);
}
}

private void setDataFormatNet(String REGISTERURL){
    OkHttpUtils
            .get()
            .url(REGISTERURL)
            .build()
            .execute(new StringCallback()
            {
                @Override
                public void onError(Call call, Exception e, int id) {
                    Log.d(TAG, "注册请求失败："+e.toString());
                    Toast.makeText(RegisterActivity.this, "注册失败，请稍后重试", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onResponse(String response, int id) {
                    Log.d(TAG, "注册请求成功："+response);
                    Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
                    intent.putExtra("username",newusername);
                    intent.putExtra("userpwsd",newpassword2);
                    startActivity(intent);
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
}
}
