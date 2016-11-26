package com.domain.airoker80.testapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

/**
 * Created by airoker80 on 8/24/2016.
 */
public class Table extends AppCompatActivity {
    TableLayout tableLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.table);
        tableLayout=(TableLayout)findViewById(R.id.table);
        tableLayout.removeAllViewsInLayout();
        for(int j =0;j<4;j++) {
            for (int i = 0; i < 4; i++) {
                TableRow tableRow=new TableRow(Table.this);
                TextView textView = new TextView(Table.this);
                textView.setTextSize(15);
                //textView.setPadding(10,0,0,0);
                String c = String.valueOf(i);
                textView.setText(c);
                 TextView textView1 = new TextView(Table.this);
                textView1.setTextSize(15);
                textView1.setPadding(10,0,0,0);
                String c1 = String.valueOf(i);
                textView1.setText(c1);
                 TextView textView2 = new TextView(Table.this);
                textView2.setTextSize(15);
                textView2.setPadding(10,0,0,0);
                String c2 = String.valueOf(i);
                textView2.setText(c2);
                 TextView textView3= new TextView(Table.this);
                textView3.setTextSize(15);
                textView3.setPadding(10,0,0,0);
                String c3 = String.valueOf(i);
                textView3.setText(c3);
                 TextView textView4 = new TextView(Table.this);
                textView4.setTextSize(15);
                textView4.setPadding(10,0,0,0);
                String c4 = String.valueOf(i);
                textView4.setText(c4);
                tableRow.addView(textView4);
                tableLayout.addView(tableRow);
                final View view = new View(Table.this);
                view.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT, 1));
                view.setBackgroundColor(Color.WHITE);
                tableLayout.addView(view);

            }
        }
    }
}

