package com.api.restApi.model;

import java.util.List;

import lombok.Data;

@Data
public class PaginationModel<T> {

	// Actual list of records
	private List<T> data;
	
	// Number of records returned
	private int pageSize;
	
	 // Indicates if more pages exist
	private boolean hasMore;
	
}
