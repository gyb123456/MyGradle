<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>主页</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 
	 
 
	<style>
		.pdfobject-container { height: 500px;}
		.pdfobject { border: 1px solid #666; }
	</style>
  </head>
  
  <body>
	<div  class="alert alert-info" align="center" role="alert">
	   <h2>Welcome GYB Website</h2>
	</div>
	 
    <div class="container-fluid">
	    <button class="btn btn-primary" onclick="preViewPDF()">用PDFObject预览PDF</button>
		<div id="pdfDiv"></div>
		<p class="bg-danger">
			说明：一般用PDFObject来解析pdf文件（还可预览pic、txt文件）。但是当浏览器不兼容时（如：IE、猎豹），它会调用下面这个按钮的功能<br/>
			下面的这个按钮可以兼容各种浏览器（但是只能解析PDF文件）
			<a target="_blank" href="http://blog.csdn.net/gyb_csdn/article/details/52598375">用法网址-我的博客</a>
		</p>
		<button class="btn btn-primary" onclick="pdfjs()">直接用PDFJs预览PDF</button>
	</div>
	<hr style="height:4px;border:none;border-top:4px groove #0066CC;" />
	
	
	 
	
  </body>
  
</html>

