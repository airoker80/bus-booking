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
 * Created by airoker80 on 7/3/2016.
 */
public class Signup extends AppCompatActivity implements View.OnClickListener {
    public static final String key_Username="UserName";
    public static final String key_Password="Password";
    public static final String key_FirstName="fname";
    public static final String key_MiddleName="Mname";
    public static final String key_LastName="Lname";
    public static final String key_Email="Email";
    public static final String key_Phone="Phone";

    private static final String REGISTERED_URL="http://192.168.43.92/Test/take_order.php";
    EditText username,password,fn,mn,lname,email,phone;
    Button signup,login;
    TextView t1;

    @Override
    protected void onCreate(@Nullable Bundle a1) {
        super.onCreate(a1);
        setContentView(R.layout.userdetails);
        username =(EditText)findViewById(R.id.et6);
        password=(EditText)findViewById(R.id.et7);
        signup=(Button)findViewById(R.id.b1);
        fn=(EditText)findViewById(R.id.et1);
        mn=(EditText)findViewById(R.id.et2);
        lname=(EditText)findViewById(R.id.et3);
        email=(EditText)findViewById(R.id.et4);
        phone=(EditText)findViewById(R.id.et5);
        login=(Button)findViewById(R.id.lg);
        signup.setOnClickListener(this);
        login.setOnClickListener(this);

    }
    public  void signup(){
        final String UserName=username.getText().toString().trim();
        final String Password = password.getText().toString().trim();
        final String fname= fn.getText().toString().trim();
        final String Mname=mn.getText().toString().trim();
        final String Lname = lname.getText().toString().trim();
        final String Email =email.getText().toString().trim();
        final String Phone=phone.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTERED_URL,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(Signup.this, response, Toast.LENGTH_LONG).show();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Signup.this, error.toString(), Toast.LENGTH_LONG).show();

                    }}){
            @Override
            public Map<String, String> getParams(){
                Map<String,String> params=new HashMap<String,String>();

                params.put(key_Username,UserName);
                params.put(key_Password,Password);
                params.put(key_FirstName,fname);
                params.put(key_MiddleName,Mname);
                params.put(key_LastName,Lname);

                params.put(key_Email,Email);
                //params.put(key_DateOfBirth,DateOfBirth);
                //params.put(key_Home,Home);
                //params.put(key_Office,Office);
                params.put(key_Phone,Phone);
                //params.put(key_PhOffice,PhOffice);
                //params.put(key_Mobile,Mobile);


                return params;

            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
       // startActivity(new Intent(this,login.class));

    }

    @Override
    public void onClick(View v) {
    if(v==signup) {
    signup();
}
    if(v==login){
        startActivity(new Intent(this,Insert.class));
    }
    }
}
