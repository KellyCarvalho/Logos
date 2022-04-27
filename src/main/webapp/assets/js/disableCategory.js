function disable(categoryCode){
    let url = "/admin/categories/disable/"+categoryCode;
    $.post(url, function (){
        $("#disableButton_"+categoryCode).hide();
        $("#status_"+categoryCode).text("Inativa")
    });
}