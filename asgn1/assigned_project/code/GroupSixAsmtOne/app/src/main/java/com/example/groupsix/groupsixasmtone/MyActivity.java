package com.example.groupsix.groupsixasmtone;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;


public class MyActivity extends ActionBarActivity {
    Object restaurant;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurant_details);
        TextView textViewDetailsName = (TextView) findViewById(R.id.textViewDetailsName);
        Intent intent = new Intent(this, MainActivity.class);
        restaurant = getIntent().getExtras().getSerializable("restaurant");
        ListView listViewDetails = (ListView) findViewById(R.id.listViewDetails);

        //input for loop to put in day information
    }
}
