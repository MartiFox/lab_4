  //===========================================================
  // ������� �������� "���� ��������"
  import java.util.*;
  import java.io.*;
  import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
  /**
   * Servlet implementation class diagrams
   */
  @WebServlet("/sponsors")
  public class sponsors extends HttpServlet
  {
    public void doGet(HttpServletRequest rq,
                      HttpServletResponse rs)
                      throws ServletException, IOException
    {
      // ������������ ��������� � ���������� ����� ��������
      String outStr=stdhead.mkHead("���������� ����� ���������!") +
        "<p><img src=elephantline.gif><br>\n" +
        "<table width=100%>\n" +
        "<tr>\n" +
        "<td>\n" +
        "<a href=index.html><img src=elemove2.gif border=0>Home</a>\n" +  
        "</td>\n" +
        "<td align=right>\n" +
        "<a href=register.html>��� ����������� �����\n" +
        "<img src=elemove2.gif border=0></a>\n" +
        "</td>\n" +
        "</tr>\n" +
        "<tr>\n" +
        "<td>\n" +
        "<h2 align=center><font color=red>���������� ����� ���������!</font></h2>\n" +
        "<table align=center>\n";
      // ������� �� �� ��������� � �� �������
      String qu = "SELECT name FROM data";
      Vector vv = new Vector();
      String rStr;
      // ���������� � ��
      sql db = new sql();
      try { rStr=db.query(qu,vv); }
      catch (Exception ex) { }
      db.commit(); db.close();
      // ������������ HTML-������� ��������� � ������� 
      Enumeration e = vv.elements();
      while (e.hasMoreElements())
      {
        outStr=outStr + "<tr><td>" +
                  (String)(e.nextElement()) +
                  "</td><td> - " +
                  (String)(e.nextElement()) +
                  "</td></tr>\n";
      }
      // ������������ ����������� �������������� ����� ��������
      outStr=outStr + "</table>\n" +
        "</td>\n" +
        "<td valign=bottom align=right><img src=sponsors.jpg></td>\n" +  
        "</tr>\n" +
        "</table>\n" +
        "<p><img src=elephantline.gif><br>\n" +
        "</body>\n" +
        "</html>";
      // ��������� ���� ��������
      rs.setContentType("text/html; charset=windows-1251");
      // ��������� ��������� ������ � ����� � ���� HTML-���������  
      PrintWriter out=rs.getWriter();
      out.println(outStr);
      out.close();
    }
  }