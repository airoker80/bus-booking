package com.domain.airoker80.testapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by airoker80 on 8/20/2016.
 */
public class ManakamanaToPokhara extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {
    Spinner sp,sp1;
    public String name1,name2;
    TextView result;
    Button bt1,bt2;
    EditText etb1,etb2,etb3;
    String url="http://192.168.0.108/Test/seatsinfo2.php";
    String sp_url="http://192.168.0.108/Test/sp_id.php";
    //String id_url="http://192.168.123.3/Test/route_id.php";
    private static final String BOOK_URL="http://192.168.0.108/Test/book3.php";
    public static final String key_route="route";
    public static final String key_rand="rand";
    public static final String key_bus_no="bus_no";
    //public static final String key_route_id="route_id";
    public static final String key_rem_seats="rem_seats";
    public static final String key_seats="no_of_seats";
    public static final String key_sp_id="sp_id";
    public static final String key_Password="password";
    public static final String key_username="username";
    ArrayList<String> listIterm = new ArrayList<>();
    ArrayList<String> listIterm1 = new ArrayList<>();
    ArrayAdapter<String> adapter,adapter1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book);
        sp=(Spinner)findViewById(R.id.spinner);
        sp1=(Spinner)findViewById(R.id.spinner2);
        adapter=new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.txt,listIterm);
        adapter1=new ArrayAdapter<String>(this,R.layout.spinner_layout1,R.id.txt1,listIterm1);
        sp.setAdapter(adapter);
        sp1.setAdapter(adapter1);
        result=(TextView)findViewById(R.id.result);
        etb2=(EditText)findViewById(R.id.eb2);
        //etb3=(EditText)findViewById(R.id.eb3);
        bt1=(Button)findViewById(R.id.btn_book);
        bt2=(Button)findViewById(R.id.bt1);
        bt2.setOnClickListener(this);
        bt1.setOnClickListener(this);
    }
    @Override
    protected void onStart() {
        super.onStart();
        BackTask backTask = new BackTask();
        backTask.execute();
        BackTask1 backTask1=new BackTask1();
        backTask1.execute();
    }
    public void  book(){
        //    final String route = etb1.getText().toString().trim();
        final String route = sp.getSelectedItem().toString();
        final String bus_no = sp1.getSelectedItem().toString();
        final String seats = etb2.getText().toString().trim();
        //  final String password = etb3.getText().toString().trim();
        final String av_seats=result.getText().toString().trim();
        final String rand = String.valueOf((int) (Math.random()*1234567800));
        final String r_id = name1;
        final String sp_id=name2;
        final int av_seat=Integer.parseInt(av_seats);
        final int req_seat=Integer.parseInt(seats);
        final int rem_seat=av_seat-req_seat;
        final String rem_seats=String.valueOf(rem_seat);
        //result.setText(rem_seat);
        // final String no_of_seats=result.getText()

        StringRequest stringRequest = new StringRequest(Request.Method.POST, BOOK_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ManakamanaToPokhara.this, response, Toast.LENGTH_LONG).show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ManakamanaToPokhara.this, error.toString(), Toast.LENGTH_LONG).show();
                        //result.setText(req_seat);

                    }}){
            @Override
            public Map<String, String> getParams(){
                Map<String,String> params=new HashMap<String,String>();


                params.put(key_route,route);
                //params.put(key_Password,password);
                params.put(key_seats,seats);
                params.put(key_bus_no,bus_no);
                params.put(key_rem_seats,rem_seats);
               // params.put(key_route_id,r_id);
                params.put(key_rand,rand);
                params.put(key_sp_id,sp_id);
               // params.put(key_username,Config.username);
                return params;

            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        // startActivity(new Intent(this,login.class));

    }

    private class BackTask extends AsyncTask<Void,Void,Void> {
        ArrayList<String> list;
        @Override
        protected Void doInBackground(Void... params) {
            InputStream inputStream=null;
            String result="";
            try {
                HttpClient httpClient =new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://192.168.0.108/Test/spin2.php");
                HttpResponse httpResponse =httpClient.execute(httpPost);
                HttpEntity entity =httpResponse.getEntity();
                inputStream = entity.getContent();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    result+=line;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                JSONArray jsonArray =new JSONArray(result);
                for(int i =0;i<jsonArray.length();i++){
                    JSONObject jsonObject =jsonArray.getJSONObject(i);
                    list.add(jsonObject.getString("route"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listIterm.addAll(list);
            adapter.notifyDataSetChanged();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            list=new ArrayList<>();
        }
    }
    private class BackTask1 extends AsyncTask<Void,Void,Void> {
        ArrayList<String> list;
        @Override
        protected Void doInBackground(Void... params) {
            InputStream inputStream=null;
            String result="";
            try {
                HttpClient httpClient =new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://192.168.0.108/Test/bus_no2.php");
                HttpResponse httpResponse =httpClient.execute(httpPost);
                HttpEntity entity =httpResponse.getEntity();
                inputStream = entity.getContent();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
                String line="";
                while ((line=bufferedReader.readLine())!=null){
                    result+=line;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                JSONArray jsonArray =new JSONArray(result);
                for(int i =0;i<jsonArray.length();i++){
                    JSONObject jsonObject =jsonArray.getJSONObject(i);
                    list.add(jsonObject.getString("bus_id"));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            listIterm1.addAll(list);
            adapter1.notifyDataSetChanged();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            list=new ArrayList<>();
        }
    }

    @Override
    public void onClick(View v) {
        if(v==bt1){
            book();
        }
        if(v==bt2){
            sp_id();
            test();
            //Test();

        }


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if(view==sp){
            String route = parent.getItemAtPosition(position).toString();
            final String Route =route;

        }
        if(view==sp1){
            String bus_no = parent.getItemAtPosition(position).toString();
            final String Bus_no =bus_no;

        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void test(){
        final String route = sp.getSelectedItem().toString();
        // Log.i(TAG,route);
        final String bus_no = sp1.getSelectedItem().toString();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
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

                        }
                        else {
                            Toast.makeText(ManakamanaToPokhara.this, response, Toast.LENGTH_LONG).show();
                            openProfile(response);
                        }
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ManakamanaToPokhara
                                .this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        )


        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<String, String>();
                map.put(key_bus_no, bus_no);
                map.put(key_route, route);
                return map;

            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
    private void openProfile(String response){
        try {
            JSONObject obj =new JSONObject(response);
            JSONArray result1=obj.getJSONArray("seats");
            JSONObject Jobj =result1.getJSONObject(0);
            String name=Jobj.getString("no_of_seats");
            result.setText(name);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    public void sp_id(){
        //final String route = sp.getSelectedItem().toString();
        // Log.i(TAG,route);
        final String bus_no = sp1.getSelectedItem().toString();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, sp_url,
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

                        }
                        else {
                            Toast.makeText(ManakamanaToPokhara.this, response, Toast.LENGTH_LONG).show();
                            op(response);
                        }
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ManakamanaToPokhara
                                .this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        )


        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<String, String>();
                map.put(key_bus_no, bus_no);
                // map.put(key_route, route);
                return map;

            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
    private void op(String response){
        try {
            JSONObject obj1 =new JSONObject(response);
            JSONArray result2=obj1.getJSONArray("sp_id");
            JSONObject Jobj1 =result2.getJSONObject(0);
            name2=Jobj1.getString("sp_id");
            //result.setText(name1);
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}
