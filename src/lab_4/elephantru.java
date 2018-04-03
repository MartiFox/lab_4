package lab_4;

public class elephantru
{
  public static String Header() 
  {
    return "<meta name=\"Keywords\" content=\"слон, elephant\">\n" + 
   "<! (C) НТУ \"ХПИ\". Каф.Информатики и интеллектуальной собственности, 2003. >";  
  }

  public static String YourSubscr() 
  {
    return "Ваш спонсорский взнос";
  }

  public static String Eleph() 
  {
    return "Слон ";
  }

  public static String[] Select() 
  {
    String sel[] = { "Наши Слоны", "Выбор Слона", "Выбрать" };
    return sel;
  }

  public static String[] Person(String sex) 
  {
    String person[] = {"Слон ", "Пол ", "Возраст ", "Вес ", "т", 
                     "", "", " лет" };
    if (sex.equalsIgnoreCase("М") )
    {
      person[5]="мужской"; person[6]=" обеспечен";
    }
    else
    {
    person[5]="женский"; person[6]=" обеспечена";
    }
    return person;
  }

  public static String Sponsors() 
  {
    return "Благодарим наших спонсоров!";
  }

  public static String[] Registration() 
  {
    String reg[] = { "Регистрация спонсора",
        "Если Вы знаете Ваш регистрационный код, введите его здесь:",
        "Если Вы впервые делаете взнос или забыли свой регистрационный код, введите свое имя:",  
        "Послать код", "Послать имя" };
    return reg;
  }

  public static String Thank() 
  {
    return "Спасибо, "; 
  }

  public static String[] Subscription() 
  {
    String subscr[] = {"Продукт", "Сумма", "или", "Количество", 
                       "Послать", "любые Слоны", "любые продукты"};
    return subscr;
  }

  public static String[] Code() 
  {
    String txt[] = {"Ваш регистрационный код", "Дальше" }; 
    return txt;
  }

  public static String[] Error() 
  {
    String sel[] = { "Код ", " не найден в базе данных", "Нет кода спонсора", 
     "Нет имени спонсора", "Нечисловое значение суммы",
     "Нечисловое значение количества", "Отрицательные взносы не принимаются",  
     "Нет ни суммы, ни количества", "Указаны и суммы, и количество",
     "Задано количество, но не выбран продукт" };
    return sel;
  }
}
