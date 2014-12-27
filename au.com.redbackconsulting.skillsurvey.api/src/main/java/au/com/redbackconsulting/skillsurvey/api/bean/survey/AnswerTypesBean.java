package au.com.redbackconsulting.skillsurvey.api.bean.survey;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class AnswerTypesBean implements Serializable {

	@Expose
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	 
}
