

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class help
 */
@WebServlet("/help")
public class help extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public help() {
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
		 String outString=stdhead.mkHead("Построение диаграммы") +	
				 "<h2 align=center>Регистрация </h2>"+

				 "<table align=right>"+
				     "<tr><td align=center><a href=index.html>Home</a></td></tr>"+
				 "</table>"+

				"<p><table align=center>"+
				 " <tr>"+
				   "<td width=50% align=center>Введите свой логин"+
				  " <p><form>"+
				  "  <input type='text' name='spcode', value=''>"+
				   " </form>"+
				    "<p><form action='pay'>"+
				    " <input type='SUBMIT' name='sendcode' value='OK'>"+
				   " </form>"+
				  " </td>"+
				   "<td width=50% align=center>Если Вы впервые на сайте, введите логин для регистрации"+
				    "<p><form>"+
				    " <input type='text' name='spname', value=''>"+
				    "</form>"+
				    "<p><form action='registration.html'>"+
				     "<input type='SUBMIT' name='sendname' value='OK'>"+
				    "</form>"+
				   "</td>"+
				 "</tr>"+
				 "</table>";
		 
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
