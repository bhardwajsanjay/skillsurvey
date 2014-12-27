package au.com.redbackconsulting.skillsurvey.api.bean.survey.report;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;

public class ReportHigherEducationPathwayBean implements Serializable {
	@Expose
	private String functionName;
	
@Expose
private List<ReportHigherEducationItemBean> higherEducationItems;

@Expose
private List<ReportLearningPathwayItemBean> pathwayItems;

 

public List<ReportHigherEducationItemBean> getHigherEducationItems() {
	return higherEducationItems;
}

public void setHigherEducationItems(
		List<ReportHigherEducationItemBean> higherEducationItems) {
	this.higherEducationItems = higherEducationItems;
}

public List<ReportLearningPathwayItemBean> getPathwayItems() {
	return pathwayItems;
}

public void setPathwayItems(List<ReportLearningPathwayItemBean> pathwayItems) {
	this.pathwayItems = pathwayItems;
}

public String getFunctionName() {
	return functionName;
}

public void setFunctionName(String functionName) {
	this.functionName = functionName;
}

}
