(function ($) {
    "use strict";

    loadDatatable();

 var table = $('#dataTable').DataTable({
	  	"searching": true,
	  	"processing": false,
	  	"serverSide": false,
	    "lengthChange": false,
	    "paging":true,
	    "ordering": true,
	    "dom": 'lfr<"fixed_height"t>ip',
	    "pageLength": 10,
	    "columnDefs": [ 
		        {"type": "submitted", "width": "20%", "targets": 0},
		        {"type": "amount", "width": "20%", "targets": 1},
		        {"type": "type", "width": "20%", "targets": 2},
		        {"type": "description", "width": "20%", "targets": 3},
		        {"type": "status", "width": "20%", "targets": 4}],
	    "columns": [
			{"data": "Submitted"},
			{"data": "Amount"},
		 	{"data": "Type"},
		 	{"data": "Description"},
			{"data": "Status"}],
		"createdRow": function ( row, data, index ) {
			for(var i = 0; i < 5; i++)
        	{
        		var td = $('td', row).eq(i);
        		var txt = td.text();
        		td.attr("title", txt);
        	}
	    },
		"initComplete": function (settings, json) {
	    }
	  });

 function loadDatatable()
 {

 	var data = {
		  	"username": sessionStorage.getItem("username")
		 };
		 
		
 	$.ajax({
	  	  context: this,
	      "url": "get-my-requests.do",
	      "type": "POST",
	      "dataType": 'json',
	      data: data,
	      success: function(data)
	      {
	    	var d = data.r;
	    	console.log(data);
		  	var rows = [];
		  	
		  	for(var i = 0; i < d.length; i++)
		  	{
		  		var item = {};
		  		item["Submitted"] = "Submitted on " + d[i].submitted + " by " + d[i].author;
		  		item["Amount"] = d[i].amount;
		  		item["Type"] = d[i].type;
		  		item["Description"] = d[i].description;
		  		if(d[i].status == "pending")
		  		{
		  			item["Status"] = d[i].status;
		  		}
		  		else{
		  			item["Status"] = d[i].status + " on " + d[i].resolved + " by " + d[i].resolver;
		  		}
		  		rows.push(item);
		  	}
		  	
			table.clear().draw();
		   	table.rows.add(rows);
		   	table.columns.adjust().draw();
	      },
	      error: function(){
	          
	      },  
	      complete: function(){
		  }
	  	});
	}
 
 $('.dataTable tbody td').each(function(index){
	    var $this = $(this);
	    var titleVal = $this.text();
	    if (typeof titleVal === "string" && titleVal !== '') {
	      $this.attr('title', titleVal);
	    }
 	});
 
 
// $('[data-toggle="popover"]').popover({
//		container: 'body',
//	    placement : 'top',
//		trigger : 'hover',
//	    html : true,
//	    content: function() {
//	        // get the url for the full size img
//	        var url = $(this).prop('src');
//	        return '<img src="' + url + '">'
//	    }
//	 });
})(jQuery);