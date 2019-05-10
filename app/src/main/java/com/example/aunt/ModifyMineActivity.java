package com.example.aunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.List;

public class ModifyMineActivity extends Activity {

    private EditText mNameEdit;
    private EditText mAgeEdit;
    private EditText mIntroEdit;
    private EditText mTypeCheck;
    private Button   modify;
    private String session;
    public void onCreate(Bundle savedInstanceState) {
        Intent it=this.getIntent();
       session =it.getStringExtra("session");
        Log.e("session123456789",session);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modifymine);
        modify=(Button)findViewById(R.id.modify );
        mNameEdit=(EditText)findViewById(R.id.newname);
        mAgeEdit=(EditText)findViewById(R.id.newage);
        mIntroEdit=(EditText)findViewById(R.id.newintro);
        modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("session123456789",session);
                OkHttpClient okHttpClient=new OkHttpClient();
                okHttpClient.setCookieHandler(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
                String mBaseUrl="http://47.101.204.40:8080/app_housework/Worker_Modify";
                FormEncodingBuilder requestBodyBuilder=new FormEncodingBuilder();
                RequestBody requestBody=requestBodyBuilder.add("newname",mNameEdit.getText().toString()).add("newage",mAgeEdit.getText().toString()).add("intro",mIntroEdit.getText().toString()).build();
                Request.Builder builder=new Request.Builder();
                Request request = builder.url(mBaseUrl).addHeader("cookie",session).post(requestBody).build();
                Call call=okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(Request request, IOException e) {
                        //输出出错信息
                        L.e("onFailure:"+e.getMessage());
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(final Response response) throws IOException {

                        final String res=response.body().string();
                        Log.e("denglu", res);
                        Intent intentindex = new Intent();
                        intentindex.setClass(ModifyMineActivity.this, IndexActivity.class);
                        intentindex.putExtra("session", session);
                        ModifyMineActivity.this.startActivity(intentindex);
                    }
                });//正确返回结束
            }
        });
    }

}
