package com.example.groupsix.groupsixasmtone;


/**
 * Created by clarkperkins on 9/20/14.
 */
public class MainTest {

    public static void main(String[] args) {

        RestaurantList rl = new RestaurantList();

        for (Restaurant r : rl) {
            System.out.println(r.toString());
        }

    }
}
