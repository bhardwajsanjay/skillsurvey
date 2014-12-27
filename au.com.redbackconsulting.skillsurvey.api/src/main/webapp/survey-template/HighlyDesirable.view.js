sap.ui
		.jsview(
				"survey-template.HighlyDesirable",
				{

					/**
					 * Specifies the Controller belonging to this View. In the
					 * case that it is not implemented, or that "null" is
					 * returned, this View does not have a Controller.
					 * 
					 * @memberOf survey-template.
					 */
					getControllerName : function() {
						return "survey-template.HighlyDesirable";
					},

					/**
					 * Is initially called once after the Controller has been
					 * instantiated. It is the place where the UI is
					 * constructed. Since the Controller is given to this
					 * method, its event handlers can be attached right away.
					 * 
					 * @memberOf survey-template.
					 */
					createContent : function(oController) {

						 var oMatrix = new sap.ui.commons.layout.MatrixLayout({
								layoutFixed : false,
								width:'100%'
									
							}).addStyleClass("surveySurveyMatrix");
							 //courseId
						 var oQuestionTable = new sap.ui.table.Table(
									{
										// title: "Table with fixed columns
										// Example",
										visibleRowCount : 15,
										//firstVisibleRow : 3,
										selectionMode : sap.ui.table.SelectionMode.Single,
										navigationMode : sap.ui.table.NavigationMode.Paginator,
										//fixedColumnCount: 2,
										width : "100%",
										columnHeaderVisible :true,
									});
//						 var courseColumn = new sap.ui.table.Column({
//								width : "8%",
//							//	sortProperty: "question", 
//								//filterProperty:"question",
//								label : new sap.ui.commons.Label({
//									text : "{mBundle>tableHeadingCourseId}",
//									
//								}),
//								template : new sap.ui.commons.TextView({
//									width:'100%',
//									wrapping :true,
//									visible:"{codeVisibility}",
//									tooltip:generateQV.createQuickView("COURSE", 1, "SurveyModel")
//									}).bindProperty("text", "courseId").attachBrowserEvent("mouseenter", function(evt){
//										//debugger;
//										var x=90;
//										generateQV.loadModel(evt.target.innerText, "SurveyModel", "COURSE");
//										//generateQV.createQuickView("COURSE", evt.target.innerText, "SurveyModel");
//									}, this),
//								hAlign : "Left",
//								vAlign : "Middle",
//								
//							
//							});
//						 oQuestionTable.addColumn(courseColumn);
						// oQuestionTable.setGroupBy(courseColumn);
						
							var uocName = new sap.ui.table.Column({
								width : "40%",
							//	sortProperty: "uocName", 
								//filterProperty:"uocName",
								label : new sap.ui.commons.Label({
									text : "{mBundle>tableHeadingCourse}",
									
								}),
								template : new sap.ui.commons.TextView({width:'100%',wrapping :true,
									visible:"{cVisibility}",	
									tooltip:generateQV.createQuickView("COURSE", 1, "SurveyModel")
								}).addStyleClass("surveyPathwayHeaderTextStyle_z")
										.bindProperty("text", "uocName").attachBrowserEvent("mouseenter", function(evt){
											var courseId = config.getObjectById(evt.delegateTarget.id).getBindingContext().getObject().courseId;
											generateQV.loadModel(courseId, "SurveyModel", "COURSE");
												
													}, this),
								hAlign : "Left",
								vAlign : "Middle",
								
							});
							oQuestionTable.addColumn(uocName);
						//	oQuestionTable.setGroupBy(uocName);
							oQuestionTable.addColumn(new sap.ui.table.Column({
								width : "40%",
							//	sortProperty: "question", 
							//	filterProperty:"question",
								label : new sap.ui.commons.Label({
									text : "{mBundle>tableHeadingQuestions}"
								}),
								template : new sap.ui.commons.Label({width:'100%',wrapping :true,
								icon:"{icon}",	
								tooltip:"{question}",
								}).addStyleClass("surveyPathwayHeaderTextStyle_z")
										.bindProperty("text", "question"),
								hAlign : "Left",
								vAlign : "Middle",
								
							
							}));
							oQuestionTable
									.addColumn(new sap.ui.table.Column(
											{
												width : "5%",
												hAlign:"Center",
												label : new sap.ui.commons.Label({
													width:'100%',
													text : "{mBundle>tableHeadingYes}",
													textAlign :  sap.ui.core.TextAlign.Center 
												}),
												template : new sap.ui.commons.CheckBox(
														{
															visible:"{check}",
															change : function(evt) {
																var checkStatus = this.getBindingContext().getObject().yes;
																var model = sap.ui.getCore().getModel("HighlyDesirable").oData;
																for ( var obj in model) {
																	if (model[obj].uocQuestionId == this.getBindingContext().getObject().uocQuestionId) {
																			this.getBindingContext().getObject().answer="Yes";
																			this.getBindingContext().getObject().yes = true;
																			this.getBindingContext().getObject().no = false;
																		model[obj] = this.getBindingContext().getObject();
																		break;
																	}
																}
																sap.ui.getCore().getModel("HighlyDesirable").setData(model,false);
																
																var model = sap.ui.getCore().getModel("HighlyDesirable").oData;
																for ( var obj in model) {
																	if (model[obj].uocQuestionId == this.getBindingContext().getObject().uocQuestionId) {
																		if (model[obj].no == false && model[obj].yes==false ) {
																			model[obj].answer="No Answer";
																		}
																
																	}
																}
																for ( var obj in model) {
																	if (model[obj].uocQuestionId == this.getBindingContext().getObject().uocQuestionId) {
																		if (model[obj].yes==true ) {
																			model[obj].answer="Yes";
																		}
																
																	}
																}
																if(checkStatus==true&&this.getBindingContext().getObject().isPrimary==1){
																	config.removeAndUpdateSecondaryQuestionByCouseId(this.getBindingContext().getObject().courseId, "HighlyDesirable");
																}
																if(this.getBindingContext().getObject().isPrimary==2&&checkStatus==true ){
																	config.getQuestionnaireByCouseId(this.getBindingContext().getObject().courseId, "HighlyDesirable");
																}
																
																
																oQuestionTable.setModel(sap.ui.getCore().getModel("HighlyDesirable"));
																oQuestionTable.bindRows("/");
															}
														}).bindProperty("checked",
														"yes"),
												vAlign : "Middle",
												hAlign : "Center",
											}));
							oQuestionTable
									.addColumn(new sap.ui.table.Column(
											{
												width : "5%",
												hAlign:"Center",
												label : new sap.ui.commons.Label({
													width:'100%',
													text : "{mBundle>tableHeadingNo}",
													textAlign :  sap.ui.core.TextAlign.Center 
												}),
												template : new sap.ui.commons.CheckBox(
														{
															visible:"{check}",
															change : function(evt) {
																var checkStatus = this.getBindingContext().getObject().no;
																var model = sap.ui
																		.getCore()
																		.getModel(
																				"HighlyDesirable").oData;
																
																for ( var obj in model) {
																	
																	
																	if (model[obj].uocQuestionId == this.getBindingContext().getObject().uocQuestionId) {
																			this.getBindingContext().getObject().answer="No";
																			this.getBindingContext().getObject().yes = false;
																			this.getBindingContext().getObject().no = true;
																			model[obj] = this.getBindingContext().getObject();
																		break;
																	}
																}
																sap.ui.getCore().getModel("HighlyDesirable").setData(	model,false);
																var model = sap.ui.getCore().getModel("HighlyDesirable").oData;
																for ( var obj in model) {
																	if (model[obj].uocQuestionId == this.getBindingContext().getObject().uocQuestionId) {
																		if (model[obj].no == false && model[obj].yes==false ) {
																			model[obj].answer="No Answer";
																		}
																
																	}
																}
																for ( var obj in model) {
																	if (model[obj].uocQuestionId == this.getBindingContext().getObject().uocQuestionId) {
																		if (model[obj].no==true ) {
																			model[obj].answer="No";
																		}
																	}
																}
																if(checkStatus==true&&this.getBindingContext().getObject().isPrimary==2){
																	config.removeAndUpdateQuestionsByCouseId(this.getBindingContext().getObject().courseId, "HighlyDesirable");
																}
																if(checkStatus==true&&this.getBindingContext().getObject().isPrimary==1){
																	config.getSecondaryQuestionByCouseId(this.getBindingContext().getObject().courseId, "HighlyDesirable");
																}
																oQuestionTable.setModel(sap.ui.getCore().getModel("HighlyDesirable"));
																oQuestionTable.bindRows("/");
															}

														}).bindProperty("checked",
														"no"),
												vAlign : "Middle",
												hAlign : "Center",

											})).addStyleClass("questionTable");

							// Create a model and bind the table rows to this model

							oQuestionTable.setModel(sap.ui.getCore().getModel(
									"HighlyDesirable"));
						 
							oQuestionTable.bindRows("/");
							var oRow = new sap.ui.commons.layout.MatrixLayoutRow({height:"10px"}).addStyleClass("actionRow");
							var oCell = new sap.ui.commons.layout.MatrixLayoutCell({hAlign:"Right"}).setColSpan(2);
							
							oCell.addContent(new sap.ui.commons.Button({text:'<Back',
								tooltip:'Back',
								press:function(){
									config.setLastSaveStatus(false);
								if(!config.updateModelBeforeSaved("HighlyDesirable")){
										 config.displayNotificationBar(sap.ui.getCore().getModel("mBundle").getResourceBundle().getText("rplSaveExceptionMessage"), "Abort save!");
											
									}else{
										var bus = sap.ui.getCore().getEventBus();
										bus.publish("nav", "fromPathway", {
											id : "HighlyDesirable",
										data:{action:config.getPathwayAction(config.getSelectedId())}
			
									});
									}
								config.navigationDone=false;
								config.navTo(0);		
							}}));
							
							oCell.addContent(new sap.ui.commons.Button({
							  id:"button_mysurvey-HighlyDesirable-subMenu",
								text:"Save",
								//enabled:config.getNeedSaveButtonStatus(),
								press:function(oEvent){
									//debugger;
								//	config.updateModelBeforeSaved("HighlyDesirable");
									if(!config.updateModelBeforeSaved("HighlyDesirable")){
										 config.displayNotificationBar(sap.ui.getCore().getModel("mBundle").getResourceBundle().getText("rplSaveExceptionMessage"), "Abort save!");
											
									}else{
										var bus = sap.ui.getCore().getEventBus();
										bus.publish("nav", "fromPathway", {
											id : "HighlyDesirable",
										data:{action:config.getPathwayAction(config.getSelectedId())}
			
									});
									}		
									
								}}).addStyleClass("surveyContinueButtonContainer"));
							
							
							//oRow.addCell(oCell);
							
							//
							oCell.addContent(new sap.ui.commons.Button({text:'Next Survey >',
								tooltip:'Next Suvery',
								press:function(){
									config.setLastSaveStatus(false);
								if(!config.updateModelBeforeSaved("HighlyDesirable")){
										 config.displayNotificationBar(sap.ui.getCore().getModel("mBundle").getResourceBundle().getText("rplSaveExceptionMessage"), "Abort save!");
											
									}else{
										var bus = sap.ui.getCore().getEventBus();
										bus.publish("nav", "fromPathway", {
											id : "HighlyDesirable",
										data:{action:config.getPathwayAction(config.getSelectedId())}
			
									});
									}
								config.navigationDone=false;
								config.navTo(1);		
							}}));
							oRow.addCell(oCell);	

							oMatrix.addRow(oRow);

						 /////////////////////
							var oRow = new sap.ui.commons.layout.MatrixLayoutRow();
							var oCell = new sap.ui.commons.layout.MatrixLayoutCell({vAlign:"Top",hAlign:"Left"}).setColSpan(2);
							oCell.addContent(oQuestionTable);
							oRow.addCell(oCell);
							
							oMatrix.addRow(oRow);
							return oMatrix;


					}

				});
