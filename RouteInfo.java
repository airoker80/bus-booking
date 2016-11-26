package com.domain.airoker80.testapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by airoker80 on 8/22/2016.
 */
public class RouteInfo extends AppCompatActivity{
    ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
    ListView listView;
    private static final String LOGIN_URL = "http://192.168.0.109/Test/login1.php";
    public static final String URL_GET_seat = "http://192.168.43.92/Test/readroute1.php";

    public static final String KEY_USERNAME = "username";
    public static final String KEY_PASSWORD = "password";
    boolean flag = true;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routesavailable);
        listView = (ListView) findViewById(R.id.list_item);
       // listView.setOnItemClickListener(this);
        sp_id();
    }

    public void sp_id() {
        //final String route = sp.getSelectedItem().toString();
        // Log.i(TAG,route);
        // final String bus_no = sp1.getSelectedItem().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_GET_seat,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            // startActivity(new Intent(this,Menu.class));
                            //Intent startsecondactivity=new Intent(Insert.this,Menu.class);
                            //startActivity(startsecondactivity);
                            //Intent intent = new Intent(Insert.this, Menu.class);
                            //startActivity(intent);

                            //openProfile(response);
                            Toast.makeText(RouteInfo.this, response, Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(RouteInfo.this, response, Toast.LENGTH_LONG).show();
                            op(response);
                        }
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RouteInfo
                                .this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        )


        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<String, String>();
                // map.put(key_bus_no, bus_no);
                // map.put(key_route, route);
                return map;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    private void op(String response) {

        try {
            JSONObject obj1 = new JSONObject(response);
            JSONArray result = obj1.getJSONArray(Config.TAG_JSON_ARRAY);


            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String route = jo.getString(Config.TAG_JSON_ARRAY);
                String bus_id=jo.getString("bus_id");
                String sa=jo.getString("seats available");
                String date=jo.getString("date");
                String time=jo.getString("time");
                String price=jo.getString("price");
                //String route = jo.getString(Config.TAG_Route);

                HashMap<String, String> Route = new HashMap<>();
                Route.put(Config.TAG_Route, route);
                Route.put(Config.TAG_sa, sa);
                Route.put(Config.TAG_Bus_id, bus_id);
                Route.put(Config.TAG_date,date);
                Route.put(Config.TAG_time,time);
                Route.put(Config.TAG_price,price);
                list.add(Route);
            }
            ListAdapter adapter = new SimpleAdapter(
                    RouteInfo.this, list, R.layout.costume_layout,
                    new String[]{Config.TAG_ID, Config.TAG_Route,Config.TAG_Bus_id,Config.TAG_date,Config.TAG_time,Config.TAG_price},
                    new int[]{R.id.Cv_1, R.id.CV_2,R.id.CV_3,R.id.CV_4,R.id.CV_5,R.id.CV_6});

            listView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}

