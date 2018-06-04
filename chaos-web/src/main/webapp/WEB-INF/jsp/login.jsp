<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>  
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">  
<html>  
  <head>  
    <base href="<%=basePath%>">  
      
    <title>登录页</title>  
    <!-- <meta http-equiv="pragma" content="no-cache">  
    <meta http-equiv="cache-control" content="no-cache">  
    <meta http-equiv="expires" content="0">      
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">  
    <meta http-equiv="description" content="This is my page">   -->
    <script src="../js/jquery-1.11.0.min.js"></script>
    <script src="../js/jquery-1.11.0.js"></script>
    <script src="../js/login.js" type="text/javascript"></script>
  </head>  
    
  <body>  
   <form id="form">  
     用户名：<input name="userName" type="text" value=""/><br>  
     密码：<input name="password" type="text" value=""/>  
   </form>  
   	<a id="login">登录</a> 
  </body>  
</html> 