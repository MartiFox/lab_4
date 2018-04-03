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
 @WebServlet("/pay")

public class pay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public pay() {
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
		         "<H1 align=center><font color=black>Ваш спонсорский взнос</font></h1>"
		         ;         


		      
		   // Формирование статической заключительной части страницы
		      outString=outString + 
		    " <table align=center>"+
		       "<tr>"+
		       "<td width='100'>"+"<input type='text' name='one' value='' class=\"filterInput\"> "+"</td>"+
		       "<td>&nbsp;</td>"+
		       "<td>"+
		       "</tr>"+
		       "</table>"+
		       "<div align=center>"+
		        "<form>"+
		         "<select name='subscr'>"+
		         "<option value='20000'>UAN"+
		          "<option value='20001'>USD"+
		          "<option value='20002'>EUR"+
		        " </select>"+
		        "</form>"+
		        "</div>"+
		        "<div align=center>"+
		        "<form action='thanks'>"+
		         "<input type='SUBMIT' name='send' value='Подтвердить'><br>"+
		        "</form>"+
		         "</div>"+  
		        "</body>\n" +
		        "</html>";
		   // Получение выходного потока и вывод в него HTML-документа  
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
