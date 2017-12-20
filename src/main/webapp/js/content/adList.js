$(function(){
    console.log("123123",$("#message").val())
    common.showMessage($("#message").val())
})
function search(currentPage) {
    console.log("1231",currentPage)
    $("#currentPage").val(currentPage)
    $("#mainForm").submit()
}
function removes(id){

    $("#id").val(id)
    $("#mainForm").attr("action",$("#basePath").val()+"/ad/remove");
    $("#mainForm").submit();
}
function update(id){
    $("#id").val(id)
    $("#mainForm").attr("action",$("#basePath").val()+"/ad/findById");
    $("#mainForm").submit();
}