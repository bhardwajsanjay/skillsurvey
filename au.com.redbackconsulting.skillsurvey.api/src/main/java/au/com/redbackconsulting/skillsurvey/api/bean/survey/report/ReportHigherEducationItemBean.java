package au.com.redbackconsulting.skillsurvey.api.bean.survey.report;

import java.io.Serializable;
import java.math.BigDecimal;

import com.google.gson.annotations.Expose;

public class ReportHigherEducationItemBean implements Serializable {
	
	@Expose
	private String sub;

	@Expose
	private BigDecimal cntMale;
	 
	@Expose
	private BigDecimal cntFemale;
	
	@Expose
	private BigDecimal cntOther;
	
	 
	public BigDecimal getCntOther() {
		return cntOther;
	}
	 
	public void setCntOther(BigDecimal cntOther) {
		this.cntOther = cntOther;
	}
	@Expose
	private BigDecimal cntAll;
	public String getQuestion() {
		return sub;
	}
	public void setQuestion(String question) {
		this.sub = question;
	}
	public BigDecimal getCntMale() {
		return cntMale;
	}
	public void setCntMale(BigDecimal cntMale) {
		this.cntMale = cntMale;
	}
	public BigDecimal getCntFemale() {
		return cntFemale;
	}
	public void setCntFemale(BigDecimal cntFemale) {
		this.cntFemale = cntFemale;
	}
	public BigDecimal getCntAll() {
		return cntAll;
	}
	public void setCntAll(BigDecimal cntAll) {
		this.cntAll = cntAll;
	}
	 
}
