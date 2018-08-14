package com.ov.basic;

import com.google.common.base.MoreObjects;

/**
 * Demonstration of Builder (wrapper) pattern
 * 
 * @author Henry.Lin
 *
 */
public class Customer {

	/**
	 * Mandatory field
	 */
	private final String username;
	
	/**
	 * Mandatory field
	 */
	private final String email;
	private String gender;
	private String title;

	/**
	 * private class constructor
	 */
	private Customer(String username, String email, String gender, String title) {
		this.username = username;
		this.email = email;
		this.gender = gender;
		this.title = title;
	}
	
	/**
	 * builder constructor returns Builder instance
	 */
	public static Builder builder(String username, String email) {
		return new Builder(username, email);
	}

	public static class Builder {

		private final String username;
		private final String email;
		private String gender;
		private String title;

		/**
		 * private Builder Constructor with mandatory fields
		 */
		private Builder(String username, String email) {
			this.username = username;
			this.email = email;
		}

		/**
		 * return instance of Builder
		 */
		public Builder gender(String gender) {
			this.gender = gender;
			return this;
		}

		/**
		 * return instance of Builder
		 */
		public Builder title(String title) {
			this.title = title;
			return this;
		}

		public Customer build() {
			return new Customer(username, email, gender, title);
		}

	}
	
	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("username", username)
				.add("email", email)
				.add("title", title)
				.add("gender", gender)
				.toString();
		
	}
	

	public static void main(String[] args) {
		
		Customer customer = Customer.builder("henrylin", "cool@cool.com")
				.title("Mr.")
				.gender("M")
				.build();
		
		System.out.println(customer);
	}

}
