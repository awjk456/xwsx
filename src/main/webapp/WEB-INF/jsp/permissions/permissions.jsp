<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE"/>
    <title></title>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/all.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/pop.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/main.css"/>
    <link rel="stylesheet" type="text/css" href="${basepath}/css/zTreeStyle/zTreeStyle.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/jquery.validate.css"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/premissions/premissions.css?s=423"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/premissions/demo.css?a=333"/>
    <link rel="stylesheet" type="text/css" href="${basePath}/css/premissions/rmenu.css"/>

    <script type="text/javascript" src="${basePath}/js/common/jquery-1.8.3.js"></script>
    <script src="${basePath}/js/validation/jquery.validate.js" type="text/javascript"></script>
    <script src="${basePath}/js/validation/messages_zh.js" type="text/javascript"></script>
    <script type="text/javascript" src="${basePath}/js/common/common.js?a=232"></script>
    <script type="text/javascript" src="${basePath}/js/common/zTree/jquery.ztree.all.js"></script>
    <script type="text/javascript" src="${basePath}/js/premissions/premissions.js?a=422">s</script>
    <style type="text/css">

    </style>

</head>
<body style="background: #e1e9eb;">
    <div class="right">
        <div class="current">当前位置：<a href="###">系统管理</a> &gt; 权限管理</div>
        <input type="hidden" value="${basePath}" id="basePath"/>
        <div class="zTreeDemoBackground">
            <ul id="user" class="ztree">

            </ul>
        </div>
        <div class="zTreeDemoBackground1">
            <ul id="group" class="ztree">

            </ul>
        </div>
        <div class="zTreeDemoBackground2">
            <ul id="menu" class="ztree">

            </ul>
        </div>
    </div>
</form>
    <!-- 蒙板 -->
    <div class="window" id="cover"><div class="tip"></div></div>
    <!-- 用户维护页面 -->
    <div class="wishlistBox" id="userMaintain" style="display:none;left:450px;top:150px;">
        <div class="personRigTop persongBgimg" style="height:200px;width:480px;">
            <div class="persongRightTit" style="width:480px;" id="userTitle">&nbsp;&nbsp;新增用户</div>
            <div class="persongRigCon">
                <form id="userForm">
                    <table class="x-form-table">
                        <tbody>
                        <tr class="line">
                            <td class="left" width="10%"><em class="required">*</em><label>用户名：</label></td>
                            <td width="90%">
                                <input type="hidden" id="userId"/>
                                <input type="text" class="normal-input" id="userName" name="userName" style="width: 240px;"/>
                            </td>
                        </tr>
                        <tr class="line">
                            <td class="left"><em class="required">*</em><label>中文名：</label></td>
                            <td>
                                <input type="text" class="normal-input" name="chName" id="chName" style="width: 240px;"/>
                            </td>
                        </tr>
                        <tr class="line">
                            <td class="left"><label>密码：</label></td>
                            <td>默认密码与用户名相同。<font color="red">请创建后立刻登录修改默认密码！</font></td>
                        </tr>
                        <tr>
                            <td class="left"></td>
                            <td class="submit">
                                <input class="tabSub" type="button" value="提交" onclick="saveUser();"/>
                                <input class="tabSub" type="reset" value="关闭" onclick="closeUser();"/>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>


   <!--=======用户组右侧菜单 ======= -->
    <div id="userMenu" class="rMenu" onmouseout="divOut();" onmouseover="divOver()">
        <ul class="rMenuUi">
            <li class="rMenuLi" onmousemove="move(this);" onclick="initAddUser();">新增</li>
            <li class="rMenuLi disabled" onmousemove="move(this);" onclick="initModifyUser();">修改</li>
            <li class="rMenuLi disabled" onmousemove="move(this);" onclick="removeUser();">删除</li>
        </ul>
    </div>

    <!--  ============= 用户组右侧菜单 =============    -->
    <div id="groupMenu" class="rMenu" onmouseout="divOut();" onmousemove="divOver()">
        <ul class="rMenuUi">
            <li class="rMenuLi" onmousemove="move(this);" onclick="initAddGroup();">新增</li>
            <li class="rMenuLi disabled" onmousemove="move(this);" onclick="initModifyUser();">修改</li>
            <li class="rMenuLi disabled" onmousemove="move(this);" onclick="removeUser();">删除</li>
        </ul>
    </div>

    <!-- 菜单组右侧菜单 -->
    <div id="menuMenu" class="rMenu" onmouseout="divOut();" onmouseover="divOver()">
        <ul class="rMenuUi">
            <li class="rMenuLi menuClass" onmousemove="move(this);" onclick="initAddMenu();">新增菜单</li>
            <li class="rMenuLi disabled menuClass" onmousemove="move(this);" onclick="initAddAction();">新增动作</li>
            <li class="rMenuLi disabled" onmousemove="move(this);" onclick="modifyOfMenu();">修改</li>
            <li class="rMenuLi disabled" onmousemove="move(this);" onclick="removeOfMenu();">删除</li>
        </ul>
    </div>
</body>
</html>