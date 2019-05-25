<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath() + "/";
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>"/>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>无标题文档</title>
	<link href="<%=basePath %>resource/admin/css/style.css" rel="stylesheet" type="text/css" />
	 <link href="<%=basePath %>resource/admin/css/style.css" rel="stylesheet" type="text/css"/>
   <!-- 引入JQuery支持的库 -->
<script language="JavaScript" src="<%=basePath%>resource/admin/js/jquery.js"></script>
<!-- 引入artDailog支持的库 -->
<link rel="stylesheet" href="<%=basePath%>resource/admin/artDialog/css/ui-dialog.css">
<script language="JavaScript" src="<%=basePath%>resource/admin/artDialog/dist/dialog-plus.js"></script>
</head>
		

<body>
<form action="comm/selQt" method="post" rel="page">
<div class="place">
	<span>位置：</span>
	<ul class="placeul">
		<li><a href="#">经费预算管理</a></li>
		<li><a href="#">经费预算信息</a></li>
	</ul>
</div>
<div class="formbody">
	<div class="formtitle" style="cursor: pointer;" id="formtitle"><span>条件查询</span></div>
	<ul class="forminfo" style="display: none;" id="forminfo">
		<li>
			<label>项目名称</label>
			<input name="gsmc"  type="text" class="dfinput" placeholder="请输入标题" />
		</li>
		<li>
			<label>&nbsp;</label>
			<input name="" type="submit" class="btn" value="查询"/>
			<input name="" type="reset" class="btn" value="重置"/>
		</li>
	</ul>
</div>
		<table class="imgtable"border = "5" >
			<thead>
			<tr>
				<th>id</th>
				<th>项目名称</th>
				<th>预算金额</th>
				<th>剩余预算</th>
				<th>备注</th>
				<th>录入人</th>
				<th>录入日期</th>
				<th>操作</th>
			</tr>
			</thead>
			<tbody>
		<c:if test="${!empty list}">
			<c:forEach items="${list}" var="list" varStatus="vs">
			<tr>
				<td>${vs.index+1}</td>
				<td>${list.gsmc}</td>
				<td>${list.cjsl}</td>
				<td>${list.amount}</td>
				<td>${list.cjje}</td>
				<td>${list.lname}</td>
				<td>${list.lrsj}</td>
				<td>
					<a href = "comm/showQt?id=${list.id}"><font color="blue">编辑</font></a>
					<a href = "comm/deleteQt?id=${list.id}"><font color="blue">删除</font></a>
				</td>	
			</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty list}">
			<tr>
				<td colspan="8" align="center">
					<strong><font color="red">暂时没有数据，请添加</font></strong>
				</td>
			</tr>
		</c:if>
		</tbody>
		</table>
		<div class="rightinfo">
			<div class="tools">
				<ul class="toolbar1">
				<li class="click"><span><img src="<%=basePath %>resource/admin/images/t01.png"/></span><a href="index/comm/sc/add.jsp">添加经费预算信息</a></li>
				</ul>
			</div>
		</div>
		<div class="pagin">
	<ul class="paginList">
	<a href="comm/selQt?page=1"><font>首页</font></a>&nbsp;&nbsp;
		共<i class="blue">&nbsp;${count}&nbsp;</i>条记录，共&nbsp;<i class="blue"> ${countPage}&nbsp;</i>页
		&nbsp;&nbsp;
	<a href="comm/selQt?page=${lastPage}">尾页</a>
	</ul>
</div>
</form>

<script type="text/javascript" src="<%=basePath %>resource/admin/jQuery/jquery.js"></script>
<script type="text/javascript">
	/**
	 * 这个使用的单选框选择判断
	 */
	  function deleteUser(){
		alert("删除成功");
		}
	function toUpdate(){
		var id = $("input[name='id']:checked").val();
		if(!id){
			alert("请选择要操作的记录");
			return false;
		}else{
			alert( "您操作的值为："+$("input[name='id']:checked").val())
		}
	}
	$(function(){
		$('.tablelist tbody tr:odd').addClass('odd');
		
		$("#formtitle").click(function(){
			$("#forminfo").slideToggle("slow");
		});
	});
</script>
<script type="text/javascript">
	$(function(){
		$('.imgtable tbody tr:odd').addClass('odd');
	});
    
</script>

</body>

</html>

