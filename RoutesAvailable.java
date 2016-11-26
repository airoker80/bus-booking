package com.domain.airoker80.testapplication;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by airoker80 on 8/6/2016.
 */
public class RoutesAvailable extends AppCompatActivity {
    private ListView listView;
//    Context context = getApplicationContext();
    private String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.routesavailable);
        listView = (ListView) findViewById(R.id.list_item);
        getJSON();
    }

    public void showRoute() {
        JSONObject jsonObject = null;
        ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
        try {
            //JSONObject  = new JSONObject(JSON_STRING);
            jsonObject = new JSONObject(JSON_STRING);
            JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

            for (int i = 0; i < result.length(); i++) {
                JSONObject jo = result.getJSONObject(i);
                String id = jo.getString(Config.TAG_ID);
                String route = jo.getString(Config.TAG_Route);

                HashMap<String, String> Route = new HashMap<>();
                Route.put(Config.TAG_ID, id);
                Route.put(Config.TAG_Route, route);
                list.add(Route);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ListAdapter adapter = new SimpleAdapter(
                RoutesAvailable.this, list, R.layout.costume_layout,
                new String[]{Config.TAG_ID, Config.TAG_Route},
                new int[]{R.id.Cv_1, R.id.CV_2});

        listView.setAdapter(adapter);
    }



    private void getJSON(){
        class GetJSON extends AsyncTask<Void,Void,String> {
            ProgressDialog loading;
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(RoutesAvailable.this,"Fetching Data","Wait...",false,false);
                Context context = getApplicationContext();
                // Toast.makeText(s., "Selected: " , Toast.LENGTH_LONG).show();
                Toast.makeText(context, "text", Toast.LENGTH_SHORT).show();
            }
            @Override
            protected void onPostExecute(String s) {
  //              Toast.makeText(context, "post", Toast.LENGTH_SHORT).show();
                super.onPostExecute(s);
                loading.dismiss();
                JSON_STRING = s;
                showRoute();

               // Toast.makeText(s., "Selected: " , Toast.LENGTH_LONG).show();


            }
            @Override
            protected String doInBackground(Void... params) {
               // Context context = getApplicationContext();
               // Toast.makeText(context, "back", Toast.LENGTH_SHORT).show();
                RequestHandler rh = new RequestHandler();
                String s = rh.sendGetRequest(Config.URL_GET_EMP);
                return s;


            }
        }
        GetJSON gj = new GetJSON();
        gj.execute();
    }

}



