package com.api.restApi.errorModel;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ExceptionModel {

	private int status;
	private String message;
	private LocalDateTime timeStamp;

	public ExceptionModel(int status, String message) {
		this.status = status;
		this.message = message;
		this.timeStamp = LocalDateTime.now();
	}

}
