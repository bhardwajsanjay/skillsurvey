package au.com.redbackconsulting.skillsurvey.persistence.report;

import java.math.BigDecimal;

public class FR_BY_PATHWAY {
	

	public FR_BY_PATHWAY() {
		// TODO Auto-generated constructor stub
	}
	public FR_BY_PATHWAY(String pathwayName, BigDecimal  cntMale, BigDecimal cntFemale, BigDecimal cntOther, BigDecimal cntAll){
		this.pathway=pathwayName;
		this.cntMale=cntMale;
		this.cntFemale= cntFemale;
		this.cntOther = cntOther;
		this.cntAll= cntAll;
		
	}
	
	 
	 
	public BigDecimal getCntOther() {
		return cntOther;
	}
 
	public void setCntOther(BigDecimal cntOther) {
		this.cntOther = cntOther;
	}
	public String getPathway() {
		return pathway;
	}
	public void setPathway(String pathway) {
		this.pathway = pathway;
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

	private String pathway;
	private BigDecimal cntMale;
	 
	private BigDecimal cntFemale;
	private BigDecimal cntOther;
	private BigDecimal cntAll;

}
