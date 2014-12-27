package au.com.redbackconsulting.skillsurvey.persistence.model;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Gender
 *
 */
@Entity

public class Gender implements IDBEntity, Serializable {

	   
	@Id
	private long id;
	private String name;
	private static final long serialVersionUID = 1L;

	public Gender() {
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
   
}
