sap.ui.jsview("survey-template.FunctionalReport", {

	/** Specifies the Controller belonging to this View. 
	* In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
	* @memberOf survey-template.FunctionalReport
	*/ 
	getControllerName : function() {
		return "survey-template.FunctionalReport";
	},

	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
	* Since the Controller is given to this method, its event handlers can be attached right away. 
	* @memberOf survey-template.FunctionalReport
	*/ 
	createContent : function(oController) {
		var oModel = sap.ui.getCore().getModel("functionReportModel");
		var reportData={};
		if(oModel!=null){
			reportData= oModel.oData;
		}
		var report = config.getReportInstance(reportData,"f");
		 var oMatrix = new sap.ui.commons.layout.MatrixLayout({	layoutFixed : false});
		
		
		 var oRow = new sap.ui.commons.layout.MatrixLayoutRow();
		 var oCell = new sap.ui.commons.layout.MatrixLayoutCell({});
		 oCell.addContent(new sap.ui.commons.Button({text:"Goto Functional Report",press:function(){
				var bus = sap.ui.getCore().getEventBus();
				bus.publish("nav", "to", {
					id : "FunctionalReports",

				});	
		 }}));
		 oCell.addContent(new sap.ui.commons.Button({text:"Print",press:function(){
				var 	width =document.documentElement.clientWidth-2;
				var reportPrint = window.open("", "MsgWindow", '"width='+width+',height=500, scrollbars=yes, resizable=no"');
				//var report = new Reports();
				reportPrint.document.write(report.generateHTMLFormat());
				reportPrint.document.close();
				reportPrint.focus();
				reportPrint.print();
		 }}));
		 oCell.addContent(new sap.ui.commons.Button({text:"Export Report",press:function(){
				var 	width =document.documentElement.clientWidth-2;
				report.exportToCVSFormat();
		 }}));	
		 oRow.addCell(oCell);
		 oMatrix.addRow(oRow);
		
		
		
		
		var ohtml = new sap.ui.core.HTML({
				content:report.generateHTMLFormat()
			});
		 var oRow = new sap.ui.commons.layout.MatrixLayoutRow();
		 var oCell = new sap.ui.commons.layout.MatrixLayoutCell({});
		 oCell.addContent(ohtml);
		 oRow.addCell(oCell);
		 oMatrix.addRow(oRow);
	
		return oMatrix;
	}

});
