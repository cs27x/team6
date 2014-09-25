package com.example.groupsix.groupsixasmtone;


import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by clarkperkins on 9/20/14.
 * 
 */
public class MainTest {

    public static void main(String[] args) throws IOException {

//        File folder = new File("");
//        File[] listOfFiles = folder.listFiles();
//
//        System.out.println(Arrays.toString(listOfFiles));

        RestaurantList rl = RestaurantList.getInstance();

        rl.sortByTime();

        for (Restaurant r : rl) {
            System.out.println(r);
        }

    }
}
