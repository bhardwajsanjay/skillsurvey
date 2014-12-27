
var FReporting = function(data,head){
	this.style = new Styles();
	this.dataModel = data;
	this.content='';
	this.tHeader = head;
	this.createNeedBlock=function(needBlock){
		var html ='<tr><td colspan="'+config.REPORT_COLUMN+'"  style ="'+this.style.getStyleSurveyTemplateReportMatrixCourseHeaderFunction()+'">'+needBlock.needName+'</td></tr>';
		html+=this.createUocDataBlock(needBlock.uocs);
		return html;
	};
	this.getNumberCount=function(data){
		var arr = data.split("=");
		if(arr==null){return parseInt(data);}
		var count = parseInt(arr[1]);
		return count;
	};
	this.getLocationBylocationId=function(lid){
		var arr = lid.split('=');
		var str = this.getLocation(arr[0])+'='+arr[1];
		return str;
	};
	this.getLocation=function(id){
		var locations = this.dataModel.locations;
		for(var l in locations){
			if(locations[l].locationId==id){
				return locations[l].locationName;
			}
		}
	};
	this.createUocDataBlock=function(uocs){
		//debugger;
		var html='';
		var numberofspan=0;
		for(var uoc in uocs){
			var items = uocs[uoc].items;
			var len = items.length;
			if(config.getSelectedReportType()=="RepByUOCCourse"){
				numberofspan= len;
			}else{
				numberofspan=len+1;
			}
			html+='<tr ><td rowspan="'+numberofspan+'" style=" border-bottom:1px solid;solid;vertical-align:text-top;'+this.style.getStyleSurveyTemplateReportingUocName()+'">'+uocs[uoc].uocCourseName+'</td>';
			var c1=0;
			var c2=0;
		    var c3=0;
			var t1=0;
			var d1=0;
			var d2=0;
			var d3=0;
			var t2=0;
			for(var i =0;i<len;i++){
/////
				
				items[i].cF = (items[i].cF).replace("-"," = ");
				
				items[i].cM = (items[i].cM).replace("-"," = ");
				
				items[i].cO = (items[i].cO).replace("-"," = ");
				
				items[i].cA = (items[i].cA).replace("-"," = ");
				
				
				
				items[i].nCF = (items[i].nCF).replace("-"," = ");
				
				
				items[i].nCM = (items[i].nCM).replace("-"," = ");
				
				
				items[i].nCO = (items[i].nCO).replace("-"," = ");
				
			
				items[i].nCA = (items[i].nCA).replace("-"," = ");
				/////
				
				c1+= this.getNumberCount(items[i].cF);
				c2+= this.getNumberCount(items[i].cM);
				c3+= this.getNumberCount(items[i].cO);
				t1+= this.getNumberCount(items[i].cA);
			
				
				
				d1+= this.getNumberCount(items[i].nCF);
				d2+= this.getNumberCount(items[i].nCM);
				d3+= this.getNumberCount(items[i].nCO);
				t2+= this.getNumberCount(items[i].nCA);
				
			
				if(config.getSelectedReportType()=="RepByLocation"){
					items[i].cF= this.getLocationBylocationId(items[i].cF);
					
					items[i].cM= this.getLocationBylocationId(items[i].cM);
					items[i].cO= this.getLocationBylocationId(items[i].cO);
					
					items[i].cA= this.getLocationBylocationId(items[i].cA);
					//items[i].cA=;
					items[i].nCF=this.getLocationBylocationId(items[i].nCF);
					items[i].nCM=this.getLocationBylocationId(items[i].nCM);
					items[i].nCO=this.getLocationBylocationId(items[i].nCO);
					items[i].nCA=this.getLocationBylocationId(items[i].nCA);
					
					
					
				}
				
				html+='<td style="'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">'+items[i].cF+'</td><td style="'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">'+items[i].cM+'</td><td style="'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">'+items[i].cO+'</td><td style="'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">'+items[i].cA+'</td>';
				html+='<td style="'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">'+items[i].nCF+'</td><td style="'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">'+items[i].nCM+'</td><td style="'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">'+items[i].nCO+'</td><td style="'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">'+items[i].nCA+'</td>';
				if(i<len-1){
					html+='</tr><tr>';
				}
			}
			html+='</tr>';
if(config.getSelectedReportType()!="RepByUOCCourse")
{
html+'<tr>';
			html+='<td style="border-top:1px solid;  border-bottom:1px solid;'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">TOTAL ='+c1+'</td><td style="border-top:1px solid; border-bottom:1px solid;'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">TOTAL ='+c2+'</td><td style="border-top:1px solid; border-bottom:1px solid;'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">TOTAL ='+c3+'</td><td style=" border-top:1px solid; border-bottom:1px solid;'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">TOTAL = '+t1+'</td>';
			html+='<td style=" border-top:1px solid; border-bottom:1px solid;'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">TOTAL = '+d1+'</td><td style="border-top:1px solid;  border-bottom:1px solid;'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">TOTAL = '+d2+'</td><td style="border-top:1px solid; border-bottom:1px solid;'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">TOTAL ='+d3+'</td><td style="border-top:1px solid;  border-bottom:1px solid;'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">TOTAL = '+t2+'</td>';
			html+'</tr>';


}
			
		}
		
		
		return html;
	};
	this.generateHTMLFormat=function(){
		//debugger;
		var headContent = this.dataModel.reportDetail;
		if(headContent==null){
			this.content='<div style="margin:2% auto;font-size:16px;color: #CF4520;font-family: Georgia, Arial,Helvetica,sans-serif;">There is no function assign to you please check and update your profile!<div>';
			return this.content;
		}
		this.content ='<div><div><table cellpadding="0" cellspacing="0"><tr><td style="'+this.style.getReportingHeaderTextFormat()+'">Function:</td>'+
		'<td style="'+this.style.getReportingTextFormat()+'">'+headContent.functionDesc+'</td><td></td><td></td></tr>'+
		'<tr><td colspan="2" style="'+this.style.getReportingHeaderTextFormat()+'">Training Gap Report By:</td>'+
		'<td style="'+this.style.getReportingTextFormat()+'">'+headContent.reportBy+'</td>'+
		'</tr></table></div><div>';
		 this.content+='<table cellpadding="0" cellspacing="0" style="margin-left:1%;background-color:white;margin-bottom:4px; border:1px #000000 solid;width:90%;">';
			
	     this.content+='<tr><td style="'+this.style.getStyleSurveyTemplateReportingNoBorderCellHeader()+'">'+this.tHeader['h1']+'</td><td style="'+this.style.getStyleSurveyTemplateReportingMiddleCellHeader()+'">'+this.tHeader['h2']+'</td><td style="'+this.style.getStyleSurveyTemplateReportingMiddleCellHeader()+'">'+this.tHeader['h3']+'</td>';
	     this.content+='<td style="'+this.style.getStyleSurveyTemplateReportingMiddleCellHeader()+'">'+this.tHeader['h4']+'</td><td style="'+this.style.getStyleSurveyTemplateReportingMiddleCellHeader()+'">'+this.tHeader['h5']+'</td><td style="'+this.style.getStyleSurveyTemplateReportingMiddleCellHeader()+'">'+this.tHeader['h6']+'</td>';
	     this.content+='<td style="'+this.style.getStyleSurveyTemplateReportingMiddleCellHeader()+'">'+this.tHeader['h7']+'</td>';
	     this.content+='<td style="'+this.style.getStyleSurveyTemplateReportingMiddleCellHeader()+'">'+this.tHeader['h8']+'</td>';
	     this.content+='<td style="'+this.style.getStyleSurveyTemplateReportingMiddleCellHeader()+'">'+this.tHeader['h9']+'</td></tr>';
			
		var content = this.dataModel.reportItems;	
		
		for(var need in content){
			this.content+=this.createNeedBlock(content[need]);
		}
		this.content+='</table></div><div style="height:50px;"></div>';
		return this.content;
	};
	this.exportToCVSFormat=function(){
		var headContent = this.dataModel.reportDetail;
		var JSONData = this.dataModel.reportItems;
		 //If JSONData is not an object then JSON.parse will parse the JSON string in an Object
	    var arrData = typeof JSONData != 'object' ? JSON.parse(JSONData) : JSONData;
	    
	    var CSV = '';    
	   
	   CSV +='"Function  : "'+ headContent.functionDesc + '\r\n';
	   CSV +='"Training Gap Report By : "' +headContent.reportBy + '\r\n\n';
	    
	    //This condition will generate the Label/Header
	   // if (ShowLabel) {
	        var row = "";
	        
	        //This loop will extract the label from 1st index of on array
	        //for (var index in rh) {
	            
	            //Now convert each value to string and comma-seprated
	            row += this.tHeader['h1'] + ',';
	            row += this.tHeader['h2'] + ',';
	            row += this.tHeader['h3'] + ',';
	            row += this.tHeader['h4'] + ',';
	            row += this.tHeader['h5'] + ',';
	            row += this.tHeader['h6'] + ',';
	            row += this.tHeader['h7'] + ',';
	            row += this.tHeader['h8'] + ',';
	            row += this.tHeader['h9'] + ',';
	       // }

	        row = row.slice(0, -1);
	        
	        //append Label row with line break
	        CSV += row + '\r\n\n';
	   // }
	    
	    //1st loop is to extract each row
	    var pathways = arrData;
	    //debugger;
	    for(var p in pathways){
	    	CSV += pathways[p].needName + '\r\n\n';
	     	var uocs = pathways[p].uocs;
	     	for(var uoc in uocs){
	     		
	     		var  uocName = uocs[uoc].uocCourseName;
	     		
	     		
	     		var items = uocs[uoc].items;
	     		var c1=0;
	     		var c2 =0;
	     		var c3=0;
	     		var t1=0;
	     		var d1=0;
	     		var d2=0;
	     		var d3=0;
	     		var t2=0;
	     		
	    		for(var it in items){
					items[it].cF = (items[it].cF).replace("-"," = ");
					
					items[it].cM = (items[it].cM).replace("-"," = ");
					
					items[it].cO = (items[it].cO).replace("-"," = ");
					
					items[it].cA = (items[it].cA).replace("-"," = ");
					
					
					
					items[it].nCF = (items[it].nCF).replace("-"," = ");
					
					
					items[it].nCM = (items[it].nCM).replace("-"," = ");
					
					
					items[it].nCO = (items[it].nCO).replace("-"," = ");
					
				
					items[it].nCA = (items[it].nCA).replace("-"," = ");
					/////
	    			row='';
	    			row += '"' + uocName + '",';
	    			
	    			c1+= this.getNumberCount(items[it].cF);
	    			c2+= this.getNumberCount(items[it].cM);
	    			c3+= this.getNumberCount(items[it].cO);
	    			t1+= this.getNumberCount(items[it].cA);
	    			
	    			d1+= this.getNumberCount(items[it].nCF);
	    			d2+= this.getNumberCount(items[it].nCM);
	    			d3+= this.getNumberCount(items[it].nCO);
	    			t2+= this.getNumberCount(items[it].nCA);
	    			
	    			
	    			row += '"' + items[it].cF + '",';
	    			row += '"' + items[it].cM + '",';
	    			row += '"' + items[it].cO + '",';
	    			row += '"' + items[it].cA + '",';
	    			
	    			row += '"' + items[it].nCF + '",';
	    			row += '"' + items[it].nCM + '",';
	    			row += '"' + items[it].nCO + '",';
	    			row += '"' + items[it].nCA + '",';
	    			
	    	        row.slice(0, row.length - 1);
	    	        CSV += row + '\r\n';
	    	        uocName="";
	    		}
	    		if(config.getSelectedReportType()!="RepByUOCCourse"){
	    			row='';
		    		row += '"' + uocName + '",';	
		    		row += '"TOTAL = ' + c1 + '",';
					row += '"TOTAL =' + c2 + '",';
					row += '"TOTAL =' + c3 + '",';
					row += '"TOTAL =' + t1+ '",';
					
					row += '"TOTAL =' + d1 + '",';
					row += '"TOTAL =' + d2 + '",';
					row += '"TOTAL =' + d3 + '",';
					row += '"TOTAL =' + t2+ '",';
					 row.slice(0, row.length - 1);
		 	        CSV += row + '\r\n\n';
	    			
	    		}
	    		
	    		
	      	        

	     	}

	   }
	    	


	    if (CSV == '') {        
	        alert("Invalid data");
	        return;
	    }   
	    
	  this.downloadCSVFile(CSV ,headContent);


	};
	this.downloadCSVFile=function(data,headContent){
	    
	//	var isIE = false || !!document.documentMode;
		var fileName = "Report_";
	   	fileName += (headContent.reportBy).replace(/ /g,"_");   
		if(config.detectID()){
			data = decodeURIComponent(data);
			  var iframe =	document.createElement("iframe");
				iframe.id="iframe";
				document.body.appendChild(iframe );
			iframe = iframe.contentWindow || iframe.contentDocument;
			//data='sep=,\r\n'+data;
			iframe.document.open("data:text/csv;charset=utf-8,","replace");
			iframe.document.write(data);
			iframe.document.close();
			iframe.focus();
			iframe.document.execCommand('SaveAs',true,fileName+'.csv');
			document.body.removeChild(document.getElementById("iframe") );
		}else{
			 
	    		
	    	var uri = 'data:text/csv;charset=utf-8,' + escape(data);
		   	var link = document.createElement("a");    
   			link.href = uri;
	    		link.style = "visibility:hidden";
	    		link.download = fileName + ".csv";
       			document.body.appendChild(link);
	    		link.click();
	    		docume.body.removeChild(link);
		}
	};

};
