package com.customer.CustomerDetails.Entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "customer_details")
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class CustomerDetailsEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@NotBlank
	private String name;

	@NotBlank(message = "Mobile number must not empty/null")
	private String mobileNumber;

	private String gstNumber;

	private String address;

	private Date dob;

	@NotBlank
	private String gender;

	private String altMobileNo;

}
