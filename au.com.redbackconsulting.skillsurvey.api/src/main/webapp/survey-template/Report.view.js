sap.ui.jsview("survey-template.Report", {

	/** Specifies the Controller belonging to this View. 
	* In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
	* @memberOf survey-template.Report
	*/ 
	getControllerName : function() {
		return "survey-template.Report";
	},

	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
	* Since the Controller is given to this method, its event handlers can be attached right away. 
	* @memberOf survey-template.Report
	*/ 
	createContent : function(oController) {
		 var oMatrix = new sap.ui.commons.layout.MatrixLayout({
				layoutFixed : false,
				width:'100%'
				
			}).addStyleClass("surveySurveyMatrix");
			 
		 var oReportTable = new sap.ui.table.Table(
					{
						// title: "Table with fixed columns
						// Example",
						visibleRowCount :18,// config.getRowCount(),
					//	firstVisibleRow : 3,
						selectionMode : sap.ui.table.SelectionMode.Single,
					//	navigationMode : sap.ui.table.NavigationMode.Paginator,
						//fixedColumnCount: 2,
						width : "100%",
						columnHeaderVisible :true,
						noData:new sap.ui.commons.Label({text:"There is no data exits."}),
						enableGrouping:true,
					});
			
			var needColumn = new sap.ui.table.Column({
				width : "35%",
				sortProperty: "needDescription", 
			//	filterProperty:"needDescription",
			//	grouped :true,
				sorted :false,
				label : new sap.ui.commons.Label({
					text : "Need Type"
				}),
				template : new sap.ui.commons.TextView({width:'100%',wrapping :true}).addStyleClass("surveyPathwayHeaderTextStyle_z")
						.bindProperty("text", "needDescription"),
				hAlign : "Left",
				vAlign : "Middle",
				
			});
			oReportTable.addColumn(needColumn);
			oReportTable.addColumn(needColumn);
			if(sap.ui.getCore().getModel("Report").oData.length>0){
			oReportTable.setGroupBy(needColumn);
			}
		//	oReportTable.setGroupBy(needColumn);
			oReportTable.addColumn(new sap.ui.table.Column({
				width : "43%",
				sortProperty: "uocName", 
				filterProperty:"uocName",
				label : new sap.ui.commons.Label({
					text : "Unit of Competency /Course Name"
				}),
				template : new sap.ui.commons.TextView({width:'100%',wrapping :true}).addStyleClass("surveyPathwayHeaderTextStyle_z")
						.bindProperty("text", "uocName"),
				hAlign : "Left",
				vAlign : "Middle",
				
			
			}));
			oReportTable
					.addColumn(new sap.ui.table.Column(
							{
								width : "7%",
								hAlign:"Center",
								label : new sap.ui.commons.Label({
									width:'100%',
									text : "Completed",
									textAlign :  sap.ui.core.TextAlign.Center 
								}),
								template : new sap.ui.commons.Label({width:'100%',textAlign :  sap.ui.core.TextAlign.Center }).bindProperty("text","completed"),
								vAlign : "Middle",
								hAlign : "Center",
							}));
			oReportTable
					.addColumn(new sap.ui.table.Column(
							{
								width : "7%",
								hAlign:"Center",
								label : new sap.ui.commons.Label({
									width:'100%',
									text : "Future Learning",
									textAlign :  sap.ui.core.TextAlign.Center 
								}),
								template : new sap.ui.commons.Label({width:'100%',textAlign :  sap.ui.core.TextAlign.Center }).bindProperty("text","fututeLearning"),
								vAlign : "Middle",
								hAlign : "Center",

							})).addStyleClass("questionTable");
			oReportTable
			.addColumn(new sap.ui.table.Column(
					{
						width : "7%",
						hAlign:"Center",
						label : new sap.ui.commons.Label({
							width:'100%',
							text : "Recognition",
							textAlign :  sap.ui.core.TextAlign.Center 
						}),
						template : new sap.ui.commons.Label({width:'100%',textAlign :  sap.ui.core.TextAlign.Center }).bindProperty("text","recognition"),
						vAlign : "Middle",
						hAlign : "Center",

					})).addStyleClass("questionTable");
			// Create a model and bind the table rows to this model

			oReportTable.setModel(sap.ui.getCore().getModel("Report"));
			oReportTable.bindRows("/");
			//oReportTable.setGroupBy("needName");
		 /////////////////////
			var oRow = new sap.ui.commons.layout.MatrixLayoutRow({height:"10px"}).addStyleClass("actionRow");
			var oCell = new sap.ui.commons.layout.MatrixLayoutCell({hAlign:"Right"}).setColSpan(2);
			
			oCell.addContent(new sap.ui.commons.Button({text:'<Back',
				tooltip:'Back',
				press:function(){
					uiNavigation.doPreviousNavigation();	
			}}));
			
			oCell.addContent(new sap.ui.commons.Button({text:"Print",
				press:function(){
					
					var mergedReport = new MergedReport(sap.ui.getCore().getModel("ReportModel").oData);
					var 	width =document.documentElement.clientWidth-2;
					var reportPrint = window.open("", "MsgWindow", '"width='+width+',height=500, scrollbars=yes, resizable=1"');
					//var report = new Reports();
					reportPrint.document.write(mergedReport.generateHTMLReport());
					reportPrint.document.close();
					reportPrint.focus();
					reportPrint.print();
				}}).addStyleClass("surveyContinueButtonContainer"));
			//oRow.addCell(oCell);
			oCell.addContent(new sap.ui.commons.Button({text:'Exit',
				tooltip:'Exit',
				press:function(){
					function fnCallBackConfirm(bResult){
						if(bResult){
							debugger;
							var bus = sap.ui.getCore().getEventBus();
							bus.publish("nav", "toLogout", {
								

							});	
						}
						
					}
					sap.ui.commons.MessageBox.confirm("Do you want to logout?",fnCallBackConfirm,"Professional Workforce - Quality Service");
		
			}}));
			oRow.addCell(oCell);
			oMatrix.addRow(oRow);
			//progress indicators
			
			
			var oRow = new sap.ui.commons.layout.MatrixLayoutRow();
			var oCell = new sap.ui.commons.layout.MatrixLayoutCell({vAlign:"Top",hAlign:"Left"}).setColSpan(2);
			oCell.addContent(new sap.ui.core.HTML({ 
				content:config.getSurveyProgressHTML()}));
			oRow.addCell(oCell);
			oMatrix.addRow(oRow);
			//
			var oRow = new sap.ui.commons.layout.MatrixLayoutRow();
			var oCell = new sap.ui.commons.layout.MatrixLayoutCell({vAlign:"Top",hAlign:"Left"}).setColSpan(2);
			oCell.addContent(oReportTable);
			oRow.addCell(oCell);
			
			oMatrix.addRow(oRow);

		
			return oMatrix;

		
		

	}

});
