package basketball.tourney.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Data
public class Location { 
 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)	
	
 private Long locationId;
 private String businessName; 
 private String streetAddress;
 private String city;
 private String state;
 private String zip; 
 private String phone; 
 
 @ToString.Exclude
 @EqualsAndHashCode.Exclude
 @ManyToOne( cascade = CascadeType.PERSIST)
 @JoinColumn(name="tourney_id")
 private Tourney tourney;




}
