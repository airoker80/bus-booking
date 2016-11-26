package com.domain.airoker80.testapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by airoker80 on 5/7/2016.
 */
public class  Menu extends ListActivity  {
    String classes[] = {"BookingInfo","RouteInfo","KathmanduToPokharaBooking","ManakamanaToPokharaBooking","KathmanduToManakamanaBooking","Bus_refrence"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, classes));
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String s1 = classes[position];
        try {
            Class ourClass = Class.forName("com.domain.airoker80.testapplication."+s1);
            Intent i1 = new Intent(Menu.this, ourClass);
            startActivity(i1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu_main,menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.au:
            Intent i1 = new Intent("com.domain.airoker80.testapplication.test1");
                startActivity(i1);
                break;
            case R.id.pr:
                break;
        }
        return  false;
    }
}