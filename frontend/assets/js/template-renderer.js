function renderNavTemplate() {
	let localAuthority = JSON.parse(localStorage.getItem("localAuthorityObj"))
	// load navigation name
	let template = $("#template-la-name").html()
	let rendered = Mustache.render(template, localAuthority)
	$("#name-nav").append(rendered)
	// load navigation logo
	template = $("#template-logo").html()
	rendered = Mustache.render(template, localAuthority)
	$("#logo-nav").append(rendered)
}