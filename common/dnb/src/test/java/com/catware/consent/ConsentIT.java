package com.catware.consent;

import static org.junit.Assert.*;

import org.junit.Test;

import com.catware.consent.impl.ConsentServiceImpl;

public class ConsentIT {
    @Test
    public void test() throws Exception {
    	String txt = new ConsentServiceImpl().create("31125464346");
    	System.out.println(txt);
    }
}

