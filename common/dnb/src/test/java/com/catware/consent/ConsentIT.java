package com.catware.consent;

import static org.junit.Assert.*;

import org.junit.Test;

import com.catware.consent.Consent;

public class ConsentIT {
    @Test
    public void test() throws Exception {
    	String txt = new Consent().create("31125464346");
    	System.out.println(txt);
    }
}

