//: NOTE: Lable texts are retreived via api from Web-INF/reporttexts.properties:
//: Initialization: Look in "this.generateHTMLReport"

var MergedReport =function(data){
	this.data = data;
	this.docContent='';
	this.column=0;
	this.style = new Styles();
	this.addHtmlReportHeader=function(contents,hData){
		//debugger;
		contents+='<tr>';
		
		contents+='<td style="'+this.style.getStyleSurveyTemplateReportMatrixHeader()+'">Unit of Competency/Course Name</td>';
		contents+='<td style="'+this.style.getStyleSurveyTemplateReportMatrixHeaderResponse()+'">Completed</td>';
		contents+='<td style="'+this.style.getStyleSurveyTemplateReportMatrixHeaderResponse()+'">Future Learning</td>';
		contents+='<td style="'+this.style.getStyleSurveyTemplateReportMatrixCourseContetHeadEndCell()+'">Recognition</td>';

		contents+='</tr>';
		return contents;
	};
	this.addCourseHtmlContent=function(courseContent){
		//debugger;
		var items = courseContent.items;
		var remainder = null;
		if(items.length<1){return;}
		var xHtml='<tr><td colspan="4" style="'+this.style.getStyleSurveyTemplateReportMatrixCourseHeader()+'">'+courseContent.needDescription+ '</td><td ></td><td ></td></tr>';
		for(var it in items){
			remainder = it % 2;
			if (remainder == 0 )
			{
				xHtml+='<tr><td style="'+this.style.getStyleSurveyTemplateReportMatrixCourseContetCell()+'">'+items[it].uocName+'</td><td  style="'+this.style.getStyleSurveyTemplateReportMatrixCourseContetCell()+'">'+items[it].completed+'</td>'+
				'<td  style="'+this.style.getStyleSurveyTemplateReportMatrixCourseContetCell()+'">'+items[it].fututeLearning+'</td>'+
				'<td  style="'+this.style.getStyleSurveyTemplateReportMatrixCourseContetEndCell()+'">'+items[it].recognition+'</td></tr>';
			}
			else
			{
				xHtml+='<tr><td style="'+this.style.getStyleSurveyTemplateReportMatrixCourseContetCell_Alt()+'">'+items[it].uocName+'</td><td  style="'+this.style.getStyleSurveyTemplateReportMatrixCourseContetCell_Alt()+'">'+items[it].completed+'</td>'+
				'<td  style="'+this.style.getStyleSurveyTemplateReportMatrixCourseContetCell_Alt()+'">'+items[it].fututeLearning+'</td>'+
				'<td  style="'+this.style.getStyleSurveyTemplateReportMatrixCourseContetEndCell_Alt()+'">'+items[it].recognition+'</td></tr>';
			}

//		}
	}
		return xHtml;
	};
	this.generateHTMLReport=function(){
	  //debugger; 
	  if(this.data==null){return ;}
	  
		this.docContent='<div class="reportContainerDiv">';
		this.docContent+='<table>';
//	Add the Report title: 
		this.docContent+='<tr>';
		this.docContent+='<td style=""><h2>Skills Gap Analysis Report</h2></td>';
		this.docContent+='</tr>';
//	Add User Profile: uses web-inf reporttexts.properties
		this.addProfileInfo(this.data.profileInfo);
//	Add instruction: 
		this.docContent+='<tr>';
		this.docContent+='<td colspan="3" style="padding: 15px 0px 5px 0px;"><i>Please use this report to have a learning and development conversation with your Supervisor or Manager.</i></td>';
		this.docContent+='</tr>';
		this.docContent+='</table>';
		
		//this.docContent+='<table style="border:0px;padding:0px auto;margin-bottom:4px; border:1px #000000 solid;"><tr height="30px"><td colspan="3" style="'+this.style.getStyleSurveyTemplateReportHeaderMatrixHeader()+'">'+this.data.title+'</td></tr>';
		
		this.docContent+='<table style="border:0px #000000 solid;"><tr height="30px"><td colspan="3" style="">'+this.data.title+'</td></tr>';
		this.docContent = this.addHtmlReportHeader(this.docContent);
		var content = this.data.items;
		var xHtml='';
		 for(var c in content){
			 xHtml+= this.addCourseHtmlContent(content[c]);
		 }
		 this.docContent+=xHtml;
		 this.docContent+='</table >';
		 this.docContent+=this.addAdditionalLearning();
		 this.docContent+=this.addFooterHtml();
		 this.docContent+=' </div>';	
		 return this.docContent;
	
	};

//adds Profile information

	this.addProfileInfo=function(info){
		//debugger;
		
		for(var h in info){
			this.docContent+='<tr>';
			//this.docContent+='<td style="font-family: Georgia,Arial,Helvetica,sans-serif;font-size:0.900rem;height:28px;">'+ info[h].name +'</td><td style="font-family: Arial,Helvetica,sans-serif;font-size:0.900rem;height:28px;">'+':'+info[h].value+'</td>';
			this.docContent+='<td style="font-family: Georgia;font-weight:bold; font-size:14px">'+ info[h].name +'</td><td style="font-family: Arial; font-size:14px">'+':'+info[h].value+'</td>';
			this.docContent+='</tr>';
		}
		
		//return contents;
	};

//Learning Pathway info
	this.addFooterHtml=function(){
//		var width = (config.getDocWidth().toString()-540)+(100*2);
		var html='<table>';
		html+='<tr><td style="color:#000000;font-weight:bold;padding-top:30px;font-size:1.2rem;font-family: Georgia,Arial,Helvetica,sans-serif;">Learning Pathway</td></tr>';
		html+='<tr><td style="'+this.style.getStyleSurveyTemplateReportMatrixCourseFooterInfo()+'">It is recommended you discuss your preferred Learning Pathway  with your Supervisor or Manager. The Learning Pathways are as follows:</td></tr>';
		//pathways:
		html+='<tr><td style="'+this.style.getStyleSurveyTemplateReportMatrixCourseFooterInfo()+'">The <b>Accredited (Green) Path </b> is for individuals that require a level of skilling beyond '+"'on the job'"+' training. Generally this requires a level of technical and/or managerial knowledge/skills and it is considered that undertaking accredited units '+
		'(and obtaining an accredited qualification identified in the occupational profiles) is the most effective way of gaining the required skills and '+
		'knowledge.</td></tr>';
		html+='<tr><td style="'+this.style.getStyleSurveyTemplateReportMatrixCourseFooterInfo()+'">The<b> People Specialist / Senior Management (Blue) Path </b> is for those individuals where there is a high level of specialist and/or managerial knowledge/skills and it '+
		'is considered that obtaining a tertiary qualification at the Bachelor Degree level or above is the most effective way of gaining the required skills and knowledge.</td></tr>';
		
		html+='<tr><td style="'+this.style.getStyleSurveyTemplateReportMatrixCourseFooterInfo()+'">The <b>Corporate (Orange) Path </b> is for individuals who are comfortable remaining in their current role, at their current level for the foreseeable future.  An individual pursuing this path may have no immediate '+
		'desire to commit to the skilling requirements needed to progress further (i.e. for promotion or to transfer to another role) in the people job family above an APS6 level. '+
		' The emphasis for these positions is '+"'on the job'"+' training, coaching and baseline skilling such as those learning opportunities offered via CAMPUS.</td></tr>';
		
		html+='<tr><td style="'+this.style.getStyleSurveyTemplateReportMatrixCourseFooterInfo()+'">Once an individual has identified which path they are in or wish to pursue, this will assist in guiding development in the short to medium term.  The identified path however does not have to be set in stone'+"-"+
		' an individual may discuss with their supervisor at any time, their desire to move to an alternate path (for example from corporate training to accreditation).</td></tr>';
//Additional Skilling		
		html+='<tr><td style="color:#000000;font-weight:bold;padding-top:30px;font-size:1.2rem;font-family: Georgia,Arial,Helvetica,sans-serif;">Additional Skilling</td></tr>';
		html+='<tr><td style="'+this.style.getStyleSurveyTemplateReportMatrixCourseFooterInfo()+'">WHS Function:</td></tr>';
		html+='<tr><td style="'+this.style.getStyleSurveyTemplateReportMatrixCourseFooterInfo()+'">DPG is currently offering funded positions (until end 2015) on Certificate IV in WHS (for APS 4/5 level) and Diploma in WHS (for APS 6/EL1 level) courses for those within the WHS Job Function.  Courses for APS 2/3 level are to be advised.</td></tr>';
		html+='<tr><td style="'+this.style.getStyleSurveyTemplateReportMatrixCourseFooterInfo()+'">Note: To undertake a Diploma in WHS, participants will require completion of prerequisite Certificate IV in WHS.</td></tr>';
		html+='<tr><td style="'+this.style.getStyleSurveyTemplateReportMatrixCourseFooterInfo()+'">More information on People Professionalisation can be found on PeopleConnect on the People Professionalisation webpage.</td></tr>';
		html+='</table>';
		return html;
		

		
	};

//Higher Education Questions

	this.addAdditionalLearning=function(){
		//var width = (config.getDocWidth().toString()-500)+(100*2);
		var html='<table>';
	
		html+='<tr ><td colspan="2" style="color:#000000;font-weight:bold;padding-top:30px;font-size:1.2rem;font-family: Georgia,Arial,Helvetica,sans-serif;">Higher Education</td></tr>';
		//html+='<tr ><td style="'+this.style.getStyleSurveyTemplateReportMatrixCourseAdditionalQuestion()+'">Are there any additional courses not listed that you believe you need to complete to be successful in your role?</td>';
		//html+='<td  style="'+this.style.getStyleSurveyTemplateReportMatrixCourseAdditionalAnswerInput()+'">Insert name Allow for more than 1 qual</td></tr>';
		
		html+='<tr ><td style="'+this.style.getStyleSurveyTemplateReportMatrixCourseAdditionalQuestion()+'">'+this.data.question1+'</td>';
		html+='<td  style="'+this.style.getStyleSurveyTemplateReportMatrixCourseAdditionalAnswer()+'" >'+this.data.answer1+'</td></tr>';
	
	//	html+='<tr ><td  style="'+this.style.getStyleSurveyTemplateReportMatrixCourseAdditionalQuestion()+'">What is the name of the degree you hold or are currently studying and what year did you complete?</td>';
	//	html+='<td  style="'+this.style.getStyleSurveyTemplateReportMatrixCourseAdditionalAnswerInput()+'">Insert name and year.	Allow for more than 1 qual.</td></tr>';
	
		html+='<tr ><td style="'+this.style.getStyleSurveyTemplateReportMatrixCourseAdditionalQuestion()+'">'+this.data.question2+'</td>';
		html+='<td style="'+this.style.getStyleSurveyTemplateReportMatrixCourseAdditionalAnswer()+'">'+this.data.answer2+'</td></tr>';
		html+='</table>';
		return html;
	};
};