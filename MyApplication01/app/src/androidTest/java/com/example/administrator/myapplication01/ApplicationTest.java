package com.example.administrator.myapplication01;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.InstrumentationTestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
        final int a=3;
        final int b=3;
        assertEquals(a,b);

    new InstrumentationTestCase();
    }
}