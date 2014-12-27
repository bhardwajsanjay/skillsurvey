
var GenerateQuickView = function(){
	this.linkUrl ='';
	this.state=false;
	this.oData ={name:"",type:"",description:"",courseId:"",actionVisibility:false};
	this.setState=function(_state){
		this.state =_state;
	};
	this.getState=function(){
		return this.state ;
	};
	this.setLinkURL=function(url){
		this.linkUrl=url;
	};
	this.getLinkURL=function(){
		return this.linkUrl;
	};
	this.validateCode=function(TCode){
		for(var i=0; i<TCode.length; i++)
	      {
	        var char1 = TCode.charAt(i);
	        var cc = char1.charCodeAt(0);

	        if((cc>64 && cc<91) || (cc>96 && cc<123))
	        {
	        	return true;
	        }
	         else {
	        // alert('Input is not alphanumeric');
	         return false;
	         }
	      }
	     return true; 
	};
	this.loadModel=function(uocId,modelId,type){
		//debugger;
		var jdata = sap.ui.getCore().getModel(modelId).oData.courseItems;
		for(var jd in jdata){
			if(jdata[jd].id==uocId){		
				//this.setLinkURL(jdata[jd].provider.courseURL);

				this.oData.type = type;
				var url=jdata[jd].provider.courseURL;
				var arr = url.split('=');
				this.setState( this.validateCode(arr[1]));
				this.oData.actionVisibility=this.validateCode(arr[1]);
				this.setLinkURL(sap.ui.getCore().getModel("mBundle").getResourceBundle().getText("external_course_link")+'='+arr[1]);
				this.oData.name=jdata[jd].name;
				this.oData.courseId = uocId;
				this.oData.description =jdata[jd].description;
				break;
				}
		}
	
			var oModel = new sap.ui.model.json.JSONModel();
			oModel.setData(this.oData,false);
			sap.ui.getCore().setModel(oModel,"quick");
			config.getObjectById('xxx').bindContext("quick>/");
	};
	
	this.onNavigate=function(event){
		alert("Navigate event with the reference to: " + event.getParameter("href"));
		// Supress link navigation from a QuickView
		event.preventDefault();
		// Close the QuickView after a click on a link
		event.getSource().close();
	};
    this.createQuickView=function(type,uocId,modelId){
    	var oModel = new sap.ui.model.json.JSONModel();
		oModel.setData(this.oData,false);
		sap.ui.getCore().setModel(oModel,"quick");
		var oQuickView = config.getObjectById('xxx');
		if(oQuickView==null){
			oQuickView = new sap.ui.ux3.QuickView({
	    		id:'xxx',
	    		type:			"{quick>type}",
	    		firstTitle:		"{quick>name}",
	    		icon:			"config/images/course.png",
	    		content:		createQuickViewContent(),
	    		navigate:		this.onNavigate,
	    		updateActionEnabled : false,
	    			followActionEnabled : false,
	    			flagActionEnabled :false,
	    			favoriteActionEnabled :false,
	    			openActionEnabled :true,
	    			showActionBar :"{quick>actionVisibility}",
	    			actionSelected :function(event){
	    					window.open(generateQV.getLinkURL());
	    			}
	    		})
	    		.bindContext("quick>/");
			oQuickView.setShowActionBar(false);
		}
    	
    	return oQuickView;
    };
};
function createQuickViewContent(){
	//debugger;
	var c = sap.ui.commons.layout;
	var oContent = new c.MatrixLayout({layoutFixed:false});
	var oRow = new c.MatrixLayoutRow();
	
	oRow.addCell( new c.MatrixLayoutCell({
		content:[ new sap.ui.commons.TextView({text:"Course Id:"})
		]
	}));
	oRow.addCell( new c.MatrixLayoutCell({
		content:[ new sap.ui.commons.TextView({text:"{quick>/courseId}"}).addStyleClass("qvDescriptionType")
		]}));
	oContent.addRow(oRow);
	
	var oRow = new c.MatrixLayoutRow();
	oRow.addCell( new c.MatrixLayoutCell({
		content:[ new sap.ui.commons.TextView({text:"Description:"})
		]}).setColSpan(2));
	
	oContent.addRow(oRow);
	var oRow = new c.MatrixLayoutRow();
	oRow.addCell( new c.MatrixLayoutCell({
		content:[ new sap.ui.commons.TextView({text:"{quick>/description}"}).addStyleClass("qvDescription")
		]}).setColSpan(2));
	oContent.addRow(oRow);
	
	return oContent;
};

var SurveyAppConstants = function (){
	this._USER_MANAGEMENT_EDIT_BUTTON_ID="user-manament-button-edit";
	this._USER_MANAGEMENT_CANCEL_BUTTON_ID="user-management-button-cancel";
	this._USER_MANAGEMENT_SAVE_BUTTON_ID="user-management-button-save";
	this._USER_MANAGEMENT_THINGINSPECTOR_ID ="user-management-thing-inspector";
	this._USER_MANAGEMENT_THING_GROUP_ID="user-management-thing-group";
	this._USER_MANAGEMENT_CREATE_PROFILE_OVERLAY_ID="userManagementCreateProfileOverlay";
	this._SURVEY_MANDATORY_MODEL_TRANS = "MandatoryTrans";
	this._SURVEY_SUPERVISOR_MODEL_TRANS ="SupervisorTrans";
	this._PROPS_RPL_QUESTION_TYPE_TEXT="rplQuestionMessage";
	this._PROPS_RPL_SAVE_EXCEPTION_MESSAGE_TEXT ="rplSaveExceptionMessage";
};


var UINavigation = function(data){
	this.navItems=[];
	this.navRootItems=[];
	this.navProps={isSingleChild:false,lastChild:false,nextRootId:null,nexChildId:null};
	this.nextNavigationId=null;
	this.previousNavigationId=null;
	this.currentNavigationId=null;
	this.selectedNavId=null;
	this.navNextIndex=1;
	this.navPreviousIndex=0;
	this.addNavigationRootItems =function(item){
		this.navRootItems.push(item);
	};
	this.addNavigationItems=function(item){
		this.navItems.push(item);
	};
	
	this.setNavItems=function(items){
		this.navItems=items;
	};
	this.getNextNavigationItem=function(){
		
		if(this.navNextIndex==this.navItems.length){
			this.navNextIndex=0;
		}
		return this.navItems[this.navNextIndex];
	};
	this.getPreviousNavigationItem=function(){
		if(this.navPreviousIndex<0){
			this.navPreviousIndex=0;
		}
		return this.navItems[this.navPreviousIndex];
	};
	this.getRootNavigationItem=function(id){
		for(var i=0;i<this.navRootItems.length;i++){
			if(id== this.navRootItems[i].id){
				return this.navRootItems[i];
			}
		}
	};
	this.changeIndexStatus=function(key){
		for(var i=0;i<this.navItems.length;i++){
			if(this.navItems[i].selectedKey==key){
				this.navNextIndex=i+1;
				this.navPreviousIndex=i-1;
				if(this.navNextIndex>this.navItems.length){
					this.navNextIndex=0;
				}
				if(this.navPreviousIndex<0){
					this.navPreviousIndex=0;
				}
			}
			  
		}
	};
	this.doPreviousNavigation=function(){
		//debugger;
			if(this.navPreviousIndex<0){
			this.navPreviousIndex=0;
			this.navNextIndex =1;
		}else{
			this.navNextIndex = this.navPreviousIndex+1;
		}
		
		var obj = this.getPreviousNavigationItem();
		var root = this.getRootNavigationItem(obj.id);
		var bus = sap.ui.getCore().getEventBus();
		
		config.getObjectById(config.getShellId()).setSelectedWorksetItem(config.getObjectById(root.selectedKey));
		config.getObjectById(config.getShellId()).setSelectedWorksetItem(config.getObjectById(obj.selectedKey));
		bus.publish("nav", "fromMenu", {
			id : obj.navId,

		});
		this.navNextIndex = this.navPreviousIndex+1;
		this.navPreviousIndex-=1;
		
	
		if(this.navPreviousIndex<0){
			this.navNextIndex=1;
			this.navPreviousIndex=0;
		}
			
		
	
		
	};
	this.doNextNavigation=function(){

		var obj = this.getNextNavigationItem();
		var root = this.getRootNavigationItem(obj.id);
		var bus = sap.ui.getCore().getEventBus();
		
		config.getObjectById(config.getShellId()).setSelectedWorksetItem(config.getObjectById(root.selectedKey));
		config.getObjectById(config.getShellId()).setSelectedWorksetItem(config.getObjectById(obj.selectedKey));
		bus.publish("nav", "fromMenu", {
			id : obj.navId,

		});
		//debugger;
		this.navPreviousIndex = this.navNextIndex-1;
		this.navNextIndex+=1;
		
		
	};
};

