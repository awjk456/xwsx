<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
	<head>
	    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	    <title>星网视讯后台管理</title>
	    <link rel="stylesheet" type="text/css" href="${basePath}/css/login.css?a=2" />
	    <link rel="stylesheet" type="text/css" href="${basePath}/css/jquery.validate.css" />
	    <script src="${basePath}/js/common/jquery-1.8.3.js" type="text/javascript"></script>
	    <script src="${basePath}/js/common/jQuery.md5.js" type="text/javascript"></script>
	    <script src="${basePath}/js/validation/jquery.validate.min.js" type="text/javascript"></script>
	    <script src="${basePath}/js/validation/messages_zh.js" type="text/javascript"></script>
	    <script src="${basePath}/js/common/common.js" type="text/javascript"></script>
	    <script src="${basePath}/js/system/login.js" type="text/javascript"></script>
	</head>
	<body>
		<input type="hidden" id="basePath" value="${basePath}"/>
		<input type="hidden" id="message" value="${pageCode.msg}"/>
		<div class="main">

		    <div class="content">
		        <div class="title"><div class="title-name">用户登陆</div></div>
		        <form id="mainForm" method="post" action="${basePath}/login/validate" class="input_content">
		            <fieldset>
		                <div class="input">
		                    <input class="input_all name" name="name" id="name" placeholder="用户名" type="text" onFocus="this.className='input_all name_now';" onBlur="this.className='input_all name'"/>
		                </div>
		                <div class="input" class="input_password">
		                	<input type="hidden" name="password" id="password_md5"/>
		                    <input class="input_all password" id="password" type="password" placeholder="密码" onFocus="this.className='input_all password_now';" onBlur="this.className='input_all password'"/>
		                </div>

		                <div class="enter">

		                   <input class="button_login hide" type="button" id="submit_login" value="登 录" />

		                </div>
		            </fieldset>
		        </form>
		    </div>
		</div>
	</body>
</html>