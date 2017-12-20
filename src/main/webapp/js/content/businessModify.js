function modify() {
	$("#mainForm").submit();
}
function opeanPic(){

    var styles = $("#imgs").css("display")
    if(styles == 'block'){
        $("#imgs").css("display","none")
    }else if(styles == 'none'){
        $("#imgs").css("display","block")
    }
}