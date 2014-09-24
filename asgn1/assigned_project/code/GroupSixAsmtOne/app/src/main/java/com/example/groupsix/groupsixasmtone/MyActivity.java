package com.example.groupsix.groupsixasmtone;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;


public class MyActivity extends ActionBarActivity {
    Restaurant restaurant;
    SimpleAdapter listViewAdapter;

    static final ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_details);

        TextView textViewDetailsName = (TextView) findViewById(R.id.textViewDetailsName);
        restaurant = (Restaurant) getIntent().getExtras().getSerializable("restaurant");
        TextView textViewFoodTypeVal = (TextView) findViewById(R.id.textViewFoodTypeVal);
        textViewFoodTypeVal.setText(restaurant.getFoodType());
        TextView textViewOnCampusVal = (TextView) findViewById(R.id.textViewOnCampusVal);
        if(restaurant.isOnCampus()) {
            textViewOnCampusVal.setText("Yes");
        } else {
            textViewOnCampusVal.setText("No");
        }
        TextView textViewMealMoneyVal = (TextView) findViewById(R.id.textViewMealMoneyVal);
        if(restaurant.isMealMoney()) {
            textViewOnCampusVal.setText("Yes");
        } else {
            textViewOnCampusVal.setText("No");
        }
        TextView textViewDeliversVal = (TextView) findViewById(R.id.textViewDeliversVal);
        if(restaurant.isDelivers()) {
            textViewOnCampusVal.setText("Yes");
        } else {
            textViewOnCampusVal.setText("No");
        }
        ListView listViewDetails = (ListView) findViewById(R.id.listViewDetails);
        //input for loop to put in day information

        String[] subitems = {"Day", "Time"};
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        listViewAdapter = new SimpleAdapter(this, list, R.layout.custom_row_view,
                subitems, new int[] {R.id.textDay,R.id.textTime});

        for(int i = 0; i < 7; i++) {
            HashMap<String,String> temp = new HashMap<String,String>();
            temp.put("Day",days[i]);
            temp.put("Time", Integer.toString(restaurant.getOpenTimes()[i] / 60) + ":00 - " + Integer.toString(restaurant.getClosingTimes()[i] / 60) + ":00");
            list.add(temp);
        }


    }
}
