package com.example.haitao.crowdfunding.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.haitao.crowdfunding.R;
import com.example.haitao.crowdfunding.bean.NewsDetailsBean;
import com.example.haitao.crowdfunding.util.Constant;

public class NewsDetailsActivity extends Activity  implements View.OnClickListener{
private NewsDetailsBean newsDetailsBean;
    private TextView tv_title,tv_publisher,tv_time,iv_content_one,tv_content_two,tv_conten_three;
    private ImageView iv_portrait,iv_follow,iv_image_one,iv_image_two,iv_image_three;
    private ImageButton ib_news_back;
    private Button btn_fabu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        newsDetailsBean= (NewsDetailsBean)getIntent().getSerializableExtra("NEWSBEAN");
        if (newsDetailsBean!=null){
              //Toast.makeText(this, "goodsBean=="+newsDetailsBean.toString(), Toast.LENGTH_SHORT).show();
           // setDataForGoods(goodInfoBean);
            initview();

            tv_title.setText(newsDetailsBean.getTitle());
            tv_publisher.setText(newsDetailsBean.getPublisher());
            tv_time.setText(newsDetailsBean.getTime());
            iv_content_one.setText(newsDetailsBean.getContentone());
            tv_content_two.setText(newsDetailsBean.getContenttwo());
            tv_conten_three.setText(newsDetailsBean.getContentthree());

            Glide.with(this).load(Constant.IMAGE_URL+newsDetailsBean.getImageurlone()).into(iv_image_one);
            Glide.with(this).load(Constant.IMAGE_URL+newsDetailsBean.getImageurltwo()).into(iv_image_two);
            Glide.with(this).load(Constant.IMAGE_URL+newsDetailsBean.getImageurlthree()).into(iv_image_three);

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ib_news_back:
                this.finish();
                break;
            case R.id.btn_fabu:
                Toast.makeText(NewsDetailsActivity.this,"发布成功",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void initview() {
        tv_title=(TextView)findViewById(R.id.tv_title);
        tv_publisher=(TextView)findViewById(R.id.tv_publisher);
        tv_time=(TextView)findViewById(R.id.tv_time);
        iv_content_one=(TextView)findViewById(R.id.iv_content_one);
        tv_content_two=(TextView)findViewById(R.id.tv_content_two);
        tv_conten_three=(TextView)findViewById(R.id.tv_conten_three);

        iv_image_one=(ImageView)findViewById(R.id.iv_image_one);
        iv_image_two=(ImageView)findViewById(R.id.iv_image_two);
        iv_image_three=(ImageView)findViewById(R.id.iv_image_three);

        ib_news_back=(ImageButton)findViewById(R.id.ib_news_back);
        ib_news_back.setOnClickListener(this);
        btn_fabu=(Button)findViewById(R.id.btn_fabu);
        btn_fabu.setOnClickListener(this);
    }

}
