package com.sample.reactive_programming.repositories;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.sample.reactive_programming.models.Employee;

public interface EmployeeRepository extends ReactiveCrudRepository<Employee, String> {

}
