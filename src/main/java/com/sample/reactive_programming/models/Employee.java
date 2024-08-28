package com.sample.reactive_programming.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {
	
	@Id
	@Column(value = "id")
	private String id;
	
	@Column(value = "first_name")
	private String firstName;
	
	@Column(value = "last_name")
	private String lastName;
	
	@Column(value = "gender")
	private String gender;
	
	@Column(value = "address")
	private String address;

}
