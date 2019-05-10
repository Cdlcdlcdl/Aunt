package com.example.aunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import entity.Ordering;


public class OrderingListActivity extends Activity {

    private String session;
    private List<Ordering> oderinglist1;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unorderlist);

        Intent it=this.getIntent();
        oderinglist1=(List<Ordering>)it.getSerializableExtra("ordering1");
        Log.e("PredictTime",oderinglist1.get(0).getPredictTime().toString());
        Log.e("oderinglist1",String.valueOf(oderinglist1.size()));
        session=it.getStringExtra("session");
        Log.e("OrderringListActivity",session   );
        ListView lv1=(ListView)findViewById(R.id.unorderlist1);
        OrderingAdapter orderingAdapter=new OrderingAdapter(OrderingListActivity.this,R.layout.orderitem,oderinglist1);
        lv1.setAdapter(orderingAdapter);
        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Ordering pass2 = oderinglist1.get(position);
                Intent it = new Intent(OrderingListActivity.this, OrderingActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("ordering", pass2);
                it.putExtras(bundle);
                it.putExtra("session",session   );
                OrderingListActivity.this.startActivity(it);

            }
        });

    }

   /* private void initOrdering(){

        Servicetype service=  new Servicetype("大扫除","intro",120.00,180.00);
        Servicetype baojie=new Servicetype("日常保洁","intro",150.00,190.00);
        Servicetype shafa=new Servicetype("真皮沙发保养","intro",190.98,273.09);
        Servicetype dala=new Servicetype("地板打蜡","intro",190.98,273.09);

        Timestamp now=new Timestamp(System.currentTimeMillis());
        User user=new User("张广洁","18189190207","123456",now);
        Worker worker2=new Worker("王媛媛","10239474929","地板打蜡");
        Ordering unorder1=new Ordering(service,now,worker2,user);
        oderinglist1.add(unorder1);

        Ordering ordering2=new Ordering(baojie,now,worker2,user);
        oderinglist1.add(ordering2);
    }
*/

}
