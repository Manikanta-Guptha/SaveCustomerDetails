package com.customer.CustomerDetails.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.customer.CustomerDetails.Entity.CustomerDetailsEntity;
import com.customer.CustomerDetails.customer.CustomerDetails;
import com.customer.CustomerDetails.mapper.CustomerMapper;
import com.customer.CustomerDetails.repository.CustomerDetailsRepo;
import com.customer.CustomerDetails.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDetailsRepo customerRepo;

	@Autowired
	private CustomerMapper customerMapper;

	@Override
	public String saveCustomerDetails(CustomerDetails details) {

		List<CustomerDetailsEntity> list = customerRepo.findByMobileNumber(details.getMobileNumber());

		if (list.isEmpty()) {

			CustomerDetailsEntity entity = customerMapper.convertVoToEntity(details);
			customerRepo.save(entity);

			return "Customer details saved successfully";
		} else {
			return "Given mobile number is already mapped ";
		}
	}

}
