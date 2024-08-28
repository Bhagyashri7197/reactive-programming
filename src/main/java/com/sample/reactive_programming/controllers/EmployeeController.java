package com.sample.reactive_programming.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.reactive_programming.models.Employee;
import com.sample.reactive_programming.services.EmployeeService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	@PostMapping("/save")
	public Mono<ResponseEntity<Employee>> createEmployee(@RequestBody Employee empl){
		return employeeService.saveEmployee(empl)
				.map(e1 -> new ResponseEntity<>(e1 , HttpStatus.CREATED));
	}
	
	@GetMapping("/all")
	public Flux<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	@GetMapping("/{empId}")
	public Mono<ResponseEntity<Employee>> getById(@PathVariable String empId){
		return employeeService.getById(empId)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/update/{empId}")
	public Mono<ResponseEntity<Employee>> updateEmployee(@PathVariable String empId, @RequestBody Employee emp){
		return employeeService.getById(empId)
				.flatMap(e -> {
					e.setFirstName(emp.getFirstName());
					e.setLastName(emp.getLastName());
					e.setGender(emp.getGender());
					e.setAddress(emp.getAddress());
					return employeeService.saveEmployee(e);
				})
				.map(updatedEmployee -> new ResponseEntity<>(updatedEmployee, HttpStatus.OK))
				.defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@DeleteMapping("/delete")
	public Mono<ResponseEntity<Void>> deleteEmployee(@PathVariable String empId) {
		return employeeService.deleteEmployee(empId)
				.then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
				.doOnError(error -> Mono.just(new ResponseEntity<Void>(HttpStatus.NOT_FOUND)));
			}
}
