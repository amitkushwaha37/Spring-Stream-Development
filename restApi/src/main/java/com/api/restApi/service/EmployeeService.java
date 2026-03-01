package com.api.restApi.service;

import java.util.List;
import java.util.Set;

import org.springframework.http.HttpMethod;

import com.api.restApi.model.EmployeeModel;
import com.api.restApi.model.PaginationModel;

public interface EmployeeService {

	public EmployeeModel createmployee(EmployeeModel employeeModel);

	List<EmployeeModel> getAllEmployee();

	public EmployeeModel getemployeeById(Long id);

	EmployeeModel updateEmployee(Long id, EmployeeModel employeeModel);

	EmployeeModel patchUpdateEmployee(Long id, EmployeeModel employeeModel);

	void deleteEmployee(Long id);

	boolean isEmployeeExist(Long id);

	Set<HttpMethod> getAllowedMethod();
	
	// Pagination Method
	PaginationModel<EmployeeModel> getPartialEmployees(int page, int size);
}
