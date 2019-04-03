(function($) {
  "use strict"; 
    
	$(".custom-file-input").on("change", function() {
	  var fileName = $(this).val().split("\\").pop();
	  $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
	  readURL(this);
	});
	
	
	
	function readURL(input) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();
	        
	        reader.onload = function (e) {
	            $('#img-upload').attr('src', e.target.result);
	        }
	        
	        reader.readAsDataURL(input.files[0]);
	    }
	}
	
	$("#request-form-validate").submit(function(event) {
		 
	 	 event.preventDefault();
	    
	   
	    if($("#amount").val().length === 0)
	    {
	    	alert("you must enter an amount")
	    }
	    else if($("#type").val().length === 0)
	    {
	    	alert("you must enter the type of reimbursement request")
	    }
	    else if($("#description").val().length === 0)
	    {
	    	alert("you must enter a description")
	    }
	    else
	    {
		    
		 
		    var formObject = {
		    		'id': sessionStorage.getItem("ID"),
		    		'username' : sessionStorage.getItem("username"),
		    		'amount':  $("#amount").val(),
		    		'type' : $("#type").val(),
		    		'description': $("#description").val()
		    }
		    
		 
		 
		  /* Send the data using post and put the results in a div */
		  $.ajax({
		      url: "submit-reimbursement.do",
		      type: "POST",
		      dataType:"json",
		      data: formObject,
		      success: function(data)
		      {
		    	  console.log(data);
	
		      	if(data.success)
		      	{
		      		window.location.href = '/ReimbursementApplication/html/request-success.html';
		      	}
		      	else
		      	{
		      		alert(data.error);
			    }
		      	
		      },
		      error: function(){
		   
		      }  
		  });
	    }
	});
	    
})(jQuery);
