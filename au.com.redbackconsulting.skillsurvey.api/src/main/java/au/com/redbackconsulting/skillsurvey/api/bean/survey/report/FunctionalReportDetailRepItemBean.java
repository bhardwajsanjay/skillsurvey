package au.com.redbackconsulting.skillsurvey.api.bean.survey.report;

import java.io.Serializable;
import java.util.List;

import au.com.redbackconsulting.skillsurvey.api.bean.survey.LocationBean;

import com.google.gson.annotations.Expose;

public class FunctionalReportDetailRepItemBean implements Serializable {
	@Expose
	private List<FunctionalReport> reportItems;
	
	@Expose 
	private ReportDetailBean reportDetail;
	
	@Expose
	private List<LocationBean> locations;

	/**
	 * @return the locations
	 */
	public List<LocationBean> getLocations() {
		return locations;
	}

	/**
	 * @param locations the locations to set
	 */
	public void setLocations(List<LocationBean> locations) {
		this.locations = locations;
	}

	public List<FunctionalReport> getReportItems() {
		return reportItems;
	}

	public void setReportItems(List<FunctionalReport> reportItems) {
		this.reportItems = reportItems;
	}

	public ReportDetailBean getReportDetail() {
		return reportDetail;
	}

	public void setReportDetail(ReportDetailBean reportDetail) {
		this.reportDetail = reportDetail;
	}
	
	

}
