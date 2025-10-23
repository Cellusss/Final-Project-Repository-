package basketball.tourney.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;



@Entity
@Data
public class Team {
@Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)	
  private Long teamId;
  

private String name; 
  
@ToString.Exclude
@EqualsAndHashCode.Exclude
@ManyToMany(mappedBy = "teams",cascade = CascadeType.PERSIST)
private Set<Tourney> tourneys = new HashSet<>();
  

}
