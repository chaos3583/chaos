$(function(){

	$("#login").click(function(){
		$.ajax({
			  type: "POST",//方法类型
	          dataType: "json",//预期服务器返回的数据类型
	          url: "/login" ,//url
	          data: $('#form').serialize(),
	          success: function (result) {
	              console.log(result);
	              if (result==1) {
	            	  location.href="/chaos/index";
	              }else{
	            	  alert("fail");
	              }
	          },
	          error : function() {
	              alert("异常！");
	          }
		})
	})
	
	$("#downModel").click(function(){
		$.ajax({
			  type: "POST",//方法类型
	          dataType: "json",//预期服务器返回的数据类型
	          url: "/chaos/downModel" ,//url
	          data:{},
	          success: function (result) {
	              console.log(result);
	              if (result==1) {
	            	  location.href="/chaos/index";
	              }else{
	            	  alert("fail");
	              }
	          },
	          error : function() {
	              alert("异常！");
	          }
		})
	})
})
