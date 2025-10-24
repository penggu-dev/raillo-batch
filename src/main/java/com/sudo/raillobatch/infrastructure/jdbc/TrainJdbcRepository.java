package com.sudo.raillobatch.infrastructure.jdbc;

import com.sudo.raillobatch.domain.Seat;
import com.sudo.raillobatch.domain.Train;
import com.sudo.raillobatch.domain.TrainCar;
import java.util.List;

public interface TrainJdbcRepository {

	void saveAllTrains(List<Train> trains);

	void saveAllTrainCars(List<TrainCar> trainCars);

	void saveAllSeats(List<Seat> seats);
}
