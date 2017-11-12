package com.coors.expensestudy;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by z8v on 2017/11/12.
 */
public class AddActivityTest {
    public static final StringBuilder sUriMatcher =
            new StringBuilder("");
    public static final String EXPENSES = "200";
    public static final String EXPENSES_WITH_ID = "201";
    static {
        sUriMatcher.append(EXPENSES);
        sUriMatcher.append(EXPENSES_WITH_ID);
    }
    @Test
    public void doVerification() {
        String et1 = "";
        String et2 = "123";
        String et3 = "";
        System.out.println(et1.trim().matches(""));
        System.out.println(et1.trim().matches("") || et2.trim().matches(""));
    }

    @Test
    public void doTest() {
        System.out.println(sUriMatcher.toString());
    }
}