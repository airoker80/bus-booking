package com.domain.airoker80.testapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by airoker80 on 8/20/2016.
 */
public class Logout  extends AppCompatActivity{
    @Override
    protected void onDestroy() {
        Intent i =new Intent(Logout.this,Menu.class);
        startActivity(i);
        super.onDestroy();
    }
}
