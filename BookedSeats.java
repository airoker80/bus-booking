package com.domain.airoker80.testapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by airoker80 on 8/22/2016.
 */
public class BookedSeats extends AppCompatActivity {
    final String up_URL="http://192.168.43.92/Test/booked.php";
    TextView response1,bid;
    String b_id,bus_id;
    final String key_id="b_id";
    final String Key_bus_id="bus_id";
    public static final String Tag_date="date";
    public static final String Tag_time="time";
    public static final String Tag_price="price";
    public static final String Tag_seat_id="seat_id";
    public static final String Tag_key_username="username";
    public  static final String Rand ="rand" ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookedseats);
        response1=(TextView)findViewById(R.id.responseb);
        bid=(TextView)findViewById(R.id.id);
        Intent i =getIntent();
        HashMap<String, String> item = (HashMap<String, String>) i.getSerializableExtra("list");
        b_id=item.get("id").toString();
        bus_id=item.get("bus_id").toString();

        bid.setText(b_id);
        book();
    }
    public void  book(){
        //    final String route = etb1.getText().toString().trim();
        // final String no_of_seats=result.getText()
        final String rand = String.valueOf((int) (Math.random()*1234567800));
        StringRequest stringRequest = new StringRequest(Request.Method.POST,up_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(BookedSeats.this, response, Toast.LENGTH_LONG).show();
                        response1.setText(response);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(BookedSeats.this, error.toString(), Toast.LENGTH_LONG).show();

                    }}){
            @Override
            public Map<String, String> getParams(){
                Map<String,String> params=new HashMap<String,String>();
                params.put(key_id,b_id);
                params.put(Key_bus_id,bus_id);
                params.put(Tag_seat_id,b_id);
                params.put(Tag_date,Config.date);
                params.put(Tag_price,Config.price);
                params.put(Tag_time,Config.Time);
                params.put(Rand,rand);
               // params.put(key_id,b_id);
                params.put(Tag_key_username,Config.username);
                return params;

            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        // startActivity(new Intent(this,login.class));

    }

}
