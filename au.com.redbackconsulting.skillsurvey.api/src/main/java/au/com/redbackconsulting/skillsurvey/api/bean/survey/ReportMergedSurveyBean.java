package au.com.redbackconsulting.skillsurvey.api.bean.survey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class ReportMergedSurveyBean implements Serializable{
	

	@Expose
	private String reportTitle;
	
	
	 
	@Expose
	private String title;
 
	public void setTitle(String title) {
		this.title = title;
	}

	@Expose 
	private String needName;
	
	@Expose 
	private String needDescription;
	
	
	public String getNeedDescription() {
		return needDescription;
	}

	public void setNeedDescription(String needDescription) {
		this.needDescription = needDescription;
	}

	public String getNeedName() {
		return needName;
	}

	public void setNeedName(String needName) {
		this.needName = needName;
	}

	public String getReportTitle() {
	return this.reportTitle;
	}

//	public void setReportTitle(String reportTitle) {
//		this.reportTitle = reportTitle;
//	}

	@Expose
	private List<ReportProfileInfoBean> profileInfo;
	
	@Expose
	private List<UocCourseStatusRecognitionBean> items= new ArrayList<UocCourseStatusRecognitionBean>();

	
	public List<ReportProfileInfoBean> getProfileInfo() {
		return profileInfo;
	}

	public void setProfileInfo(List<ReportProfileInfoBean> profileInfo) {
		this.profileInfo = profileInfo;
	}

	public List<UocCourseStatusRecognitionBean> getItems() {
		return items;
	}

	public void setItems(List<UocCourseStatusRecognitionBean> items) {
		this.items = items;
	}
	
	public void addAll(List<UocCourseStatusRecognitionBean> collection){
		this.items.addAll(collection);
	}
	  
	
   
	public List<UocCourseStatusRecognitionBean> getUocCourses() {
		return items;
	}

	public void setUocCourses(List<UocCourseStatusRecognitionBean> uocCourses) {
		this.items = uocCourses;
	}
	
	
	
	
	


}
