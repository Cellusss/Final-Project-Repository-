package basketball.tourney.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import basketball.tourney.entity.Tourney;
	
	public interface TourneyDao extends JpaRepository<Tourney, Long>{

}
