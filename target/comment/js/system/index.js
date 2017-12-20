function clickFirstMenu (element){
    //判断当前单机的节点是否为选中样式，如果已经是选中样式，不再出发单击
    if($(element).attr("class")!="on"){
        //将同级节点的选中样式清空
        $("#mainMenuUl").children().attr('class','');
        //将当前单击的节点置为选中样式
        $(element).attr('class','on');
        //加载二级菜单内容
        $("#menuDiv").html()
    }
}
/*
*
* 方法描述：单击二级菜单（页面左部菜单），初始化主页面
* */
function clickSecondMenu(element,path){
    console.log("-========element=========",$(element))
    //将其他选中的节点的样式清空
    $("#menuDiv a").removeClass("class","");
    //将当前单击的即诶单置为选中样式
    $(element).children().attr("class","on");
    //将主页面跳转到指定地址
    $("#mainPage").attr('src',path)
}
function showAdList(){
    console.log('1232131')
    $("#menuAdDiv").removeClass('hiddeList');
    $("#menuAdDiv").addClass("showList");
    $("#menuSysDiv").addClass("hiddeList");
    $("#menuSysDiv").removeClass("showList");
}
function showSysList() {
    $("#menuAdDiv").removeClass('showList');
    $("#menuAdDiv").addClass("hiddeList");
    $("#menuSysDiv").addClass("showList");
    $("#menuSysDiv").removeClass("hiddeList");
}