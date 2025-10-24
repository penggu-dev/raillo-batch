package com.sudo.raillobatch.presentation;

import com.sudo.raillobatch.application.util.StationFareCreator;
import com.sudo.raillobatch.application.util.TrainScheduleCreator;
import com.sudo.raillobatch.application.dto.TrainAdminSuccess;
import com.sudo.raillobatch.global.success.SuccessResponse;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/trains")
@RequiredArgsConstructor
public class TrainAdminController {

	private final TrainScheduleCreator trainScheduleCreator;
	private final StationFareCreator stationFareCreator;

	@PostMapping("/parse")
	public SuccessResponse<TrainAdminSuccess> parseExcel() {
		// 스케줄 파싱
		trainScheduleCreator.parseTrainSchedule();

		// 운임표 파싱
		stationFareCreator.parseStationFare();

		return SuccessResponse.of(TrainAdminSuccess.TRAIN_PARSE_SUCCESS);
	}

	@PostMapping("/generate/day")
	public SuccessResponse<TrainAdminSuccess> generateDaySchedule() {
		trainScheduleCreator.createTrainSchedule();
		return SuccessResponse.of(TrainAdminSuccess.TRAIN_SCHEDULE_CREATED);
	}

	@PostMapping("/generate/month")
	public SuccessResponse<TrainAdminSuccess> generateMonthSchedule() {
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plusMonths(1).plusDays(1);

		List<LocalDate> dates = startDate.datesUntil(endDate).toList();

		trainScheduleCreator.createTrainSchedule(dates);
		return SuccessResponse.of(TrainAdminSuccess.TRAIN_SCHEDULE_CREATED);
	}
}
