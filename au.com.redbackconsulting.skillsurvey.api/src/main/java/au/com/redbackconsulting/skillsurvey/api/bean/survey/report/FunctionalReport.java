package au.com.redbackconsulting.skillsurvey.api.bean.survey.report;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;

public class FunctionalReport  implements Serializable{
	
	public long getNeedId() {
		return needId;
	}

	public void setNeedId(long needId) {
		this.needId = needId;
	}

	
	public String getNeedName() {
		return needName;
	}

	public void setNeedName(String needName) {
		this.needName = needName;
	}

	public List<CourseBean> getCourses() {
		return uocs;
	}

	public void setCourses(List<CourseBean> courses) {
		this.uocs = courses;
	}

	@Expose
	private long needId;
	
	@Expose
	private String needName;
	
	

	@Expose
	private List<CourseBean> uocs;
	
	
	

}
