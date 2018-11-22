$('.sexs').click(function(e){
  e.stopPropagation();
  $(this).addClass('xz');
  $(this).find('div').addClass('stop');
  $(this).siblings('.sexs').find('div').removeClass('stop');
  $(this).siblings('.sexs').removeClass('xz');
  $('.search').hide();//搜索隐藏
  $('.seacont').hide();//内容隐藏
  $('.zhe').show();//遮罩显示
  // 分类选择列表
  var type = $(this).attr('type');
  if(type == 1){
    $('.singlelist').show();
    $('.doublelist').hide();
  }else {
    $('.doublelist').show();
    $('.singlelist').hide();
  }
})

$('.zhe').click(function(){
  $('.search').show();//搜索显示
  $('.seacont').show();//内容显示
  $('.zhe').hide();//遮罩隐藏
  $('.sexs').find('div').removeClass('stop');
  $('.sexs').removeClass('xz');
  $('.doublelist').hide();
  $('.singlelist').hide();

})
