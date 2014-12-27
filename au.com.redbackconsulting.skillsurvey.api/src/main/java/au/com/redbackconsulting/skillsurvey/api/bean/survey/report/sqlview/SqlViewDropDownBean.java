package au.com.redbackconsulting.skillsurvey.api.bean.survey.report.sqlview;

import com.google.gson.annotations.Expose;

public class SqlViewDropDownBean {

	public SqlViewDropDownBean(String key , String label) {
		this.key=key;
		this.label=label;
	}
	
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	@Expose
	private String label;
	
	@Expose 
	
	private String key;
	
}
