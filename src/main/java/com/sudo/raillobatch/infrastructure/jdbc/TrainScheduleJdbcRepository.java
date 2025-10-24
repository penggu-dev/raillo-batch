package com.sudo.raillobatch.infrastructure.jdbc;

import com.sudo.raillobatch.domain.TrainSchedule;
import java.util.List;

public interface TrainScheduleJdbcRepository {

	void saveAll(List<TrainSchedule> trainSchedules);
}
