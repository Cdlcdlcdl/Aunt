package com.example.aunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;

import entity.Unorder;


public class UnorderActivity extends Activity {

    private TextView username;
    private TextView userphone;
    private TextView usertime;
    private TextView usertype;
    private TextView useradd;

    private Button qvxiao;
    private String session;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order1);

        Intent it=this.getIntent();
        session=it.getStringExtra("session");
        final Unorder unorder=(Unorder)it.getSerializableExtra("unorder");

        toast(unorder.getServicetype().getServiceTypeName());

        qvxiao=(Button)findViewById(R.id.modify);
        username=(TextView)findViewById(R.id.unorderusername);
        userphone=(TextView)findViewById(R.id.unorderuserphone);
        usertime=(TextView)findViewById(R.id.unordertime);
        usertype=(TextView)findViewById(R.id.unordertype);
        useradd=(TextView)findViewById(R.id.unorderadd);

        username.setText("用户名："+unorder.getUser().getUserName());
        userphone.setText("用户电话："+unorder.getUser().getPhoneNumber());
        usertype.setText("服务类型："+unorder.getServicetype().getServiceTypeName());
        usertime.setText("服务价格："+unorder.getServicetype().getUserPrice()+"元/小时");
        useradd.setText("订单地址："+unorder.getAddress());

        qvxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //去接单
                OkHttpClient okHttpClient=new OkHttpClient();
                okHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
                String mBaseUrl="http://47.101.204.40:8080/app_housework/Ordering_Save";
                FormEncodingBuilder requestBodyBuilder=new FormEncodingBuilder();
                RequestBody requestBody=requestBodyBuilder.add("unorder_id",unorder.getOrderId()).build();
                Request.Builder builder=new Request.Builder();
                Request request = builder.url(mBaseUrl).post(requestBody).post(requestBody).build();
                Call call=okHttpClient.newCall(request);
                //这里取消订单
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {

                    }

                    @Override
                    public void onResponse(Response response) throws IOException {
                           if(response.isSuccessful()){
                               toast("接单成功");
                               Log.e("result",response.body().toString());
                               Intent it=new Intent(UnorderActivity.this,IndexActivity.class);
                               it.putExtra("session",session);
                               UnorderActivity.this.startActivity(it);

                           }else{
                               toast("订单取消失败");
                           }
                    }
                });
            }
        });


    }
    private void toast(final String str) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(UnorderActivity.this, str, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
