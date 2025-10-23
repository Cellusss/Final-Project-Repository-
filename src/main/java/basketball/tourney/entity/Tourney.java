package basketball.tourney.entity;

import java.util.HashSet;
import java.util.Set;

import basketball.tourney.controller.model.LocationData;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.ToString.Exclude;

@Entity
@Data 
public class Tourney { 
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)	
  private Long tourneyId;
  
  @EqualsAndHashCode.Exclude
  private String name; 
  
  @EqualsAndHashCode.Exclude
  private int age;
  
  @EqualsAndHashCode.Exclude
  private String color; 
  
  @Exclude
  @EqualsAndHashCode.Exclude
  @OneToMany(mappedBy="tourney")
  private Set<Location> locations = new HashSet<>();
  
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @ManyToMany(cascade = CascadeType.PERSIST)
  @JoinTable(name = "tourney_team" , joinColumns = @JoinColumn(name = "tourney_id"), inverseJoinColumns = @JoinColumn (name = "team_id"))
  
  private Set<Team> teams = new HashSet<>();
  

}
