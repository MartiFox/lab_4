

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class maps
 */
@WebServlet("/maps")
public class maps extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public maps() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		
		response.setContentType("text/html;charset=windows-1251");
	      // получение выходного потока
	      PrintWriter out=response.getWriter();
	      // 1-я строка документа
	      String docType="<!DOCTYPE HTML PUBLIC \\"; 
		
		String outString;
		outString=docType+
		         "<HTML>" + "<HEAD>" +
		         "<title>Спасибо</title>"  +  
		         "</head>" + "<body>"  +
		       " <table align=right>"+
		      "  <tr><td align=center><a href=index.html>Home</a></td></tr>"+
		    " </table>"+
		    "<h1 align=center>World map</h1>"+
		    " <p align=center>Ukraine Map</p>"+
		    " <table align=center>"+
		     " <tr><td align=center><img src=images/blank_map.gif>  </td></tr>"+
		     "</table>"
		         + "</body>" + "</html>";         

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
