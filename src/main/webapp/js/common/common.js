//因为自己写的共通方法也算是小型第三方插件，为了防止方法名和其他第三方的发生冲突，我们要写一个命名空间
//避免和其他命名空间相同的产生污染
var common=window.common ||{}

common.showMessage = function(msg){
    if(msg){
        alert(msg);
    }
}
common.ajax=function(param){
    var mergeParam = $.extend({
        timeout:10000
    },param);
    $.ajax(mergeParam);
}
common.pageCode= {
    ADD_SUCCESS:1000,
    UPDATE_SUCCESS:1200,
    DELETE_SUCCESS:1100
}