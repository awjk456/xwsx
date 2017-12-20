$(function () {

    $("#mainForm").validate({
        rules:{
            "title":"required",
            "link":"required",
            "weight":{
                required:true,
                digits:true
            }
        },
        message:{

        }
    })
})
function opeanPic(){

    var styles = $("#imgs").css("display")
    if(styles == 'block'){
        $("#imgs").css("display","none")
    }else if(styles == 'none'){
        $("#imgs").css("display","block")
    }
}
function update() {
    $("#mainForm").submit();
}
function check(){
    return true;
}