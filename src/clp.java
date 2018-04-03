import java.sql.*;
  import java.io.*;
  import java.math.*;
  import java.util.*;

  class clp
  { 
    //----------------------------------------------
    // вспомогательный метод - выравнивание строки s 
    // по левому краю до длины len
    public static String left(String s, int len) 
    {
      if (s.length()==len) return s;
      if (s.length()>len) return s.substring(0,len);
      while (s.length()<len) s=s.concat(" ");
      return s;
    }
    
    //----------------------------------------------
    // главный метод CLP
    //public object (String argv[])
    //public string (String argv[]) 
    public static void main (String argv[]) 
       throws SQLException, IOException, ClassNotFoundException
    {
      // имя драйвера и url БД
      //String drName="";
      String url="jdbc:postgresql://localhost:5432/visual";
      Connection con=null;  // соединение
      ResultSet rs=null;    // результат выборки
      byte inb [] = new byte[512]; // массив для текста оператора
      int i, n;             // рабочие переменные
      int nRows=0;          // число строк ввыборке
      boolean errorFlag;    // признак ошибки
   
      // регистрация драйвера
      //System.out.println("Driver= " + drName);
      //Class.forName(drName);
        
      Properties prop = new Properties();
      prop.setProperty("user", "postgres");
      prop.setProperty("password", "0");
      
      // соединение с БД
      System.out.println("Connection." +url+" Please wait...");
      con=DriverManager.getConnection(url, prop);
      con.setAutoCommit(false);
      System.out.println("Connection established! AUTOCOMMIT=OFF");
    
      Statement st=con.createStatement();    // создание оператора

      while (true) 
      {
        // ввод оператора
        System.out.print("Enter statement>"); System.out.flush();
        n = System.in.read(inb,0,512);
        String s2, stmt = new String(inb,0,n-2);
        // выход по команде "quit"
        if (stmt.equalsIgnoreCase("quit")) break;

        errorFlag=false;
        // выделение 1-го слова из оператора
        n=stmt.indexOf(" ",0);
        if (n>=0) s2=stmt.substring(0,stmt.indexOf(" ",0));
        else s2=stmt;
        if (s2.equalsIgnoreCase("select")) 
        {
          // выполнение оператора SELECT
          try 
          {
            rs=st.executeQuery(stmt);
          }
          catch (SQLException e)
          { 
            // в случае ошибки выводится код ошибки
            System.out.println("Error Code = "+e.getErrorCode());
            errorFlag=true;
            System.out.println(e);
          }

          // если ошибки не было - выбираем результаты
          if (!errorFlag)
          {
            ResultSetMetaData mtd = rs.getMetaData();
            int colNumb = mtd.getColumnCount();  // число столбцов
            int cType[] = new int[colNumb];      // типы данных в столбцах
            String cNames[] = new String[colNumb]; // имена столбцов
            int cMaxLen[] = new int[colNumb];    // ширина для каждого столбца
            // заполнение информации о столбцах (нумерация столбцов - с 1)
            for (n=0; n<colNumb; n++) 
            {
              cType[n]=mtd.getColumnType(n+1);
              cNames[n]=mtd.getColumnName(n+1);
              cMaxLen[n]=mtd.getColumnDisplaySize(n+1);
            }
            // вывод заголовков столбцов
            for (n=0; n<colNumb; n++) 
              System.out.print(left(cNames[n],cMaxLen[n])+" ");
            System.out.println();
            // вывод подчеркиваний под заголовками
            for (n=0; n<colNumb; n++) 
            {
              for (i=0; i<cMaxLen[n]; i++) System.out.print('-');
              System.out.print(' ');
            }
            System.out.println();
           
            Object col = null; // объект, в который выбираются данные из столбца  
            String sCol;       // его строковое представление
            // выборка данных по строкам
            for (nRows=0; rs.next(); nRows++)
            {
              // перебираются столбцы данной строки
              for (n=0; n<colNumb; n++) 
              {
                // применяется метод выборки, зависящий 
                // от типа данных в столбце
                switch(cType[n]) 
                {
                  case Types.DATE: col=rs.getDate(n+1); break;
                  case Types.DECIMAL: col=rs.getBigDecimal(n+1); break;
                  case Types.VARCHAR: col=rs.getString(n+1); break;
                  case Types.CHAR: col=rs.getString(n+1); break;
                  case Types.SMALLINT: col=new Short(rs.getShort(n+1)); break;
                  case Types.INTEGER: col=new Integer(rs.getInt(n+1)); break;
                }
                // выбранное значение преобразуется в строку и сохраняется
                // выводится с максимальной шириной столбца
                // значение NULL заменяется на "-"
                if (rs.wasNull()) col=new String("-");
                sCol=col.toString();
                System.out.print(left(sCol,cMaxLen[n])+" ");
              }
              System.out.println();
            }   // while (rs.next())
          // вывод итоговой строки
          System.out.println("------------------");
          System.out.println(nRows + " rows selected");
          }  // if (!errorFlag)
        }  // if (s2.equalsIgnoreCase("select")) 
        else if (s2.equalsIgnoreCase("commit")) 
        {
          con.commit();        // фиксация транзакции
        }
        else if (s2.equalsIgnoreCase("rollback")) 
        {
          con.rollback();      // откат транзакции
        }
        else 
        {
          // выполнение операторов INSERT, DELETE, UPDATE
          try 
          {
            nRows=st.executeUpdate(stmt);
          }
          catch (SQLException e)
          { 
            // в случае ошибки выводится код ошибки и сообщение DB2
            System.out.println("Error Code = "+e.getErrorCode());
            errorFlag=true;
          }
          if (!errorFlag)
          {
            // вывод итоговой строки
            System.out.println("Statement processed successfully"); 
            System.out.println(nRows + " rows processed"); 
          }      
        }  // else
      }  // while (true) 
      // закрытие оператора и соединения
      st.close();
      con.close();
    }  // public void main
  }  // class clp