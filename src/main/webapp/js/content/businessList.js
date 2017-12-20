function remove(id){
    $("#mainForm").attr("action",$("#basePath").val()+"/businesses/"+id);
    $("#mainForm").submit();
}

function update(id){
    console.log("林森11")
    $("#mainForm").attr("method","GET");
    $("#hiddenMethod").val("GET");
    $("#mainForm").attr("action",$("#basePath").val()+"/businesses/"+id);
    $("#mainForm").submit();
    //location.href=$("#basePath").val()+"/businesses/?id="+id;
}