package com.domain.airoker80.testapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
 * Created by airoker80 on 7/4/2016.
 */
public class Register extends AppCompatActivity implements View.OnClickListener {

    public static final String key_Username="UserName";
    public static final String key_Password="Password";

    private static final String REGISTERED_URL="http://192.168.0.129/Test/take_order.php";
    EditText username,password;
    Button signup;
    TextView t1;

    @Override
    protected void onCreate(@Nullable Bundle a1) {
        super.onCreate(a1);
        setContentView(R.layout.signup);
        username =(EditText)findViewById(R.id.name);
        password=(EditText)findViewById(R.id.password);
        signup=(Button)findViewById(R.id.btnRegister);
      //  t1=(TextView)findViewById(R.id.btnLinkToLoginScreen);
        signup.setOnClickListener(this);

    }
    public  void signup(){
        final String UserName=username.getText().toString().trim();
        final String Password = password.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTERED_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Register.this, response, Toast.LENGTH_LONG).show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Register.this, error.toString(), Toast.LENGTH_LONG).show();

                    }}){
            @Override
            public Map<String, String> getParams(){
                Map<String,String> params=new HashMap<String,String>();
                //params.put(key_FirstName,FirstName);
                //params.put(key_lastName,LastName);
                params.put(key_Username,UserName);
                params.put(key_Password,Password);
                // params.put(key_Repassword,RePassword);
                //params.put(key_BloodGroup,BloodGroup);
                //params.put(key_DateOfBirth,DateOfBirth);
                //params.put(key_Home,Home);
                //params.put(key_Office,Office);
                //params.put(key_PhHome,PhHome);
                //params.put(key_PhOffice,PhOffice);
                //params.put(key_Mobile,Mobile);


                return params;

            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        startActivity(new Intent(this,Insert.class));

    }

    @Override
    public void onClick(View v) {
        if(v==signup) {
            signup();
        }
        if(v==t1){
            startActivity(new Intent(this,Login.class));
        }
    }
}


