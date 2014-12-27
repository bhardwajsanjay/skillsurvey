sap.ui.controller("survey-template.FunctionalReports", {

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf survey-template.FunctionalReports
*/
//	onInit: function() {
//
//	},

/**
* Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
* (NOT before the first rendering! onInit() is used for that one!).
* @memberOf survey-template.FunctionalReports
*/
//	onBeforeRendering: function() {
//
//	},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf survey-template.FunctionalReports
*/
//	onAfterRendering: function() {
//
//	},
	handleTilesPress:function(oModel){
		 var bus = sap.ui.getCore().getEventBus();
		 bus.publish("nav", "toFunctionalReport", { 
		     id : oModel.key,   
		});
		
	}

/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf survey-template.FunctionalReports
*/
//	onExit: function() {
//
//	}

});