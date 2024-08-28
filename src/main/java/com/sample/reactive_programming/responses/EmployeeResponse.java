package com.sample.reactive_programming.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
	private String id;
	private String firstName;
	private String lastName;
	private Double salary;
	private String address;

}
