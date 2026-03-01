package com.api.restApi.serviceimpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.api.restApi.customException.EmployeeNotFoundException;
import com.api.restApi.entity.EmployeeEntity;
import com.api.restApi.model.EmployeeModel;
import com.api.restApi.model.PaginationModel;
import com.api.restApi.repository.EmployeeRepository;
import com.api.restApi.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

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
		employeeEntity.setActive(employeeModel.getActive());

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
		response.setActive(savedEntity.getActive());

		return response;
	}

	@Override
	public List<EmployeeModel> getAllEmployee() {

		List<EmployeeEntity> entityList = employeeRepository.findAll();

		List<EmployeeModel> modelList = new ArrayList<>();

		for (EmployeeEntity entity : entityList) {

			EmployeeModel employeeModel = new EmployeeModel();

			employeeModel.setId(entity.getId());
			employeeModel.setName(entity.getName());
			employeeModel.setEmail(entity.getEmail());
			employeeModel.setAge(entity.getAge());
			employeeModel.setDob(entity.getDob());
			employeeModel.setSalary(entity.getSalary());
			employeeModel.setActive(entity.getActive());

			modelList.add(employeeModel);
		}
		return modelList;
	}

	@Override
	public EmployeeModel getemployeeById(Long id) {
		// 1st way 500 not handle exception
//		EmployeeEntity entity1 = employeeRepository.findById(id)
//				.orElseThrow(() -> new RuntimeException("Employee Id not found"));

		// 2.way
		EmployeeEntity entity = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee Id not found"));

		EmployeeModel employeeModel = new EmployeeModel();

		employeeModel.setId(entity.getId());
		employeeModel.setName(entity.getName());
		employeeModel.setEmail(entity.getEmail());
		employeeModel.setAge(entity.getAge());
		employeeModel.setDob(entity.getDob());
		employeeModel.setSalary(entity.getSalary());
		employeeModel.setActive(entity.getActive());

		return employeeModel;
	}

	@Override
	public EmployeeModel updateEmployee(Long id, EmployeeModel employeeModel) {

		EmployeeEntity employeeEntity = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee Id Not Found"));

		employeeEntity.setName(employeeModel.getName());
		employeeEntity.setEmail(employeeModel.getEmail());
		employeeEntity.setAge(employeeModel.getAge());
		employeeEntity.setSalary(employeeModel.getSalary());
		employeeEntity.setDob(employeeModel.getDob());
		employeeEntity.setActive(employeeModel.getActive());

		EmployeeEntity updateEntity = employeeRepository.save(employeeEntity);

		EmployeeModel response = new EmployeeModel();

		response.setId(updateEntity.getId());
		response.setName(updateEntity.getName());
		response.setEmail(updateEntity.getEmail());
		response.setAge(updateEntity.getAge());
		response.setSalary(updateEntity.getSalary());
		response.setDob(updateEntity.getDob());
		response.setActive(updateEntity.getActive());

		return response;
	}

	@Override
	public EmployeeModel patchUpdateEmployee(Long id, EmployeeModel employeeModel) {

		EmployeeEntity entity = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee Not found"));

		if (employeeModel.getName() != null) {
			entity.setName(employeeModel.getName());
		}

		if (employeeModel.getEmail() != null) {
			entity.setEmail(employeeModel.getEmail());
		}

		if (employeeModel.getSalary() != null) {
			entity.setSalary(employeeModel.getSalary());
		}

		if (employeeModel.getDob() != null) {
			entity.setDob(employeeModel.getDob());
		}

		if (employeeModel.getAge() != null) {
			entity.setAge(employeeModel.getAge());
		}

		if (employeeModel.getActive() != null) {
			entity.setActive(employeeModel.getActive());
		}
		EmployeeEntity employeeEntity = employeeRepository.save(entity);

		EmployeeModel response = new EmployeeModel();

		response.setId(employeeEntity.getId());
		response.setName(employeeEntity.getName());
		response.setEmail(employeeEntity.getEmail());
		response.setAge(employeeEntity.getAge());
		response.setSalary(employeeEntity.getSalary());
		response.setDob(employeeEntity.getDob());
		response.setActive(employeeEntity.getActive());

		return response;
	}

	@Override
	public void deleteEmployee(Long id) {

		if (!employeeRepository.existsById(id)) {
			throw new EmployeeNotFoundException("Employee Not found");
		}

		employeeRepository.deleteById(id);

	}

	@Override
	public boolean isEmployeeExist(Long id) {

		boolean exists = employeeRepository.existsById(id);

		if (!exists) {
			throw new EmployeeNotFoundException("Employee Not found");
		}

		return true;
	}

	@Override
	public Set<HttpMethod> getAllowedMethod() {

		Set<HttpMethod> methods = new HashSet<>();

		methods.add(HttpMethod.GET);
		methods.add(HttpMethod.POST);
		methods.add(HttpMethod.PATCH);
		methods.add(HttpMethod.PUT);
		methods.add(HttpMethod.DELETE);
		methods.add(HttpMethod.OPTIONS);
		methods.add(HttpMethod.HEAD);

		return methods;
	}

	@Override
	public PaginationModel<EmployeeModel> getPartialEmployees(int page, int size) {

		/*
		 * Step 1 Create Pageable object using page number and page size
		 */
		Pageable pageable = PageRequest.of(page, size);
		/*
		 * Step 2 Fetch paginated data from database
		 */
		Page<EmployeeEntity> employeePage = employeeRepository.findAll(pageable);

		/*
		 * Step 3 Convert Entity → Model
		 */

		List<EmployeeModel> modelPage = employeePage.getContent().stream().map(entity -> {

			EmployeeModel model = new EmployeeModel();

			model.setId(entity.getId());
			model.setName(entity.getName());
			model.setEmail(entity.getEmail());
			model.setAge(entity.getAge());
			model.setSalary(entity.getSalary());
			model.setDob(entity.getDob());
			model.setActive(entity.getActive());

			return model;
		}).toList();

		/*
		 * Step 4 Build custom pagination response
		 */

		PaginationModel<EmployeeModel> response = new PaginationModel<>();

		response.setData(modelPage);

		// // how many records returned
		response.setPageSize(modelPage.size());
		response.setHasMore(employeePage.hasNext());

		return response;

	}
}