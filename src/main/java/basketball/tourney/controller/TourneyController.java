package basketball.tourney.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import basketball.tourney.controller.model.LocationData;
import basketball.tourney.controller.model.LocationData.TeamData;
import basketball.tourney.controller.model.LocationData.TourneyData;
import basketball.tourney.entity.Team;
import basketball.tourney.entity.Tourney;
import basketball.tourney.service.TourneyService;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/basketball_tourney")
@Slf4j
public class TourneyController {
  @Autowired 
  private TourneyService tourneyService;
	//Make like employee//
	@PostMapping("/{tourneyId}/location")
  @ResponseStatus(code = HttpStatus.CREATED)
   public LocationData addLocation(@PathVariable Long tourneyId, @RequestBody LocationData locationData) {
	 log.info("Adding Location {} to tourney with Id {} ", locationData, tourneyId); 
	 return tourneyService.saveLocation(tourneyId, locationData);
 }

@PutMapping("/{tourneyId}/location/{locationId}")
public LocationData updateLocation(@PathVariable Long tourneyId, @PathVariable Long locationId, @RequestBody LocationData locationData) {
locationData.setLocationId(locationId);
log.info("updating location {}", locationData);
return tourneyService.saveLocation(tourneyId,locationData);
}

@GetMapping("/location/{locationId}")
public LocationData retrieveLocation(@PathVariable Long locationId) {
 log.info("Retrieving location with ID= {}", locationId);
 return tourneyService.retrieveLocationBYId(locationId); 
 
} 

@GetMapping
public List<LocationData> retrieveAllLocations() {
 log.info("Retrieving all locations");
 return tourneyService.retrieveAllLocations();

} 
@DeleteMapping("/location/{locationId}")
public Map<String, String> deleteLocation(@PathVariable Long locationId) { 
log.info("Deleting location with ID=" + locationId + " . ");
tourneyService.deleteLocation(locationId); 

return Map.of("message", "Location with ID=" + locationId + "was deleted sucesfully.");
}

@PostMapping("/{tourneyId}/team")
@ResponseStatus(code = HttpStatus.CREATED)
public TeamData addTeam( @PathVariable Long tourneyId, @RequestBody TeamData teamData) {
log.info("Adding Team {} ", teamData);
return tourneyService.saveTeam(tourneyId, teamData); 

} 

@PostMapping("/tourney")
@ResponseStatus(code = HttpStatus.CREATED)
public TourneyData addTourney( @RequestBody TourneyData tourneyData) {
log.info("Adding Tourney {} ", tourneyData);
return tourneyService.saveTourney(tourneyData);
}

}