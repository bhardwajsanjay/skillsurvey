var countDownCounter=120;
function displayListener(bShow) {
			//var bShow = oEvent.getParameter("show");
			var oNotiBar = config.getObjectById("notificationBar");
			if (bShow) {
				$("#footerBar").css("display","block");
				$("#footerBar").css("height","100px");
				$("#left").css("display","none");
				$("#right").css("display","none");
				
				var sStatus = sap.ui.ux3.NotificationBarStatus.Default;
				oNotiBar.setVisibleStatus(sStatus);
			} else {
				$("#footerBar").css("display","none");
				$("#footerBar").css("height","0px");
				$("#left").css("display","block");
				$("#right").css("display","block");
				
				var sStatus = sap.ui.ux3.NotificationBarStatus.None;
				oNotiBar.setVisibleStatus(sStatus);
			}
};
		
function openConfirm() {
	if(sap.ui.getCore().getModel("mBundle").getResourceBundle().getText("is_auto_save")=="ON"){
		//alert(config.getAutoProperties().key);
		if(config.getAutoProperties().key!=null){
			if(!config.updateModelBeforeSaved(config.getAutoProperties().key)){
				
			}else{
				var bus = sap.ui.getCore().getEventBus();
				bus.publish("nav", "fromPathway", {
					id : config.getAutoProperties().key,
					data:{action:config.getPathwayAction(config.getSelectedId())}

				});
			}	
		}
		
	}
	sap.ui.commons.MessageBox.confirm("Your session is about to timeout.  Press OK to to logout or CANCEL to continue your session.", fnCallbackConfirm, "Professional Workforce - Quality Service Session TimeOut Alert");
}
function fnCallbackConfirm(bResult) {
	//alert("Result returned to fnCallbackConfirm: " + bResult);
	if(bResult==false){
		var bus = sap.ui.getCore().getEventBus();
		bus.publish("nav", "toLogout", {
			

		});	
	}else{
		config.callForSessionTimeoutService();
	}
}
//debugger;
var openFlag = false;
var appSesstionStartTime= 0;//config.getAppStartTime();
function startSessionCountDown(){
	//debugger;
	var appSessionEndTime = config.getSessionEndTime();
	//alert("starTime:"+appSesstionStartTime+":: end time-"+appSessionEndTime);
	if((appSessionEndTime-appSesstionStartTime)==2){
		if(openFlag){
			openFlag =false;
			appSesstionStartTime = appSessionEndTime;
			config.setSTime(appSesstionStartTime);
			openConfirm();
			
		}else{
			openFlag =true;
		} 	
	}else{
		appSesstionStartTime+=1;
		config.setSTime(appSesstionStartTime);
	}
	setTimeout(function(){startSessionCountDown();},60000);
};

sap.ui.jsview("survey-template.App", {

	/** Specifies the Controller belonging to this View. 
	* In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
	* @memberOf survey-template.App
	*/ 
	getControllerName : function() {
		return "survey-template.App";
	},
   
	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
	* Since the Controller is given to this method, its event handlers can be attached right away. 
	* @memberOf survey-template.App
	*/ 
	createContent : function(oController) {
		debugger;
		 appSesstionStartTime= config.getAppStartTime();
		config.callForSessionTimeoutService();
		startSessionCountDown();
		var oShell = new sap.ui.ux3.Shell( {
			id:config.getShellId(),
			appTitle:"{mBundle>appTitle}",// "Professional Workforce - Quality Service"
			appIcon: "config/images/defencelogo.png",
			appIconTooltip: "Australian Government",
			showLogoutButton: true,
			showSearchTool: false,
			showInspectorTool: false,
			showFeederTool: false,
			showTools:true,
			showPane:true,
			//headerType: sap.ui.ux3.ShellHeaderType.Standard,
			//designType: sap.ui.ux3.ShellDesignType.Standard,
			fullHeightContent:true, //default is false was true
		//	worksetItems:workSetsItems,
		//	paneBarItems: [ new sap.ui.core.Item("PI_ContactUs",{key:"ContantUs",text:"Contact Us"}),
		//	              ],
	
		
		headerItems: [new sap.ui.commons.TextView({id:"appUserName",text:"",tooltip:""}),
			     new sap.ui.commons.Button({id:"button-user-profile",text:"My Profile",tooltip:"My Profile",
			    	 visible:false,
			    	 press:function(oEvent){
			            	  //debugger;
			            	  config.profieInitial=false;
			            	     var bus = sap.ui.getCore().getEventBus();
								bus.publish("nav", "toProfile", {
									id : "UserProfile",
									data:{action:"get"}
								});		            	  
			              }}),
			              new sap.ui.commons.Button({id:"button-user-help",text:"Help",tooltip:"Please refer to the User Instructions PDF document in the first instance.  This document was sent as an attachment when you were sent the tool link and login email. If you still require assistance, please email workforce.development@defence.gov.au",
						    	 //press:function(oEvent){
						         //   	  //debugger;
						         //   	     var bus = sap.ui.getCore().getEventBus();
							//				bus.publish("nav", "toHelp", {
							//					id : "toHelp",
					//
					//						});		            	  
					//	              }
						}),
			      
			     ],
			
			worksetItemSelected: function(oEvent){
			//debugger;
				
			oController.mainmenuSelectHandler(oEvent.getParameter("id"));
			
			},
			
			paneBarItemSelected: function(oEvent){
				var sKey = oEvent.getParameter("key");
				var oShell = oEvent.oSource;
				switch (sKey) {
				case "pi_date":
					var oDate = new Date();
					oShell.setPaneContent(new sap.ui.commons.TextView({text:oDate.toLocaleString()}), true);
					break;
				case "pi_browser":
					oShell.setPaneContent(new sap.ui.commons.TextView({text:"You browser provides the following information:\n"+navigator.userAgent}), true);
					break;
				default:
					break;
				}
			},
			logout:function(){
				 function fnCallBackConfirm(bResult){
						if(bResult){
							//debugger;
							var bus = sap.ui.getCore().getEventBus();
							bus.publish("nav", "toLogout", {
								

							});	
						}
						
					}
					sap.ui.commons.MessageBox.confirm("Do you want to logout?",fnCallBackConfirm,"Professional Workforce - Quality Service");
						
		
			},
		 	search:function(oEvent){
		 		alert("Search triggered: " + oEvent.getParameter("text"));
		 	},
		 	feedSubmit:function(oEvent){
		 		alert("Feed entry submitted: " + oEvent.getParameter("text"));
		 	},
		 	paneClosed : function(oEvent) {
		 	   // alert("Pane has been closed: " + oEvent.getParameter("id"));
		 	}
		});
		this.toolPoUpApp();
		///
		var navNext = new sap.ui.commons.Button({text:'Next>',
	
			tooltip:'Next',
			press:function(){
			uiNavigation.doNextNavigation();
		}});
		//navNext.placeAt("right");
//		var navPrevious = new sap.ui.commons.Button({text:'<Back',
//	//	var navPrevious = new sap.ui.commons.Button({icon:'confing/images/icons/back.png',
//			tooltip:'Previous',
//			press:function(){
//			uiNavigation.doPreviousNavigation();
//		}});
//		navPrevious.placeAt("left");
		//oRow.addCell(oCell);
		//oMatrix.addRow(oRow);
		
		
		//
		return oShell;
		
	},
		toolPoUpApp:function(){
		var oNotiBar = new sap.ui.ux3.NotificationBar({
			id:"notificationBar",
			display : displayListener,
			visibleStatus : "None",
			resizeEnabled : false,
			
		});
		 oNotiBar.placeAt("footerBar");
	}

});
