package com.example.groupsix.groupsixasmtone;


import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by clarkperkins on 9/20/14.
 * 
 */
public class MainTest {

    public static void main(String[] args) throws IOException {

        RestaurantList rl = RestaurantList.getInstance();

        for (Restaurant r : rl) {
            JSONSerializer serializer = new JSONSerializer().include("openTimes").include("closingTimes");

            String serialized = serializer.serialize(r);

            System.out.println(serialized);

            JSONDeserializer<Restaurant> deserializer = new JSONDeserializer<Restaurant>();

            Restaurant r2 = deserializer.deserialize(serialized);

            System.out.println(r2);

        }



    }
}
