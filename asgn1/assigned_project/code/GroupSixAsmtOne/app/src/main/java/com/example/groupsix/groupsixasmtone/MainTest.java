package com.example.groupsix.groupsixasmtone;


import java.io.IOException;

/**
 * Created by clarkperkins on 9/20/14.
 * 
 */
public class MainTest {

    public static void main(String[] args) throws IOException {

        RestaurantList rl = RestaurantList.getInstance();

        rl.sortByTime();

        for (Restaurant r : rl) {
            System.out.println(r);
        }

    }
}
