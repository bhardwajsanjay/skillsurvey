package au.com.redbackconsulting.skillsurvey.api.bean.survey;

import java.io.Serializable;

import au.com.redbackconsulting.skillsurvey.persistence.model.Pathway;

import com.google.gson.annotations.Expose;

public class PathwayBean implements Serializable{

	public PathwayBean() {
		 
	}

	
	@Expose
	private long  id;

	@Expose
	private String name;

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

	public static PathwayBean get(Pathway pathway) {
		if(pathway!=null){
			PathwayBean bean = new PathwayBean();
			bean.setId(pathway.getId());
			bean.setName(pathway.getName());
			return bean;
		}
		return new PathwayBean();
	}

}
