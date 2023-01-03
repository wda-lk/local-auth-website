var localAuthority = JSON.parse(localStorage.getItem("localAuthorityObj"))
var localAuthorityId = localStorage.getItem("localAuthorityId")
var hostName = `${document.location.protocol}//${document.location.hostname}`

if (localStorage.getItem("districtId") == null || localAuthority == null) {
	window.location.href = "./index.html"
}