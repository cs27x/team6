package com.example.groupsix.groupsixasmtone;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainActivity extends ListActivity {
	RestaurantList allRestaurants;
    RestaurantList filteredRestaurants;
	ListView listViewRestaurants;
	Spinner spinnerSorting;
	Spinner spinnerFilter;
	ArrayAdapter<String> spinnerSortingAdapter;
	ArrayAdapter<String> spinnerFilterAdapter;
	SimpleAdapter listViewAdapter;
	
	static final ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        allRestaurants = RestaurantList.getInstance();

        setUpSpinners();
//        listViewRestaurants = (ListView) findViewById(R.id.listViewRestaurants);
        ListView listViewRestaurants = (ListView) findViewById(android.R.id.list);

        filteredRestaurants = allRestaurants.getOpen();

        listViewRestaurants.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Restaurant restaurant = new Restaurant();
                if (!filteredRestaurants.isEmpty()) {
                    restaurant = filteredRestaurants.get(i);
                }
                Intent intent = new Intent(getBaseContext(), MyActivity.class);
                intent.putExtra("restaurant", restaurant);
                startActivity(intent);
            }
        });

        populateList();

        setListAdapter(listViewAdapter);
    }
    
	private void setUpSpinners() {
		// TODO Auto-generated method stub
		spinnerSorting = (Spinner) findViewById(R.id.spinnerSorting);
		spinnerSortingAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		spinnerSortingAdapter.add("Distance");
		spinnerSortingAdapter.add("Time Until Closed");
		spinnerSorting.setAdapter(spinnerSortingAdapter);
		spinnerSorting.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if(position == 0) {
                    GPS_Locator gps = new GPS_Locator(getApplicationContext());
				    filteredRestaurants.sortByDistance(gps.getLatitude(), gps.getLongitude());
				} else {
					filteredRestaurants.sortByTime();
				}
				populateList();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			};
		});
			
		spinnerFilter = (Spinner) findViewById(R.id.spinnerFilters);
		spinnerFilterAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
		spinnerFilterAdapter.add("Open");
		spinnerFilterAdapter.add("Meal Plan");
		spinnerFilterAdapter.add("Food Type");
		spinnerFilterAdapter.add("Taste of Nashville");
		spinnerFilter.setAdapter(spinnerFilterAdapter);
		spinnerFilter.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				if (position == 0) {
                    filteredRestaurants = allRestaurants.getOpen();
				} else if (position == 1) {
					filteredRestaurants = allRestaurants.getMealPlan();
				} else {
					filteredRestaurants = allRestaurants.getToN();
				}
				populateList();
			}
			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			};
		});
	}

	private void populateList() {
		// TODO Auto-generated method stub
		
		//MAKE SURE TO CLEAR LISTVIEW BEFORE POPULATING LITVIEW
        list.clear();

		String[] subitems = {"Name", "Distance", "Hours Status", "Meal Status"};
        listViewAdapter = new SimpleAdapter(this, list, R.layout.custom_row_view,
        										  subitems, new int[] {R.id.text1,R.id.text2, R.id.text3, R.id.text4});

        String[] names = {"Adog", "Btheone", "Cdoit", "Dthecode", "Eyourmeal", "Fyourself", "Geachone"};
        String[] distance = {"1000", "2000", "3000", "4000", "5000", "6000", "7000"};
        String[] mealstatus = {"Red", "Orange", "Yellow", "Green", "Cyan", "Blue", "Purple"};
        for(int i = 0; i < 7; i++) {
			//populate listViewAdapter
			HashMap<String,String> temp = new HashMap<String,String>();
            temp.put("Name", names[i]);
			temp.put("Distance",distance[i]);
			temp.put("Hours Status", "Open");
			temp.put("Meal Status", mealstatus[i]);
			list.add(temp);
		}
	}
}
