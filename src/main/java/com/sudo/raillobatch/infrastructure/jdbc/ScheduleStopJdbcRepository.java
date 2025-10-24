package com.sudo.raillobatch.infrastructure.jdbc;

import com.sudo.raillobatch.domain.ScheduleStop;
import java.util.List;

public interface ScheduleStopJdbcRepository {

	void saveAll(List<ScheduleStop> scheduleStops);
}
