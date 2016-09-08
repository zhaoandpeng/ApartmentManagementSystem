<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="/resource/bootstrap2.3.2/css/bootstrap.min.css" rel="stylesheet" />
    <link href="/resource/styles/Common.css" rel="stylesheet" />
    <link href="/resource/styles/Index.css" rel="stylesheet" />
    <link href="/resource/styles/Index2.css" rel="stylesheet" />
    <link href="/assets/css/lyz.calendar.css" rel="stylesheet" />
</head>
<div class="header">
        <img class="logo" src="/resource/images/logo.png" />
        <label class="logo-title">通用后台模板</label>
        <ul class="inline">
           <%--  <li><!-- &nbsp;&nbsp; -->
                <img src="/resource/images/32/client.png" id="img"/>欢迎您,${username}
            </li>
            <li class="dropdown">
                <a class="dropdown-toggle mymsg" data-toggle="dropdown" href="#"><img src="/resource/images/32/166.png" />&nbsp;&nbsp;修改个人信息<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="#">修改密码</a></li>
                </ul>

            </li> --%>
            <li>
                <img src="/resource/images/32/200.png" />&nbsp;&nbsp;<a class="loginout" href="/index.jsp">退出</a>
            </li>
        </ul>
</div>
<div class="nav">
        <ul class="breadcrumb">
            <li>
                <img src="/resource/images/32/5025_networking.png" />
            </li>
            <li><a href="#">首页</a> <span class="divider">>></span></li>
            <li class="active"></li>
        </ul>
</div>