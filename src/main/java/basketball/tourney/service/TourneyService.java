package basketball.tourney.service;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import basketball.tourney.controller.model.LocationData;
import basketball.tourney.controller.model.LocationData.TeamData;
import basketball.tourney.controller.model.LocationData.TourneyData;
import basketball.tourney.dao.LocationDao;
import basketball.tourney.dao.TeamDao;
import basketball.tourney.dao.TourneyDao;
import basketball.tourney.entity.Location;
import basketball.tourney.entity.Team;
import basketball.tourney.entity.Tourney;



@Service
public class TourneyService {
@Autowired 
private TourneyDao tourneyDao;
@Autowired	
 private LocationDao locationDao;
@Autowired
private TeamDao teamDao;
	@Transactional(readOnly = false )
	public LocationData saveLocation(Long tourneyId, LocationData locationData) {
		Tourney tourney = findTourneyById(tourneyId);
		Location Location = findOrCreateLocation(tourneyId, locationData.getLocationId());
		
		copyLocationFields(Location, locationData); 
		 
		Location.setTourney(tourney);
		tourney.getLocations().add(Location);
		Location savedLocation = locationDao.save(Location);
		return new LocationData(savedLocation);
	
	
	}

	
	private void copyLocationFields(Location location, LocationData locationData) {

		location.setLocationId(locationData.getLocationId());
		location.setState(locationData.getState());
		location.setCity(locationData.getCity());
		location.setPhone(locationData.getPhone());
		location.setStreetAddress(locationData.getStreetAddress());
		location.setZip(locationData.getZip());
		
		
		
	}


	private Location findOrCreateLocation(Long tourneyId, Long locationId) {
   Location location;
		
		if(Objects.isNull(tourneyId)) {
			location = new Location();
		} else { 
	
			}
		return findLocationById(tourneyId,locationId);
	}
	
	@Transactional(readOnly = true)
	public LocationData retrieveLocationBYId(Long locationId) {
	Location location = findLocationById(locationId);
	return new LocationData(location);
	}

	private Location findLocationById(Long tourneyId, Long locationId) {
		Location location = locationDao.findById(locationId)
		 .orElseThrow (() -> new NoSuchElementException ("Location with ID=" + locationId + "was not found."));
		if (!location.getTourney().getTourneyId().equals(tourneyId)) {
			throw new IllegalArgumentException("Location with ID=" + locationId + "does not host tourney with ID" + tourneyId);
		}
		return location;
	}

	private Location findLocationById(Long locationId) {
		return locationDao.findById(locationId)
		 .orElseThrow (() -> new NoSuchElementException ("Location with ID=" + locationId + "was not found."));
	}


	@Transactional(readOnly = true)
	public List<LocationData> retrieveAllLocations() {
	 List<Location> locationEntities = locationDao.findAll ();
	 List<LocationData>	locationDtos = new LinkedList<>(); 
	 

	for(Location location : locationEntities) {
		 LocationData locationdata = new LocationData(location);
		 locationDtos.add(locationdata);
	 }
	 
	 return locationDtos;
	}
     
	@Transactional
	public void deleteLocation(Long locationId) {
	 Location location = findLocationById(locationId);
	 locationDao.delete(location);
		
	}

	@Transactional(readOnly = false)
	public TourneyData saveTourney(TourneyData tourneyData) {
	 Long tourneyId = tourneyData.getTourneyId();
		
		
	    Tourney tourney = findOrCreateTourney (tourneyId); 
	    
	    copyTourneyFields(tourney, tourneyData);
	   
	    
	    return new TourneyData(tourneyDao.save(tourney));
	    
	    
	    
	    
	    
	}

	private void copyTourneyFields(Tourney tourney, TourneyData tourneyData) {
		tourney.setTourneyId(tourneyData.getTourneyId());
		tourney.setAge(tourneyData.getAge());
		tourney.setColor(tourneyData.getColor());
		tourney.setName(tourneyData.getName());
		
		
	
	}


	private void copyTeamFields(Team team, TeamData teamData) {
	team.setTeamId(teamData.getTeamId());
	team.setName(teamData.getName());
	
	
		
	}

	private Tourney findOrCreateTourney(Long tourneyId) {
		Tourney tourney;
		
		if(Objects.isNull(tourneyId)) {
			tourney = new Tourney();
		} else { 
		 tourney = findTourneyById(tourneyId); 
	}

	return tourney; 
	
	}
	@Transactional(readOnly = false)
	public TeamData saveTeam (Long tourneyId, TeamData teamData) {
		
		Tourney tourney = findTourneyById(tourneyId);
	    Team team = findOrCreateTeam(tourneyId, teamData.getTeamId());
	   
	    copyTeamFields(team, teamData);
	    
	    team.getTourneys().add(tourney);
	    tourney.getTeams().add(team);
	    
	    return new TeamData(teamDao.save(team));
	    
	}




	private Team findOrCreateTeam(Long tourneyId, Long teamId) {
		if(Objects.isNull(teamId)) {
			return new Team();
			} else {
				return findTeamById(tourneyId,teamId);
	} 
		
	
	}


	private Team findTeamById(Long tourneyId, Long teamId) {
		Team team = teamDao.findById(teamId).orElseThrow(() -> new NoSuchElementException("team with ID =" + tourneyId + " was not found"));
			boolean found = false; 
			for (Tourney tourney : team.getTourneys()) {
				if(tourney.getTourneyId() == tourneyId) {
					found = true;
					break;
				}
			}
			if (!found) {
				throw new IllegalArgumentException("Team with ID" + teamId + "is not particpating in Tourney with ID" + tourneyId);
		
		}
		return team;
	}


	private Tourney findTourneyById(Long tourneyId) {
	return tourneyDao.findById(tourneyId).orElseThrow(() -> new NoSuchElementException("tourney with ID =" + tourneyId + " was not found"));
	}


	





}
