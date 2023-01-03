function loadNav(pathToContentDirectory) {
	$.ajax({
		type: "get",
		url: pathToContentDirectory + "/navbar.html",
		dataType: "html",
		success: function (header) {
			$("#navbar").html(header)
			renderNavTemplate()
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