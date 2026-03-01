package com.api.restApi.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.api.restApi.model.EmployeeModel;
import com.api.restApi.model.PaginationModel;
import com.api.restApi.rabbitMQ.producer.EmployeeProducer;
import com.api.restApi.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/employees") // Base URL for all endpoints
@RequiredArgsConstructor
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeProducer employeeProducer;

	@PostMapping("/createEmployee")
	public ResponseEntity<EmployeeModel> createEmployee(@RequestBody EmployeeModel employeeModel) {

		EmployeeModel savedEmployee = employeeService.createmployee(employeeModel);

		return ResponseEntity.ok(savedEmployee);
	}

	@GetMapping
	public ResponseEntity<List<EmployeeModel>> getEmployee() {

		List<EmployeeModel> list = employeeService.getAllEmployee();

		return ResponseEntity.ok(list);

	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable Long id) {

		EmployeeModel model = employeeService.getemployeeById(id);

		return ResponseEntity.ok(model);

	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeModel> updateEmployeeById(@PathVariable Long id,
			@RequestBody EmployeeModel employeeModel) {

		EmployeeModel updated = employeeService.updateEmployee(id, employeeModel);

		return ResponseEntity.ok(updated);
	}

	@PatchMapping("/{id}")
	public ResponseEntity<EmployeeModel> patchUpdateEmployee(@PathVariable Long id,
			@RequestBody EmployeeModel employeeModel) {

		EmployeeModel patch = employeeService.patchUpdateEmployee(id, employeeModel);

		return ResponseEntity.ok(patch);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {

		employeeService.deleteEmployee(id);

		return ResponseEntity.ok("Employee Deleted Successfully");
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.HEAD)
	public ResponseEntity<Void> headEmployee(@PathVariable Long id) {

		employeeService.isEmployeeExist(id);

		return ResponseEntity.ok().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.OPTIONS)
	public ResponseEntity<Void> optionsEmployee() {

		Set<HttpMethod> methods = employeeService.getAllowedMethod();
		return ResponseEntity.ok().allow(methods.toArray(new HttpMethod[0])).build();

	}

	@GetMapping("/page")
	public ResponseEntity<PaginationModel<EmployeeModel>> getPageEmployee(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {

		PaginationModel<EmployeeModel> employee = employeeService.getPartialEmployees(page, size);

		return ResponseEntity.ok(employee);

	}
	
	@GetMapping("/testQueue")
	public String testQueue() {

	    employeeProducer.sendEmployeeCreatedMessage("Test Message");

	    return "Message Sent";
	}

}
