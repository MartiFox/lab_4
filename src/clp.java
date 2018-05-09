import java.sql.*;
  import java.io.*;
  import java.util.*;

  class clp
  { 
    //----------------------------------------------
    // ��������������� ����� - ������������ ������ s 
    // �� ������ ���� �� ����� len
    public static String left(String s, int len) 
    {
      if (s.length()==len) return s;
      if (s.length()>len) return s.substring(0,len);
      while (s.length()<len) s=s.concat(" ");
      return s;
    }
    
    //----------------------------------------------
    // ������� ����� CLP
    //public object (String argv[])
    //public string (String argv[]) 
    public static void main (String argv[]) 
       throws SQLException, IOException, ClassNotFoundException
    {
      // ��� �������� � url ��
      //String drName="";
      String url="jdbc:postgresql://localhost:5433/VisualD";
      Connection con=null;  // ����������
      ResultSet rs=null;    // ��������� �������
      byte inb [] = new byte[512]; // ������ ��� ������ ���������
      int i, n;             // ������� ����������
      int nRows=0;          // ����� ����� ��������
      boolean errorFlag;    // ������� ������
   
      // ����������� ��������
      //System.out.println("Driver= " + drName);
      //Class.forName(drName);
        
      Properties prop = new Properties();
      prop.setProperty("user", "postgres");
      prop.setProperty("password", "0000");
      
      // ���������� � ��
      System.out.println("Connection." +url+" Please wait...");
      con=DriverManager.getConnection(url, prop);
      con.setAutoCommit(false);
      System.out.println("Connection established! AUTOCOMMIT=OFF");
    
      Statement st=con.createStatement();    // �������� ���������

      while (true) 
      {
        // ���� ���������
        System.out.print("Enter statement>"); System.out.flush();
        n = System.in.read(inb,0,512);
        String s2, stmt = new String(inb,0,n-2);
        // ����� �� ������� "quit"
        if (stmt.equalsIgnoreCase("quit")) break;

        errorFlag=false;
        // ��������� 1-�� ����� �� ���������
        n=stmt.indexOf(" ",0);
        if (n>=0) s2=stmt.substring(0,stmt.indexOf(" ",0));
        else s2=stmt;
        if (s2.equalsIgnoreCase("select")) 
        {
          // ���������� ��������� SELECT
          try 
          {
            rs=st.executeQuery(stmt);
          }
          catch (SQLException e)
          { 
            // � ������ ������ ��������� ��� ������
            System.out.println("Error Code = "+e.getErrorCode());
            errorFlag=true;
            System.out.println(e);
          }

          // ���� ������ �� ���� - �������� ����������
          if (!errorFlag)
          {
            ResultSetMetaData mtd = rs.getMetaData();
            int colNumb = mtd.getColumnCount();  // ����� ��������
            int cType[] = new int[colNumb];      // ���� ������ � ��������
            String cNames[] = new String[colNumb]; // ����� ��������
            int cMaxLen[] = new int[colNumb];    // ������ ��� ������� �������
            // ���������� ���������� � �������� (��������� �������� - � 1)
            for (n=0; n<colNumb; n++) 
            {
              cType[n]=mtd.getColumnType(n+1);
              cNames[n]=mtd.getColumnName(n+1);
              cMaxLen[n]=mtd.getColumnDisplaySize(n+1);
            }
            // ����� ���������� ��������
            for (n=0; n<colNumb; n++) 
              System.out.print(left(cNames[n],cMaxLen[n])+" ");
            System.out.println();
            // ����� ������������� ��� �����������
            for (n=0; n<colNumb; n++) 
            {
              for (i=0; i<cMaxLen[n]; i++) System.out.print('-');
              System.out.print(' ');
            }
            System.out.println();
           
            Object col = null; // ������, � ������� ���������� ������ �� �������  
            String sCol;       // ��� ��������� �������������
            // ������� ������ �� �������
            for (nRows=0; rs.next(); nRows++)
            {
              // ������������ ������� ������ ������
              for (n=0; n<colNumb; n++) 
              {
                // ����������� ����� �������, ��������� 
                // �� ���� ������ � �������
                switch(cType[n]) 
                {
                  case Types.DATE: col=rs.getDate(n+1); break;
                  case Types.DECIMAL: col=rs.getBigDecimal(n+1); break;
                  case Types.VARCHAR: col=rs.getString(n+1); break;
                  case Types.CHAR: col=rs.getString(n+1); break;
                  case Types.SMALLINT: col=new Short(rs.getShort(n+1)); break;
                  case Types.INTEGER: col=new Integer(rs.getInt(n+1)); break;
                }
                // ��������� �������� ������������� � ������ � �����������
                // ��������� � ������������ ������� �������
                // �������� NULL ���������� �� "-"
                if (rs.wasNull()) col=new String("-");
                sCol=col.toString();
                System.out.print(left(sCol,cMaxLen[n])+" ");
              }
              System.out.println();
            }   // while (rs.next())
          // ����� �������� ������
          System.out.println("------------------");
          System.out.println(nRows + " rows selected");
          }  // if (!errorFlag)
        }  // if (s2.equalsIgnoreCase("select")) 
        else if (s2.equalsIgnoreCase("commit")) 
        {
          con.commit();        // �������� ����������
        }
        else if (s2.equalsIgnoreCase("rollback")) 
        {
          con.rollback();      // ����� ����������
        }
        else 
        {
          // ���������� ���������� INSERT, DELETE, UPDATE
          try 
          {
            nRows=st.executeUpdate(stmt);
          }
          catch (SQLException e)
          { 
            // � ������ ������ ��������� ��� ������ � ��������� DB2
            System.out.println("Error Code = "+e.getErrorCode());
            errorFlag=true;
          }
          if (!errorFlag)
          {
            // ����� �������� ������
            System.out.println("Statement processed successfully"); 
            System.out.println(nRows + " rows processed"); 
          }      
        }  // else
      }  // while (true) 
      // �������� ��������� � ����������
      st.close();
      con.close();
    }  // public void main
  }  // class clp