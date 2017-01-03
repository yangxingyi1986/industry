$(document).bind('click',function(){
	$('#mapalert').css('display','none'); 
}); 

function clickalert(e){
	$("#mapalert").css("left",e.offsetX);
	$("#mapalert").css("top",e.offsetY);
	$('#mapalert').css("display","block");
	
	stopPropagation(e);
}