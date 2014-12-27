var Styles = function(){
	this.no=90;
	//color:#808080;
	this.getStyleSurveyTemplateReportMatrixHeaderResponse=function(){
//completed and future learning headings
		return "color:#000000; margin:0px auto;border-top:1px #000000 solid;border-right:1px #000000 solid; padding: 0px 0px 25px 0px;font-family: Georgia,Arial,Helvetica,sans-serif;font-size:0.900rem;font-weight:bold;height:30px;width:100px;";
	};
//survey headings
	this.getStyleSurveyTemplateReportMatrixCourseHeader=function(){
		return"color:#000000;font-weight:bold;background-color:white;padding-top:30px;border-top:1px #000000 solid;margin:0px auto;font-family: Georgia,Arial,Helvetica,sans-serif;font-size:1.2rem;height:30px";
	};
	this.getStyleSurveyTemplateReportMatrixCourseContetCell_1=function(){
		return "margin:0px auto;border-top:1px #000000 solid;border-right:1px #000000 solid;font-family: Arial,Helvetica,sans-serif;height:28px;font-size:0.800rem;";
	};
	this.getStyleSurveyTemplateReportMatrixCourseContetCell_2=function(){
		return "margin:0px auto;border-top:1px #000000 solid;border-right:1px #000000 solid;font-family: Arial,Helvetica,sans-serif;font-size:0.800rem;height:28px;";
	};
	this.getStyleSurveyTemplateReportMatrixCourseContetCellNag=function(){
		return "margin:0px auto;border-top:1px #000000 solid;border-right:1px #000000 solid;font-family: Arial,Helvetica,sans-serif;font-size:0.800rem;height:28px;";
	};
	this.getStyleSurveyTemplateReportMatrixHeader=function(){
		var width = config.getDocWidth().toString()-570;
		return "color:#000000; margin:0px auto;border-top:1px #000000 solid;border-right:1px #000000 solid;font-family: Georgia,Arial,Helvetica,sans-serif;font-size:0.900rem;font-weight:bold;width:"+width+"px;height:30px;";
	};
	this.getStyleSurveyTemplateReportHeaderMatrixHeader=function(){
		var width = config.getDocWidth().toString()-570;
		return "color:#000000; margin:0px auto; width:100%;font-family: Georgia,Arial,Helvetica,sans-serif;font-size:0.900rem;font-weight:bold;width:"+width+"px;height:30px;";
	};
	
//additional questions

	this.getStyleSurveyTemplateReportMatrixCourseHeaderAdditional=function(){
		return"background-color:white;margin:0px auto;border:1px #000000 solid;font-family: Arial,Helvetica,sans-serif;font-size:1rem;height:30px; padding-top:15px;padding-bottom:15px;";
	};
	
	this.getStyleSurveyTemplateReportMatrixCourseAdditionalQuestion=function(){
		return "margin:0px auto;border-top:1px #000000 solid;padding-top:15px;padding-bottom:15px; border-right:1px #000000 solid;font-family: Arial,Helvetica,sans-serif;height:28px;font-size:0.800rem; width:60%";

	};
	this.getStyleSurveyTemplateReportMatrixCourseAdditionalAnswer=function(){
		return "margin:0px auto;border-top:1px #000000 solid; padding-top:15px;padding-bottom:15px;font-family: Arial,Helvetica,sans-serif;height:28px;font-size:0.800rem; width:40%;";

	};
	this.getStyleSurveyTemplateReportMatrixCourseAdditionalAnswerInput=function(){
		return "margin:0px auto;border-top:1px #000000 solid; padding-top:15px;padding-bottom:15px;font-family: Arial,Helvetica,sans-serif;height:28px;font-size:0.800rem; width:40%;height:100px;";

	};
//footer styles
	this.getStyleSurveyTemplateReportMatrixCourseFooterInfo=function(){
		return "margin:0px auto;font-family: Arial,Helvetica,sans-serif; padding-top:10px;padding-bottom:10px;height:26px;font-size:0.800rem; width:100%";

	};
	
	this.getStyleSurveyTemplateReportMatrixCourseContetEndCell=function(){
		return "margin:0px auto;border-top:1px #000000 solid; padding-top:15px;padding-bottom:15px;font-family: Georgia,Arial,Helvetica,sans-serif;font-size:0.800rem;height:28px;";
	};
	this.getStyleSurveyTemplateReportMatrixCourseContetEndCell_Alt=function(){
		return "margin:0px auto;border-top:1px #000000 solid;font-family: Arial,Helvetica,sans-serif;font-size:0.800rem;height:28px;background-color:#dddddd";
	};

	this.getStyleSurveyTemplateReportMatrixCourseContetHeadEndCell=function(){
		return "color:#000000;margin:0px auto;border-top:1px #000000 solid;font-family: Arial,Helvetica,sans-serif;font-size:0.900rem;font-weight:bold;height:28px;";
	};
	this.getStyleSurveyTemplateReportMatrixCourseContetCell=function(){
		return "margin:0px auto;border-top:1px #000000 solid;border-right:1px #000000 solid;font-family:Arial,Helvetica,sans-serif;height:28px;font-size:0.800rem;";
	};
	this.getStyleSurveyTemplateReportMatrixCourseContetCell_Alt=function(){
		return "margin:0px auto;border-top:1px #000000 solid;border-right:1px #000000 solid;font-family: Arial,Helvetica,sans-serif;height:28px;font-size:0.800rem;background-color:#dddddd";
	};
/////////////////////////////////////////////////////////////////22-08-2014


	this.getStyleSurveyTemplateReportingMiddleCellHeader=function(){
		return"border-left:1px #000000 solid;padding-right:3px;color:#FF0000;background-color:white;margin:0px auto;font-family: Arial,Helvetica,sans-serif;font-size:0.800rem;height:30px";
	};
	this.getReportingHeaderTextFormat=function(){
		return"padding-left:3px;padding-right:3px;color:#FF0000;background-color:white;margin:0px auto;font-weight:bold;font-family: Arial,Helvetica,sans-serif;font-size:0.800rem;height:30px";
	};
	this.getReportingTextFormat=function(){
		return"padding-left:3px;padding-right:3px;color:#FF0000;background-color:white;margin:0px auto;font-family: Arial,Helvetica,sans-serif;font-size:0.800rem;height:30px";
	};
	this.getStyleSurveyTemplateReportingNoBorderCellHeader=function(){
		return"padding-left:3px;padding-right:3px;color:#FF0000;background-color:white;margin:0px auto;font-family: Arial,Helvetica,sans-serif;font-size:0.800rem;height:30px";
	};




	this.getStyleSurveyTemplateReportingEndCellHeader=function(){
		return"color:#FF0000;background-color:white;border-left:1px #000000 solid;margin:0px auto;font-family: Arial,Helvetica,sans-serif;font-size:0.800rem;height:30px";
	};
	this.getStyleSurveyTemplateReportingUocName=function(){
		return"padding-left:3px ; color:#FF0000;background-color:white;border-left:0px #000000 solid;margin:0px auto;font-family: Arial,Helvetica,sans-serif;font-size:0.800rem;height:30px";
	};
	this.getStyleSurveyTemplateReportingUocNameCellvalue=function(){
		return"color:#FF0000;text-align: center;background-color:white;border-left:1px #000000 solid;margin:0px auto;font-family: Arial,Helvetica,sans-serif;font-size:0.600rem;height:30px";

	};
	this.getStyleSurveyTemplateReportMatrixCourseHeaderFunction=function(){
		return"padding-left:3px;color:#FF0000;background-color:white;border-top:1px #000000 solid;border-bottom:1px #000000 solid;margin:0px auto;font-family: Arial,Helvetica,sans-serif;font-size:0.800rem;height:30px";
	};
	this.getStyleSurveyTemplateReportingUocNameCellvaluePathway=function(){
		return"color:#FF0000;vertical-align:text-topbackground-color:white;margin:0px auto;font-family: Arial,Helvetica,sans-serif;font-size:0.600rem;height:30px";
	};


};