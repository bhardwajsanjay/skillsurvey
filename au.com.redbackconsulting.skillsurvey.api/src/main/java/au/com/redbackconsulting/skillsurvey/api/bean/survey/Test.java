package au.com.redbackconsulting.skillsurvey.api.bean.survey;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

public class Test implements Serializable {
	
	

@Expose
private SurveyDataBean SurveyDataBean;

public SurveyDataBean getSurveyDataBean() {
	return SurveyDataBean;
}

public void setSurveyDataBean(SurveyDataBean surveyDataBean) {
	SurveyDataBean = surveyDataBean;
}
}
