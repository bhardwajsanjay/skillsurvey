var state=false;
var navigationBarState=0; //0 for profile, 1 for password, 2 for role assignment;
sap.ui.controller("survey-template.UserManagement", {
	
	userDataSet:{row:false,column:false},

/**
* Called when a controller is instantiated and its View controls (if available) are already created.
* Can be used to modify the View before it is displayed, to bind event handlers and do other one-time initialization.
* @memberOf survey-template.UserManagement
*/
//	onInit: function() {
//
//	},

/**
* Similar to onAfterRendering, but this hook is invoked before the controller's View is re-rendered
* (NOT before the first rendering! onInit() is used for that one!).
* @memberOf survey-template.UserManagement
*/
//	onBeforeRendering: function() {
//
//	},

/**
* Called when the View has been rendered (so its HTML is part of the document). Post-rendering manipulations of the HTML could be done here.
* This hook is the same one that SAPUI5 controls get after being rendered.
* @memberOf survey-template.UserManagement
*/
//	onAfterRendering: function() {
//
//	},

/**
* Called when the Controller is destroyed. Use this one to free resources and finalize activities.
* @memberOf survey-template.UserManagement
*/
//	onExit: function() {
//
//	}
    createThingGroup:function(type){
   	var oTI = config.getObjectById(constant._USER_MANAGEMENT_THINGINSPECTOR_ID);
   	oTI.destroyFacetContent() ;
    	var editable = false;
    	var group = config.getObjectById(constant._USER_MANAGEMENT_THING_GROUP_ID);
    	if(group==null){
    		group =  new sap.ui.ux3.ThingGroup({id:constant._USER_MANAGEMENT_THING_GROUP_ID});		
    	}
    	group.destroyContent() ;
    	if(type!="overview"){
    		editable = true;
    		group.addContent(this.createOverviewContent(editable));
    		group.addContent(this.passwordAssignmentForm());
    		group.addContent(this.roleAssignmentForm());
    	}
    	if(type=="overview"){
    		group.addContent(this.createReadOnlyForm());
    		group.addContent(this.passwordAssignmentReadOnlyForm());
    		group.addContent(this.roleAssignmentReadOnlyForm());
    	}
   
    	
    	oTI.addFacetContent(group);
    	oTI.open();
   
    },
    createOverviewContent:function(editable){
    	var uType= config.getObjectById("profile-user-type-dropdown");
    	if(uType==null){
    		uType = new sap.ui.commons.DropdownBox({width:"200px",height:"30px" ,
        		id:"profile-user-type-dropdown",
        		editable:editable,
        		layoutData: new sap.ui.core.VariantLayoutData({
        			multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
        			                  	     new sap.ui.layout.form.GridElementData({hCells: "5"}),
        			                  	   new sap.ui.layout.GridData({span: "L1 M1 S12"})]
        	  }),

        	selectedKey:"{selectedModel>/userType/userId}"}).bindAggregation("items", "userTypes>/", new sap.ui.core.ListItem({
        	      text: "{userTypes>userTypeDescription}",
        	      key: "{userTypes>userId}",
        	     
        	      selectedItemId :"{selectedModel>/userType/userId}"
        	      
        	    })) .setModel(new sap.ui.model.json.JSONModel(sap.ui.getCore().getModel("userTypes")));
    	}
    	
    	var uGender = config.getObjectById("profile-gender-dropdown");
    	if(uGender==null){
    		uGender = new sap.ui.commons.DropdownBox({width:"200px",height:"30px" ,
        		id:"profile-gender-dropdown",
        		editable:editable,
        		layoutData: new sap.ui.core.VariantLayoutData({
        			multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
        			                  	     new sap.ui.layout.form.GridElementData({hCells: "5"}),
        			                  	   new sap.ui.layout.GridData({span: "L1 M1 S12"})]
        	  }),

        		selectedKey:"{selectedModel>/gender/genderId}"}).bindAggregation("items", "genders>/", new sap.ui.core.ListItem({
        	      text: "{genders>genderName}",
        	      key: "{genders>genderId}",
        	      selectedItemId :"{selectedModel>/gender/genderId}"
        	      
        	    })) .setModel(new sap.ui.model.json.JSONModel(sap.ui.getCore().getModel("genders")));
    	}
    	
    	var uLocation = config.getObjectById("profile-location-dropdown");
    	if(uLocation==null){
    		uLocation = new sap.ui.commons.DropdownBox({width:"200px",height:"30px" ,
        		id:"profile-location-dropdown",
        		editable:editable,
        		layoutData: new sap.ui.core.VariantLayoutData({
        			multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
        			                  	     new sap.ui.layout.form.GridElementData({hCells: "5"}),
        			                  	   new sap.ui.layout.GridData({span: "L1 M1 S12"})]
        	  }),

        		selectedKey:"{selectedModel>/location/locationId}"}).bindAggregation("items", "locations>/", new sap.ui.core.ListItem({
        	      text: "{locations>locationName}",
        	      key: "{locations>locationId}",
        	      selectedItemId :"{selectedModel>/location/locationId}"
        	      
        	    })) .setModel(new sap.ui.model.json.JSONModel(sap.ui.getCore().getModel("locations")));
    	}
    	
    	var uFunction = config.getObjectById("profile-function-dropdown");
    	if(uFunction==null){
    		uFunction =new sap.ui.commons.DropdownBox({width:"200px",height:"30px" ,
        		id:"profile-function-dropdown",
        		editable:editable,
        		layoutData: new sap.ui.core.VariantLayoutData({
        			multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
        			                  	     new sap.ui.layout.form.GridElementData({hCells: "5"}),
        			                  	   new sap.ui.layout.GridData({span: "L1 M1 S12"})]
        	  }),
        	  change:function(evt){
         		 
  				//toDropDownService
  				//debugger;
  				var _id = evt.getSource().mBindingInfos.selectedKey.binding.oValue;
  				 var bus = sap.ui.getCore().getEventBus();
       			bus.publish("nav", "toDropDownService", {
       				id : "jobTitles",
       				context:{id:_id,target:"profile-job-title-dropdown"}

       			});
  			}, 
        		selectedKey:"{selectedModel>/function/id}"}).bindAggregation("items", "functions>/", new sap.ui.core.ListItem({
        	      text: "{functions>description}",
        	      key: "{functions>id}",
        	      selectedItemId :"{selectedModel>/function/id}"
        	      
        	    })) .setModel(new sap.ui.model.json.JSONModel(sap.ui.getCore().getModel("functions")));
    	}
          var uAPSLevel = config.getObjectById("profile-aps-level-dropdown");
          if(uAPSLevel==null){
        	  uAPSLevel =new sap.ui.commons.DropdownBox({width:"200px",height:"30px" ,
          		id:"profile-aps-level-dropdown",
          		editable:editable,
          		layoutData: new sap.ui.core.VariantLayoutData({
          			multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
          			                  	     new sap.ui.layout.form.GridElementData({hCells: "5"}),
          			                  	   new sap.ui.layout.GridData({span: "L1 M1 S12"})]
          	  }),

          		selectedKey:"{selectedModel>/level/id}"}).bindAggregation("items", "levels>/", new sap.ui.core.ListItem({
          	      text: "{levels>description}",
          	      key: "{levels>id}",
          	      selectedItemId :"{selectedModel>/level/id}"
          	      
          	    })) .setModel(new sap.ui.model.json.JSONModel(sap.ui.getCore().getModel("levels")));
          }
    	
          var uPathway = config.getObjectById("profile-path-way-dropdown");
          if(uPathway==null){
        	  uPathway =new sap.ui.commons.DropdownBox({width:"200px",height:"30px" ,
          		id:"profile-path-way-dropdown",
          		editable:editable,
          		layoutData: new sap.ui.core.VariantLayoutData({
          			multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
          			                  	     new sap.ui.layout.form.GridElementData({hCells: "5"}),
          			                  	   new sap.ui.layout.GridData({span: "L1 M1 S12"})]
          	  }),

          		selectedKey:"{selectedModel>/learningPathway/id}"}).bindAggregation("items", "pathways>/", new sap.ui.core.ListItem({
          	      text: "{pathways>name}",
          	      key: "{pathways>id}",
          	      selectedItemId :"{selectedModel>/learningPathway/id}"
          	      
          	    })) .setModel(new sap.ui.model.json.JSONModel(sap.ui.getCore().getModel("pathways")));
          }
    	
    	var uJobTitle = config.getObjectById("profile-job-title-dropdown");
    	if(uJobTitle==null){
    		uJobTitle =new sap.ui.commons.DropdownBox({
    	    	id:"profile-job-title-dropdown",
    	    	width:"200px",height:"30px" ,
    	    	//editable:false,
    	    	editable:editable,
    	    	layoutData: new sap.ui.core.VariantLayoutData({
    	    		multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    	    		                  	     new sap.ui.layout.form.GridElementData({hCells: "5"}),
    	    		                  	   new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    	    	}),
    	    	change:function(evt){
    	    		//alert('dd');
    	      	  //debugger;
    	      		var _id = evt.getSource().mBindingInfos.selectedKey.binding.oValue;
    	  			 var bus = sap.ui.getCore().getEventBus();
    	  			bus.publish("nav", "toDropDownService", {
    	  				id : "levels",
    	  				context:{id:_id,target:"profile-levels-dropdown"}

    	  			});
    	  		},
    	    	selectedKey:"{selectedModel>/jobTitle/id}"}).bindAggregation("items", "jobTitles>/", new sap.ui.core.ListItem({
    	    	  text: "{jobTitles>description}",
    	    	  key: "{jobTitles>id}",
    	    	  selectedItemId :"{selectedModel>/jobTitile/id}"
    	    	  
    	    	})).setModel(new sap.ui.model.json.JSONModel(sap.ui.getCore().getModel("jobTitles")));
    	}
    
    	

    	var oLayout1 = new sap.ui.layout.form.GridLayout();

    	var oForm1 = new sap.ui.layout.form.Form({
    		width:"900px",
    		id:'userManagement-edit-user-basic-info-form',
    		layout: oLayout1,
    		formContainers: [
    			new sap.ui.layout.form.FormContainer("F1C1",{
    				title: "Basic Information",
    				formElements: [
    				               new sap.ui.layout.form.FormElement({
    				            	   label: new sap.ui.commons.Label({text:"User Id :",
    				            		   enabled:false,
    				            		   layoutData: new sap.ui.core.VariantLayoutData({
    				            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    				            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    				            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    				            		   			})
    				            	   }),
    				            	   	fields: [ new sap.ui.commons.TextField({value: "{selectedModel>/loginId}",
    				            	   		editable:false,
    				            	   				layoutData: new sap.ui.core.VariantLayoutData({
    				            	   					multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 4}),
    				            	   					                     new sap.ui.layout.form.GridElementData({hCells: "5"})]
    				            	   				})
    				            	   	})
    				            	],
    				            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                        }),
    	                        new sap.ui.layout.form.FormElement({
    				            	   label: new sap.ui.commons.Label({text:"User Type :",
    				            		   layoutData: new sap.ui.core.VariantLayoutData({
    				            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    				            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    				            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    				            		   			})
    				            	   }),
    				            	   	fields: [ uType],
    				            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                        }),
    	                        new sap.ui.layout.form.FormElement({
    				            	   label: new sap.ui.commons.Label({text:"Gender :",
    				            		   layoutData: new sap.ui.core.VariantLayoutData({
    				            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    				            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    				            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    				            		   			})
    				            	   }),
    				            	   	fields: [ uGender],
    				            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                        }),
    	                        new sap.ui.layout.form.FormElement({
    				            	   label: new sap.ui.commons.Label({text:"Location :",
    				            		   layoutData: new sap.ui.core.VariantLayoutData({
    				            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    				            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    				            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    				            		   			})
    				            	   }),
    				            	   	fields: [ uLocation	],
    				            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                        }),
    	                        new sap.ui.layout.form.FormElement({
    				            	   label: new sap.ui.commons.Label({text:"Function :",
    				            		   layoutData: new sap.ui.core.VariantLayoutData({
    				            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    				            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    				            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    				            		   			})
    				            	   }),
    				            	   	fields: [ uFunction	],
    				            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                        }),

    	                        new sap.ui.layout.form.FormElement({
    				            	   label: new sap.ui.commons.Label({text:"Job Title :",
    				            		   layoutData: new sap.ui.core.VariantLayoutData({
    				            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    				            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    				            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    				            		   			})
    				            	   }),
    				            	   	fields: [ uJobTitle],
    				            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                        }),
    	                        
    	                        new sap.ui.layout.form.FormElement({
    				            	   label: new sap.ui.commons.Label({text:"APS Level :",
    				            		   layoutData: new sap.ui.core.VariantLayoutData({
    				            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    				            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    				            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    				            		   			})
    				            	   }),
    				            	   	fields: [ uAPSLevel],
    				            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                        }),
    	                      new sap.ui.layout.form.FormElement({
    			            	   label: new sap.ui.commons.Label({text:"Learning Pathway :",
    			            		   layoutData: new sap.ui.core.VariantLayoutData({
    			            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    			            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    			            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    			            		   			})
    			            	   }),
    			            	   	fields: [ uPathway],
    			            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                 }),
    	                 new sap.ui.layout.form.FormElement({
			            	   label: new sap.ui.commons.Label({text:"Locked :",
			            		   enabled:false,
			            		   layoutData: new sap.ui.core.VariantLayoutData({
			            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
			            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
			            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
			            		   			})
			            	   }),
			            	   	fields: [ new sap.ui.commons.CheckBox({checked: "{selectedModel>/mylock}",
			            	   		editable:editable,
			            	   				layoutData: new sap.ui.core.VariantLayoutData({
			            	   					multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 4}),
			            	   					                     new sap.ui.layout.form.GridElementData({hCells: "5"})]
			            	   				})
			            	   	})
			            	],
			            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
                      }),
 	                 new sap.ui.layout.form.FormElement({
		            	   label: new sap.ui.commons.Label({text:"Locked On :",
		            		   enabled:false,
		            		   layoutData: new sap.ui.core.VariantLayoutData({
		            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
		            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
		            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
		            		   			})
		            	   }),
		            	   	fields: [ new sap.ui.commons.TextField({value: "{selectedModel>/mylockedOn}",
		            	   		editable:false,
		            	   				layoutData: new sap.ui.core.VariantLayoutData({
		            	   					multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 4}),
		            	   					                     new sap.ui.layout.form.GridElementData({hCells: "5"})]
		            	   				})
		            	   	})
		            	],
		            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
                }),
                new sap.ui.layout.form.FormElement({
	            	   label: new sap.ui.commons.Label({text:"",
	            		   enabled:false,
	            		   layoutData: new sap.ui.core.VariantLayoutData({
	            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
	            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
	            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
	            		   			})
	            	   }),
	            
	            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
         })
    	                        
    	  ]

    	}),
    	        
    	]		
    			
    	});
    	
    	return oForm1;
    },
    doAction:function(id){
    	if(id=="edit"){
    		state=true;
    		var bus = sap.ui.getCore().getEventBus();
			bus.publish("nav", "fromUserManagement", {
				id : "edit",
			});
    		
    		
    	}else if(id=="cancel"){
    		state= false,
    		config.getObjectById(constant._USER_MANAGEMENT_CANCEL_BUTTON_ID).setEnabled(false);
    		config.getObjectById(constant._USER_MANAGEMENT_SAVE_BUTTON_ID).setEnabled(false);
    		var oTI = config.getObjectById(constant._USER_MANAGEMENT_THINGINSPECTOR_ID);
    		oTI.destroyFacets();
    		oTI.destroyFacetContent() ;
    		var facts =  new sap.ui.ux3.NavigationItem({key:"overview", text:"Overview "});;
    		oTI.addFacet(facts);
    		var facts =  new sap.ui.ux3.NavigationItem({key:"change-password", text:"Change Password"});
    		oTI.addFacet(facts);
    		var facts =  new sap.ui.ux3.NavigationItem({key:"role-assignment", text:"Role Assignment"});
    		oTI.addFacet(facts);
    		oTI.addFacetContent(this.createThingGroup("overview"));
    	}
    	else if(id=="save"){
    		var bus = sap.ui.getCore().getEventBus();
    		switch(navigationBarState){
    		case 0:
    			
    			
				bus.publish("nav", "fromUserManagement", {
					id:'edit-save-profile'
				});
    			break;
    		case 1:
    			if(isValidPassword()){
    				
    				bus.publish("nav", "fromUserManagement", {
    					id:'edit-save-password'
    				});
    			}
    			
    			break;
    		case 2:
    			if(isValidRole()){
    				bus.publish("nav", "toRole", {
    					context:{action:"assign"}
    				});
    			}
    			break;
    		
    		}

    	}
    	else{
    		sap.ui.commons.MessageBox.alert("Password don not match!\n Re-enter your password");
			return;
    	}
    },
    createThingInspector:function(oController){
    	var oActionEdit = config.getObjectById(constant._USER_MANAGEMENT_EDIT_BUTTON_ID);
    	if(oActionEdit==null){
    		oActionEdit = new sap.ui.ux3.ThingAction({
    			id : constant._USER_MANAGEMENT_EDIT_BUTTON_ID,
    			text : "Edit",
    			tooltip: "Edit",
    		});
    	}
    	var oActionCancel = config.getObjectById(constant._USER_MANAGEMENT_CANCEL_BUTTON_ID);
    	if(oActionCancel==null){
    		oActionCancel = new sap.ui.ux3.ThingAction({
    			id : constant._USER_MANAGEMENT_CANCEL_BUTTON_ID,
    			text : "Cancel",
    			tooltip: "Cancel",
    			enabled:false,
    		});
    	}
    	oActionCancel.setEnabled(false);
    
		var oActionSave = config.getObjectById(constant._USER_MANAGEMENT_SAVE_BUTTON_ID);
		if(oActionSave==null){
			oActionSave = new sap.ui.ux3.ThingAction({
				id : constant._USER_MANAGEMENT_SAVE_BUTTON_ID,
				text : "Save",
				tooltip: "Save",
				enabled:false,
			});
		}
		oActionSave.setEnabled(false);
	    var oTI = config.getObjectById(config.getUserManagementThingInspactorId());
	    if(oTI==null){
	    	oTI = new sap.ui.ux3.ThingInspector(
					{
						id:config.getUserManagementThingInspactorId(),
						secondTitle : "{selectedModel>loginId}",
						type : "{selectedModel>/userType/userTypeName}",
						icon : "sap-icon://person-placeholder",
						openButtonVisible:false,
						updateActionEnabled : false,
						facets : [ new sap.ui.ux3.NavigationItem({
							key : "overview",
							text : "Overview"
						}),new sap.ui.ux3.NavigationItem({key:"change-password", text:"Change Password"}),
						 new sap.ui.ux3.NavigationItem({key:"role-assignment", text:"Role Assignment"})],
						facetSelected : function(oEvent) {
							//oTI.removeAllFacetContent();
							//oTI.removeAllActions();
							switch (oEvent.getParameter("key")) {
							case "overview":
								navigationBarState=0;
								if(state){
									config.getObjectById("userManagement-edit-user-basic-info-form").setVisible(true);
									config.getObjectById("userManagement-edit-user-role-assignment").setVisible(false);
									config.getObjectById("userManagement-edit-user-password").setVisible(false);
								}else{
									config.getObjectById("userManagement-readOnly-user-basic-info-form").setVisible(true);
									config.getObjectById("userManagement-readOnly-user-role-assignment").setVisible(false);
									config.getObjectById("userManagement-readOnly-user-password").setVisible(false);
								}
								
								break;
							case "change-password":
								navigationBarState = 1;
								if(state){
									config.getObjectById("userManagement-edit-user-basic-info-form").setVisible(false);
									config.getObjectById("userManagement-edit-user-role-assignment").setVisible(false);
									config.getObjectById("userManagement-edit-user-password").setVisible(true);
								}else{
									config.getObjectById("userManagement-readOnly-user-basic-info-form").setVisible(false);
									config.getObjectById("userManagement-readOnly-user-role-assignment").setVisible(false);
									config.getObjectById("userManagement-readOnly-user-password").setVisible(true);
								}
								
								
								break;
							case "role-assignment":
								navigationBarState=2;
								var bus = sap.ui.getCore().getEventBus();
								if(state){
									if(config.getObjectById("userManagement-edit-user-re-enter-password").getValue()==config.getObjectById("userManagement-edit-user-enter-password").getValue()){
										
										bus.publish("nav", "toRole", {
											context:{action:'get',data:[
											                            {id:'userManagement-edit-user-role-assignment',visible:true},
											                            {id:'userManagement-edit-user-password',visible:false},
											                            {id:'userManagement-edit-user-basic-info-form',visible:false}]}
										});
									}else{
										sap.ui.commons.MessageBox.alert("Password don not match!\n Re-enter your password");
										return;
									}
								}else{
									bus.publish("nav", "toRole", {
										context:{action:'get',data:[
										                            {id:'userManagement-readOnly-user-role-assignment',visible:true},
										                            {id:'userManagement-readOnly-user-password',visible:false},
										                            {id:'userManagement-readOnly-user-basic-info-form',visible:false}]}
									});
								}
								
								break;
							}
						},
						actions : [oActionEdit,oActionSave,oActionCancel],
						headerContent : [ new sap.ui.ux3.ThingGroup(
								{
									title : "About",
									content : [ this.createDataForm() ]
								}) ],
						openNew : function(oEvent) {
						},

						actionSelected : function(oEvent) {
							var oAction = oEvent
									.getParameter("action");
							var sActionId = oAction.getId();
							//alert(sActionId);
							var idx = sActionId.lastIndexOf("-");
							if (idx >= 0) {
								sActionId = sActionId.substring(idx + 1);
								oController.doAction(sActionId);
						  }
						}
					});
			
				function handler(oEvent) {
				// alert("Event '" + oEvent.getId() + "'
				// triggered");
				}
	    }
			
    },
    createDataForm:function(){

		var c = sap.ui.commons;
		return  new c.form.Form(
		{
			width : "300px",
			layout : new c.form.GridLayout(),
			formContainers : [ new c.form.FormContainer(
					{
						formElements : [
								new c.form.FormElement({
										label : new c.Label({
												text : "ID ",
												layoutData : new c.form.GridElementData({
													hCells : "6"
													})
										}),
										fields : [ new c.TextField({
														value : "{selectedModel>/loginId}",
														editable : false
										}) ]
								}),
						new c.form.FormElement({
							label : new c.Label({
								text : "Gender",
								layoutData : new c.form.GridElementData({
									hCells : "6"
								})
							}),
							fields : [ new c.TextField({
								value : "{selectedModel>/gender/genderName}",
								editable : false
							}) ]
						}),
						new c.form.FormElement({
							label : new c.Label({
								text : "Function ID",
								layoutData : new c.form.GridElementData({
									hCells : "6"
								})
							}),
							fields : [ new c.TextField({
								value : "{selectedModel>/function/id}",
								editable : false
							}) ]
						}),
						new c.form.FormElement({
							label : new c.Label({
								text : "Occupation ID",
								layoutData : new c.form.GridElementData({
										hCells : "6"
								})
							}),
							fields : [ new c.TextField({
									value : "{selectedModel>/jobTitle/id}",
									editable : false
									}) ]
							}),
						new c.form.FormElement({
								label : new c.Label({
										text : "Level ID",
										layoutData : new c.form.GridElementData({
											hCells : "6"
										})
								}),
								fields : [ new c.TextField({
									value : "{selectedModel>/level/id}",
									editable : false
							    }) ]
								}) ]
					}) ]
		});
    },
    createReadOnlyForm:function(){
    	var oLayout1 = new sap.ui.layout.form.GridLayout();

    	var oForm1 = new sap.ui.layout.form.Form({
    		width:"900px",
    		//title: new sap.ui.core.Title({text: "User Detail Profile", tooltip: "User Detail Profile"}),
    		layout: oLayout1,
    		id:'userManagement-readOnly-user-basic-info-form',
    		formContainers: [
    			new sap.ui.layout.form.FormContainer("F1C1",{
    				title: "Basic Information",
    				formElements: [
    				               new sap.ui.layout.form.FormElement({
    				            	   label: new sap.ui.commons.Label({text:"User Id :",
    				            		   enabled:false,
    				            		   layoutData: new sap.ui.core.VariantLayoutData({
    				            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    				            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    				            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    				            		   			})
    				            	   }),
    				            	   	fields: [ new sap.ui.commons.TextField({value: "{selectedModel>/loginId}",
    				            	   		editable:false,
    				            	   				layoutData: new sap.ui.core.VariantLayoutData({
    				            	   					multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 4}),
    				            	   					                     new sap.ui.layout.form.GridElementData({hCells: "4"})]
    				            	   				})
    				            	   	})
    				            	],
    				            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                        }),
    	                        new sap.ui.layout.form.FormElement({
 				            	   label: new sap.ui.commons.Label({text:"User Type :",
 				            		   enabled:false,
 				            		   layoutData: new sap.ui.core.VariantLayoutData({
 				            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
 				            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
 				            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
 				            		   			})
 				            	   }),
 				            	   	fields: [ new sap.ui.commons.TextField({value: "{selectedModel>/userType/userTypeDescription}",
 				            	   		editable:false,
 				            	   				layoutData: new sap.ui.core.VariantLayoutData({
 				            	   					multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 4}),
 				            	   					                     new sap.ui.layout.form.GridElementData({hCells: "4"})]
 				            	   				})
 				            	   	})
 				            	],
 				            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
 	                        }),
    	                        new sap.ui.layout.form.FormElement({
    				            	   label: new sap.ui.commons.Label({text:"Gender :",
    				            		   layoutData: new sap.ui.core.VariantLayoutData({
    				            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    				            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    				            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    				            		   			})
    				            	   }),
    				            	   	fields: [ new sap.ui.commons.TextField({value: "{selectedModel>/gender/genderName}",
    				            	   		editable:false,
			            	   				layoutData: new sap.ui.core.VariantLayoutData({
			            	   					multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 4}),
			            	   					                     new sap.ui.layout.form.GridElementData({hCells: "4"})]
			            	   				})
			            	   	})],
    				            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                        }),
    	                        new sap.ui.layout.form.FormElement({
    				            	   label: new sap.ui.commons.Label({text:"Location :",
    				            		   layoutData: new sap.ui.core.VariantLayoutData({
    				            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    				            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    				            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    				            		   			})
    				            	   }),
    				            	   	fields: [ new sap.ui.commons.TextField({value: "{selectedModel>/location/locationName}",
    				            	   		editable:false,
			            	   				layoutData: new sap.ui.core.VariantLayoutData({
			            	   					multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 4}),
			            	   					                     new sap.ui.layout.form.GridElementData({hCells: "4"})]
			            	   				})
			            	   	})	],
    				            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                        }),
    	                        new sap.ui.layout.form.FormElement({
    				            	   label: new sap.ui.commons.Label({text:"Function :",
    				            		   layoutData: new sap.ui.core.VariantLayoutData({
    				            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    				            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    				            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    				            		   			})
    				            	   }),
    				            	   	fields: [ new sap.ui.commons.TextField({value: "{selectedModel>/function/description}",
    				            	   		editable:false,
			            	   				layoutData: new sap.ui.core.VariantLayoutData({
			            	   					multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 4}),
			            	   					                     new sap.ui.layout.form.GridElementData({hCells: "4"})]
			            	   				})
			            	   	})	],
    				            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                        }),

    	                        new sap.ui.layout.form.FormElement({
    				            	   label: new sap.ui.commons.Label({text:"Job Title :",
    				            		   layoutData: new sap.ui.core.VariantLayoutData({
    				            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    				            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    				            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    				            		   			})
    				            	   }),
    				            	   	fields: [ new sap.ui.commons.TextField({value: "{selectedModel>/jobTitle/name}",
    				            	   		editable:false,
			            	   				layoutData: new sap.ui.core.VariantLayoutData({
			            	   					multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 4}),
			            	   					                     new sap.ui.layout.form.GridElementData({hCells: "4"})]
			            	   				})
			            	   	})],
    				            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                        }),
    	                        
    	                        new sap.ui.layout.form.FormElement({
    				            	   label: new sap.ui.commons.Label({text:"APS Level :",
    				            		   layoutData: new sap.ui.core.VariantLayoutData({
    				            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    				            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    				            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    				            		   			})
    				            	   }),
    				            	   	fields: [ new sap.ui.commons.TextField({value: "{selectedModel>/level/description}",
    				            	   		editable:false,
			            	   				layoutData: new sap.ui.core.VariantLayoutData({
			            	   					multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 4}),
			            	   					                     new sap.ui.layout.form.GridElementData({hCells: "4"})]
			            	   				})
			            	   	})],
    				            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                        }),
    	                      new sap.ui.layout.form.FormElement({
    			            	   label: new sap.ui.commons.Label({text:"Learning Pathway :",
    			            		   layoutData: new sap.ui.core.VariantLayoutData({
    			            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    			            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    			            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    			            		   			})
    			            	   }),
    			            	   	fields: [ new sap.ui.commons.TextField({value: "{selectedModel>/learningPathway/name}",
				            	   		editable:false,
		            	   				layoutData: new sap.ui.core.VariantLayoutData({
		            	   					multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 4}),
		            	   					                     new sap.ui.layout.form.GridElementData({hCells: "4"})]
		            	   				})
		            	   	})],
    			            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                 }),
    	                 new sap.ui.layout.form.FormElement({
			            	   label: new sap.ui.commons.Label({text:"Locked :",
			            		   enabled:false,
			            		   layoutData: new sap.ui.core.VariantLayoutData({
			            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
			            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
			            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
			            		   			})
			            	   }),
			            	   	fields: [ new sap.ui.commons.CheckBox({checked: "{selectedModel>/mylock}",
			            	   		enabled:false,
			            	   				layoutData: new sap.ui.core.VariantLayoutData({
			            	   					multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 4}),
			            	   					                     new sap.ui.layout.form.GridElementData({hCells: "4"})]
			            	   				})
			            	   	})
			            	],
			            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
                      }),
 	                 new sap.ui.layout.form.FormElement({
		            	   label: new sap.ui.commons.Label({text:"Locked On :",
		            		   enabled:false,
		            		   layoutData: new sap.ui.core.VariantLayoutData({
		            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
		            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
		            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
		            		   			})
		            	   }),
		            	   	fields: [ new sap.ui.commons.TextField({value: "{selectedModel>/mylockedOn}",
		            	   		editable:false,
		            	   				layoutData: new sap.ui.core.VariantLayoutData({
		            	   					multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 4}),
		            	   					                     new sap.ui.layout.form.GridElementData({hCells: "4"})]
		            	   				})
		            	   	})
		            	],
		            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
                }),
                new sap.ui.layout.form.FormElement({
	            	   label: new sap.ui.commons.Label({text:"",
	            		   enabled:false,
	            		   layoutData: new sap.ui.core.VariantLayoutData({
	            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
	            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
	            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
	            		   			})
	            	   }),
	            
	            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
         })
    	                        
    	  ]

    	}),
    	        
    	]		
    			
    	}).addStyleClass("userForm");
    	
    	return oForm1;
		
    },
    passwordAssignmentForm:function(){
    	var pwdModel = new sap.ui.model.json.JSONModel();
    	pwdModel.setDefaultBindingMode("TwoWay");
    	 pwdModel.setData({"password":null,"rePassword":null,"loginId":sap.ui.getCore().getModel("selectedModel").oData.loginId}, false);
		 sap.ui.getCore().setModel(pwdModel,"passwordAssignmentModel");
    	var oLayout2 = new sap.ui.layout.form.GridLayout();
      	 return new sap.ui.layout.form.Form({
    		width:'900px',
    		height:'70%',
    		layout: oLayout2,
    		id:'userManagement-edit-user-password',
    		visible:false,
    		formContainers: [
    			new sap.ui.layout.form.FormContainer({
    				title: " Password Assignment",
    				formElements: [
    				               new sap.ui.layout.form.FormElement({
    				            	   label: new sap.ui.commons.Label({text:"Enter new password:",
    				            		   enabled:true,
    				            		   layoutData: new sap.ui.core.VariantLayoutData({
    				            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    				            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    				            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    				            		   			})
    				            	   }),
    				            	   	fields: [ new sap.ui.commons.PasswordField({value: "{passwordAssignmentModel>/password}",
    				            	   		id:'userManagement-edit-user-enter-password',
    				            	   		placeholder:'Enter password',
    				            	   				layoutData: new sap.ui.core.VariantLayoutData({
    				            	   					multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 4}),
    				            	   					                     new sap.ui.layout.form.GridElementData({hCells: "4"})]
    				            	   				})
    				            	   	})
    				            	],
    				            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                        }),
    	                        new sap.ui.layout.form.FormElement({
 				            	   label: new sap.ui.commons.Label({text:"Re -Enter password:",
 				            		   enabled: true,
 				            		   layoutData: new sap.ui.core.VariantLayoutData({
 				            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
 				            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
 				            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
 				            		   			})
 				            	   }),
 				            	   	fields: [ new sap.ui.commons.PasswordField({value: "{passwordAssignmentModel>/rePassword}",
 				            	   	placeholder:'Re-Enter password',
 				            	   id:'userManagement-edit-user-re-enter-password',
 				            	   				layoutData: new sap.ui.core.VariantLayoutData({
 				            	   					multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 4}),
 				            	   					                     new sap.ui.layout.form.GridElementData({hCells: "4"})]
 				            	   				})
 				            	   	})
 				            	],
 				            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
 	                        })
    				        ]
    			})
    			]
		});                        
    },
    roleAssignmentForm:function(){
    	var roleModel = new sap.ui.model.json.JSONModel();
		 roleModel.setDefaultBindingMode("TwoWay");
		// roleModel.setData({"id":-1,"name":"","loginId":sap.ui.getCore().getModel("selectedModel").oData.loginId}, false);
		 sap.ui.getCore().setModel(roleModel,"roleModel");
		 
		var roleType= config.getObjectById("create-create-profile-role-type-dropdown");
   	if(roleType==null){
   		roleType = new sap.ui.commons.DropdownBox({width:"200px",height:"30px" ,
       		id:"create-create-profile-role-type-dropdown",
       		layoutData: new sap.ui.core.VariantLayoutData({
       			multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
       			                  	     new sap.ui.layout.form.GridElementData({hCells: "4"}),
       			                  	   new sap.ui.layout.GridData({span: "L1 M1 S12"})]
       	  }),
       	selectedKey:"{roleModel>/role/id}"
       		
   		}).bindAggregation("items", "roleModel>/roles/", new sap.ui.core.ListItem({
       	      text: "{roleModel>description}",
       	      key: "{roleModel>id}",
       	     
       	      selectedItemId :"{roleModel>/role/id}"
       	      
       	    })) .setModel(new sap.ui.model.json.JSONModel(sap.ui.getCore().getModel("roleModel")));
   	}
    	var oLayout3 = new sap.ui.layout.form.GridLayout();
    	return new sap.ui.layout.form.Form({
    		id:'userManagement-edit-user-role-assignment',
    		width:'900px',
    		height:'70%',
    		layout: oLayout3,
    		visible:false,
    		formContainers: [
    			new sap.ui.layout.form.FormContainer({
    				title: " Assign Role To User",
    				formElements: [
    				               new sap.ui.layout.form.FormElement({
    				            	   label: new sap.ui.commons.Label({text:"Select role  ",
    				            		   enabled:true,
    				            		   layoutData: new sap.ui.core.VariantLayoutData({
    				            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    				            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    				            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    				            		   			})
    				            	   }),
    				            	   	fields: [roleType
    				            	],
    				            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                        })
    				        ]
    			})
    			]
		});
    },
    passwordAssignmentReadOnlyForm:function(){
    	var oLayout2 = new sap.ui.layout.form.GridLayout();
    	var _password= sap.ui.getCore().getModel("selectedModel").oData.password;
    	 return new sap.ui.layout.form.Form({
    		width:'900px',
    		height:'70%',
    		layout: oLayout2,
    		id:'userManagement-readOnly-user-password',
    		visible:false,
    		formContainers: [
    			new sap.ui.layout.form.FormContainer({
    				title: " Password Assignment",
    				formElements: [
    				               new sap.ui.layout.form.FormElement({
    				            	   label: new sap.ui.commons.Label({text:"Enter new password:",
    				            		   enabled:true,
    				            		   layoutData: new sap.ui.core.VariantLayoutData({
    				            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    				            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    				            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    				            		   			})
    				            	   }),
    				            	   	fields: [ new sap.ui.commons.PasswordField({value: "{selectedModel>/password}",
    				            	   		//id:'userManagement-edit-user-enter-password',
    				            	   		editable:false,
    				            	   		placeholder:'Enter password',
    				            	   				layoutData: new sap.ui.core.VariantLayoutData({
    				            	   					multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 4}),
    				            	   					                     new sap.ui.layout.form.GridElementData({hCells: "4"})]
    				            	   				})
    				            	   	})
    				            	],
    				            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                        }),
    	                        new sap.ui.layout.form.FormElement({
 				            	   label: new sap.ui.commons.Label({text:"Re -Enter password:",
 				            		   enabled: true,
 				            		   layoutData: new sap.ui.core.VariantLayoutData({
 				            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
 				            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
 				            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
 				            		   			})
 				            	   }),
 				            	   	fields: [ new sap.ui.commons.PasswordField({value: _password,
 				            	   	placeholder:'Re-Enter password',
 				            	   	editable:false,
 				            	  // id:'userManagement-edit-user-re-enter-password',
 				            	   				layoutData: new sap.ui.core.VariantLayoutData({
 				            	   					multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 4}),
 				            	   					                     new sap.ui.layout.form.GridElementData({hCells: "4"})]
 				            	   				})
 				            	   	})
 				            	],
 				            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
 	                        })
    				        ]
    			})
    			]
		});                        
    },
    roleAssignmentReadOnlyForm:function(){
    	var roleModel = new sap.ui.model.json.JSONModel();
		 roleModel.setDefaultBindingMode("TwoWay");
		 sap.ui.getCore().setModel(roleModel,"roleModel");
		 
		var roleType= config.getObjectById("userManagement-readOnly-role-type-dropdown");
   	if(roleType==null){
   		roleType = new sap.ui.commons.DropdownBox({width:"200px",height:"30px" ,
       		id:"userManagement-readOnly-role-type-dropdown",
       		layoutData: new sap.ui.core.VariantLayoutData({
       			multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
       			                  	     new sap.ui.layout.form.GridElementData({hCells: "4"}),
       			                  	   new sap.ui.layout.GridData({span: "L1 M1 S12"})]
       	  }),
       	selectedKey:"{roleModel>/role/id}",
       	editable:false,	
   		}).bindAggregation("items", "roleModel>/roles/", new sap.ui.core.ListItem({
       	      text: "{roleModel>description}",
       		placeholder:'Please Select',
       	      key: "{roleModel>id}",
       	     
       	      selectedItemId :"{roleModel>/role/id}"
       	      
       	    })) .setModel(new sap.ui.model.json.JSONModel(sap.ui.getCore().getModel("roleModel")));
   	}
    	var oLayout3 = new sap.ui.layout.form.GridLayout();
    	return new sap.ui.layout.form.Form({
    		id:'userManagement-readOnly-user-role-assignment',
    		width:'900px',
    		height:'70%',
    		layout: oLayout3,
    		visible:false,
    		formContainers: [
    			new sap.ui.layout.form.FormContainer({
    				title: " Assign Role To User",
    				formElements: [
    				               new sap.ui.layout.form.FormElement({
    				            	   label: new sap.ui.commons.Label({text:"Select role  ",
    				            		   enabled:true,
    				            		   layoutData: new sap.ui.core.VariantLayoutData({
    				            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    				            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    				            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    				            		   			})
    				            	   }),
    				            	   	fields: [roleType
    				            	],
    				            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                        })
    				        ]
    			})
    			]
		});
    },
    
});

function isValidPassword(){
	var pModel = sap.ui.getCore().getModel("passwordAssignmentModel").oData;
	if(pModel==null){
		return false;
	}
	if((pModel.password==null || pModel.rePassword==null)){
		sap.ui.commons.MessageBox.alert("Password don not match!\n Re-enter your password\n You must have assign the initial password to user");
		return false;
	};
	if(pModel.password=="" || pModel.rePassword==""){
		sap.ui.commons.MessageBox.alert("Password don not match!\n Re-enter your password\n You must have assign the initial password to user");
		return false;
	}
	return true;
}
function isValidRole(){
	var roleModel = sap.ui.getCore().getModel("roleModel").oData;
	if(roleModel.id==-1){
		sap.ui.commons.MessageBox.alert("You have must assign a role to user!");
		return false;
	}
	return true;
}