package au.com.redbackconsulting.skillsurvey.persistence.model;

import java.io.Serializable;
import java.lang.String;
import java.util.Comparator;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Location
 *
 */
@Entity

public class Location implements Serializable, IDBEntity {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String name;
	private static final long serialVersionUID = 1L;

	public Location() {
		super();
	}   
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}   
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public static Comparator<Location> locationNameComparator 
    = new Comparator<Location>() {

public int compare(Location location1, Location location2) {

String locationName1 = location1.getName().toUpperCase();
String locationName2 = location2.getName().toUpperCase();

//ascending order
return locationName1.compareTo(locationName2);

//descending order
//return fruitName2.compareTo(fruitName1);
}

};
	
	
   
}
