package au.com.redbackconsulting.skillsurvey.api.bean.survey;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class UocCourseStatusRecognitionBean extends UocCourseStatusBean implements Serializable {
	
	
	
	
	@Expose
	private String recognition;

	public String getRecognition() {
		return recognition;
	}

	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}
	

}
