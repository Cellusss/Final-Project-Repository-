package basketball.tourney.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import basketball.tourney.entity.Team;

public interface TeamDao extends JpaRepository<Team, Long> {

}
