  //===========================================================
  // Сервлет страницы "Наши спонсоры"
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
      // Формирование заголовка и постоянной части страницы
      String outStr=stdhead.mkHead("Благодарим наших спонсоров!") +
        "<p><img src=elephantline.gif><br>\n" +
        "<table width=100%>\n" +
        "<tr>\n" +
        "<td>\n" +
        "<a href=index.html><img src=elemove2.gif border=0>Home</a>\n" +  
        "</td>\n" +
        "<td align=right>\n" +
        "<a href=register.html>Ваш спонсорский взнос\n" +
        "<img src=elemove2.gif border=0></a>\n" +
        "</td>\n" +
        "</tr>\n" +
        "<tr>\n" +
        "<td>\n" +
        "<h2 align=center><font color=red>Благодарим наших спонсоров!</font></h2>\n" +
        "<table align=center>\n";
      // Выборка из БД спонсоров и их взносов
      String qu = "SELECT name FROM data";
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
        outStr=outStr + "<tr><td>" +
                  (String)(e.nextElement()) +
                  "</td><td> - " +
                  (String)(e.nextElement()) +
                  "</td></tr>\n";
      }
      // Формирование статической заключительной части страницы
      outStr=outStr + "</table>\n" +
        "</td>\n" +
        "<td valign=bottom align=right><img src=sponsors.jpg></td>\n" +  
        "</tr>\n" +
        "</table>\n" +
        "<p><img src=elephantline.gif><br>\n" +
        "</body>\n" +
        "</html>";
      // Установка типа контента
      rs.setContentType("text/html; charset=windows-1251");
      // Получение выходного потока и вывод в него HTML-документа  
      PrintWriter out=rs.getWriter();
      out.println(outStr);
      out.close();
    }
  }