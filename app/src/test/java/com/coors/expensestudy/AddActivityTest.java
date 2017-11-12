package com.coors.expensestudy;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by z8v on 2017/11/12.
 */
public class AddActivityTest {

    @Test
    public void doVerification() {
        String et1 = "";
        String et2 = "123";
        String et3 = "";
        System.out.println(et1.trim().matches(""));
        System.out.println(et1.trim().matches("") || et2.trim().matches(""));
    }

}