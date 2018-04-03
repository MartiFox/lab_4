

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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	      
	      // 1-я строка документа
		 String outString=stdhead.mkHead("Построение диаграммы") +		
		         "<table align=center border='1'>"+
		   "<caption class='bold'>Таблица значений</caption>";
		
		String qu = "SELECT *, ss FROM data,";
	      Vector vv = new Vector();
	      String rStr;
	      // Соединение с БД
	      sql db = new sql();
	      try { rStr=db.query(qu,vv); }
	      catch (Exception ex) { }
	      db.commit(); db.close();
	      // Формирование HTML-таблицы спонсоров и взносов 
	      Enumeration e = vv.elements();
	      while (e.hasMoreElements())
	      {
	        outString=outString + "<tr><td>" +
	                  (String)(e.nextElement()) +
	                  "</td><td> - " +
	                  (String)(e.nextElement()) +
	                  "</td></tr>\n"+
	                  "</table>";
	      }
	      
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
