package com.customer.CustomerDetails.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customer.CustomerDetails.Entity.CustomerDetailsEntity;

@Repository
public interface CustomerDetailsRepo extends JpaRepository<CustomerDetailsEntity, Long> {

	List<CustomerDetailsEntity> findByMobileNumber(String mobileNumber);

}
