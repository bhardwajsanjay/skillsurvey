sap.ui.jsview("survey-template.ExportReport", {

	/** Specifies the Controller belonging to this View. 
	* In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
	* @memberOf survey-template.ExportReport
	*/ 
	getControllerName : function() {
		return "survey-template.ExportReport";
	},

	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
	* Since the Controller is given to this method, its event handlers can be attached right away. 
	* @memberOf survey-template.ExportReport
	*/ 
	createContent : function(oController) {
		
		 var oMatrix = new sap.ui.commons.layout.MatrixLayout({	layoutFixed : false});
			
			
		 var oRow = new sap.ui.commons.layout.MatrixLayoutRow();
		 var oCell = new sap.ui.commons.layout.MatrixLayoutCell({colSpan:3});
		 oCell.addContent(new sap.ui.commons.Button({text:"Goto Organizational Report",press:function(){
				var bus = sap.ui.getCore().getEventBus();
				bus.publish("nav", "to", {
					id : "OrganizationalReports",

				});	
		 }}));
		 oRow.addCell(oCell);
		 oMatrix.addRow(oRow);
		 var oRow = new sap.ui.commons.layout.MatrixLayoutRow();
		 var oCell = new sap.ui.commons.layout.MatrixLayoutCell();
		 oCell.addContent(new sap.ui.commons.TextView({text:"Select report type for export:"}));
		 oRow.addCell(oCell);
		 
		 var oCell = new sap.ui.commons.layout.MatrixLayoutCell();
		 oCell.addContent( new sap.ui.commons.DropdownBox({width:"200px",height:"30px" ,
	    		id:"export-report-dropdown",
	    		  change:function(evt){
	    			  debugger;
	    	    	  var ctx =evt.mParameters.selectedItem.getBindingContext("exportRepModel").getObject();
			    	  config.setExportReportContext(ctx);
	    	      },
	    		 selectedKey:"{selectedModel>/key}"
	    		 }).bindAggregation("items", "exportRepModel>/", new sap.ui.core.ListItem({
	    	     text: "{exportRepModel>label}",
	    	     key: "{exportRepModel>key}",
	    	     selectedItemId :"{selectedModel>/key}",
	    	    
	    	    })) .setModel(new sap.ui.model.json.JSONModel(sap.ui.getCore().getModel("exportRepModel"))));
		 oRow.addCell(oCell);
		 var oCell = new sap.ui.commons.layout.MatrixLayoutCell();
		 oCell.addContent(new sap.ui.commons.Button({text:'Export',
				tooltip:'Export',
				press:function(evt){
					var bus = sap.ui.getCore().getEventBus();
					 bus.publish("nav", "toOrganizationalReport", { 
					     id : "ExportReport-CallExportData",   
					});
				}}));
		 oRow.addCell(oCell);
		 oMatrix.addRow(oRow);
		return oMatrix;
		
	}

});
