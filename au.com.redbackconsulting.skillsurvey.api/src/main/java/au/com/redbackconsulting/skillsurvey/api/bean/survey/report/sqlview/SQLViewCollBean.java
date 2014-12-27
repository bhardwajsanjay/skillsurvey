package au.com.redbackconsulting.skillsurvey.api.bean.survey.report.sqlview;

import java.util.List;

import com.google.gson.annotations.Expose;

public class SQLViewCollBean {

	@Expose
	private List<SQLVIEWBean> items ; 
	
	public SQLViewCollBean(List<SQLVIEWBean > coll ){
		
		this.items=coll;
	}
}
