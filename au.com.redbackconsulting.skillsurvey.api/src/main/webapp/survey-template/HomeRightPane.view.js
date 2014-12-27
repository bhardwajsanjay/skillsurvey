sap.ui.jsview("survey-template.HomeRightPane", {

	/** Specifies the Controller belonging to this View. 
	* In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
	* @memberOf survey-template.HomeRightPane
	*/ 
	getControllerName : function() {
		return "survey-template.HomeRightPane";
	},

	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
	* Since the Controller is given to this method, its event handlers can be attached right away. 
	* @memberOf survey-template.HomeRightPane
	*/ 
	createContent : function(oController) {
		var oMatrix = new sap.ui.commons.layout.MatrixLayout({
			layoutFixed : false,
			width:'100%'
			//width:(config.getDocWidth()-308).toString()+'px',
			//height:(config.getMatrixHeight()).toString()+'px'
			
		}).addStyleClass("home-left-container");
		var oRow = new sap.ui.commons.layout.MatrixLayoutRow();
		var oCell = new sap.ui.commons.layout.MatrixLayoutCell({
			vAlign:"Middle",
			hAlign:"Left",
			width:'100%'
			});
		oCell.addContent(this.firstContentForLeftPane());
		oRow.addCell(oCell);
		oMatrix.addRow(oRow);
		return oMatrix;
	},
	firstContentForLeftPane:function(){
		return new sap.ui.core.HTML({ 
			content:'<div>'+
			'</div>'
				});
	}

});
