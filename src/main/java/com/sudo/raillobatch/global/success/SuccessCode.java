package com.sudo.raillobatch.global.success;

import org.springframework.http.HttpStatus;

public interface SuccessCode {

	HttpStatus getStatus();

	String getMessage();
}
