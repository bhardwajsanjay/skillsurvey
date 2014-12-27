package au.com.redbackconsulting.skillsurvey.api.bean.survey.report;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class CourseCount  implements Serializable{

	 @Expose
	 private String cmpFemale;
	 
	 
	 @Expose
	 private String cmpMale;
	 
	 
	 @Expose
	 private String cmpAll;
	 
	 @Expose
	 private String notCmpFemale;
	 
	 
	 @Expose
	 private String notCmpMale;
	 
	 
	 @Expose
	 private String notCmpAll;


	public String getCmpFemale() {
		return cmpFemale;
	}


	public void setCmpFemale(String cmpFemale) {
		this.cmpFemale = cmpFemale;
	}


	public String getCmpMale() {
		return cmpMale;
	}


	public void setCmpMale(String cmpMale) {
		this.cmpMale = cmpMale;
	}


	public String getCmpAll() {
		return cmpAll;
	}


	public void setCmpAll(String cmpAll) {
		this.cmpAll = cmpAll;
	}


	public String getNotCmpFemale() {
		return notCmpFemale;
	}


	public void setNotCmpFemale(String notCmpFemale) {
		this.notCmpFemale = notCmpFemale;
	}


	public String getNotCmpMale() {
		return notCmpMale;
	}


	public void setNotCmpMale(String notCmpMale) {
		this.notCmpMale = notCmpMale;
	}


	public String getNotCmpAll() {
		return notCmpAll;
	}


	public void setNotCmpAll(String notCmpAll) {
		this.notCmpAll = notCmpAll;
	}



}
