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

public class MainActivity extends ListActivity {
	RestaurantList restaurants;
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
        restaurants = RestaurantList.getInstance();
        setUpSpinners();
//        listViewRestaurants = (ListView) findViewById(R.id.listViewRestaurants);
        ListView listViewRestaurants = (ListView) findViewById(android.R.id.list);
        listViewRestaurants.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Restaurant restaurant = restaurants.get(i);
                Intent intent = new Intent(getBaseContext(), MyActivity.class);
                intent.putExtra("restaurant", restaurant);
                startActivity(intent);

            }
        });
        //sort restaurants here by something!
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
				    restaurants.sortByDistance(
                            
                    );
				} else {
					// call sorting for Time Until closed
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
					//call filter for open
				} else if (position == 1) {
					// call filter for Meal Plan
				} else if(position == 2) {
					// call filter for Food Type
				} else {
					// call filter for Taste of Nashville
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
        for(int i = 0; i < 7; i++) {
			//populate listViewAdapter
			HashMap<String,String> temp = new HashMap<String,String>();
			temp.put("Distance","MONT Blanc");
			temp.put("Hours Status", "200.00$");
			temp.put("Meal Status", "Silver, Grey, Black");
			list.add(temp);
		}
	}
}
