package com.customer.CustomerDetails.service;

import org.springframework.stereotype.Component;

import com.customer.CustomerDetails.customer.CustomerDetails;

@Component
public interface CustomerService {

	String saveCustomerDetails(CustomerDetails details);

}
