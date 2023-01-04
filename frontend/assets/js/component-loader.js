function loadHeader(pathToContentDirectory) {
	$.ajax({
		type: "get",
		url: pathToContentDirectory + "/header.html",
		dataType: "html",
		success: function (header) {
			$("#navbar").html(header)
			renderHeaderTemplate()
		}
	})
}

function loadFooter(pathToContentDirectory) {
	$.ajax({
		type: "get",
		url: pathToContentDirectory + "/footer.html",
		dataType: "html",
		success: function (footer) {
			$("#footer").html(footer)
		}
	})
}

function loadNav(pathToContentDirectory) {
	$.ajax({
		type: "get",
		url: pathToContentDirectory + "/navigation.html",
		dataType: "html",
		success: function (nav) {
			$("#nav-container").html(nav)
		}
	})
}

function loadServices(pathToContentDirectory) {
	$.ajax({
		type: "get",
		url: pathToContentDirectory + "/service.html",
		dataType: "html",
		success: function (services) {
			$("#service-container").html(services)
		}
	})
}