  //========================================================================
  // ��������������� ����� ��� ������������ ��������� HTML-c�������
  public class stdhead
  {
    public static String mkHead(String title)
    {
      String hd=
        "<!DOCTYPE HTML PUBLIC \".//W3C//DTD HTML 4.0 Transitional//EN\">\n"+
        "<HTML>\n" +
        "<HEAD>\n" +
        "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1251\">\n" +  
        "<meta name=\"Keywords\" content=\"���������, diagrams\">\n" +
        "<title>"+
        title +   // ��������� ����������� � �����
        "</title>\n" +
        "<! (C) Owner: AK >\n" +
        "<! (C) ��� \"���\". ���.����������� � ���������������� �������������, 2018. >\n" +
        "</head>\n" +
        "<body background=background.jpg>\n";
      return hd;
    }
  }