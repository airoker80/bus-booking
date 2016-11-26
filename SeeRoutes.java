package com.domain.airoker80.testapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by airoker80 on 8/8/2016.
 */
public class SeeRoutes extends AppCompatActivity {
    TextView tv1,tv2;
    String url="http://192.168.0.121/Test/readroute.php";
    RequestQueue requestQueue;
    private ListView listView;

    private String JSON_STRING;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.routesavailable);
            listView = (ListView) findViewById(R.id.list_item);
            //listView.setOnItemClickListener(this);
            getJSON();
        }


        private void showEmployee(){
            JSONObject jsonObject = null;
            ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String, String>>();
            try {
                jsonObject = new JSONObject(JSON_STRING);
                JSONArray result = jsonObject.getJSONArray(Config.TAG_JSON_ARRAY);

                for(int i = 0; i<result.length(); i++){
                    JSONObject jo = result.getJSONObject(i);
                    String id = jo.getString(Config.TAG_ID);
                    String name = jo.getString(Config.TAG_Route);

                    HashMap<String,String> employees = new HashMap<>();
                    employees.put(Config.TAG_ID,id);
                    employees.put(Config.TAG_Route,name);
                    list.add(employees);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            ListAdapter adapter = new SimpleAdapter(
                    SeeRoutes.this, list, R.layout.costume_layout,
                    new String[]{Config.TAG_ID,Config.TAG_Route},
                    new int[]{R.id.Cv_1, R.id.CV_2});

            listView.setAdapter(adapter);
        }

        private void getJSON(){
            class GetJSON extends AsyncTask<Void,Void,String> {

                ProgressDialog loading;
                @Override
                protected void onPreExecute() {
                    super.onPreExecute();
                    loading = ProgressDialog.show(SeeRoutes.this,"Fetching Data","Wait...",false,false);
                }

                @Override
                protected void onPostExecute(String s) {
                    super.onPostExecute(s);
                    loading.dismiss();
                    JSON_STRING = s;
                    showEmployee();
                }

                @Override
                protected String doInBackground(Void... params) {
                    RequestHandler rh = new RequestHandler();
                    String s = rh.sendGetRequest(Config.URL_GET_EMP);
                    return s;
                }
            }
            GetJSON gj = new GetJSON();
            gj.execute();
        }
    }

