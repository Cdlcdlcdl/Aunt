package com.example.aunt;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.sql.Timestamp;

import entity.Ordered;
import entity.Ordering;


public class TimerActivity extends Activity {

    private Button startTimer;
    private Button stopTimer;
    private Button restartTimer;
    private Button complete;
    private Chronometer chronometer;
    private Timestamp startTime;
    private Timestamp finishTime;
    private boolean IsStop=false;
    private  Ordering ording;
    private  String session;
    public void onCreate(Bundle savedInstanceState) {
        Intent it=this.getIntent();
        ording=(Ordering)it.getSerializableExtra("ordering");
        session=it.getStringExtra("session");
        Log.e("session",session );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer);
        chronometer=(Chronometer)findViewById(R.id.timer);
        startTimer=(Button)findViewById( R.id.start);
        stopTimer=(Button)findViewById( R.id.stop);
        restartTimer=(Button)findViewById( R.id.restart);
        complete=(Button) findViewById(R.id.complete);
        startTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                int hour = (int) ((SystemClock.elapsedRealtime() - chronometer.getBase()) / 1000 / 60);
                chronometer.setFormat("0"+String.valueOf(hour)+":%s");
                chronometer.start();
                startTime = new Timestamp(System.currentTimeMillis());
                startTimer.setText("正在计时");
            }
        });
        stopTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!IsStop) {
                    chronometer.stop();
                    stopTimer.setText("继续计时");
                    finishTime=new Timestamp(System.currentTimeMillis());
                    IsStop=true;
                }
                else{

                    chronometer.start();;
                    stopTimer.setText("停止计时");
                    finishTime=new Timestamp(System.currentTimeMillis());
                    IsStop=false;
                }

            }
        });
        restartTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                startTimer.setText("正在计时");
            }
        });
        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Timestamp now=new Timestamp(System.currentTimeMillis());
                Ordered ordered=new Ordered();
                Log.e("startTime",startTime.toString());
                ordered.setStartTime(startTime);
                ordered.setFinishTime(finishTime);
                ordered.setOrderId(ording.getOrderId());
                ordered.setAddress(ording.getAddress());
                ordered.setWorkid(ording.getWorker().getWorkerId());
                ordered.setUserid(ording.getUser().getUserId());
                ordered.setIsPaid(false);
                ordered.setPredictTime(now);
                ordered.setCost(100.0);
                ordered.setAddTime(now);
                ordered.setServicetypeid(ording.getServicetype().getServiceTypeId());
                Gson gson=new GsonBuilder().setPrettyPrinting().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
                String json=gson.toJson(ordered);
                OkHttpClient okHttpClient=new OkHttpClient();
                //  RequestBody requestBody=RequestBody.create(JSON,json);
                FormEncodingBuilder requestBodyBuilder=new FormEncodingBuilder();
                RequestBody requestBody=requestBodyBuilder.add("OrderingJson", json).add("editorType","add").build();
                Request.Builder builder=new Request.Builder();
                String url=" http://47.101.204.40:8080/app_housework/Ordered_Save" ;
                Request request = builder.url(url).post(requestBody).post(requestBody).build();
                                                  /* Request request=new Request.Builder()
                                                           .addHeader("cookie",session)
                                                           .url("http://114.115.139.178:8080/app_housework/Unorder_Save")
                                                           .post(requestBody)
                                                           .build();*/
                Call call=okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                       // toast("创建订单失败，请检查网络连接");
                    }
                    @Override
                    public void onResponse(Response response) throws IOException {
                        if (response.isSuccessful()) {
                            //添加成功后 跳转到主页面
                            //toast("您已成功下单！");
                            Log.e("ordering ",response.body().toString());
                            Intent it = new Intent(TimerActivity.this, IndexActivity.class);
                            it.putExtra("session", session);
                            TimerActivity.this.startActivity(it);
                        }
                    }
                });
                // toast("下单成功");
                Log.e("ording",ording.getOrderId());
                Log.e("unorder1", json);
            }
        });

    }

}
