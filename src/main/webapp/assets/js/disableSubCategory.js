function disable(subcategoryCode){
    let url = "/admin/subcategories/disable/"+subcategoryCode;
    $.post(url, function (){
        $("#disableButton_"+subcategoryCode).hide();
        $("#status_"+subcategoryCode).text("Inativa");
    });
}