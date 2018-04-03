//=================================================================
  // Класс JDBC-операций для сервлетов
  import java.util.*;
  import java.sql.*;

  
  class sql
  {
    // URL и имя драйвера CloudscapeDB
    static String url="jdbc:postgresql://localhost:5432/visual;create=false";
//    static String drName="COM.cloudscape.core.RmiJdbcDriver";
    Connection con=null; // соединение

    //------------------------------------------------------
    // Конструктор. Устанавливает соединение с БД
    sql() 
    {
      // регистрация драйвера и соединение с БД
    	Properties prop = new Properties();
    	prop.setProperty("user", "postgres");
        prop.setProperty("password", "0");
        

//    	try { Class.forName(drName);  }
//      catch (SQLException e) { System.exit(0);  }
//      try { con=DriverManager.getConnection(url); }
//      catch (SQLException e) { System.exit(0);  }
//      try { con.setAutoCommit(false); }
//      catch (SQLException e) { System.exit(0);  }
    }        

    //------------------------------------------------------
    // метод выполнения любого SQL-запроса
    // stmt - текст SQL-запроса 
    // reply - вектор, в который метод заносит результат выборки
    // Возвращаемое значение - текст сообщения
    public String query(String stmt,Vector reply) 
                       throws SQLException
    {
      int i, n, l;    // рабочие переменные
      String s2;   
      String repString=new String(""); // выходная строка
      boolean errorFlag=false;  // признак ошибки

      Statement st=con.createStatement(); // создание объекта оператора   
      // выделение 1-го слова из текстаоператора
      l=stmt.indexOf(" ",0);
      if (l>=0) s2=stmt.substring(0,stmt.indexOf(" ",0));
      else s2=stmt;
      if (s2.equalsIgnoreCase("select")) 
      {
        ResultSet rs=null;  // объект для результатов выборки
        // выполнение оператора SELECT
        try { rs=st.executeQuery(stmt); }
        catch (SQLException e)
        { 
          // в случае ошибки в вых.строку выводится код ошибки
          repString=new String("Error Code = "+e.getErrorCode());
          errorFlag=true;
        }
        // если ошибки не было - выбираем результаты
        if (!errorFlag)
        {
          // метаданные результата выборки
          ResultSetMetaData mtd = rs.getMetaData(); // метаданные результата 
          int colNumb = mtd.getColumnCount();    // число столбцов
          int cType[] = new int[colNumb];        // типы данных в столбцах
          // заполнение информации о столбцах
          for (n=0; n<colNumb; n++) 
            cType[n]=mtd.getColumnType(n+1);
          Object col = null;  // объект, в который выбираются данные из столбца  
          String sCol=new String("");    // строковое представление объекта
          // выборка данных
          while ( rs.next() )
          {
            String str = new String("");
            // перебираются столбцы данной строки
            for (n=0; n<colNumb; n++) 
            {
              // применяется метод выборки, зависящий от типа данных в столбце
              switch(cType[n]) 
              {
                case Types.DATE: col=rs.getDate(n+1); break;
                case Types.DECIMAL: col=rs.getBigDecimal(n+1); break;
                case Types.VARCHAR: col=rs.getString(n+1); break;
                case Types.CHAR: col=rs.getString(n+1); break;
                case Types.SMALLINT: col=new Short(rs.getShort(n+1)); break;
                case Types.INTEGER: col=new Integer(rs.getInt(n+1)); break;
              }
              // выбранное значение преобразуется в строку и сохраняется в
              // векторе; значение NULL заменяется на "-"
              if (rs.wasNull()) col=new String("-");
              sCol=col.toString();
              if ((cType[n]==Types.VARCHAR)||(cType[n]==Types.CHAR))
                sCol=decoder.dosToWin(sCol);
              reply.addElement(sCol);
            }
          }   // while (rs.next())
        }
      }  // if (s2.equalsIgnoreCase("select")) 
      else 
      {
        // выполнение операторов INSERT, DELETE, UPDATE
        try { st.executeUpdate(stmt); }
        catch (SQLException e)
        { 
          repString=new String("Error Code = "+e.getErrorCode());
          errorFlag=true;
        }
      }  // else
      if (!errorFlag) // вывод успешной итоговой строки
        repString=new String("Statement processed successfully"); 
      st.close();
      return repString;
    } 

    //------------------------------------------------------
    // Метод выполнения оператора COMMIT
    public void commit() 
    {
      try { con.commit(); }
      catch (SQLException e) { }
    }

    //------------------------------------------------------
    // Метод выполнения оператора ROLLBACK
    public void rollback() 
    {
      try { con.rollback(); }
      catch (SQLException e) { }
    }

    //------------------------------------------------------
    // Закрытие соединения
    public void close() 
    {
      try { con.close(); }
      catch (SQLException e) { }
    }
  }