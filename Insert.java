package com.domain.airoker80.testapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
 * Created by airoker80 on 7/4/2016.
 */
public class Insert extends AppCompatActivity implements View.OnClickListener {
    private static final String LOGIN_URL="http://192.168.43.92/Test/login1.php";
    public static final String KEY_USERNAME="username";
    public static final String KEY_PASSWORD="password";
    boolean flag=true;
    // int flag=1;
    Button login,signup;
    EditText eusername,epassword;
    String username,password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eusername=(EditText)findViewById(R.id.lname);
        epassword=(EditText)findViewById(R.id.lpass);
        signup=(Button)findViewById(R.id.ssignup);
        login=(Button)findViewById(R.id.llogin);
        login.setOnClickListener(this);
        signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v==login) {
            login();
        }
        if(v==signup){
            startActivity(new Intent(this,Signup.class));
            //Toast.makeText(Insert.this,Config.username,Toast.LENGTH_LONG).show();
        }
    }

    private void login() {

        username=eusername.getText().toString().trim();
        password=epassword.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.trim().equals("success")) {
                            // startActivity(new Intent(this,Menu.class));
                            //Intent startsecondactivity=new Intent(Insert.this,Menu.class);
                            //startActivity(startsecondactivity);
                            //Intent intent = new Intent(Insert.this, Menu.class);
                            //startActivity(intent);
                            Config.username=username;
                            openProfile();
                        }
                        else {
                            Toast.makeText(Insert.this, response, Toast.LENGTH_LONG).show();
                        }
                    }


                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Insert
                                .this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
        )


        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> map = new HashMap<String, String>();
                map.put(KEY_USERNAME, username);
                map.put(KEY_PASSWORD, password);
                return map;

            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void openProfile(){
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);

    }
}
