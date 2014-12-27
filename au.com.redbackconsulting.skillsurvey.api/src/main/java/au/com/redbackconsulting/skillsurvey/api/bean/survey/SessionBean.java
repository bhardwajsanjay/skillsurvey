package au.com.redbackconsulting.skillsurvey.api.bean.survey;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class SessionBean implements Serializable{

	
	@Expose
	private String sessionId;
	
	@Expose
	private int timeInterval;
	
	@Expose
	private long creationTime ;
	
	@Expose
	private long lastAccessedtime;
	
	
	public long getLastAccessedtime() {
		return lastAccessedtime;
	}

	public void setLastAccessedtime(long lastAccessedtime) {
		this.lastAccessedtime = lastAccessedtime;
	}

	public long getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(long creationTime) {
		this.creationTime = creationTime;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}


	
	public int getTimeInterval() {
		return timeInterval;
	}

	public void setTimeInterval(int timeInterval) {
		this.timeInterval = timeInterval;
	}

	
}
