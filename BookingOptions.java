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
 * Created by airoker80 on 8/18/2016.
 */
public class BookingOptions extends ListActivity {
    String classes[] = {"Reserve","KtmToManakamana","ManakamanaToPokhara","KMseeBook","MkPkseeBook"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(BookingOptions.this, android.R.layout.simple_list_item_1, classes));
    }


    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String s1 = classes[position];
        try {
            Class ourClass = Class.forName("com.domain.airoker80.testapplication."+s1);
            Intent i1 = new Intent(BookingOptions.this, ourClass);
            startActivity(i1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu m) {
        super.onCreateOptionsMenu(m);
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menue_book,m);
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
