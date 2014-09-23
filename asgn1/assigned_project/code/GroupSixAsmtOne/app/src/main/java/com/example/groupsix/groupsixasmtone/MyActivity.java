package com.example.groupsix.groupsixasmtone;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;


public class MyActivity extends ActionBarActivity {
    Restaurant restaurant;
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
    }
}
