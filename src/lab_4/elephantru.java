package lab_4;

public class elephantru
{
  public static String Header() 
  {
    return "<meta name=\"Keywords\" content=\"����, elephant\">\n" + 
   "<! (C) ��� \"���\". ���.����������� � ���������������� �������������, 2003. >";  
  }

  public static String YourSubscr() 
  {
    return "��� ����������� �����";
  }

  public static String Eleph() 
  {
    return "���� ";
  }

  public static String[] Select() 
  {
    String sel[] = { "���� �����", "����� �����", "�������" };
    return sel;
  }

  public static String[] Person(String sex) 
  {
    String person[] = {"���� ", "��� ", "������� ", "��� ", "�", 
                     "", "", " ���" };
    if (sex.equalsIgnoreCase("�") )
    {
      person[5]="�������"; person[6]=" ���������";
    }
    else
    {
    person[5]="�������"; person[6]=" ����������";
    }
    return person;
  }

  public static String Sponsors() 
  {
    return "���������� ����� ���������!";
  }

  public static String[] Registration() 
  {
    String reg[] = { "����������� ��������",
        "���� �� ������ ��� ��������������� ���, ������� ��� �����:",
        "���� �� ������� ������� ����� ��� ������ ���� ��������������� ���, ������� ���� ���:",  
        "������� ���", "������� ���" };
    return reg;
  }

  public static String Thank() 
  {
    return "�������, "; 
  }

  public static String[] Subscription() 
  {
    String subscr[] = {"�������", "�����", "���", "����������", 
                       "�������", "����� �����", "����� ��������"};
    return subscr;
  }

  public static String[] Code() 
  {
    String txt[] = {"��� ��������������� ���", "������" }; 
    return txt;
  }

  public static String[] Error() 
  {
    String sel[] = { "��� ", " �� ������ � ���� ������", "��� ���� ��������", 
     "��� ����� ��������", "���������� �������� �����",
     "���������� �������� ����������", "������������� ������ �� �����������",  
     "��� �� �����, �� ����������", "������� � �����, � ����������",
     "������ ����������, �� �� ������ �������" };
    return sel;
  }
}
