<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <base href="<%=basePath%>">  
      
    <title>图片</title>  
    <script src="../js/jquery-1.11.0.min.js"></script>
    <script src="../js/jquery-1.11.0.js"></script>
    <script src="../js/imgJcrop.js" type="text/javascript"></script>
  </head>

	<body>
		<div id="cropboxdiv">
			<img id="cropbox" src="">
		</div>
		<form id="uploadForm">
			<table>
				<tr>
					<a href="javascript:void(0);" class="file">选择文件 <input
						type="file" name="file" onchange="uploadImg()">
					</a>
				</tr>
			</table>
		</form>
		<input type="hidden" id="x" />
		<input type="hidden" id="y" />
		<input type="hidden" id="w" />
		<input type="hidden" id="h" />
		<input type="hidden" id="imgPath" />
		<input type="hidden" id="srcPath" />
		<input type="hidden" id="fileName" />
	</body>
</html> 