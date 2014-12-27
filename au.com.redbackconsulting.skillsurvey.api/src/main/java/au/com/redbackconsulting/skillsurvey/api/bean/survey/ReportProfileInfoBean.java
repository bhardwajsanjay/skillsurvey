package au.com.redbackconsulting.skillsurvey.api.bean.survey;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class ReportProfileInfoBean implements Serializable {

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Expose
	private String name ;
	
	@Expose
	private String value;
}
