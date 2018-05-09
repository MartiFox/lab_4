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
	      // ��������� ��������� ������

	      // 1-� ������ ���������
		 String outString=stdhead.mkHead("���������� ���������") +
				 "<table align=right>"+
			     "<tr><td align=center><a href=index.html>Home</a></td></tr>"+
			 "</table>"+
		         "<table align=center "
		         + "border='1'>"
				 
	   +"<caption class='bold'>������� ��������</caption>"
				 ;
		
		String qu = "SELECT data_id, datat_id  FROM datatable";
	      Vector vv = new Vector();
	      String rStr;
	      // ���������� � ��
	      sql1 db = new sql1();
	      try { rStr=db.query(qu,vv); }
	      catch (Exception ex) { }
	      db.commit(); db.close();
   
	      // ������������ HTML-������� ��������� � ������� 
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
		    "<h3 align=center>��� ���������</h3>"+
		    "<form  action=diagramtypes.html>"+
		     "<p align=center>"+
		     "<select name='choice' >"+
		      "<option value='1'>����������� "+
		      "<option value='2'>�������� ��������� "+
		      "<option value='3'>��������� ��������� "+
		      "<option value='4'>���������� ���������"+
		      "<option value='5'>��������� � ��������� "+
		      "<option value='6'>�������� ��������� "+
		      "<option value='7'>����������� ��������� "+
		      "<option value='8'>������"+
		     "</select>"+
		     "<input type='SUBMIT' name='select' value='�������' onclick='functionname'> "+
		    "</form>"+
		   "</td>"+
		  "</tr>"+
		  "</table>";
		 
		 
		 
	      outString=outString + 
	  		        "</body>\n" +
	  		        "</html>";
	      
	      
		response.setContentType("text/html;charset=windows-1251");

	   // ��������� ��������� ������
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
