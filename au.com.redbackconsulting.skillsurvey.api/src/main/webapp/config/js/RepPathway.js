var RepExtra = function(data){
	this.style = new Styles();
	this.dataModel = data;
	this.content='';
	this.createHeadBlock=function(head,hBlock){
		
		
		var html ='<tr><td colspan="5"  style ="'+this.style.getStyleSurveyTemplateReportMatrixCourseHeaderFunction()+'">'+head+'</td></tr>';

			html+=this.createHeadDataBlock(hBlock);
		
		return html;
	};
	
	this.createHeadDataBlock=function(hBlock){
		//debugger;
		var html='';
		var i='';
		var len = hBlock.length;
		for(var i=0;i<hBlock.length;i++){
				html+='<td style="'+this.style.getStyleSurveyTemplateReportingUocNameCellvaluePathway()+'">'+hBlock[i].sub+'</td><td style="'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">'+hBlock[i].cntFemale+'</td><td style="'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">'+hBlock[i].cntMale+'</td><td style="'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">'+hBlock[i].cntOther+'</td>';
				html+='<td style="'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">'+hBlock[i].cntAll+'</td>';
				if(i <len-1){
					html+='</tr><tr>';
				}
		}
		
		return html;
	};
	
	this.generateHTMLFormat=function(){
		//debugger;
		//var headContent = this.dataModel.reportDetail;
		this.content ='<div><div><table cellpadding="0" cellspacing="0"><tr><td style="'+this.style.getReportingHeaderTextFormat()+'">Function:</td>'+
		'<td style="'+this.style.getReportingTextFormat()+'">'+this.dataModel.functionName+'</td><td></td><td></td></tr>'+
	
		'</table></div><div>';
		 this.content+='<table cellpadding="0" cellspacing="0" style="margin-left:1%;background-color:white;margin-bottom:4px; border:1px #000000 solid;width:90%;">';
			
	     this.content+='<tr><td></td><td style="'+this.style.getStyleSurveyTemplateReportingNoBorderCellHeader()+'">Total number of females:</td><td style="'+this.style.getStyleSurveyTemplateReportingMiddleCellHeader()+'">Total number of males:</td><td style="'+this.style.getStyleSurveyTemplateReportingMiddleCellHeader()+'">Total number of others:</td>';
	     this.content+='<td style="'+this.style.getStyleSurveyTemplateReportingMiddleCellHeader()+'">Total number for function:</td></tr>';
			
		var content = this.dataModel;//.reportItems;	
		
		//for(var need in content){
			this.content+=this.createHeadBlock("HIGHER EDUCATION",content.higherEducationItems);
			this.content+=this.createHeadBlock("LEARNING PATHWAY",content.pathwayItems);

		//}
		this.content+='</table></div>';
		return this.content;
	};
	this.exportToCVSFormat=function(){
		
	    
	    var CSV = '';    
	   
	   CSV +='"Function  : "'+ this.dataModel.functionName + '\r\n';
	  // CSV +='"Training Gap Report By : "' +headContent.reportBy + '\r\n\n';
	    
	   
	        var row = "";
	        row += ' ,';
	            row += 'Total number of females,';
	            row +=  'Total number of males,';
	            row +=  'Total number of others,';
	            row +=  'Total number for function,';
	            

	        row = row.slice(0, -1);
	        
	        //append Label row with line break
	        CSV += row + '\r\n\n';
	   row='';
         var content = this.dataModel;
          var heitems = content.higherEducationItems;
          row+='HIGHER EDUCATION';
           CSV += row + '\r\n\n';
	   for(var i=0;i<heitems.length;i++){
row='';
var sub = heitems[i].sub;
sub =sub.replace(",", "-");
			row+= sub +",";
			row+= heitems[i].cntFemale+",";
			row+=heitems[i].cntMale+",";
			row+=heitems[i].cntOther+",";
			row+=heitems[i].cntAll+",";
 CSV += row + '\r\n\n';

	   }
         // CSV += row + '\r\n\n';
           var heitems = content.pathwayItems;
row='';
          row+='LEARNING PATHWAY';
           CSV += row + '\r\n\n';
	   for(var i=0;i<heitems.length;i++){
  row='';
		
	var sub = heitems[i].sub;
sub =sub.replace(",", "-");
			row+= sub +",";
			row+= heitems[i].cntFemale+",";
			row+=heitems[i].cntMale+",";
			row+=heitems[i].cntOther+",";
			row+=heitems[i].cntAll+",";
 CSV += row + '\r\n\n';

	   }

	    //debugger;
	  
	    	


	    if (CSV == '') {        
	        alert("Invalid data");
	        return;
	    }   
	    
	    this.downloadCSVFile(CSV, {reportBy:this.dataModel.functionName});
	};	
	
	this.downloadCSVFile=function(data,headContent){
	     
			var isIE = false || !!document.documentMode;
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