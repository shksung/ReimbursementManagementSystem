
(function ($) {
	"use strict";

	if (sessionStorage) 
	{
		$("#username").empty();
		$("#dropdown").empty();
		$("#navbar").empty();
		// Store data
		if (sessionStorage.getItem("logged-in") == 1) 
		{
			var username = sessionStorage.getItem("username");
			$("#username").html(username + `<i class="fas fa-user-circle fa-fw"
			style="margin-left: 10px"></i>`);

			$("#dropdown").html(`<a href="/ReimbursementApplication/html/logout.html">Logout</a>`);

			if(sessionStorage.getItem("user-level") == 1)
			{
				$("#navbar").html(`<li class="nav-item" id="make-requests"><a class="nav-link" 
					href="/ReimbursementApplication/html/make-requests.html">Make A Request</a></li>
					<li class="nav-item" id="request-history"><a class="nav-link"
					href="/ReimbursementApplication/html/request-history.html">Request History</a></li>`);
			}
			else if(sessionStorage.getItem("user-level") == 2)
			{
				$("#navbar").html(`<li class="nav-item" id="view-all-tickets"><a class="nav-link"
					href="/ReimbursementApplication/html/all-reimbursements.html">View All Tickets</a></li>`);
			}
		} 
		else 
		{
			$("#username").html(`Not signed in <i class="fas fa-user-circle fa-fw"
				style="margin-left: 10px"></i>`);

			$("#dropdown").html(`<a href="/ReimbursementApplication/html/login.html">Login</a> 
				<a href="/ReimbursementApplication/html/register.html">Sign Up</a>`);
		}
	} 
})(jQuery);