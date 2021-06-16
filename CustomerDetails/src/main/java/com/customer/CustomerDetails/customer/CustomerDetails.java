package com.customer.CustomerDetails.customer;

import java.sql.Date;

import lombok.Data;

@Data
public class CustomerDetails {

	private String name;

	private String mobileNumber;

	private String gstNumber;

	private String address;

	private Date dob;

	private String gender;

	private String altMobileNo;

}
