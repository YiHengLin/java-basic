package com.ov.basic;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Static factory methods example
 * 
 * @author Henry.Lin
 *
 */
public class Address {
	
	private static final Address COMMON = new Address("CA", "Los Angelos", 90069, "8425 Melrose Ave");
	
	private String state;
	private String city;
	private int zipCode;
	private String address;
	
	/**
	 * private constructor
	 */
	private Address(String state, String city, int zipCode, String address) {
		this.state = state;
		this.city = city;
		this.zipCode = zipCode;
		this.address = address;
	}

	/**
	 * Static factory method has pros:
	 * 1. Can have name 
	 * 2. Not required to create a new Object each time they're invoked.
	 */
	public static Address of(String state, String city, int zipCode, String address) {
	
		// can validate argument before instantiation 
		checkArgument(zipCode < 100);
		
		// can return the same instance instead of create a new one 
		if("8425 Melrose Ave".equals(address)) {
			return COMMON;
		}
		return new Address(state, city, zipCode, address);
	}
	
}
