import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import java.io.IOException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class thanks
 */
 @WebServlet("/thanks")

public class thanks extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public thanks() {
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
	      // ��������� ��������� ������
	      PrintWriter out=response.getWriter();
	      // 1-� ������ ���������
	      String docType="<!DOCTYPE HTML PUBLIC \\"; 
		
		String outString;
		outString=docType+
		         "<HTML>" + "<HEAD>" +
		         "<title>�������</title>"  +  
		         "</head>" + "<body>"  +
		         "<p align=center><a href=index.html>Home</a>" +
		         "<p align=center>"+
		         "<H1 align=center><font color=red>������� �� ���������!</font></h1>+"
		         + "</body>" + "</html>";         

		         //		 outString="<h1 align=center><font color=red>������� �� ���������!</font></h1>";
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
