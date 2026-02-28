package com.api.restApi.service;

import java.util.List;

import com.api.restApi.model.EmployeeModel;

public interface EmployeeService {
	
	public EmployeeModel createmployee(EmployeeModel employeeModel);
	
	List<EmployeeModel> getAllEmployee();
	
	public EmployeeModel getemployeeById(Long id);
}
