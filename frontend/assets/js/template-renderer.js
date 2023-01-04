function renderHeaderTemplate() {
	// load navigation name
	let template = $("#template-la-name").html()
	let rendered = Mustache.render(template, localAuthority)
	$("#name-nav").append(rendered)
	// load navigation logo
	template = $("#template-logo").html()
	rendered = Mustache.render(template, localAuthority)
	$("#logo-nav").append(rendered)
}

function renderMetaData() {
	let template = $("#meta-data-tmpl").html()
	let rendered = Mustache.render(template, localAuthority)
	$("head").append(rendered)
}