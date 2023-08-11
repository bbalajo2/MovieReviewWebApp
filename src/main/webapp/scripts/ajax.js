function getFilmsTable(rows) {
	var headings = ["Id", "Title", "Year", "Director", "Stars", "Review"];
	return (getTable(headings, rows));
}

/*Retrieve the data type*/
function showTable(dataType, resultRegion) {
	var format = getValue(dataType);
	console.log(format);
	var address = "filmsControllerAPI?format=" + format;
	var responseHandler = dataHandler(format);
	ajaxResult(address, format,
		function(request) {
			responseHandler(request, resultRegion);
		});
}
/*Loading JSON TABLE*/
function jsonTable(request) {
	if ((request.readyState == 4) && (request.status == 200)) {
		var rawData = request.responseText;
		var data = eval("(" + rawData + ")");
		var rows = new Array();
		for (var i = 0; i < data.length; i++) {
			var films = data[i];
			rows[i] = [films.id, films.title, films.year, films.director, films.stars, films.review];
		}

		var table = $('#tableBody').DataTable({
			columns: [
				{ data: 'id' },
				{ data: 'title' },
				{ data: 'year' },
				{ data: 'director' },
				{ data: 'stars' },
				{ data: 'review' }
			]
		});
		table.clear();
		table.rows.add(rows);
		table.draw();
	}
}

/*Loading STRING table*/
function stringTable(request) {
  if ((request.readyState == 4) &&
      (request.status == 200)) {
    var rawData = request.responseText;
    var rowStrings = rawData.split(/[\n\r]+/);
    var rows = new Array(rowStrings.length - 1);
    console.log(rows);
    for (var i = 1; i < rowStrings.length; i++) {
      rows[i - 1] = rowStrings[i].split("#");
    }

    var table = $('#tableBody').DataTable({
      columns: [
        { data: '0' },
        { data: '1' },
        { data: '2' },
        { data: '3' },
        { data: '4' },
        { data: '5' }
      ]
    });
    
    table.clear();
    table.rows.add(rows);
    table.draw();
  }
}

/*Loading XML table */
function xmlTable(request) {
  if ((request.readyState == 4) &&
      (request.status == 200)) {
    var xmlDocument = request.responseXML;

    if (xmlDocument) {
      var films = xmlDocument.getElementsByTagName("film");
      var rows = new Array(films.length);
      var subElementNames = ["id", "title", "year", "director", "stars", "review"];
      for (var i = 0; i < films.length; i++) {
        rows[i] = getElementValues(films[i], subElementNames);
      }
      var table = getFilmsTable(rows);
      htmlInsert(resultRegion, table);
    }
  }
}


function dataHandler(format) {
	if (format == "application/xml") {
		return (xmlTable);
	} else if (format == "application/json") {
		return (jsonTable);
	} else {
		return (stringTable);
	}
}

$(document).ready(function() {
	$('#tableBody').DataTable();
	$('.dataTables_length').addClass('bs-select');
});
