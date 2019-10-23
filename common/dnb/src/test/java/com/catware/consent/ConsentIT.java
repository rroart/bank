package com.catware.consent;

import static org.junit.Assert.*;

import org.junit.Test;

import com.catware.consent.impl.ConsentServiceImpl;
import com.catware.util.http.MyResponse;

public class ConsentIT {

	private static final String ssn = "31125464346";

	private static final String ssnerror = "31125464347";

	@Test
    public void test() throws Exception {
    	MyResponse response = new ConsentServiceImpl().create(ssn);
    	System.out.println(response);
    }
}

