package au.com.redbackconsulting.skillsurvey.api.bean;

import java.io.Serializable;

import au.com.redbackconsulting.skillsurvey.persistence.model.Role;

import com.google.gson.annotations.Expose;

public class RoleBean implements Serializable {
	@Expose
	private long id;
	@Expose
	private String name;
	@Expose
	private String description;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static RoleBean get(Role entity) {
		
		if(entity!=null){
		RoleBean bean = new RoleBean();
		
		bean.setDescription(entity.getDescription());
		bean.setId(entity.getIdrole());
		bean.setName(entity.getName());
		return bean;}
		return new RoleBean();
	}
	
	

}
