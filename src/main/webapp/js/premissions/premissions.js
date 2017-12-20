

function addDiyDomByUser(treeId, treeNode) {
    var spaceWidth = 5;
    var switchObj = $("#" + treeNode.tId + "_switch"),
        icoObj = $("#" + treeNode.tId + "_ico");
    switchObj.remove();
    icoObj.before(switchObj);
    if (treeNode.level > 1) {
        var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
        switchObj.before(spaceStr);
    }
}
function addDiyDomByGroup(treeId, treeNode) {
    var spaceWidth = 5;
    var switchObj = $("#" + treeNode.tId + "_switch"),
        icoObj = $("#" + treeNode.tId + "_ico");
    switchObj.remove();
    //icoObj.before(switchObj);
    if (treeNode.level > 1) {
        var spaceStr = "<span style='display: inline-block;width:" + (spaceWidth * treeNode.level)+ "px'></span>";
        switchObj.before(spaceStr);
    }
}

function beforeClickByUser(treeId, treeNode) {
    if (treeNode.level == 0 ) {
        var zTree = $.fn.zTree.getZTreeObj("user");
        zTree.expandNode(treeNode);
        return false;
    }
    return true;
}
function beforeClickByGroup(treeId, treeNode) {
    if (treeNode.level == 0 ) {
        var zTree = $.fn.zTree.getZTreeObj("group");
        zTree.expandNode(treeNode);
        return false;
    }
    return true;
}
$(document).ready(function(){
    initUser();
    initGroup();
    initMenu();
    $("#userForm").validate({
        rules : {
            "userName" : "required",
            "chName" : "required"
        }
    });

});
function initUser(){
    common.ajax({
        url:$("#basePath").val()+"/users",
        success:function(result,status){
           var zTree_Menu = null;
            var setting = {
                view: {
                    showLine: true,
                    selectedMulti: false,
                    dblClickExpand: true,
                },
                data: {
                    simpleData: {
                        enable: true
                    },
                    key:{
                        name:'chName'
                    }
                },
                callback: {
                    //beforeClick: beforeClickByUser
                    onRightClick: userRightCilck
                }
            };
            var treeObj = $("#user");
            result.push({ id:0, pId:0, chName:"用户", open:true});
            $.fn.zTree.init(treeObj, setting, result);

        }
    })
}
function initGroup(){
    common.ajax({
        url:$("#basePath").val()+"/group",
        success:function(result,status){
            var setting = {
                check : {
                    enable : true,
                    chkStyle : "radio"
                },
                view : {
                    dblClickExpand : true,// 定义双击展开
                    showLine : true,
                    selectedMulti : false
                },
                data : {
                    simpleData : {
                        enable : true
                    }
                },
                callback : {

                    onRightClick : groupRightClick
                }
            };
            var treeObj = $("#group");
            result.push({ id:0, pId:0, name:"用户组", open:true,nocheck:true})
            $.fn.zTree.init(treeObj, setting, result);
       }
    })
}
function initMenu(){
    common.ajax({
        url:$("#basePath").val()+"/menu",
        success:function(data,status){
            //console.log("======menu======",result)
            var setting = {
                edit : {
                    enable : true,
                    showRemoveBtn : false,
                    showRenameBtn : false,
                    drag : {
                        isCopy : false
                    }
                },
                check : {
                    enable : true
                },
                view : {
                    dblClickExpand : true,// 定义双击展开
                    showLine : true,
                    selectedMulti : false
                },
                data : {
                    simpleData: {
                        enable: true,
                        pIdKey: "parentId"
                    }
                },
                callback:{
                    onRightClick: menusRightClick
                }
            };
            data.push({id:"0",name:"菜单",open:true,nocheck:true});
            $.fn.zTree.init($("#menu"), setting, data);
        }
    })
}

/**
 * 菜单组右键点击方法
 */

function menusRightClick(event, treeId, treeNode){
    if(!treeNode){
        return;
    }
    $.fn.zTree.getZTreeObj(treeId).selectNode(treeNode);
    rightClick(event,'menuMenu');
    if(treeNode.id === 0) {
        $(".disabled").hide();
    } else {
        $(".disabled").show();
    }
}

/**
 * 用户右键点击方法
 */

function userRightCilck(event, treeId, treeNode){
    if(!treeNode){
        return;
    }
    $.fn.zTree.getZTreeObj(treeId).selectNode(treeNode);
    rightClick(event,'userMenu');
    if(treeNode.id === 0) {
        $(".disabled").hide();
    } else {
        $(".disabled").show();

    }
}

/**
 * 用户组右键点击方法
 * @param event
 * @param treeId
 * @param treeNode
 */
function groupRightClick(event, treeId, treeNode){
    if(!treeNode){
        return;
    }
    $.fn.zTree.getZTreeObj(treeId).selectNode(treeNode)
    rightClick(event,"groupMenu")
    if(treeNode.id === 0) {
        $(".disabled").hide();
    } else {
        $(".disabled").show();
    }
}

/**
 * 右键菜单列表样式生成
 * @param event
 * @param rMenuId
 */
function rightClick(event,rMenuId){
    $("#"+rMenuId).css({
        "top":event.clientY+"px",
        "left":event.clientX+"px",
        "visibility" : "visible"
    })
}

/**
 * 鼠标移入样式
 * @param element
 */
function move(element) {
    $(".rMenuLiMove").addClass("rMenuLi");
    $(".rMenuLiMove").removeClass("rMenuLiMove");

    $(element).addClass("rMenuLiMove");
    $(element).removeClass("rMenuLi");
}
/**
 * 鼠标划出右键菜单层时，去除“鼠标经过菜单时”的样式。
 */
function divOut() {
    $("body").bind("mousedown", mousedown);

    $(".rMenuLiMove").addClass("rMenuLi");
    $(".rMenuLiMove").removeClass("rMenuLiMove");
}

/**
 * 鼠标在弹出层上方时，解除鼠标按下的事件
 */
function divOver() {
    $("body").unbind("mousedown", mousedown);
}
/**
 * 单击鼠标事件：
 * 在页面任意地方单击鼠标，关闭右键弹出的菜单
 */
function mousedown() {
    $("#userMenu").css({
        "visibility" : "hidden"
    });
    $("#groupMenu").css({
        "visibility" : "hidden"
    });
    $("#menuMenu").css({
        "visibility" : "hidden"
    });
}

/**
 * 初始化新增用户功能
 */
function initAddUser() {
    mousedown();
    clearUser();
    $("#userTitle").html("&nbsp;&nbsp;新增用户");
    $("#cover").show();
    $("#userMaintain").show();
}

/**
 * 清空就数据
 */
function clearUser() {
    $("#userId").val("");
    $("#userName").val("");
    $("#chName").val("");
}

/**
 * 新增或修改的保存
 */
function saveUser(){
    if($("#userForm").valid()){
        if($("#userId").val()){
            common.ajax({
                url:$("#basePath").val()+"/users/"+$("#userId").val(),
                type:"POST",
                data:{
                    "name":$("#userName").val(),
                    "chName":$("#chName").val(),
                    "_method":"PUT"
                },
                success:function(data){
                    if(data.code == common.pageCode.UPDATE_SUCCESS){
                        initUser();
                        closeUser();
                    }
                    common.showMessage(data.msg)
                }
            })
        }else{
            common.ajax({
                url:$("#basePath").val()+"/users",
                type:"POST",
                data:{
                    "name":$("#userName").val(),
                    "chName":$("#chName").val(),
                    "password":$("#userName").val()
                },
                success:function(data){
                    if(data.code === common.pageCode.ADD_SUCCESS){
                        initUser();
                        closeUser();
                    }
                    common.showMessage(data.msg)
                }
            })
        }
    }
}

/**
 * 关闭按钮
 */
function closeUser(){
    $("#cover").hide();
    $("#userMaintain").hide();
}

function initModifyUser(){
    mousedown();
    getUser();
    $("#userTitle").html("&nbsp;&nbsp;修改用户");
    $("#cover").show();
    $("#userMaintain").show();
}
function getUser(){
    var nodes = $.fn.zTree.getZTreeObj("user").getSelectedNodes();
    $("#userName").val(nodes[0].name)
    $("#chName").val(nodes[0].chName);
    $("#password").val(nodes[0].password);
    console.log(nodes[0].id)
    $("#userId").val(nodes[0].id)
    console.log("========nodes=========",nodes)
}

function resetPassword(){
    mousedown();
    getRestPassword();
    $("#cover").show();
    $("#userRestPassword").show();
}
function getRestPassword(){
    var nodes = $.fn.zTree.getZTreeObj("user").getSelectedNodes();
    $("#userRestId").val(nodes[0].id)
    $("#passowrd").val("")
}
function removeUser(){
    var nodes = $.fn.zTree.getZTreeObj("user").getSelectedNodes();
    if(confirm("您确定要删除用户【"+nodes[0].name+"】吗？")){
        common.ajax({
            url:$("#basePath").val()+"/users/"+nodes[0].id,
            type:"POST",
            data:{
              "_method":"DELETE"
            },
            success:function(data){
                if(data.code === common.pageCode.DELETE_SUCCESS){
                    initUser();
                }
                common.showMessage(data.msg)
            }
        })

    }
}