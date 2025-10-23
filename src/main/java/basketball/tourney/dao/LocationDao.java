package basketball.tourney.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import basketball.tourney.entity.Location;

public interface LocationDao extends JpaRepository<Location, Long> {

}
