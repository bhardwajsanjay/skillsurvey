package au.com.redbackconsulting.skillsurvey.persistence.report;

public class FR_BY_GENDER_FUNCTION {
	

	
	private int needId ;
	private String needDesc; 
	private String uocId; 
	private String uocName; 
	private int functionId; 
	public int getFunctionId() {
		return functionId;
	}



	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}



	public String getFunctionDesc() {
		return functionDesc;
	}



	public void setFunctionDesc(String functionDesc) {
		this.functionDesc = functionDesc;
	}

	private String functionDesc; 
	private int coCntMale; 
	private int coCntFemale; 
	private int coCntOther;
	private int coCntAll; 
	
	private int inCoCntMale; 
	private int inCoCntFemale; 
	private int inCoCntOther;
	private int inCoCntAll; 
	
	
	
public FR_BY_GENDER_FUNCTION  (
		 int needId ,
		 String needDesc, 
		 String uocId, 
		 String uocName, 
		 int functionId,
		 String functionDesc, 
		 int coCntMale, 
		 int coCntFemale, 
         int coCntOther,
		 int coCntAll, 
		 int inCoCntMale,
		 int inCoCntFemale,
		 int inCoCntOther,
		 int inCoCntAll
		
		
		) {

	this.needId= needId;
	this.needDesc= needDesc;
	this.uocId= uocId;
	this.uocName= uocName;
	this.functionId= functionId;
	this.functionDesc= functionDesc;
	this.coCntMale= coCntMale;
	this.coCntFemale= coCntFemale;
	this.coCntOther=coCntOther;
	this.coCntAll= coCntAll;
	this.inCoCntMale= inCoCntMale;
	this.inCoCntFemale= inCoCntFemale;
	this.inCoCntOther= inCoCntOther;
	this.inCoCntAll= inCoCntAll;
	
}



public int getNeedId() {
	return needId;
}

public String getNeedDesc() {
	return needDesc;
}

public String getUocId() {
	return uocId;
}

public String getUocName() {
	return uocName;
}

 

public int getCoCntMale() {
	return coCntMale;
}

public int getCoCntFemale() {
	return coCntFemale;
}

public int getCoCntOther() {
	return coCntOther;
}

public int getCoCntAll() {
	return coCntAll;
}

public int getInCoCntMale() {
	return inCoCntMale;
}

public int getInCoCntFemale() {
	return inCoCntFemale;
}

public int getInCoCntOther() {
	return inCoCntOther;
}

public int getInCoCntAll() {
	return inCoCntAll;
}

 


}
