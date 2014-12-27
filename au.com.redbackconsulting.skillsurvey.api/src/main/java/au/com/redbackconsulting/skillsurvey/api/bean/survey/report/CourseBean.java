package au.com.redbackconsulting.skillsurvey.api.bean.survey.report;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;

public class CourseBean implements Serializable {
	 

	public String getUocCourseName() {
		return uocCourseName;
	}

	public void setUocCourseName(String uocCourseName) {
		this.uocCourseName = uocCourseName;
	}
 
	public List<Count> getItems() {
		return items;
	}

	public void setItems(List<Count> items) {
		this.items = items;
	}

	 
	@Expose
	private String uocCourseName;
	

	 @Expose
	 private List<Count> items;
}
