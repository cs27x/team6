package com.example.groupsix.groupsixasmtone;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

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
        Log.i("SIZE all", "" + allRestaurants.size());
        setUpSpinners();
//        listViewRestaurants = (ListView) findViewById(R.id.listViewRestaurants);
        ListView listViewRestaurants = (ListView) findViewById(android.R.id.list);

        filteredRestaurants = allRestaurants.getOpen();
        Log.i("SIZE all", "" + filteredRestaurants.size());

        listViewRestaurants.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Restaurant restaurant = filteredRestaurants.get(i);
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
		String[] subitems = {"Name", "Distance", "Hours Status", "Meal Status"};
        listViewAdapter = new SimpleAdapter(this, list, R.layout.custom_row_view,
        										  subitems, new int[] {R.id.text1,R.id.text2, R.id.text3, R.id.text4});
		//for(Object o : restaurants) {
        GPS_Locator gps = new GPS_Locator(getApplicationContext());
        for(int i = 0; i < filteredRestaurants.size(); i++) {
			//populate listViewAdapter
            Restaurant restaurant = filteredRestaurants.get(i);
			HashMap<String,String> temp = new HashMap<String,String>();
			temp.put("Distance","" + restaurant.getDistanceFrom(gps.getLatitude(), gps.getLongitude()));
			if(restaurant.isOpen()) {
                temp.put("Hours Status", "Open");
            } else {
                temp.put("Hours Status", "Closed");
            }
            if(restaurant.isMealMoney()) {
                temp.put("Meal Status", "Meal Money");
            } else {
                temp.put("Meal Status", "Meal Plan");
            }
			list.add(temp);
		}
	}
}
