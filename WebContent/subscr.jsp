<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <!DOCTYPE HTML PUBLIC ".//W3C//DTD HTML 4.0 Transitional//EN">
  <HTML>
  <% response.setContentType("text/html; charset=windows-1251"); %>  
  <%@ page import="java.util.*" %>
  <%@ page import="lab_4.*" %> 
   <%@ page import="lab_4.appjdbc" %>

  <%
        String errors[] = elephantru.Error();
        String textes[] = elephantru.Subscription();

        // Параметр - код спонсора
        String spName="";
        String id = request.getParameter("spcode");
        Vector vv;
        Enumeration e;
        String rStr, qu, outStr;
        sql1 db;
        if (id.equals("null")) 
        {
          id=(String)(session.getAttribute("uCode"));        
          spName=(String)(session.getAttribute("uName"));        
          db = new sql1();
        }
        else 
        {
          if (id.equals("") )
          {
            // Если код спонсора отсутствует, устанавливаются
            // атрибуты и управление передается на сервлет страницы ошибки  
            request.setAttribute("error",errors[2]);
            request.setAttribute("return","register.jsp");
  %>
            <jsp:forward page="aerr"/>
  <%        
          }       
    
          // Проверка наличия кода спонсора в БД
          db = new sql1();
          qu = "SELECT name from sponsor where spons_id=" + id;
          vv = new Vector();
          try  { rStr=db.query(qu,vv); }
          catch (Exception ex) { }
          if (vv.isEmpty()) 
          {
            // Если кода спонсора нет в БД, устанавливаются
            // атрибуты и управление передается на сервлет страницы ошибки  
            db.close();
            request.setAttribute("error",errors[0]+id+errors[1]);
            request.setAttribute("return","register.jsp");
  %>
            <jsp:forward page="aerr"/>
  <%        
          }
          e=vv.elements();
          spName=(String)(e.nextElement());
        }

        // Код и имя спонсора устанавливаются атрибутами сеанса
        session.setAttribute("uCode",id);
        session.setAttribute("uName",spName);
  %>
  <HEAD>
  <meta http-equiv="Content-Type" content="text/html;charset=windows-1251">  
  <meta name="Owner" content="derev@insart.kharkov.ua">
  <%= elephantru.Header() %>
  <title> <%= elephantru.YourSubscr() %> </title>
  <! (C) Owner: AK >
  </head>
  <body background=./img/background.jpg>
  <p><img src=./img/elephantline.gif>
  <p><a href=index.html>
  <p><img src=./img/elemove2.gif border=0>Home</a>
  <h2 align=center><%= elephantru.YourSubscr() %></h2>
  <form action="thanks.jsp">
   <table align=center>
    <tr>
     <td width=47%>&nbsp;</td>
     <td width=6%>&nbsp;</td>
     <td width=47%>&nbsp;</td>
    </tr>
    <tr>
     <td align=right valign=top><%= elephantru.Eleph() %></td>
     <td>&nbsp;</td>
     <td>
      <select name="elephant">
       <option value="10000"> <%= textes[5] %>
       <%
         // Формирование списка Слонов 
         vv = new Vector();
         try  { rStr=db.query("SELECT eleph_id,name FROM elephant",vv); }
         catch (Exception ex) { }
         e=vv.elements();
         while (e.hasMoreElements())
           out.println("<option value=\"" +
                       (String)(e.nextElement()) + "\">" +
                       (String)(e.nextElement()));
       %>
      </select>
     </td>
    </tr>
    <tr>
     <td align=right valign=top><%= textes[0] %></td>
     <td>&nbsp;</td>
     <td>
      <select name="product">
       <option value="20000"><%= textes[6] %>
       <%
        // Формирование списка продуктов
        vv = new Vector();
        try  { rStr=db.query("SELECT prod_id,name FROM product",vv); }
        catch (Exception ex) { }
        e=vv.elements();
        while (e.hasMoreElements())
          out.println("<option value=\"" +
                      (String)(e.nextElement()) + "\">"+
                      (String)(e.nextElement()));
       %>
      </select>
     </td>
    </tr>
    <tr>
     <td align=center valign=top><%= textes[1] %>
      <input type="text" name="sum", value="">
     </td>
     <td align=center><%= textes[2] %></td>
     <td align=center valign=top><%= textes[3] %>
      <input type="text" name="quantity", value="">
     </td>
    </tr>
   </table>
   <p align=center>
   <input type="SUBMIT" name="send" value="<%= textes[4] %>"><br>
  </form>
  <p><img src=./img/elephantline.gif>
  </body>
  </html>