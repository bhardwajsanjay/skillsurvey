sap.ui.jsview("survey-template.Master", {

	/** Specifies the Controller belonging to this View. 
	* In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
	* @memberOf survey-template.Master
	*/ 
	getControllerName : function() {
		return "survey-template.Master";
	},

	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
	* Since the Controller is given to this method, its event handlers can be attached right away. 
	* @memberOf survey-template.Master
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
		oCell.addContent(new sap.ui.commons.Button({text:'Next>',
			tooltip:'Next',
			press:function(){
			uiNavigation.doNextNavigation();
		}}));
		oRow.addCell(oCell);	
		oMatrix.addRow(oRow);
		//////
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
//			'<div class="homeFirstContent">I strongly encourage you to complete this package.  In addition to building our organisation capacity this tool will assist you: </div>'+
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

//third row content
	createHomeBottomContent:function(){
		return new sap.ui.core.HTML({ 
			content:
			'<div id="container">'+
			'<div id="header" class="homeFirstContentHeader">Welcome to the Skills Gap Analysis Tool</div>'+
			'<div id="content">'+
			'<table width="100%"><tr width="100%">'+
			'<td width="80%">'+
			'<div class="homeFirstContent">I strongly encourage you to complete this package.  In addition to building our organisation capacity this tool will assist you:</div>'+
			'<div>'+
			'<ul>'+
			'<li class="homeFirstContent">Identifying areas for development; </li>'+
			'<li class="homeFirstContent">Identifying gaps between your knowledge and experience and the expectations of your current or future role; </li>'+
			'<li class="homeFirstContent">Support performance discussions; and  </li>'+
			'<li class="homeFirstContent">Build a learning and development plan as part of your development priorities.</li>'+
			'</ul>'+
		        '</div>'+
			'<div class="homeFirstContent">As the sponsor of the People Job Family, I am committed to ensuring that each individual within this Job Family has the appropriate skills and core competencies to deliver our work today and into the future.<div>'+
			'<div class="homeFirstContent">Thank you for joining me.</div>'+
			'<div class="homeFirstContent" style="font-style:italic;">Rebecca Skinner</div>'+
			'<div >Deputy Secretary Defence People </div>'+
			'</td><td align="bottom" rowspan="2" width="20%" height="100%"><img border="0" src="config/images/people/RS.jpg"  height="400" width="300" align="bottom" alt="Rebecca Skinner"></td></tr>'+
			'<tr width="100%"><td width="80%">'+
			'<div>'+
			'<div class="homeBottomContent" style="font-style:italic;color:#FFFFFF;float:right">By increasing the training and development opportunities'+
			' (the key components of Professionalisation) for employees within an organisation, the organisation'+
			' benefits from the resultant increased work outputs, knowledge, employee flexibility and workforce'+
			' capability.'+
			' <div style="font-style:normal;float:right;padding-top:10px;padding-right:15px;">People Job Family Professionalisation Team</div>'+
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




//third row content
//	createHomeBottomContent:function(){
//		return new sap.ui.core.HTML({ 
//			content:
//			'<div>'+
//			'<div class="homeBottomContent" style="font-style:italic;color:#FFFFFF;float:left">By increasing the training and development opportunities'+
//			'(the key components of Professionalisation) for employees within an organisation, the organisation'+
//			' benefits from the resultant increased work outputs, knowledge, employee flexibility and workforce'+
//			' capability.'+
//			' <div style="font-style:normal;float:right;padding-top:25px;padding-right:45px;">People Job Family Professionalisation Team</div>'+
//			'</div>'
//			
//		});	
//	},
//
//});



