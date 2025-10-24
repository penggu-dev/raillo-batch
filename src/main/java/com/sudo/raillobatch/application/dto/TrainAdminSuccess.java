package com.sudo.raillobatch.application.dto;

import com.sudo.raillobatch.global.success.SuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum TrainAdminSuccess implements SuccessCode {

	TRAIN_PARSE_SUCCESS(HttpStatus.OK, "파싱에 성공했습니다."),
	TRAIN_SCHEDULE_CREATED(HttpStatus.CREATED, "스케줄이 성공적으로 생성되었습니다.");

	private final HttpStatus status;
	private final String message;
}
