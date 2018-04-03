//=================================================================
  // ����� JDBC-�������� ��� ���������
  import java.util.*;
  import java.sql.*;

  
  class sql
  {
    // URL � ��� �������� CloudscapeDB
    static String url="jdbc:postgresql://localhost:5433/Visual;create=false";
//    static String drName="COM.cloudscape.core.RmiJdbcDriver";
    Connection con=null; // ����������

    //------------------------------------------------------
    // �����������. ������������� ���������� � ��
    sql() 
    {
      // ����������� �������� � ���������� � ��
    	Properties prop = new Properties();
    	prop.setProperty("user", "postgres");
        prop.setProperty("password", "0000");
        

//    	try { Class.forName(drName);  }
//      catch (SQLException e) { System.exit(0);  }
      try { con=DriverManager.getConnection(url); }
      catch (SQLException e) { System.exit(0);  }
      try { con.setAutoCommit(false); }
      catch (SQLException e) { System.exit(0);  }
    }        

    //------------------------------------------------------
    // ����� ���������� ������ SQL-�������
    // stmt - ����� SQL-������� 
    // reply - ������, � ������� ����� ������� ��������� �������
    // ������������ �������� - ����� ���������
    public String query(String stmt,Vector reply) 
                       throws SQLException
    {
      int i, n, l;    // ������� ����������
      String s2;   
      String repString=new String(""); // �������� ������
      boolean errorFlag=false;  // ������� ������

      Statement st=con.createStatement(); // �������� ������� ���������   
      // ��������� 1-�� ����� �� ���������������
      l=stmt.indexOf(" ",0);
      if (l>=0) s2=stmt.substring(0,stmt.indexOf(" ",0));
      else s2=stmt;
      if (s2.equalsIgnoreCase("select")) 
      {
        ResultSet rs=null;  // ������ ��� ����������� �������
        // ���������� ��������� SELECT
        try { rs=st.executeQuery(stmt); }
        catch (SQLException e)
        { 
          // � ������ ������ � ���.������ ��������� ��� ������
          repString=new String("Error Code = "+e.getErrorCode());
          errorFlag=true;
        }
        // ���� ������ �� ���� - �������� ����������
        if (!errorFlag)
        {
          // ���������� ���������� �������
          ResultSetMetaData mtd = rs.getMetaData(); // ���������� ���������� 
          int colNumb = mtd.getColumnCount();    // ����� ��������
          int cType[] = new int[colNumb];        // ���� ������ � ��������
          // ���������� ���������� � ��������
          for (n=0; n<colNumb; n++) 
            cType[n]=mtd.getColumnType(n+1);
          Object col = null;  // ������, � ������� ���������� ������ �� �������  
          String sCol=new String("");    // ��������� ������������� �������
          // ������� ������
          while ( rs.next() )
          {
            String str = new String("");
            // ������������ ������� ������ ������
            for (n=0; n<colNumb; n++) 
            {
              // ����������� ����� �������, ��������� �� ���� ������ � �������
              switch(cType[n]) 
              {
                case Types.DATE: col=rs.getDate(n+1); break;
                case Types.DECIMAL: col=rs.getBigDecimal(n+1); break;
                case Types.VARCHAR: col=rs.getString(n+1); break;
                case Types.CHAR: col=rs.getString(n+1); break;
                case Types.SMALLINT: col=new Short(rs.getShort(n+1)); break;
                case Types.INTEGER: col=new Integer(rs.getInt(n+1)); break;
              }
              // ��������� �������� ������������� � ������ � ����������� �
              // �������; �������� NULL ���������� �� "-"
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
        // ���������� ���������� INSERT, DELETE, UPDATE
        try { st.executeUpdate(stmt); }
        catch (SQLException e)
        { 
          repString=new String("Error Code = "+e.getErrorCode());
          errorFlag=true;
        }
      }  // else
      if (!errorFlag) // ����� �������� �������� ������
        repString=new String("Statement processed successfully"); 
      st.close();
      return repString;
    } 

    //------------------------------------------------------
    // ����� ���������� ��������� COMMIT
    public void commit() 
    {
      try { con.commit(); }
      catch (SQLException e) { }
    }

    //------------------------------------------------------
    // ����� ���������� ��������� ROLLBACK
    public void rollback() 
    {
      try { con.rollback(); }
      catch (SQLException e) { }
    }

    //------------------------------------------------------
    // �������� ����������
    public void close() 
    {
      try { con.close(); }
      catch (SQLException e) { }
    }
  }