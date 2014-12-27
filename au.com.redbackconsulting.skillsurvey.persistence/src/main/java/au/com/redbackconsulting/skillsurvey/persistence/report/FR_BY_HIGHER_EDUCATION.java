package au.com.redbackconsulting.skillsurvey.persistence.report;

import java.math.BigDecimal;

public class FR_BY_HIGHER_EDUCATION {
	
	
	public FR_BY_HIGHER_EDUCATION() {
		// TODO Auto-generated constructor stub
	}
	 
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
 

	private String question;
	private BigDecimal yesCntMale;	 
	private BigDecimal yesCntFemale;
	private BigDecimal yesCntOther;
	/**
	 * @param yesCntOther the yesCntOther to set
	 */
	public void setYesCntOther(BigDecimal yesCntOther) {
		this.yesCntOther = yesCntOther;
	}


	/**
	 * @return the yesCntOther
	 */
	public BigDecimal getYesCntOther() {
		return yesCntOther;
	}


	private BigDecimal YesCntAll;
	
	 
	public BigDecimal getYesCntMale() {
		return yesCntMale;
	}


	public void setYesCntMale(BigDecimal yesCntMale) {
		this.yesCntMale = yesCntMale;
	}


	public BigDecimal getYesCntFemale() {
		return yesCntFemale;
	}


	public void setYesCntFemale(BigDecimal yesCntFemale) {
		this.yesCntFemale = yesCntFemale;
	}


	public BigDecimal getYesCntAll() {
		return YesCntAll;
	}


	public void setYesCntAll(BigDecimal yesCntAll) {
		YesCntAll = yesCntAll;
	}


	 

}
