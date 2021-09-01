var URL = "http://localhost:8089/api/accounts";
var accID;

$(document).ready(function() {
	
	var container = $("#container");

	container.on("click", ".account", function() {
	accID = account.id;
	localStorage.setItem("account", $(this).data("index"));
	window.location.assign("messages.html");
	
});

	$.ajax({
		url: URL + '/getAccountsForUser',
		type: "GET",
		success: function(accounts) {
			//var counter = 1;
			for (account of accounts) {
				container.append("<button class='account' data-index='" + account.id + "'>" + account.displayName + '</br>' + account.username + "</button>");
				//counter++;
			}
		}
	});
});