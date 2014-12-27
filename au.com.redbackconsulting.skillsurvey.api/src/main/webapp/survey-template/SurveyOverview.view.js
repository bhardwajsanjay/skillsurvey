sap.ui.jsview("survey-template.SurveyOverview", {

	/** Specifies the Controller belonging to this View. 
	* In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
	* @memberOf survey-template.SurveyOverview
	*/ 
	getControllerName : function() {
		return "survey-template.SurveyOverview";
	},

	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
	* Since the Controller is given to this method, its event handlers can be attached right away. 
	* @memberOf survey-template.SurveyOverview
	*/ 

	createContent : function(oController) {
		 var oMatrix = new sap.ui.commons.layout.MatrixLayout({	layoutFixed : false,}).addStyleClass("surveySurveyMatrix");
		 var oQuestionTable = new sap.ui.table.Table(
			{
				// title: "Survey Status",
				visibleRowCount :4, // config.getRowCount(),
				//visibleRowCount : config.getRowCount(),
				//firstVisibleRow : 3,
				//selectionMode : sap.ui.table.SelectionMode.Single,
				navigationMode : sap.ui.table.NavigationMode.Paginator,
				//fixedColumnCount: 5,
				width : "70%",
				columnHeaderVisible :true,
				//enableGrouping:true,
			});
			oQuestionTable.addColumn(new sap.ui.table.Column({
				width : "20%",
				sortProperty: "needName", 
				label : new sap.ui.commons.Label({
					text : "Surveys"
				}),
				template : new sap.ui.commons.TextView({width:'100%',wrapping :true})
						.bindProperty("text", "needDesciption"),
				hAlign : "Left",
				vAlign : "Middle",
			}));
			oQuestionTable.addColumn(new sap.ui.table.Column({
				width : "20%",
				sortProperty: "needName", 
				label : new sap.ui.commons.Label({
					text : "Status"
				}),
				template : new sap.ui.commons.TextView({width:'100%',wrapping :true})
						.bindProperty("text", "status"),
				hAlign : "Left",
				vAlign : "Middle",
			}));
   			  oQuestionTable.addColumn(new sap.ui.table.Column({
				width : "20%",
				sortProperty: "needName", 
				label : new sap.ui.commons.Label({
				text : "Progress"
				}),
				template :new sap.ui.commons.ProgressIndicator( {
				width: "100px", 
				percentValue: "{percentage}", 
				displayValue:"{percentageStr}"
				}),
				hAlign : "Left",
				vAlign : "Middle",
			}));
    			 oQuestionTable.addColumn(new sap.ui.table.Column({
				width : "20%",
				//sortProperty: "pathwayName", 
				label : new sap.ui.commons.Label({
				text : "Started At"
				}),
				template : new sap.ui.commons.TextView({width:'100%',wrapping :true})
					.bindProperty("text", "startDate"),
				hAlign : "Left",
				vAlign : "Middle",	
			}));
    			 oQuestionTable.addColumn(new sap.ui.table.Column({
				width : "20%",
				//sortProperty: "pathwayName", 
				label : new sap.ui.commons.Label({
				text : "Completed At"
				}),
				template : new sap.ui.commons.TextView({width:'100%',wrapping :true})
					.bindProperty("text", "finishDate"),
				hAlign : "Left",
				vAlign : "Middle",		
			}));
			
			oQuestionTable.setModel(sap.ui.getCore().getModel("SurveyOverview"));
			oQuestionTable.bindRows("/");
			/////
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
			
			////
			var oRow = new sap.ui.commons.layout.MatrixLayoutRow();
			var oCell = new sap.ui.commons.layout.MatrixLayoutCell({vAlign:"Top",hAlign:"Left"});
			oCell.addContent(oQuestionTable);
			oRow.addCell(oCell);
				
			oMatrix.addRow(oRow);
		//add image
			
		var oRow = new sap.ui.commons.layout.MatrixLayoutRow();
		var oCell = new sap.ui.commons.layout.MatrixLayoutCell();
		oCell.addContent(this.createHomeBottomContent());
		oRow.addCell(oCell);	
		oMatrix.addRow(oRow);

		return oMatrix;

},

//third row content //Please click on the Help button for additional assistance
	createHomeBottomContent:function(){
		return new sap.ui.core.HTML({ 
			content:
			'<div id="container">'+
			//'<div id="header" class="homeFirstContentHeader">Getting Started</div>'+
			'<div id="content">'+
			'<table width="100%" height="100%"><tr>'+
			'<td width="80%">'+
			//'<div class="homeFirstContent">The tool consists of 4 surveys which will take you approximately 20 minutes to complete.</div>'+
			//'<div class="homeFirstContent">You can stop at any time, save your results and resume your survey later.</div>'+
			//'<div class="homeFirstContent">Following survey completion, you will receive a short summary  showing  the courses you have completed and helping you decide what training to do next.</div>'+
			//'<div class="homeFirstContent">The summary will also indicate what <b>units of competency</b> you might achieve under a <b>Recognition of Prior Learning</b> process.</div>'+
			//'<div class="homeFirstContent">To begin the survey click on the My Surveys button.  Each survey consists of the Mandatory, Supervisor (if applicable), Highly Desirable and Desirable courses and units of competency for your role.</div>'+
			//'<div class="homeFirstContent">Reports can either be printed or saved as a PDF.</div>'+
			//'<div class="homeFirstContent" style="padding-bottom:50px;"></div>'+
			'</td><td align="bottom" rowspan="2" width="20%" height="100%"><img border="0" src="config/images/people/SurveyOverview.jpg"  height="400" width="500" align="bottom" alt=""></td></tr>'+
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

