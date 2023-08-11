<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="f"%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>

<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>

<script src="https://code.jquery.com/jquery-3.6.2.js"
	integrity="sha256-pkn2CUZmheSeyssYw3vMp1+xyub4m+e+QK4sQskvuo4="
	crossorigin="anonymous"></script>

<link rel="stylesheet"
	href="http://cdn.datatables.net/1.13.1/css/jquery.dataTables.min.css">

<script
	src="http://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
<link rel="stylesheet" href="stylesheet.css">
<meta charset="ISO-8859-1">
<title>20052038BaboucarrBalajo</title>

<h2 id="title"> Film review</h2>
</head>
<body>
	<div class="container">
		<table id="tableBody" class="table table-dark" width="100%">
			<thead>
				<tr>
					<th class="th-sm"></th>
					<th class="th-sm"></th>
					<th class="th-sm"></th>
					<th class="th-sm"></th>
					<th class="th-sm"></th>
					<th class="th-sm"></th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<td id="resultRegion"></td>
					<td id="resultRegion"></td>
					<td id="resultRegion"></td>
					<td id="resultRegion"></td>
					<td id="resultRegion"></td>
					<td id="resultRegion">Select data type to insert</td>
				</tr>
			</tbody>
		</table>
		<div id="dataType">
			<label>Server Data Type:</label> <select id="dataFormat">
				<option id="dataType" value="application/json" selected="selected">JSON</option>
				<option id="dataType" value="application/xml">XML</option>
				<option id="dataType" value="text/plain">String</option>
			</select> <input type="button" value="Show Films"
				onclick="showTable('dataFormat', 'resultRegion')"></input>
		</div>
	</div>

	<script src="./scripts/ajax.js" type="text/javascript"></script>
	<script src="./scripts/ajax-utils.js" type="text/javascript"></script>
</body>
</html>