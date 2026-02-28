package com.api.restApi.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.api.restApi.entity.EmployeeEntity;
import com.api.restApi.model.EmployeeModel;
import com.api.restApi.repository.EmployeeRepository;
import com.api.restApi.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService  {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public EmployeeModel createmployee(EmployeeModel employeeModel) {
		EmployeeEntity employeeEntity = new EmployeeEntity();
		
		 // Step 1️⃣ Convert Model → Entity
		employeeEntity.setName(employeeModel.getName());
		employeeEntity.setEmail(employeeModel.getEmail());
		employeeEntity.setAge(employeeModel.getAge());
		employeeEntity.setSalary(employeeModel.getSalary());
		employeeEntity.setDob(employeeModel.getDob());
		employeeEntity.setActive(employeeModel.isActive());
		
		  // Step 2️⃣ Save to database
		EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);
		
		// Step 3️⃣ Convert Entity → Model
		EmployeeModel response = new EmployeeModel();
		response.setId(savedEntity.getId());
		response.setName(savedEntity.getName());
		response.setEmail(savedEntity.getEmail());
		response.setAge(savedEntity.getAge());
		response.setSalary(savedEntity.getSalary());
		response.setDob(savedEntity.getDob());
		response.setActive(savedEntity.isActive());
		
		return response;
	}
	
	@Override
	public List<EmployeeModel> getAllEmployee(){
		
	List<EmployeeEntity> entityList 	=employeeRepository.findAll();
	
	List<EmployeeModel> modelList = new ArrayList<>();
	
	for (EmployeeEntity entity : entityList) {
		
		EmployeeModel employeeModel = new EmployeeModel();
		
		employeeModel.setId(entity.getId());
		employeeModel.setName(entity.getName());
		employeeModel.setEmail(entity.getEmail());
		employeeModel.setAge(entity.getAge());
		employeeModel.setDob(entity.getDob());
		employeeModel.setSalary(entity.getSalary());
		employeeModel.setActive(entity.isActive());
		
		modelList.add(employeeModel);
	}
		
		return modelList;
		
	}

	@Override
	public EmployeeModel getemployeeById(Long id) {
		//1st way
//		EmployeeEntity entity1 = employeeRepository.findById(id)
//				.orElseThrow(() -> new RuntimeException("Employee Id not found"));
		
		//2.way
		EmployeeEntity entity = employeeRepository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee Id not found"));
		
		EmployeeModel employeeModel = new EmployeeModel();
		
		employeeModel.setId(entity.getId());
		employeeModel.setName(entity.getName());
		employeeModel.setEmail(entity.getEmail());
		employeeModel.setAge(entity.getAge());
		employeeModel.setDob(entity.getDob());
		employeeModel.setSalary(entity.getSalary());
		employeeModel.setActive(entity.isActive());
		
		return employeeModel;
	}

	@Override
	public EmployeeModel updateEmployee(Long id, EmployeeModel employeeModel) {
		
	EmployeeEntity employeeEntity=	employeeRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Employee Id Not Found"));
		
	employeeEntity.setName(employeeModel.getName());
	employeeEntity.setEmail(employeeModel.getEmail());
	employeeEntity.setAge(employeeModel.getAge());
	employeeEntity.setSalary(employeeModel.getSalary());
	employeeEntity.setDob(employeeModel.getDob());
	employeeEntity.setActive(employeeModel.isActive());
		
	EmployeeEntity updateEntity =employeeRepository.save(employeeEntity);
	
	EmployeeModel response = new EmployeeModel();
	
	response.setId(updateEntity.getId());
	response.setName(updateEntity.getName());
	response.setEmail(updateEntity.getEmail());
	response.setAge(updateEntity.getAge());
	response.setSalary(updateEntity.getSalary());
	response.setDob(updateEntity.getDob());
	response.setActive(updateEntity.isActive());
		
		return response;
	}

	@Override
	public void deleteEmployee(Long id) {
		
		if(!employeeRepository.existsById(id)) {
			throw new ResponseStatusException(
					HttpStatus.NOT_FOUND,
					"Employee Not found"
					);
		}
		
		employeeRepository.deleteById(id);
		
	}

	
	
	

}
