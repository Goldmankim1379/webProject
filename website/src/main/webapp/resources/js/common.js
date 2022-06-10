
//input file 추가 삭제 작동 버튼 
function addFileInput(type,tId){	
	var fileCnt = $("#"+tId+" input[type=file]").last().attr("id").replace("userfile","");
	if(type=="M" && Number(fileCnt)==1){ 
		alert("더이상 지울 수 없습니다.");
		return false;
	}	
	var fileCntVal = Number(fileCnt)+1;	
	//alert(addInputText);
	if(type=="P"){ 
		var addInputText = "<input type='file' id='userfile"+fileCntVal+"' name='userfile"+fileCntVal+"'  onchange=\"uploadImg_Change(this.value,'userfile"+fileCntVal+"')\"/><br/>";
		$("#"+tId).append(addInputText);
		//SetContainerAutoHeight();
	}else{ 
		 $("#"+tId+" input[type=file]").last().remove();
		 $("#"+tId+" br").last().remove();
	}		
	return false;
}



function uploadImg_Change(value,gId){
	  //alert(value.slice(value.toLowerCase()));
	   var str_loc  = value.lastIndexOf(".");
	   var file_ext = value.substring(str_loc+1); 
	   file_ext = file_ext.toLowerCase();
	   if(value == null || value =="") return;
	   existExt ="exe,asp,aspx,php,jsp,cs,js,bat,css,htm,html,htm,eml,xml,msi,dll";
		 existExtArray =existExt.split(",");
	    for(var i=0; i<existExtArray.length; i++){
	      if(file_ext ==existExtArray[i]){
	        alert("허용되지 않은 확장자 입니다. 그림및 문서파일만 가능합니다.");
	        //alert(document.form1.gId.value);
	        document.getElementById(gId).value = "";
	        document.getElementById(gId).focus();
	        document.getElementById(gId).select();
	        document.selection.clear();
	        
	        return false;
	    }		          
		}		 
} 

