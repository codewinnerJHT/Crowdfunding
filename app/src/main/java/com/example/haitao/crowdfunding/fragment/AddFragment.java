package com.example.haitao.crowdfunding.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.haitao.crowdfunding.R;
import com.example.haitao.crowdfunding.util.Constant;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by haitao on 2020/6/19.
 */

public class AddFragment extends BaseFragment {

    private String TAG="AddFragmentActivity";
    private EditText tv_project_name,tv_info,tv_money,tv_day,tv_brief;
    private Button release_button;
    private String ADDURL;
    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_add, null);
        tv_project_name=(EditText)view.findViewById(R.id.tv_project_name);
        tv_info=(EditText)view.findViewById(R.id.tv_info);
        tv_money=(EditText)view.findViewById(R.id.tv_money);
        tv_day=(EditText)view.findViewById(R.id.tv_day);
        tv_brief=(EditText)view.findViewById(R.id.tv_brief);
        release_button=(Button)view.findViewById(R.id.release_button);
        release_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=tv_project_name.getText().toString();
                String information=tv_info.getText().toString();
                String money=tv_money.getText().toString();
                String day=tv_day.getText().toString();
                String briefintroduction=tv_brief.getText().toString();
                ADDURL= Constant.ROOTURL+"add?name="+name+"&information="+information+"&money="+money+"&day="+day+"&briefintroduction="+briefintroduction;
                //http://10.10.81.189:8080/crowdfunding/add?name=1&information=1"&money=1&day=1&briefintroduction=12


                AlertDialog.Builder dialog=new AlertDialog.Builder(mContext);
                dialog.setTitle("确认发布");
                dialog.setMessage("你确认发布吗？");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        addproject(ADDURL);
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
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        Log.d(TAG,"添加页面的数据初始化了");
    }

    private void addproject(String url){
        OkHttpUtils
                .get()
                .url(url)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d(TAG, "发布项目请求失败：" + e.toString());
                        Toast.makeText(mContext, "发布项目失败，请稍后重试", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.d(TAG, "发布项目请求成功：" + response);
                        Toast.makeText(mContext, "发布成功", Toast.LENGTH_SHORT).show();
                        tv_project_name.setText("");
                        tv_info.setText("");
                        tv_money.setText("");
                        tv_day.setText("");
                        tv_brief.setText("");
                    }
                });
    }

}
