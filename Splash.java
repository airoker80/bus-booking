package com.domain.airoker80.testapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by airoker80 on 5/7/2016.
 */
public class Splash extends Activity {
    @Override

    protected void onCreate(Bundle iamSameer) {
        super.onCreate(iamSameer);
        setContentView(R.layout.splash);
        Thread t1= new Thread(){
            public  void run(){
                try {
                    sleep(2500);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent i=new Intent("com.domain.airoker80.testapplication.Insert");
                    startActivity(i);
                }
            }
        };
        t1.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
