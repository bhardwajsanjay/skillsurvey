package au.com.redbackconsulting.skillsurvey.persistence.report;

public class FR_BY_GENDER_LOCATION {
	
	
	private int needId ;
	private String needDesc; 
	private String uocId; 
	private String uocName; 
	private int locationId; 
	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public String getLocationDesc() {
		return locationDesc;
	}

	public void setLocationDesc(String locationDesc) {
		this.locationDesc = locationDesc;
	}

	private String locationDesc; 
	private int coCntMale; 
	private int coCntFemale; 
	private int coCntOther; 
	private int coCntAll; 
	private int inCoCntMale; 
	private int inCoCntFemale; 
	private int inCoCntOther;
	private int inCoCntAll; 
	
	
	
public FR_BY_GENDER_LOCATION (
		 int needId ,
		 String needDesc, 
		 String uocId, 
		 String uocName, 
		 int locationId,
		 String locationDesc, 
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
	this.locationId= locationId;
	this.locationDesc= locationDesc;
	this.coCntMale= coCntMale;
	this.coCntFemale= coCntFemale;
	this.coCntOther = coCntOther;
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
