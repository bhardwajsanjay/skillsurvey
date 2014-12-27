package au.com.redbackconsulting.skillsurvey.api.bean;

import java.io.Serializable;

import au.com.redbackconsulting.skillsurvey.api.bean.survey.ProviderBean;
import au.com.redbackconsulting.skillsurvey.persistence.model.Uoc;

import com.google.gson.annotations.Expose;

public class UocBean implements Serializable{
	@Expose
	private String id;
	@Expose
	private String name;
	@Expose
	private String description;
	@Expose
	private String type;
	
	@Expose
	private ProviderBean provider ;
@Expose
private boolean providerVisibility;
	 

	public boolean isProviderVisibility() {
	return providerVisibility;
}

public void setProviderVisibility(boolean providerVisibility) {
	this.providerVisibility = providerVisibility;
}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static UocBean get(Uoc entity) {
		UocBean bean = new UocBean();
		bean.setId(entity.getIdsuoc());
		bean.setDescription(entity.getDescription());
		bean.setName(entity.getName());
		bean.setType(entity.getType());

		return bean;
	}

	public ProviderBean getProvider() {
		return provider;
	}

	public void setProvider(ProviderBean provider) {
		this.provider = provider;
	}


}
