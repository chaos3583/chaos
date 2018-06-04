$(function(){
	//图片上传的onchange事件，用于上传并回显图片  
    function uploadImg() {    
         var formData = new FormData($( "#uploadForm" )[0]);   
         $.ajax({    
              url: 'img/uploadImag' ,    
              type: 'POST',    
              data: formData,    
              async: false,    
              cache: false,    
              contentType: false,    
              processData: false,    
              success: function (returndata) {   
                  var json=eval("("+returndata+")");  
                  var cropboxdiv=$('#cropboxdiv');//图片回显区域  
                  $('#cropboxdiv').empty();  
                  $("<img id='cropbox' src='"+json.path+"/"+json.fileName+"'>").appendTo(cropboxdiv);  
                  $('#imgPath').val(json.path+"/"+json.fileName);  
                  $('#srcPath').val(json.path);  
                  $('#fileName').val(json.fileName);  
                  $('#cropbox').Jcrop({//初始化Jcrop图片裁剪器  
                    maxSize : [ 150, 170 ],  
                    minSize: [135,135],  
                    onSelect : function(c){//图片选择事件  
                        $('#x').val(c.x);  
                        $('#y').val(c.y);  
                        $('#w').val(c.w);  
                        $('#h').val(c.h);  
                    }  
                });  
              }   
         });    
    }  
    //图片裁剪区域选好之后，交给java后台进行裁剪  
    function cutImg(){  
            $.ajax({      
                    type:"post",  
                    url:"img/cutImg",  
                    cache:false,  
                    data:{  
                        x:$('#x').val(),  
                        y:$('#y').val(),  
                        w:$('#w').val(),  
                        h:$('#h').val(),  
                        srcPath:$('#srcPath').val(),  
                        fileName:$('#fileName').val()  
                    },  
                    success:function(r){  
                        $('#imgPath').val($('#srcPath').val()+"/"+$('#fileName').val());  
                    }  
            });  
    }  
})