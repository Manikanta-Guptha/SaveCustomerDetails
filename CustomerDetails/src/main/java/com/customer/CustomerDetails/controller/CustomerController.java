package com.customer.CustomerDetails.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customer.CustomerDetails.common.Status;
import com.customer.CustomerDetails.customer.CustomerDetails;
import com.customer.CustomerDetails.service.CustomerService;

@RestController
@RequestMapping(Status.CUSTOMER)
public class CustomerController {

	@Autowired
	private CustomerService service;

	@PostMapping(Status.SAVE_CUSTOMERDETAILS)
	public String saveCustomerDetails(@RequestBody CustomerDetails details) {

		String result = service.saveCustomerDetails(details);
		return result;
	}

}
