import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
/**
 * Servlet implementation class thanks
 */
 @WebServlet("/pay2")

public class pay2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pay2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());

		response.setContentType("text/html;charset=windows-1251");
	      // получение выходного потока
	      PrintWriter out=response.getWriter();
	      // 1-я строка документа
	      String docType="<!DOCTYPE HTML PUBLIC \\"; 
		
		String outString;
		outString=docType+
		         "<HTML>" + "<HEAD>" +
		         "<title>Оплата</title>"  +  
		         "</head>" + "<body>"  +
		         "<p align=center><a href=index.html>Home</a>" +
		         "<p align=center>"+
		         "<H1 align=center><font color=black>Ваш спонсорский взнос</font></h1>+"
		         + "</body>" + "</html>";         

		String qu = "SELECT name, ss FROM data,";
		      Vector vv = new Vector();
		      String rStr;
		      // Соединение с БД
		      sql db = new sql();
		      try { rStr=db.query(qu,vv); }
		      catch (Exception ex) { }
		      db.commit(); db.close();
		      
		      
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
