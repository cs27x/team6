package com.example.groupsix.groupsixasmtone;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by clarkperkins on 9/20/14.
 *
 */
public class RestaurantListTest {

    @Test
    public void testLoadFromJSON() throws Exception {
        RestaurantList rl = new RestaurantList();
        assertEquals(rl.size(), 10);

        for (Object r : rl) {
            assertTrue(r instanceof Restaurant);
        }
    }

    @Test
    public void test2() throws Exception {

    }
}
