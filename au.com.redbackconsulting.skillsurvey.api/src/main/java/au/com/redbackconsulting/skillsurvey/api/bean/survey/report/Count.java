package au.com.redbackconsulting.skillsurvey.api.bean.survey.report;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class Count implements Serializable{
 
 
 @Expose
 private String cF;
 
 
 @Expose
 private String cM;
 
 @Expose
 private String cO;
 
 
 @Expose
 private String cA;
 
 @Expose
 private String nCF;
 
 
 @Expose
 private String nCM;
 
 @Expose
 private String nCO;
 
 @Expose
 private String nCA;
 
 
 /**
 * @return the cmpOther
 */
public String getCmpOther() {
	return cO;
}


/**
 * @param cmpOther the cmpOther to set
 */
public void setCmpOther(String cmpOther) {
	this.cO = cmpOther;
}


/**
 * @return the notCMPOther
 */
public String getNotCmpOther() {
	return nCO;
}


/**
 * @param notCMPOther the notCMPOther to set
 */
public void setNotCmpOther(String notCMPOther) {
	this.nCO = notCMPOther;
}





public String getCmpFemale() {
	return cF;
}


public void setCmpFemale(String cmpFemale) {
	this.cF = cmpFemale;
}


public String getCmpMale() {
	return cM;
}


public void setCmpMale(String cmpMale) {
	this.cM = cmpMale;
}


public String getCmpAll() {
	return cA;
}


public void setCmpAll(String cmpAll) {
	this.cA = cmpAll;
}


public String getNotCmpFemale() {
	return nCF;
}


public void setNotCmpFemale(String notCmpFemale) {
	this.nCF = notCmpFemale;
}


public String getNotCmpMale() {
	return nCM;
}


public void setNotCmpMale(String notCmpMale) {
	this.nCM = notCmpMale;
}


public String getNotCmpAll() {
	return nCA;
}


public void setNotCmpAll(String notCmpAll) {
	this.nCA = notCmpAll;
}



}
