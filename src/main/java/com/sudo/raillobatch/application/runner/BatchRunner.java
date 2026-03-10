package com.sudo.raillobatch.application.runner;

import com.sudo.raillobatch.application.util.StationFareCreator;
import com.sudo.raillobatch.application.util.TrainScheduleCreator;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class BatchRunner implements CommandLineRunner {

	private final TrainScheduleCreator trainScheduleCreator;
	private final StationFareCreator stationFareCreator;

	@Value("${BATCH_MODE:}")
	private String batchMode;

	@Value("${BATCH_DATE:}")
	private String batchDate;

	@Override
	public void run(String... args) {
		if (batchMode.isBlank()) {
			return;
		}

		log.info("배치 모드 실행: {}", batchMode);

		long startTime = System.currentTimeMillis();

		try {
			switch (batchMode) {
				case "parse" -> runParse();
				case "day" -> runDay();
				case "month" -> runMonth();
				case "init" -> {
					runParse();
					runMonth();
				}
				default -> {
					log.error("알 수 없는 배치 모드: {}", batchMode);
					System.exit(1);
				}
			}
			long elapsed = System.currentTimeMillis() - startTime;
			log.info("배치 완료 (소요시간: {}ms)", elapsed);
			System.exit(0);
		} catch (Exception e) {
			log.error("배치 실행 중 오류 발생", e);
			System.exit(1);
		}
	}

	private void runParse() {
		log.info("엑셀 파싱 시작");
		trainScheduleCreator.parseTrainSchedule();
		stationFareCreator.parseStationFare();
		log.info("엑셀 파싱 완료");
	}

	private void runDay() {
		if (!batchDate.isBlank()) {
			LocalDate date = LocalDate.parse(batchDate);
			log.info("[{}] 하루 스케줄 생성", date);
			trainScheduleCreator.createTrainSchedule(List.of(date));
		} else {
			log.info("하루 스케줄 생성 (마지막 운행일 기준 다음 날)");
			trainScheduleCreator.createTrainSchedule();
		}
	}

	private void runMonth() {
		LocalDate startDate = LocalDate.now();
		LocalDate endDate = startDate.plusMonths(1).plusDays(1);
		List<LocalDate> dates = startDate.datesUntil(endDate).toList();

		log.info("[{} ~ {}] 한달 스케줄 생성", startDate, endDate.minusDays(1));
		trainScheduleCreator.createTrainSchedule(dates);
	}
}
