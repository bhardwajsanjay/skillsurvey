sap.ui.jsview("survey-template.User", {

	/** Specifies the Controller belonging to this View. 
	* In the case that it is not implemented, or that "null" is returned, this View does not have a Controller.
	* @memberOf survey-template.User
	*/ 
	getControllerName : function() {
		return "survey-template.User";
	},

	/** Is initially called once after the Controller has been instantiated. It is the place where the UI is constructed. 
	* Since the Controller is given to this method, its event handlers can be attached right away. 
	* @memberOf survey-template.User
	*/ 
	createContent : function(oController) {
		
		// defining role dropdown
		 var roleModel = new sap.ui.model.json.JSONModel();
		 roleModel.setDefaultBindingMode("TwoWay");
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
        	  change:function(evt){
        		  var _id = config.getObjectById(evt.getSource().getId()).getSelectedKey();
        		  if(_id!=null){
        			  sap.ui.getCore().getModel("roleModel").oData.role.id=_id;
        		  }
  			}, 
        	selectedKey:"{roleModel>/role/id}"
        		
    		}).bindAggregation("items", "roleModel>/roles/", new sap.ui.core.ListItem({
        	      text: "{roleModel>description}",
        	      key: "{roleModel>id}",
        	     
        	      selectedItemId :"{roleModel>/role/id}"
        	      
        	    })) .setModel(new sap.ui.model.json.JSONModel(sap.ui.getCore().getModel("roleModel")));
    	}
		
		// end of role dropdown
		
		
		
		var uType= config.getObjectById("create-create-profile-user-type-dropdown");
    	if(uType==null){
    		uType = new sap.ui.commons.DropdownBox({width:"200px",height:"30px" ,
        		id:"create-create-profile-user-type-dropdown",
        		 
        		layoutData: new sap.ui.core.VariantLayoutData({
        			multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
        			                  	     new sap.ui.layout.form.GridElementData({hCells: "4"}),
        			                  	   new sap.ui.layout.GridData({span: "L1 M1 S12"})]
        	  }),
        	selectedKey:"{blankProfileModel>/userType/userId}"
        		
    		}).bindAggregation("items", "blankProfileModel>/userTypes/", new sap.ui.core.ListItem({
        	      text: "{blankProfileModel>userTypeDescription}",
        	      key: "{blankProfileModel>userId}",
        	     
        	      selectedItemId :"{blankProfileModel>/userType/userId}"
        	      
        	    })) .setModel(new sap.ui.model.json.JSONModel(sap.ui.getCore().getModel("blankProfileModel")));
    	}
    	
    	var uGender = config.getObjectById("create-create-profile-gender-dropdown");
    	if(uGender==null){
    		uGender = new sap.ui.commons.DropdownBox({width:"200px",height:"30px" ,
        		id:"create-create-profile-gender-dropdown",
        		 
        		layoutData: new sap.ui.core.VariantLayoutData({
        			multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
        			                  	     new sap.ui.layout.form.GridElementData({hCells: "4"}),
        			                  	   new sap.ui.layout.GridData({span: "L1 M1 S12"})]
        	  }),
        		selectedKey:"{blankProfileModel>/gender/genderId}"}).bindAggregation("items", "blankProfileModel>/genders/", new sap.ui.core.ListItem({
        	      text: "{blankProfileModel>genderName}",
        	      key: "{blankProfileModel>genderId}",
        	      selectedItemId :"{blankProfileModel>/gender/genderId}"
        	      
        	    })) .setModel(new sap.ui.model.json.JSONModel(sap.ui.getCore().getModel("blankProfileModel")));
    	}
    	
    	var uLocation = config.getObjectById("create-create-profile-location-dropdown");
    	if(uLocation==null){
    		uLocation = new sap.ui.commons.DropdownBox({width:"200px",height:"30px" ,
        		id:"create-create-profile-location-dropdown",
        		 
        		layoutData: new sap.ui.core.VariantLayoutData({
        			multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
        			                  	     new sap.ui.layout.form.GridElementData({hCells: "4"}),
        			                  	   new sap.ui.layout.GridData({span: "L1 M1 S12"})]
        	  }),
        		selectedKey:"{blankProfileModel>/location/locationId}"}).bindAggregation("items", "blankProfileModel>/locations/", new sap.ui.core.ListItem({
        	      text: "{blankProfileModel>locationName}",
        	      key: "{blankProfileModel>locationId}",
        	      selectedItemId :"{blankProfileModel>/location/locationId}"
        	      
        	    })) .setModel(new sap.ui.model.json.JSONModel(sap.ui.getCore().getModel("blankProfileModel")));
    	}
    	//function dropdown
    	var uFunction = config.getObjectById("create-create-profile-function-dropdown");
    	if(uFunction==null){
    		uFunction =new sap.ui.commons.DropdownBox({width:"200px",height:"30px" ,
        		id:"user-management-create-function-dropdown",
        		 
        		layoutData: new sap.ui.core.VariantLayoutData({
        			multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
        			                  	     new sap.ui.layout.form.GridElementData({hCells: "4"}),
        			                  	   new sap.ui.layout.GridData({span: "L1 M1 S12"})]
        	  }),
        	  change:function(evt){
        		 
    				//toDropDownService
    				//debugger;
    				var _id = evt.getSource().mBindingInfos.selectedKey.binding.oValue;
    				 var bus = sap.ui.getCore().getEventBus();
         			bus.publish("nav", "toDropDownService", {
         				id : "jobtitles",
         				context:{id:_id,target:"user-management-create-jobtitles-dropdown"}

         			});
    			}, 
        		selectedKey:"{blankProfileModel>/function/id}"}).bindAggregation("items", "blankProfileModel>/functions/", new sap.ui.core.ListItem({
        	      text: "{blankProfileModel>description}",
        	      key: "{blankProfileModel>id}",
        	      selectedItemId :"{blankProfileModel>/function/id}"
        	     
        	      
        	    })) .setModel(new sap.ui.model.json.JSONModel(sap.ui.getCore().getModel("blankProfileModel")));
    	}
    	
    	//end of function dropdown
          var uAPSLevel = config.getObjectById("create-create-profile-aps-level-dropdown");
          if(uAPSLevel==null){
        	  uAPSLevel =new sap.ui.commons.DropdownBox({width:"200px",height:"30px" ,
          		id:"user-management-create-aps-level-dropdown",
          		enabled:false,
          		layoutData: new sap.ui.core.VariantLayoutData({
          			multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
          			                  	     new sap.ui.layout.form.GridElementData({hCells: "4"}),
          			                  	   new sap.ui.layout.GridData({span: "L1 M1 S12"})]
          	  }),
          		selectedKey:"{blankProfileModel>/level/id}"}).bindAggregation("items", "levels>/", new sap.ui.core.ListItem({
          	      text: "{levels>description}",
          	      key: "{levels>id}",
          	      selectedItemId :"{blankProfileModel>/level/id}"
          	      
          	    })) .setModel(new sap.ui.model.json.JSONModel(sap.ui.getCore().getModel("levels")));
          }
    	
          var uPathway = config.getObjectById("create-create-profile-path-way-dropdown");
          if(uPathway==null){
        	  uPathway =new sap.ui.commons.DropdownBox({width:"200px",height:"30px" ,
          		id:"create-create-profile-path-way-dropdown",
          		 
          		layoutData: new sap.ui.core.VariantLayoutData({
          			multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
          			                  	     new sap.ui.layout.form.GridElementData({hCells: "4"}),
          			                  	   new sap.ui.layout.GridData({span: "L1 M1 S12"})]
          	  }),
          		selectedKey:"{blankProfileModel>/learningPathway/id}"}).bindAggregation("items", "blankProfileModel>/pathways/", new sap.ui.core.ListItem({
          	      text: "{blankProfileModel>name}",
          	      key: "{blankProfileModel>id}",
          	      selectedItemId :"{blankProfileModel>/learningPathway/id}"
          	      
          	    })) .setModel(new sap.ui.model.json.JSONModel(sap.ui.getCore().getModel("blankProfileModel")));
          }
    	
    	var uJobTitle = config.getObjectById("create-create-profile-job-title-dropdown");
    	if(uJobTitle==null){
    		uJobTitle =new sap.ui.commons.DropdownBox({
    	    	id:"user-management-create-jobtitles-dropdown",
    	    	width:"200px",height:"30px" ,
    	    	enabled:false,
    	    	layoutData: new sap.ui.core.VariantLayoutData({
    	    		multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    	    		                  	     new sap.ui.layout.form.GridElementData({hCells: "4"}),
    	    		                  	   new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    	    	}),
    	    	 change:function(evt){
    	       	  //debugger;
    	       	   var _id = config.getObjectById(evt.getSource().getId()).getSelectedKey();
    	   			 var bus = sap.ui.getCore().getEventBus();
    	   			bus.publish("nav", "toDropDownService", {
    	   				id : "levels",
    	   				context:{id:_id,target:"user-management-create-aps-level-dropdown"}

    	   			});
    	   		},
    	    	selectedKey:"{blankProfileModel>/jobTitle/id}"}).bindAggregation("items", "jobtitles>/", new sap.ui.core.ListItem({
    	    	  text: "{jobtitles>description}",
    	    	  key: "{jobtitles>id}",
    	    	  selectedItemId :"{blankProfileModel>/jobTitile/id}"
    	    	  
    	    	})).setModel(new sap.ui.model.json.JSONModel(sap.ui.getCore().getModel("jobtitles")));
    	}
    
    	var oLayout1 = new sap.ui.layout.form.GridLayout();

    	var oForm1 = new sap.ui.layout.form.Form({
    		width:'100%',
    		height:'70%',
    		layout: oLayout1,
    		formContainers: [
    			new sap.ui.layout.form.FormContainer({
    				title: "User Prorfile Basic Information",
    				formElements: [
    				               new sap.ui.layout.form.FormElement({
    				            	   label: new sap.ui.commons.Label({text:"User ID:",
    				            		   enabled:false,
    				            		   layoutData: new sap.ui.core.VariantLayoutData({
    				            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    				            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    				            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    				            		   			})
    				            	   }),
    				            	   	fields: [ new sap.ui.commons.TextField({value: "{blankProfileModel>/loginId}",
    				            	   		 
    				            	   				layoutData: new sap.ui.core.VariantLayoutData({
    				            	   					multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 4}),
    				            	   					                     new sap.ui.layout.form.GridElementData({hCells: "4"})]
    				            	   				})
    				            	   	})
    				            	],
    				            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                        }),

    	              //          new sap.ui.layout.form.FormElement({
    			//	            	   label: new sap.ui.commons.Label({text:"User Type :",
    			//	            		   layoutData: new sap.ui.core.VariantLayoutData({
    			//	            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    			//	            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    			//	            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    			//	            		   			})
    			//	            	   }),
    			//	            	   	fields: [ uType],
    			//	            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                //        }),
    	                //        new sap.ui.layout.form.FormElement({
    			//	            	   label: new sap.ui.commons.Label({text:"Gender :",
    			//	            		   layoutData: new sap.ui.core.VariantLayoutData({
    			//	            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    			//	            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    			//	            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    			//	            		   			})
    			//	            	   }),
    			//	            	   	fields: [ uGender],
    			//	            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                //       }),
    	               //         new sap.ui.layout.form.FormElement({
    			//	            	   label: new sap.ui.commons.Label({text:"Location :",
    			//	            		   layoutData: new sap.ui.core.VariantLayoutData({
    			//	            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    			//	            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    			//	            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    			//	            		   			})
    			//	            	   }),
    			//	            	   	fields: [ uLocation	],
    			//	            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                //        }),
    	               //         new sap.ui.layout.form.FormElement({
    			//	            	   label: new sap.ui.commons.Label({text:"Function :",
    			//	            		   layoutData: new sap.ui.core.VariantLayoutData({
    			//	            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    			//	            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    			//	            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    			//	            		   			})
    			//	            	   }),
    			//	            	   	fields: [ uFunction	],
    			//	            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                //        }),

    	                //        new sap.ui.layout.form.FormElement({
    			//	            	   label: new sap.ui.commons.Label({text:"Job Title :",
    			//	            		   layoutData: new sap.ui.core.VariantLayoutData({
    			//	            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    			//	            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    			//	            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    			//	            		   			})
    			//	            	   }),
    			//	            	   	fields: [ uJobTitle],
    			//	            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                //       }),
    	                        
    	                //        new sap.ui.layout.form.FormElement({
    			//	            	   label: new sap.ui.commons.Label({text:"APS Level :",
    			//	            		   layoutData: new sap.ui.core.VariantLayoutData({
    			//	            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    			//	            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    			//	            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    			//	            		   			})
    			//	            	   }),
    			//	            	   	fields: [ uAPSLevel],
    			//	            	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	                //        }),
    	                //      new sap.ui.layout.form.FormElement({
    			//            	   label: new sap.ui.commons.Label({text:"Learning Pathway :",
    			//            		   layoutData: new sap.ui.core.VariantLayoutData({
    			//            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
    			//            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
    			 //           			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
    			//            		   			})
    			//            	   }),
    			//            	   	fields: [ uPathway],
    			//           	layoutData: new sap.ui.layout.ResponsiveFlowLayoutData({linebreak: true, margin: false})
    	               //  }),
    	                 new sap.ui.layout.form.FormElement({
			            	   label: new sap.ui.commons.Label({text:"Locked :",
			            		   enabled:false,
			            		   layoutData: new sap.ui.core.VariantLayoutData({
			            			   multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 1}),
			            			                        new sap.ui.layout.form.GridElementData({hCells: "4"}),
			            			                        new sap.ui.layout.GridData({span: "L1 M1 S12"})]
			            		   			})
			            	   }),
			            	   	fields: [ new sap.ui.commons.CheckBox({check: "{blankProfileModel>/myLock}",		            	   		 
			            	   				layoutData: new sap.ui.core.VariantLayoutData({
			            	   					multipleLayoutData: [new sap.ui.layout.ResponsiveFlowLayoutData({weight: 4}),
			            	   					                     new sap.ui.layout.form.GridElementData({hCells: "1"})]
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
		            	   	fields: [ new sap.ui.commons.TextField({value: "{blankProfileModel>/myLockedOn}",
		            	   		 
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

    	})
    	
    	]		
    			
    	});//.addStyleClass("userManagementForm");
    	
    	//return oForm1;
    	
    	var oMatrix = new sap.ui.commons.layout.MatrixLayout({
    		id:"user-management-profile-basic",
			layoutFixed : false,
			width:'99%',
			visible:true,
		}).addStyleClass("userManagementForm");
		
    	var oRow = new sap.ui.commons.layout.MatrixLayoutRow();
		var oCell = new sap.ui.commons.layout.MatrixLayoutCell({vAlign:"Top",hAlign:"Right"});
		oCell.addContent(new sap.ui.commons.Button({text:sap.ui.getCore().getModel("mBundle").getResourceBundle().getText("nextButtonText"),tooltip:"Next",
			press:function(evt){
				var t1 = config.getObjectById("user-management-profile-basic");
				if(t1!==null){
					t1.setVisible(false);
				}
				var t1 = config.getObjectById("user-management-password-assignment");
				if(t1!==null){
					t1.setVisible(true);
				}
			}}));
		
		oCell.addContent(new sap.ui.commons.Button({text:sap.ui.getCore().getModel("mBundle").getResourceBundle().getText("exitButtonText"),tooltip:"Exit",
			press:function(evt){
				var oOverlayContainer = config.getObjectById(constant._USER_MANAGEMENT_CREATE_PROFILE_OVERLAY_ID);
				if(oOverlayContainer.isOpen()){
					oOverlayContainer.close();
				}
			}
		
		}));
		oRow.addCell(oCell);
		oMatrix.addRow(oRow);
		var oRow = new sap.ui.commons.layout.MatrixLayoutRow({height:'350px'});
		var oCell = new sap.ui.commons.layout.MatrixLayoutCell({vAlign:"Top",hAlign:"Left"});
		
		
		oCell.addContent(oForm1);
		oRow.addCell(oCell);
		oMatrix.addRow(oRow);
		
//		
		// password assignement
		var oPasswordAssignmentMatrix = new sap.ui.commons.layout.MatrixLayout({
    		id:"user-management-password-assignment",
			layoutFixed : false,
			width:'99%',
			visible:false,
		}).addStyleClass("userManagementForm");
		
	 	var oLayout2 = new sap.ui.layout.form.GridLayout();
	 	var oRow = new sap.ui.commons.layout.MatrixLayoutRow();
		var oCell = new sap.ui.commons.layout.MatrixLayoutCell({vAlign:"Top",hAlign:"Right"});
		oCell.addContent(new sap.ui.commons.Button({text:sap.ui.getCore().getModel("mBundle").getResourceBundle().getText("backButtonText"),tooltip:"Back",
			press:function(evt){
				var t1 = config.getObjectById("user-management-password-assignment");
				if(t1!==null){
					t1.setVisible(false);
				}
				var t1 = config.getObjectById("user-management-profile-basic");
				if(t1!==null){
					t1.setVisible(true);
				}
			}}));
		oCell.addContent(new sap.ui.commons.Button({text:sap.ui.getCore().getModel("mBundle").getResourceBundle().getText("nextButtonText"),tooltip:"Next",
			press:function(evt){
			//	var text1 = config.getObjectById("userManagement-new-enter-password")
				if(config.getObjectById("userManagement-re-enter-password").getValue()==config.getObjectById("userManagement-new-enter-password").getValue()){
					var bus = sap.ui.getCore().getEventBus();
					bus.publish("nav", "toRole", {
						context:{action:'get',data:[
						                            {id:'user-management-role-assignment',visible:true},
						                            {id:'user-management-profile-basic',visible:false},
						                            {id:'user-management-password-assignment',visible:false}]}
					});
				}else{
					sap.ui.commons.MessageBox.alert("Password don not match!\n Re-enter your password");
					return;
				}
				
				
			}}));
		oCell.addContent(new sap.ui.commons.Button({text:sap.ui.getCore().getModel("mBundle").getResourceBundle().getText("exitButtonText"),tooltip:"Exit",
			press:function(evt){
				var oOverlayContainer = config.getObjectById(constant._USER_MANAGEMENT_CREATE_PROFILE_OVERLAY_ID);
				if(oOverlayContainer.isOpen()){
					oOverlayContainer.close();
				}
			}
		
		}));
		oRow.addCell(oCell);
		oPasswordAssignmentMatrix.addRow(oRow);
		var oRow = new sap.ui.commons.layout.MatrixLayoutRow();
		var oCell = new sap.ui.commons.layout.MatrixLayoutCell({vAlign:"Top",hAlign:"Left"});
		
		//****************************************
		
		//*******************************8
		oCell.addContent( new sap.ui.layout.form.Form({
    		width:'100%',
    		height:'70%',
    		layout: oLayout2,
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
    				            	   	fields: [ new sap.ui.commons.PasswordField({value: "{blankProfileModel>/password}",
    				            	   		id:'userManagement-new-enter-password',
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
 				            	   	fields: [ new sap.ui.commons.PasswordField({value: "{blankProfileModel>/rePassword}",
 				            	   	placeholder:'Re-Enter password',
 				            	   id:'userManagement-re-enter-password',
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
		}));                        
		oRow.addCell(oCell);
		oPasswordAssignmentMatrix.addRow(oRow);
		
		
		
		
		
		//end of password assignement;
		// role Assignment
		var oRoleAssigmentMatrix = new sap.ui.commons.layout.MatrixLayout({
    		id:"user-management-role-assignment",
			layoutFixed : false,
			width:'99%',
			visible:false,
		}).addStyleClass("userManagementForm");
		var oLayout3 = new sap.ui.layout.form.GridLayout();
		var oRow = new sap.ui.commons.layout.MatrixLayoutRow();
		
		
		var oCell = new sap.ui.commons.layout.MatrixLayoutCell({vAlign:"Top",hAlign:"Right"});
		oCell.addContent(new sap.ui.commons.Button({text:sap.ui.getCore().getModel("mBundle").getResourceBundle().getText("backButtonText"),tooltip:"Back",
			press:function(evt){
				var t1 = config.getObjectById("user-management-role-assignment");
				if(t1!==null){
					t1.setVisible(false);
				}
				var t1 = config.getObjectById("user-management-password-assignment");
				if(t1!==null){
					t1.setVisible(true);
				}
			}}));
		oCell.addContent(new sap.ui.commons.Button({text:sap.ui.getCore().getModel("mBundle").getResourceBundle().getText("saveButtonText"),tooltip:"Save",
			press:function(evt){
				if(isValid()){
					var bus = sap.ui.getCore().getEventBus();
					bus.publish("nav", "fromUserManagement", {
						id:'create-save'
					});
				}
				
			}
		
		}));
		oCell.addContent(new sap.ui.commons.Button({text:sap.ui.getCore().getModel("mBundle").getResourceBundle().getText("exitButtonText"),tooltip:"Exit",
			press:function(evt){
				var oOverlayContainer = config.getObjectById(constant._USER_MANAGEMENT_CREATE_PROFILE_OVERLAY_ID);
				if(oOverlayContainer.isOpen()){
					oOverlayContainer.close();
				}
			}
		
		}));
		oRow.addCell(oCell);
		oRoleAssigmentMatrix.addRow(oRow);
		var oRow = new sap.ui.commons.layout.MatrixLayoutRow();
		var oCell = new sap.ui.commons.layout.MatrixLayoutCell({vAlign:"Top",hAlign:"Left"});

		
		oCell.addContent( new sap.ui.layout.form.Form({
    		width:'100%',
    		height:'70%',
    		layout: oLayout3,
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
		}));                        
		oRow.addCell(oCell);
		oRoleAssigmentMatrix.addRow(oRow);
		
		var arr=[];
		arr.push(oMatrix);
		arr.push(oPasswordAssignmentMatrix);
		arr.push(oRoleAssigmentMatrix);
		
		
		
		
		return arr;
		

	},
	

});

function isValid(){
	var roleModel = sap.ui.getCore().getModel("roleModel").oData;
	if(roleModel==null){
		return false;
	}
//blankProfileModel>/loginId
	var bModel = sap.ui.getCore().getModel("blankProfileModel").oData;
	if(bModel==null){
		return false;
	}
	
		
	if((bModel.password==null || bModel.rePassword==null)){
		sap.ui.commons.MessageBox.alert("Password don not match!\n Re-enter your password\n You must have assign the initial password to user");
		return false;
	};
	if(bModel.password=="" || bModel.rePassword==""){
		sap.ui.commons.MessageBox.alert("Password don not match!\n Re-enter your password\n You must have assign the initial password to user");
		return false;
	}
	if(bModel.loginId=="" || bModel.loginId==null){
		sap.ui.commons.MessageBox.alert("PMKeyS No field conn't be left blank!");
		return false;
	}
	if(roleModel.id==-1){
		sap.ui.commons.MessageBox.alert("You have must assign a role to user!");
		return false;
	}else{
		roleModel.loginId = bModel.loginId;
		sap.ui.getCore().getModel("roleModel").oData=roleModel;
		
	}
	return true;
}