<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="https://www.google.com/jsapi"></script> /* Дополнительно для карты. */

google.charts.load('current', {'packages':['geochart']});
google.charts.setOnLoadCallback(drawChart);

drawChart()
var data = google.visualization.arrayToDataTable([
	['Country', 'Popularity'],
	          ['Sweden', 300],
	          ['United States', 300],
	          ['France', 400],
	          ['Canada', 500],
	          ['Spain', 500],
	          ['RU', 900]
	        ]);

var options = {
		title: 'Simple map' // Заголовок.
		};

var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));
chart.draw(data, options);
}

<div id="regions_div" style="width: 900px; height: 500px;"></div>