function getRequestObject() {
	if (window.XMLHttpRequest) {
		return (new XMLHttpRequest());
	} else if (window.ActiveXObject) {
		return (new ActiveXObject("Microsoft.XMLHTTP"));
	} else {
		return (null);
	}
}

function htmlInsert(id, htmlData) {
	$('table').DataTable().clear();
	document.getElementById(id).innerHTML = htmlData;
}

function getValue(id) {
	return (escape(document.getElementById(id).value));
}

function ajaxPost(address, format, responseHandler) {
	var request = getRequestObject();
	request.onreadystatechange = function(request) { findHandler(format, request, resultRegion) };

	request.open("POST", address, true);
	request.setRequestHeader("Content-Type", format);
	request.send(address);
}

function ajaxResult(address, format, responseHandler) {
	var request = getRequestObject();
	request.onreadystatechange = function() {
	responseHandler(request); };
	request.open("GET", address, true);
	request.setRequestHeader("Accept", format);
	request.send(null);
}

function getBodyContent(element) {
	element.normalize();
	return (element.childNodes[0].nodeValue);
}

function getTable(headings, rows) {
	var table = "<table border='1' class='ajaxTable'>\n" +
		getTableHeadings(headings) +
		getTableBody(rows) +
		"</table>";
	return (table);
}

function getTableHeadings(headings) {
	var firstRow = "  <tr>";
	for (var i = 0; i < headings.length; i++) {
		firstRow += "<th>" + headings[i] + "</th>";
	}
	firstRow += "</tr>\n";
	return (firstRow);
}

function getTableBody(rows) {
	var body = "";
	for (var i = 0; i < rows.length; i++) {
		body += "  <tr>";
		var row = rows[i];
		for (var j = 0; j < row.length; j++) {
			body += "<td>" + row[j] + "</td>";
		}
		body += "</tr>\n";
	}
	return (body);
}