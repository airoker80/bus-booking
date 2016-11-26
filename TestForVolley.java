package com.domain.airoker80.testapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
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
 * Created by airoker80 on 8/15/2016.
 */
public class TestForVolley extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener{
    private static final String TAG =null ;
    Spinner sp,sp1;
    Button bt1;
    RequestQueue requestQueue;
    EditText etb1,etb2,etb3;

    String url="http://192.168.0.112/Test/seatsinfo.php";
    TextView result;
    private static final String BOOK_URL="http://192.168.0.112/Test/book.php";
    public static final String key_route="route";
    public static final String key_bus_no="bus_no";
    public static final String key_seats="no_of_seats";
    public static final String key_Password="password";
    ArrayList<String> listIterm = new ArrayList<>();
    ArrayList<String> listIterm1 = new ArrayList<>();
    ArrayAdapter<String> adapter,adapter1;
    protected void onStart() {
        super.onStart();
        BackTask backTask = new BackTask();
        backTask.execute();
        BackTask1 backTask1=new BackTask1();
        backTask1.execute();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testforvolley);
        sp=(Spinner)findViewById(R.id.spinner);
        sp1=(Spinner)findViewById(R.id.spinner2);
        adapter=new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.txt,listIterm);
        adapter1=new ArrayAdapter<String>(this,R.layout.spinner_layout1,R.id.txt1,listIterm1);
        sp.setAdapter(adapter);
        sp1.setAdapter(adapter1);
        etb2=(EditText)findViewById(R.id.eb2);
       // etb3=(EditText)findViewById(R.id.eb3);
        bt1=(Button)findViewById(R.id.btn_book);
        bt1.setOnClickListener(this);
        result=(TextView)findViewById(R.id.result);
        result.setOnClickListener(this);
    }
    private class BackTask extends AsyncTask<Void,Void,Void> {
        ArrayList<String> list;
        @Override
        protected Void doInBackground(Void... params) {
            InputStream inputStream=null;
            String result="";
            try {
                HttpClient httpClient =new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://192.168.0.112/Test/spin.php");
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
                    list.add(jsonObject.getString("route_name"));
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
                HttpPost httpPost = new HttpPost("http://192.168.0.112/Test/bus_no.php");
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
                    list.add(jsonObject.getString("bus_no"));
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

        test();
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
                            Toast.makeText(TestForVolley.this, response, Toast.LENGTH_LONG).show();
                            openProfile(response);
                        }
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(TestForVolley
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


}
