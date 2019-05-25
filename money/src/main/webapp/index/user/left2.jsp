<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath() + "/";
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>" target="rightFrame"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>课题研究经费管理系统</title>
	<link href="<%=basePath %>resource/admin/css/style.css" rel="stylesheet" type="text/css" />
</head>

<body style="background:#f0f9fd;">

<div class="lefttop"><span></span>管理信息</div>
<dl class="leftmenu">
	<dd>
		<div class="title">
			<span><img src="<%=basePath %>resource/admin/images/leftico01.png" /></span>系统管理
		</div>
		<ul class="menuson">
			<li><cite></cite><a href="index/admin/password/updatePassword.jsp">密码管理</a><i></i></li>
		</ul>   
	</dd>
	<dd>
		<div class="title">
			<span><img src="<%=basePath %>resource/admin/images/leftico01.png" /></span>个人信息管理
		</div>
		<ul class="menuson">
			<li><cite></cite><a href="user/selUserXx">个人信息</a><i></i></li>
		</ul>   
	</dd>
	<dd>
		<div class="title">
			<span><img src="<%=basePath %>resource/admin/images/leftico01.png"/></span>公告管理
		</div>
		<ul class="menuson">
			<li><cite></cite><a href="comm/selGg">公告管理</a><i></i></li>
		</ul> 
	</dd>
	<dd>
		<div class="title">
			<span><img src="<%=basePath %>resource/admin/images/leftico01.png"/></span>消息管理
		</div>
		<ul class="menuson">
			<li><cite></cite><a href="comm/selXx">消息管理</a><i></i></li>
		</ul> 
	</dd>
	<dd>
		<div class="title">
			<span><img src="<%=basePath %>resource/admin/images/leftico01.png"/></span>申报经费管理
		</div>
		<ul class="menuson">
			<li><cite></cite><a href="comm/selDd">申报经费管理</a><i></i></li>
		</ul> 
	</dd>
	<dd>
		<div class="title">
			<span><img src="<%=basePath %>resource/admin/images/leftico01.png"/></span>经费预算管理
		</div>
		<ul class="menuson">
			<li><cite></cite><a href="comm/selQt">经费预算管理</a><i></i></li>
		</ul> 
	</dd>
	
	
</dl>

<script type="text/javascript" src="<%=basePath %>plugins/jQuery/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	
</script>
</body>
</html>
