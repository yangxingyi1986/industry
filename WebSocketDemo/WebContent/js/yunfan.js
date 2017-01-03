//阻止时间传递
function stopPropagation(e){
	if(e.stopPropagation){
		e.stopPropagation();
	}else{
		e.cancelBuddle=true;
	}
}