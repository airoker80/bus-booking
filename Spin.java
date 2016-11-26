package com.domain.airoker80.testapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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

/**
 * Created by airoker80 on 8/14/2016.
 */
public class Spin extends AppCompatActivity {
    Spinner sp;
    ArrayList<String> listIterm = new ArrayList<>();
    ArrayAdapter<String> adapter;

    //  private ListView listView;

    private String JSON_STRING;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spiner);
        sp=(Spinner)findViewById(R.id.spiner);
        adapter=new ArrayAdapter<String>(this,R.layout.spinner_layout,R.id.txt,listIterm);
        sp.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        BackTask backTask = new BackTask();
        backTask.execute();
    }

    private class BackTask extends AsyncTask<Void,Void,Void> {
        ArrayList<String> list;
        @Override
        protected Void doInBackground(Void... params) {
            InputStream inputStream=null;
            String result="";
            try {
                HttpClient httpClient =new DefaultHttpClient();
                HttpPost httpPost = new HttpPost("http://192.168.123.3/Test/spin.php");
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
}
