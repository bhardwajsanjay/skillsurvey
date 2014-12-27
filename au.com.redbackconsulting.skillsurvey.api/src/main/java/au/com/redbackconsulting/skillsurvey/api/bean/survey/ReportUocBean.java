package au.com.redbackconsulting.skillsurvey.api.bean.survey;

import java.io.Serializable;

public class ReportUocBean implements Serializable{
	private String uocId;
	private String uocName;
	public String getUocId() {
		return uocId;
	}
	public void setUocId(String uocId) {
		this.uocId = uocId;
	}
	public String getUocName() {
		return uocName;
	}
	public void setUocName(String uocName) {
		this.uocName = uocName;
	}
	

}
