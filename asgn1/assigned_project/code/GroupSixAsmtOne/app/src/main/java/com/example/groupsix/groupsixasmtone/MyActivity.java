package com.example.groupsix.groupsixasmtone;

import android.app.ListActivity;
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
import java.util.List;
import java.util.Map;


public class MyActivity extends ListActivity {
    Restaurant restaurant;
    SimpleAdapter listViewAdapter;

    static final List<Map<String,String>> list = new ArrayList<Map<String,String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_details);
        list.clear();
        TextView textViewDetailsName = (TextView) findViewById(R.id.textViewDetailsName);
        restaurant = (Restaurant) getIntent().getExtras().getSerializable("restaurant");
        textViewDetailsName.setText(restaurant.getName());
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
            textViewMealMoneyVal.setText("Yes");
        } else {
            textViewMealMoneyVal.setText("No");
        }

        TextView textViewDeliversVal = (TextView) findViewById(R.id.textViewDeliversVal);
        if(restaurant.isDelivers()) {
            textViewDeliversVal.setText("Yes");
        } else {
            textViewDeliversVal.setText("No");
        }
        ListView listViewDetails = (ListView) findViewById(android.R.id.list);
        //input for loop to put in day information

        String[] subitems = {"Day", "Time"};
        String[] days = {"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        listViewAdapter = new SimpleAdapter(this, list, R.layout.custom_row_view_two,
                subitems, new int[] {R.id.dayDetails,R.id.subitemDetails});
        setListAdapter(listViewAdapter);
        for(int i = 0; i < 7; i++) {
            HashMap<String,String> temp = new HashMap<String,String>();
            temp.put("Day",days[i]);
            String open = restaurant.getFormattedOpenTimeString(i);
            String close = restaurant.getFormattedClosedTimeString(i);
            if (open.equals("closed") || close.equals("closed")) {
                temp.put("Time", "closed");
            } else {
                temp.put("Time", open + " - " + close);
            }
            list.add(temp);
        }
        listViewDetails.deferNotifyDataSetChanged();

        ///sdf//

    }
}
