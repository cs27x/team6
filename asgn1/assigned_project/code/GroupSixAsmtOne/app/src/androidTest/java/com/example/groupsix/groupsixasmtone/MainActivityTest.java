package com.example.groupsix.groupsixasmtone;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by clarkperkins on 9/23/14.
 *
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    private MainActivity mMainActivity;

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mMainActivity = getActivity();
    }

    public void testJSONLoad() {
        RestaurantList rl = RestaurantList.getInstance(mMainActivity.getResources());

        assertEquals(rl.size(), 10);

        for (Object r : rl) {
            assertTrue(r instanceof Restaurant);
        }
    }
}
