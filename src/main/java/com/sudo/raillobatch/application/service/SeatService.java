package com.sudo.raillobatch.application.service;

import com.sudo.raillobatch.config.TrainTemplateProperties;
import com.sudo.raillobatch.domain.Seat;
import com.sudo.raillobatch.domain.TrainCar;
import com.sudo.raillobatch.infrastructure.jdbc.TrainJdbcRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SeatService {

	private final TrainTemplateProperties properties;
	private final TrainJdbcRepository trainJdbcRepository;

	/**
	 * 좌석 생성
	 */
	public void createSeats(List<TrainCar> trainCars) {
		// 좌석 생성
		List<Seat> seats = trainCars.stream()
			.flatMap(trainCar -> {
				TrainTemplateProperties.CarSpec spec = properties.getCarSpec(trainCar);
				TrainTemplateProperties.SeatLayout layout = properties.getSeatLayout(spec);
				return trainCar.generateSeats(spec, layout).stream();
			}).toList();

		// 좌석 저장
		trainJdbcRepository.saveAllSeats(seats);
		log.info("{}개의 좌석 저장 완료", seats.size());
	}
}
