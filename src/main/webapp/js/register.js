(function ($) {
    "use strict";
	
	var request;
	 
	$(".login100-form-btn").on( "click", function(){
	 
	  	
	   var fields=$('.validate-form .input100');
	   var failed = false;
	   for(var i=0; i < fields.length; i++) {
            if(!validate(fields[i])){
            	failed = true;
            }
        }
        
        if(failed)
        {
	       return; 
	    }
	    
	   
	   var values = {
		"email": $("#email").val(),
		"uname": $("#uname").val(),
		"fullname": $("#fullname").val(),
		"pass": $("#pass").val(),
		};

	 
	  /* Send the data using post and put the results in a div */
	  $.ajax({
	      url: "register.do",
	      dataType:"json",
	      type: "POST",
	      data: values,
	      success: function(data)
	      {
	    	var response = JSON.parse(data);
	    	console.log(response);
	    	
//	 
	      	
	      	if(response.success)
	      	{
	      		window.location.href = "/ReimbursementApplication/html/register-success.html";
	      	}
	      	else
	      	{
		     	if(response['user-exists'])
		      	{
		      		var message = "Username " + values.username+ " already in use."; 
		      		showValidate($("#uname"), message);
		      	}
		      	if(response['email-exists'])
		      	{
		      		var message =  "Email " + values.email + " already in use."; 
		      		showValidate($("#email"), message);
		      	}
		    }
	      	
	      },
	      error: function(jqXHR, textStatus, errorThrown)
		  {
		  		console.log(errorThrown);
	      }  
	  });
	});
		
    
    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
           hideValidate(this);
        });
        
         $(this).blur(function(){
           validate(this);
        });
    });
    
    
    
    function validate(input)
    {
    	 var check = true;
    	 
    	if($(input).attr('name') == 'email')
    	{
    		if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
           		showValidate(input, "Valid email is required: ex@abc.xyz");
           		check = false;
           }
    	}
    	else if($(input).attr('name') == 'uname')
    	{
    		if($(input).val().length < 8) {
           		showValidate(input, "Username must be 8 characters long.");
           		check = false;
           }
    	}
    	else if($(input).attr('name') == 'pass')
    	{
    		if($(input).val().length < 8) {
           		showValidate(input, "Password must be 8 characters long.");
           		check = false;
           }
    	}
    	else if($(input).attr('name') == 'passRe')
    	{
    		if($(input).val() != $('#pass').val()) {
           		showValidate(input, "Passwords do not match.");
           		check = false;
           }
    	}
    	else if($(input).attr('name') == 'fullname')
    	{
    		if($(input).val().length < 4) {
           		showValidate(input, "Please enter your full name.");
           		check = false;
           }
    	}
    	
    	return check;
   	}
    
    
    function showValidate(input, message) {
		var thisAlert = $(input).parent();
		thisAlert.attr("data-validate", message);
		
        $(thisAlert).addClass('alert-validate');
    }
    
    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }
    
})(jQuery);
