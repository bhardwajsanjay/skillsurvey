package au.com.redbackconsulting.skillsurvey.api.bean.survey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class SurveyMergedReport implements Serializable{

	public List<ReportProfileInfoBean> getProfileInfo() {
		return profileInfo;
	}

	public void setProfileInfo(List<ReportProfileInfoBean> profileInfo) {
		this.profileInfo = profileInfo;
	}

	public List<ReportMergedSurveyBean> getItems() {
		return items;
	}

	public void setItems(List<ReportMergedSurveyBean> items) {
		this.items = items;
	}

	@Expose
	private String title; 
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Expose
	private   List<ReportProfileInfoBean> profileInfo ;
	
	@Expose
	private List<ReportMergedSurveyBean> items= new ArrayList<ReportMergedSurveyBean>();;

	
	@Expose
	private String question1;
	
	@Expose
	private String answer1;

	public String getQuestion1() {
		return question1;
	}

	public void setQuestion1(String question1) {
		this.question1 = question1;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getQuestion2() {
		return question2;
	}

	public void setQuestion2(String question2) {
		this.question2 = question2;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	@Expose
	private String question2;
	
	@Expose
	private String answer2;
}
