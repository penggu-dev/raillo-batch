package com.sudo.raillobatch.infrastructure;

import com.sudo.raillobatch.domain.Train;
import com.sudo.raillobatch.domain.TrainCar;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainCarRepository extends JpaRepository<TrainCar, Long> {

	List<TrainCar> findByTrainIn(Collection<Train> trains);
}
