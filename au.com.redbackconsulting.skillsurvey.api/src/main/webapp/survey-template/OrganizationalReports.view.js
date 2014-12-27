sap.ui.jsview("survey-template.OrganizationalReports", {

	/** Specifies the Controller belonging to this View. 
	* In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
	* @memberOf survey-template.OrganizationalReports
	*/ 
	getControllerName : function() {
		return "survey-template.OrganizationalReports";
	},

	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
	* Since the Controller is given to this method, its event handlers can be attached right away. 
	* @memberOf survey-template.OrganizationalReports
	*/ 
	createContent : function(oController) {
		
			var oTileCont = new sap.m.TileContainer({
				height:"400px"
				
			});
			//Tile Template
			var oTileTmp = new sap.m.StandardTile({
				//id:"{id}",
				icon : "{icon}",
				title : "{OrganizationalReportsModel>label}",
				info : "{OrganizationalReportsModel>label}",
				press:function(evt){
					oController.handleTilesPress(evt.getSource().getBindingContext("OrganizationalReportsModel").getObject());
				}
			});

			//Bind Categories use template to build tiles
			
			oTileCont.bindAggregation("tiles","OrganizationalReportsModel>/",oTileTmp);
			 var oMatrix = new sap.ui.commons.layout.MatrixLayout({
					layoutFixed : false,
					width:'100%'
						
				}).addStyleClass("surveySurveyMatrix");
			 
			 var oRow = new sap.ui.commons.layout.MatrixLayoutRow();
				var oCell = new sap.ui.commons.layout.MatrixLayoutCell({hAlign:"Right"});
				oCell.addContent(new sap.ui.commons.Button({text:'Next>',
					tooltip:'Next',
					press:function(){
					uiNavigation.doNextNavigation();
				}}));
				oRow.addCell(oCell);	
				oMatrix.addRow(oRow); 
				var oRow = new sap.ui.commons.layout.MatrixLayoutRow();
				var oCell = new sap.ui.commons.layout.MatrixLayoutCell();
				oCell.addContent(oTileCont);
				oRow.addCell(oCell);	
				oMatrix.addRow(oRow);
			return oMatrix;
	}

});
