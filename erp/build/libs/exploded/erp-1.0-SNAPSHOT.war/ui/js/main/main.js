$(function(){

	$('#sidebarMenu').on('click','.menuTree',function(){
		var that = $(this);
		var url = that.attr('url')
		if(url){
			$('#every_module').attr('src',url+'.html');
		}
	});
});