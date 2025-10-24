package com.sudo.raillobatch.infrastructure;

import com.sudo.raillobatch.domain.ScheduleStopTemplate;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleStopTemplateRepository extends JpaRepository<ScheduleStopTemplate, UUID> {
}
