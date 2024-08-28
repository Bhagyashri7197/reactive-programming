package com.sample.reactive_programming.services;

import org.springframework.stereotype.Service;

import com.sample.reactive_programming.models.Employee;
import com.sample.reactive_programming.repositories.EmployeeRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository;
	
	public Mono<Employee> saveEmployee(Employee empl) {
	    return Mono.just(empl)
	        .map(e -> Employee.builder()
//	            .id(UUID.randomUUID().toString())
	            .firstName(e.getFirstName())
	            .lastName(e.getLastName())
	            .gender(e.getGender())
	            .address(e.getAddress())
	            .build())
	        .flatMap(employeeRepository::save);
	}
	
	public Flux<Employee> getAllEmployees(){
		return employeeRepository.findAll().switchIfEmpty(Flux.empty());
	}
	
	public Mono<Employee> getById(String empId){
		return employeeRepository.findById(empId).switchIfEmpty(Mono.empty());
	}
	
	public Mono<Employee> updateEmployee(String empId, Employee emp){
		return employeeRepository.findById(empId)
				.flatMap(e -> {
					e.setFirstName(emp.getFirstName());
					e.setLastName(emp.getLastName());
					e.setGender(emp.getGender());
					e.setAddress(emp.getAddress());
					return employeeRepository.save(e);
				})
				.switchIfEmpty(Mono.empty());
	}
	
	public Mono<Void> deleteEmployee(String empId) {
		return employeeRepository.findById(empId)
				.flatMap(e -> employeeRepository.deleteById(empId))
				.switchIfEmpty(Mono.empty());
			}

}
