sap.ui.jsview("survey-template.Instructions", {

	/** Specifies the Controller belonging to this View. 
	* In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
	* @memberOf survey-template.Instructions
	*/ 
	getControllerName : function() {
		return "survey-template.Instructions";
	},

	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
	* Since the Controller is given to this method, its event handlers can be attached right away. 
	* @memberOf survey-template.Instructions
	*/ 

	createContent : function(oController){
		var oMatrix = new sap.ui.commons.layout.MatrixLayout();
// First row
//		var oRow = new sap.ui.commons.layout.MatrixLayoutRow();
//		var oCell = new sap.ui.commons.layout.MatrixLayoutCell();
//		oCell.addContent(this.firstContentForLeftPane());
//		oRow.addCell(oCell);
//		oMatrix.addRow(oRow);
// Second row
//		var oRow = new sap.ui.commons.layout.MatrixLayoutRow();
//		var oCell = new sap.ui.commons.layout.MatrixLayoutCell();
//		oCell.addContent(this.createImageContent());
//		oRow.addCell(oCell);
//		oMatrix.addRow(oRow);


// Third row
		
		var oRow = new sap.ui.commons.layout.MatrixLayoutRow();
		var oCell = new sap.ui.commons.layout.MatrixLayoutCell({hAlign:"Right"});
		oCell.addContent(new sap.ui.commons.Button({text:'<Back',
			tooltip:'Back',
			press:function(){
			uiNavigation.doPreviousNavigation();
		}}));
		oCell.addContent(new sap.ui.commons.Button({text:'Next>',
			tooltip:'Next',
			press:function(){
			uiNavigation.doNextNavigation();
		}}));
		oRow.addCell(oCell);	
		oMatrix.addRow(oRow);
		var oRow = new sap.ui.commons.layout.MatrixLayoutRow();
		var oCell = new sap.ui.commons.layout.MatrixLayoutCell();
		oCell.addContent(this.createHomeBottomContent());
		oRow.addCell(oCell);	
		oMatrix.addRow(oRow);
		return oMatrix;
	},

//second row content	
//	createImageContent:function(){
//		return new sap.ui.core.HTML({ 
//		content:
//		'<div  class="ImageContentHome"><img src="config/images/home_img.gif"></div>'
//		});
//	},

//first row content
//	firstContentForLeftPane:function(){
//		return new sap.ui.core.HTML({ 
//			content:
//			'<div>'+
//			'<div class="homeFirstContentHeader">Welcome to the Skills Gap Analysis Tool.</div>'+
//			'<div class="homeFirstContent">I strongly encourage you to complete this package.  In addition to building our organisation capacity this tool will assist you:</div>'+
//			'<div>'+
//			'<ul>'+
//			'<li class="homeFirstContent">Identifying areas for development; </li>'+
//			'<li class="homeFirstContent">Identifying gaps between your knowledge and experience and the expectations of your current or future role; </li>'+
//			'<li class="homeFirstContent">Support performance discussions; and  </li>'+
//			'<li class="homeFirstContent">Build a learning and development plan as part of your development priorities.</li>'+
//			'</ul>'+
//		        '</div>'+
//			'<div class="homeFirstContent">As the sponsor of the People Job Family, I am committed to ensuring that each individual within this Job Family has the appropriate skills and core competencies to deliver our work today and into the future.<div>'+
//			'<div class="homeFirstContent">Thank you for joining me.</div>'+
//			'<div class="homeFirstContent" style="font-style:italic;">Rebecca Skinner</div>'+
//			'<div style="padding-bottom:20px;">Deputy Secretary Defence People </div>'+
//                      '<span style="float:right"><img border="0" src="config/images/people/RS.jpg" alt="Rebecca Skinner"></span>'+
//			'</div>'
//		});
//	},

//third row content //Please click on the Help button for additional assistance
	createHomeBottomContent:function(){
		return new sap.ui.core.HTML({ 
			content:
			'<div id="container">'+
			'<div id="header" class="homeFirstContentHeader">Getting Started</div>'+
			'<div id="content">'+
			'<table width="100%" height="100%"><tr>'+
			'<td width="80%">'+
			'<div class="homeFirstContent">Click on My Surveys or the Next Button.</div>'+
			'<div class="homeFirstContent">Complete all 3 surveys (4 for Supervisors) – as shown on the My Surveys page.</div>'+
			'<div class="homeFirstContent">Each survey contains the courses and <span class="callout" title="Units of competency are accredited units of study which describe the skills and knowledge people'+
 			'need to perform effectively in the workplace.  Units of competency, when grouped together, work towards a nationally endorsed vocational qualification such as a Certificate III, Certificate IV, Diploma and Advanced Diploma."'+  
			'><u><b>units of competency (UoC)</b></u></span> for your role.</div>'+
			'<div class="homeFirstContent">You will be asked which courses you have completed and which you wish to do in the future.</div>'+
			'<div class="homeFirstContent">You will also be prompted to answer questions about <span class="callout" title="Recognition is a process for giving you credit for a unit of competency for the skills, knowledge and experience gained through work and learning."><u><b>'+
			'Recognition of Current Competency (RCC)</b></u></span>. This is only available for UoCs  - as indicated by an asterisk*.</div>'+
			'<div class="homeFirstContent">Some surveys may be longer  than one page. Use the pagination buttons at the bottom of the page to move to the next page.</div>'+
			'<div class="homeFirstContent">To move onto the next survey, use the Next Survey Button.</div>'+
			'<div class="homeFirstContent">If you need to exit midway through a survey  select the Save button. The Next Survey and Back navigation buttons also save your work.</div>'+
			'<div class="homeFirstContent">Following survey completion, you will receive a short report to help you make your next training decision.</div>'+
			'<div class="homeFirstContent">For additional information, please consult the instruction manual emailed to you.</div>'+
		//	'<div class="homeFirstContent">To begin the survey click on the My Surveys button.  Each survey consists of the Mandatory, Supervisor (if applicable), Highly Desirable and Desirable courses and units of competency for your role.</div>'+
		//	'<div class="homeFirstContent">Reports can either be printed or saved as a PDF.</div>'+
			'<div class="homeFirstContent" style="padding-bottom:50px;"></div>'+
			'</td><td align="bottom" rowspan="2" width="20%" height="100%"><img border="0" src="config/images/people/instruction.jpg"  height="400" width="300" align="bottom" alt=""></td></tr>'+
			'<tr width="100%"><td width="80%">'+
		//	'<div class="homeBottomContent" style="font-style:italic;color:#FFFFFF;float:right">Text'+
		//	' benefits from the resultant increased work outputs, knowledge, employee flexibility and workforce'+
			'</div>'+
			'</td></tr>'+
			'</table>'+
			'</div>'+
			//'<div id="footer" style="background-color:#FFA500;text-align:center;">'+
			//'Copyright � Australian Government</div>'+
			'</div>'
		});	
	},

});
