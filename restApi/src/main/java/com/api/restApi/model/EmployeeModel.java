package com.api.restApi.model;

import java.math.BigDecimal;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;


@JsonPropertyOrder({
    "id",
    "name"
})
@Data
public class EmployeeModel {

	private Long id;
	private String name;
	private String email;
	private int age;
	private BigDecimal salary;
	private LocalDate dob;
	private boolean active;
	
	
	
}
