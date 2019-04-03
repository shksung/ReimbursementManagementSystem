
(function ($) {
    "use strict";


    var request;

    
    $("#login-form-validate").submit(function(event) {
	 
	 	 event.preventDefault();
	    // abort any pending request
	    if (request) {
	        request.abort();
	    }
	    /* stop form from submitting normally */
	  	
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
		"password": $("#pass").val()
		};
		
	 
	  /* Send the data using post and put the results in a div */
	  $.ajax({
	      url: "login.do",
	      dataType:"json",
	      type:"POST",
	      data: values,
	      success: function(data)
	      {	
	    	  var response = data;
	    	  console.log(response);

	      	if(response['success'])
	      	{
	      		var user = response['user']
	      		sessionStorage.setItem("username", user['username']);
	      		sessionStorage.setItem('ID', user['id']);
	      		sessionStorage.setItem("logged-in", 1);
	      		console.log(response);
	      		
	      		let num ;
	      		switch(user['type']) {
	      		
	      		case 'employee':
	      			num = 1
	      			break;
	      		case 'admin':
	      			console.log("Here")
	      			num=2
	      			break;
	      		}
	      		sessionStorage.setItem("user-level", num);
	      		
	      		
	      		
	      		if(num == 2)
	      		{
	      			console.log("sup")
	      			window.location.href = "/ReimbursementApplication/html/all-reimbursements.html";
	      		}
	      		else
	      		{
	      			window.location.href = "/ReimbursementApplication/html/make-requests.html";
	      		}
	      	}
	      	else
	      	{
		      	var message = "The username or password you entered was incorrect.	Please try again"; 
		      	showValidate($("#email"), message);
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
    	 
    	if($(input).attr('name') == 'pass')
    	{
    		if($(input).val().length < 8) {
           		showValidate(input, "Password must be 8 characters long.");
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