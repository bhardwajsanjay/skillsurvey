var TGRByAPSLevel = function(data){
	this.style = new Styles();
	this.dataModel = data;
	this.content='';
	this.createNeedBlock=function(needBlock){
		
		
		var html ='<tr><td colspan="7"  style ="'+this.style.getStyleSurveyTemplateReportMatrixCourseHeaderFunction()+'">'+needBlock.needName+'</td></tr>';
		//for(var uoc in needBlock){
			html+=this.createUocDataBlock(needBlock.uocs);
		//}
		//html+='</table>';
		return html;
	};
	this.getNumberCount=function(data){
		var arr = data.split("=");
		var count = parseInt(arr[1]);
		return count;
	}
	this.createUocDataBlock=function(uocs){
		debugger;
		var html='';
		for(var uoc in uocs){
			var items = uocs[uoc].items;
			var len = items.length;
			html+='<tr ><td rowspan="'+(len+1)+'" style=" border-bottom:1px solid;solid;vertical-align:text-top;'+this.style.getStyleSurveyTemplateReportingUocName()+'">'+uocs[uoc].uocCourseName+'</td>';
			var c1=0;
			var c2=0;
		
			var t1=0;
			var d1=0;
			var d2=0;
			var t2=0;
			for(var i =0;i<len;i++){
				c1+= this.getNumberCount(items[i].cmpFemale);
				c2+= this.getNumberCount(items[i].cmpMale);
				t1+= this.getNumberCount(items[i].cmpAll);
				//
				d1+= this.getNumberCount(items[i].notCmpFemale);
				d2+= this.getNumberCount(items[i].notCmpMale);
				t2+= this.getNumberCount(items[i].notCmpAll);
				
				html+='<td style="'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">'+items[i].cmpFemale+'</td><td style="'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">'+items[i].cmpMale+'</td><td style="'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">'+items[i].cmpAll+'</td>';
				html+='<td style="'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">'+items[i].notCmpFemale+'</td><td style="'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">'+items[i].notCmpMale+'</td><td style="'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">'+items[i].notCmpAll+'</td>';
				if(i<len-1){
					html+='</tr><tr>';
				}
			}
			html+='</tr>';
			html+'<tr>';
			html+='<td style="border-top:1px solid;  border-bottom:1px solid;'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">TOTAL ='+c1+'</td><td style="border-top:1px solid; border-bottom:1px solid;'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">TOTAL ='+c2+'</td><td style=" border-top:1px solid; border-bottom:1px solid;'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">TOTAL = '+t1+'</td>';
			html+='<td style=" border-top:1px solid; border-bottom:1px solid;'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">TOTAL = '+d1+'</td><td style="border-top:1px solid;  border-bottom:1px solid;'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">TOTAL = '+d2+'</td><td style="border-top:1px solid;  border-bottom:1px solid;'+this.style.getStyleSurveyTemplateReportingUocNameCellvalue()+'">TOTAL = '+t2+'</td>';
			html+'</tr>';
		}
		
		
		return html;
	};
	this.generateHTMLFormat=function(){
		debugger;
		var headContent = this.dataModel.reportDetail;
		this.content ='<div><div><table cellpadding="0" cellspacing="0"><tr><td style="'+this.style.getReportingHeaderTextFormat()+'">Function:</td>'+
		'<td style="'+this.style.getReportingTextFormat()+'">'+headContent.functionDesc+'</td><td></td><td></td></tr>'+
		'<tr><td colspan="2" style="'+this.style.getReportingHeaderTextFormat()+'">Training Gap Report By:</td>'+
		'<td style="'+this.style.getReportingTextFormat()+'">'+headContent.reportBy+'</td>'+
		'</tr></table></div><div>';
		 this.content+='<table cellpadding="0" cellspacing="0" style="margin-left:1%;background-color:white;margin-bottom:4px; border:1px #000000 solid;width:90%;">';
			
	     this.content+='<tr><td style="'+this.style.getStyleSurveyTemplateReportingNoBorderCellHeader()+'">Unit or Competency Course Name</td><td style="'+this.style.getStyleSurveyTemplateReportingMiddleCellHeader()+'">Total number completed by APS Level - Female</td><td style="'+this.style.getStyleSurveyTemplateReportingMiddleCellHeader()+'">Total number completed by APS Level - Male</td>';
	     this.content+='<td style="'+this.style.getStyleSurveyTemplateReportingMiddleCellHeader()+'">Total number completed by APS Level</td><td style="'+this.style.getStyleSurveyTemplateReportingMiddleCellHeader()+'">Total number to be completed by APS Level - Female</td><td style="'+this.style.getStyleSurveyTemplateReportingMiddleCellHeader()+'">Total number to be completed by APS Level - Male</td>';
	     this.content+='<td style="'+this.style.getStyleSurveyTemplateReportingMiddleCellHeader()+'">Total number to be completed by APS Level</td></tr>';
			
		var content = this.dataModel.reportItems;	
		
		for(var need in content){
			this.content+=this.createNeedBlock(content[need]);
		}
		this.content+='</table></div>';
		return this.content;
	};



};
