package com.sample.reactive_programming.requests;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EmployeeRequest {
	private String firstName;
	private String lastName;
	private Double salary;
	private String address;


}
