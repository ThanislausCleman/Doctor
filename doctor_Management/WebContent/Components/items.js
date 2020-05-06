$(document).ready(function() {
	if ($("#alertSuccess").text().trim() == "") {
		$("#alertSuccess").hide();
	}
	$("#alertError").hide();
});

//SAVE ============================================ 
$(document).on("click", "#btnSave", function(event) { 
	// Clear alerts---------------------
							 $("#alertSuccess").text("");
							$("#alertSuccess").hide();
							 $("#alertError").text("");
							 $("#alertError").hide();
	 
	 // Form validation------------------- 
var status = validateItemForm();
if (status != true) 
{ 
	$("#alertError").text(status);
	$("#alertError").show(); 
	return; 
	}
	 
	 // If valid------------------------ 
var type = ($("#hidDocIDSave").val() == "") ? "POST" : "PUT"; 

$.ajax( 
		{  
	url : "doctorServiceAPI",  
	type : type,  
	data : $("#formdoctorService").serialize(),  
	dataType : "text",  
	complete : function(response, status) 
	{   
		onItemSaveComplete(response.responseText, status);
		} 
});

});




//save response
function onItemSaveComplete(response, status) 
{  
	if (status == "success")  
	{   
		var resultSet = JSON.parse(response); 

if (resultSet.status.trim() == "success")  
{    
	$("#alertSuccess").text("Successfully saved.");
	$("#alertSuccess").show();
	
	$("#divItemsGrid").html(resultSet.data);   
	} else if (resultSet.status.trim() == "error")  
	{   
		$("#alertError").text(resultSet.data);    
		$("#alertError").show();
		} 
	

} else if (status == "error")  
{   
	$("#alertError").text("Error while saving.");   
	$("#alertError").show();  
	} else  
	{   
		$("#alertError").text("Unknown error while saving..");   
		$("#alertError").show();
		} 


$("#hidDocIDSave").val("");  
$("#formdoctorService")[0].reset(); 

}






//UPDATE========================================== 
$(document).on("click",".btnUpdate",function(event) {
			$("#hidDocIDSave").val($(this).closest("tr").find('#hidDocIDUpdate').val());
			$("#Name").val($(this).closest("tr").find('td:eq(0)').text());
			$("#Specialization").val($(this).closest("tr").find('td:eq(1)').text());
			$("#Mobile").val($(this).closest("tr").find('td:eq(3)').text());
			$("#DoctorFee").val($(this).closest("tr").find('td:eq(5)').text());
		}); 



//Remove(request algorithm)=========================================
$(document).on("click", ".btnRemove", function(event)
		{  
	$.ajax(  
			{   
				url : "doctorServiceAPI",   
				type : "DELETE",   
				data : "DID=" + $(this).data("DID"),   
				dataType : "text",   
				complete : function(response, status)   
				{    
					onItemDeleteComplete(response.responseText, status);   
					}  
			}); 
});



//delete response algorithm
function onItemDeleteComplete(response, status) 
{  
	if (status == "success")
	{   
		var resultSet = JSON.parse(response); 

if (resultSet.status.trim() == "success")   
{    
	$("#alertSuccess").text("Successfully deleted.");    
	$("#alertSuccess").show(); 

 $("#divItemsGrid").html(resultSet.data); 
 } else if (resultSet.status.trim() == "error")   
 {    
	 $("#alertError").text(resultSet.data);    
	 $("#alertError").show();   
	 } 

} else if (status == "error")  
{   
	$("#alertError").text("Error while deleting.");   
	$("#alertError").show(); 
	} else  
	{   
		$("#alertError").text("Unknown error while deleting..");   
		$("#alertError").show();  
		} 
	}










//CLIENTMODEL========================================================================= 
function validateItemForm() {  
	
	if ($("#Name").val().trim() == "") 
	{
		return "Insert Doctor name.";
		}
	 
	if ($("#Specialization").val().trim() == "")
	{ 
		return "Insert Doctor Specialization."; 
		}
	
	
	
	if ($("#Mobile").val().trim() == "") 
	{
		return "Insert Doctor Mobile.";
		}
	
	 // is numerical value  
	var tmpmob = $("#Mobile").val().trim();  
	if (!$.isNumeric(tmpmob))  
	{   
		return "Insert a numerical value for Mobile."; 
		} 
	
	
	
	if ($("#DoctorFee").val().trim() == "") 
	{
		return "Insert Doctor DoctorFee.";
		}
	 
	 // is numerical value  
	var tmpfee = $("#DoctorFee").val().trim();  
	if (!$.isNumeric(tmpfee))  
	{   
		return "Insert a numerical value for DoctorFee."; 
		} 
	 
	 // convert to decimal price  
	$("#DoctorFee").val(parseFloat(tmpfee).toFixed(2)); 
	 
	 
	 
	 return true; 
	 } 













