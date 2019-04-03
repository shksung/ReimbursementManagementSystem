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
	    		{"type": "id", "width": "5%", "targets": 0},
		        {"type": "submitted", "width": "20%", "targets": 1},
		        {"type": "amount", "width": "15%", "targets": 2},
		        {"type": "type", "width": "10%", "targets": 3},
		        {"type": "description", "width": "20%", "targets": 4},
		        {"type": "status", "width": "20%", "targets": 5},
		        {"type": "approve", "width": "10%", "targets": 6, "orderable": false}],
	    "columns": [
	    	{"data": "ID"},
			{"data": "Submitted"},
			{"data": "Amount"},
		 	{"data": "Type"},
		 	{"data": "Description"},
			{"data": "Status"},
			{"data": "Approve"}],
		"createdRow": function ( row, data, index ) {
//            var td4 = $('td', row).eq(4);
//            var img = td4.text();
//            td4.empty();
//            td4.append('<img src="' + img + '" alt="" class="receipt-img" data-toggle="popover">');
			for(var i = 0; i < 7; i++)
        	{
        		var td = $('td', row).eq(i);
        		var txt = td.text();
        		td.attr("title", txt);
        	}
			
			
        	var status = $('td', row).eq(5).text();
        	if(status == "pending")
        	{
        		 var td6 = $('td', row).eq(6);
                 td6.empty();
                 
                 var appr = $('<button/>', {
                	class: 'btn btn-success btn-xs',
                	text: '+',
                	name: $('td', row).eq(0).text()
                 });
                 appr.bind("click", function(event){
                	console.log("approve");
             	   
             	    approveDeny( "approved", $(this).attr('name'));
                 });
                 appr.appendTo(td6);
                 
                 var deny = $('<button/>', {
                 	class: 'btn btn-danger btn-xs',
                 	text: '-',
                 	name: $('td', row).eq(0).text()
                  });
                  deny.bind("click", function(event){
                 	console.log("deny");
              	    
              	    approveDeny( "denied", $(this).attr('name'));
                  });
                  deny.appendTo(td6);
        	}
        	
        	
	    },
		"initComplete": function (settings, json) {
			 $('#pending-filter').prependTo("#dataTable_wrapper");
	    }
	  });
 
 function loadDatatable()
 {
	
	 console.log($('input[name="pendingopt"]:checked').val());
	 
	var data = {
		"pending-only": $('input[name="pendingopt"]:checked').val()
	};
	
 	$.ajax({
	      "url": "get-all-reimbursements.do",
	      "type": "POST",
	      "dataType": 'json',
	      data: data,
	      success: function(data)
	      {
	    	  var d = data.reimbursements;
	    	  console.log(data);
		  	var rows = [];
		  	
		  	for(var i = 0; i < d.length; i++)
		  	{
		  		var item = {};
		  		item["ID"] = d[i].id;
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
		  		item["Approve"] = '';
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
 
 $("input[name=pendingopt]:radio").change(function () {
	 console.log("ok");
	 loadDatatable();
 });
 
 
 $('.dataTable tbody td').each(function(index){
	    var $this = $(this);
	    var titleVal = $this.text();
	    if (typeof titleVal === "string" && titleVal !== '') {
	      $this.attr('title', titleVal);
	    }
 	});
 
 function approveDeny( action, id)
 {
	 
	var data = {
		"id": id,
		"action": action,
		"username": sessionStorage.getItem("username")
	};
	
 	$.ajax({
	      "url": "approve-deny.do",
	      "type": "POST",
	      "dataType": 'json',
	      data: data,
	      success: function()
	      {
	    	  
	      },
	      error: function(){
	          
	      },  
	      complete: function(){
	    	  loadDatatable();
		  }
	  	});
	 
	}

 $('.dataTable tbody td').each(function(index){
 	var $this = $(this);
 	var titleVal = $this.text();
 	if(typeof titleVal === "string" && titleVal !== ''){
 		$this.attr('title', titleVal);
 	}
});
 
 $('[data-toggle="popover"]').popover({
	container: 'body',
    placement : 'top',
	trigger : 'hover',
    html : true,
    content: function() {
        // get the url for the full size img
        var url = $(this).prop('src');
        return '<img src="' + url + '">'
    }
 });
})(jQuery);