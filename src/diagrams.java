import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class diagrams
 */
@WebServlet("/diagrams")
public class diagrams extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=windows-1251");
	      // получение выходного потока

	      // 1-я строка документа
		 String outString=stdhead.mkHead("Построение диаграммы") +
				 "<table align=right>"+
			     "<tr><td align=center><a href=index.html>Home</a></td></tr>"+
			 "</table>"+
		         "<table align=center "
		         + "border='1'>"
				 
	   +"<caption class='bold'>Таблица значений</caption>"
				 ;
		
		String qu = "SELECT data_id, datat_id  FROM datatable";
	      Vector vv = new Vector();
	      String rStr;
	      // Соединение с БД
	      sql1 db = new sql1();
	      try { rStr=db.query(qu,vv); }
	      catch (Exception ex) { }
	      db.commit(); db.close();
   
	      // Формирование HTML-таблицы спонсоров и взносов 
	      Enumeration e = vv.elements();
	      while (e.hasMoreElements())
	      {
		        outString=outString + "<tr><td>" +
	                  (String)(e.nextElement()) +
	                  "</td><td> " +
	                  (String)(e.nextElement()) +
	                  "</td></tr>\n"
	                 ;
	      }
  
		 
		 outString=outString + 
				 "</table>"+
		"<table align=center>"+
		 "<tr>"+
		 "  <td>"+
		    "<h3 align=center>Тип диаграммы</h3>"+
		    "<form  action=diagramtypes.html>"+
		     "<p align=center>"+
		     "<select name='choice' >"+
		      "<option value='1'>Гистограмма "+
		      "<option value='2'>Круговая диаграмма "+
		      "<option value='3'>Кольцевая диаграмма "+
		      "<option value='4'>Линейчатая диаграмма"+
		      "<option value='5'>Диаграмма с областями "+
		      "<option value='6'>Точечная диаграмма "+
		      "<option value='7'>Лепестковая диаграмма "+
		      "<option value='8'>График"+
		     "</select>"+
		     "<input type='SUBMIT' name='select' value='Выбрать' onclick='functionname'> "+
		    "</form>"+
		   "</td>"+
		  "</tr>"+
		  "</table>";
		 
		 
		 
	      outString=outString + 
	  		        "</body>\n" +
	  		        "</html>";
	      
	      
		response.setContentType("text/html;charset=windows-1251");

	   // получение выходного потока
	      PrintWriter out=response.getWriter();
			 out.println(outString);
		      out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
