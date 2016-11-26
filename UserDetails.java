package com.domain.airoker80.testapplication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by airoker80 on 8/2/2016.
 */
public class UserDetails extends AppCompatActivity implements View.OnClickListener{
    EditText et1;
    Button b1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        b1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

    }
}
