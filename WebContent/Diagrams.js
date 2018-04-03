
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DemoServlet
 */

google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
      function drawChart() {
        var data = google.visualization.arrayToDataTable([
['Color', 'Quantity'],
          ['Orange',     11],
          ['Black',      2],
          ['Red',  1],
          ['White', 2],
          ['Green',    7]
        ]);
        
        var options = {
                title: 'Quantity of colors',
                is3D: true
              }
        
        slices: {  4: {offset: 0.2},
            1: {offset: 0.3},
            2: {offset: 0.4},
            3: {offset: 0.5},
  }