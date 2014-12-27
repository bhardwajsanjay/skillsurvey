package au.com.redbackconsulting.skillsurvey.api.bean.survey.report;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class ReportDetailBean implements Serializable {
	@Expose
	private String functionName;
	
	@Expose
	private String functionDesc;
	
	public String getFunctionDesc() {
		return functionDesc;
	}

	public void setFunctionDesc(String functionDesc) {
		this.functionDesc = functionDesc;
	}

	@Expose
	private String reportBy;

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getReportBy() {
		return reportBy;
	}

	public void setReportBy(String reportBy) {
		this.reportBy = reportBy;
	}
	

}
