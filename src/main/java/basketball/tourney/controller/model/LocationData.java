
package basketball.tourney.controller.model;

import java.util.HashSet;
import java.util.Set;
import basketball.tourney.entity.Location;
import basketball.tourney.entity.Team;
import basketball.tourney.entity.Tourney;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LocationData {
	private Long locationId;
	private String businessName;
	private String streetAddress;
	private String city;
	private String state;
	private String zip;
	private String phone;

	public LocationData(Location location) {
		this.locationId = location.getLocationId();
		this.businessName = location.getBusinessName();
		this.streetAddress = location.getStreetAddress();
		this.city = location.getCity();
		this.state = location.getState();
		this.zip = location.getZip();
		this.phone = location.getPhone();
	}

	public Location toLocation() {
		Location location = new Location();
		location.setLocationId(locationId);
		location.setBusinessName(businessName);
		location.setStreetAddress(streetAddress);
		location.setCity(city);
		location.setState(state);
		location.setZip(zip);
		location.setPhone(phone);

		return location;
	}

	@Data
	@NoArgsConstructor
	public static class TourneyData {
		private Long tourneyId;
		private String name;
		private int age;
		private String color;
		private Set<LocationData> locations = new HashSet<>();
		private Set<TeamData> teams = new HashSet<>();
		
		public TourneyData(Tourney tourney) {
			this.tourneyId = tourney.getTourneyId();
			this.name = tourney.getName();
			this.age = tourney.getAge();
			this.color = tourney.getColor();

			for (Location location : tourney.getLocations()) {
				this.locations.add(new LocationData(location));
			}
			for (Team team : tourney.getTeams()) {
				this.teams.add(new TeamData(team));
			}
		}

		public Tourney toTourney() {
			Tourney tourney = new Tourney();

			tourney.setTourneyId(tourneyId);
			tourney.setName(name);
			tourney.setAge(age);
			tourney.setColor(color);

			for (LocationData locationData : locations) {
				tourney.getLocations().add(locationData.toLocation());
			}
			
			for (TeamData teamData : teams) {
				tourney.getTeams().add(teamData.toTeam());
			}
			return tourney;
		}
	}

	@Data
	@NoArgsConstructor
	public static class TeamData {
		private Long teamId;
		private String name;

		public TeamData(Team team) {
			this.teamId = team.getTeamId();
			this.name = team.getName();
		}

		public Team toTeam() {
			Team team = new Team();

			team.setTeamId(teamId);
			team.setName(name);

			return team;
		}

	}

}
