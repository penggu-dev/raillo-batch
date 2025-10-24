package com.sudo.raillobatch.infrastructure;

import com.sudo.raillobatch.domain.Station;
import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, Long> {

	List<Station> findByStationNameIn(Collection<String> stationNames);
}
