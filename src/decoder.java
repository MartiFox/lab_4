
public class decoder 
{
  static String dos=
" ЎўЈ¤Ґ¦§Ё©Є«¬­®ЇабвгдежзийклмнопЂЃ‚ѓ„…†‡€‰Љ‹ЊЌЋЏђ‘’“”•–—™љ›њќћџ";
  static String win=
"абвгдежзийклмнопрстуфхцчшщъыьэюяАБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
  static String koi=
"БВЧЗДЕЦЪЙКЛМНОПРТУФХЖИГЮЫЭЯЩШЬАСбвчздецъйклмнопртуфхжигюыэящшьас";
  static String decode(String S, String A1, String A2)
  {
    int i, j;
    String S2=new String("");

    for (i=0; i<S.length(); i++) {
      j=A1.indexOf(S.charAt(i));
      if (j>=0) S2=S2.concat(A2.substring(j,j+1));
      else S2=S2.concat(S.substring(i,i+1));
      }      
    return S2;
  }
  public static String dosToWin (String S)
  {
    return decode(S,dos,win);
  }
  public static String dosToKoi (String S)
  {
    return decode(S,dos,koi);
  }
  public static String winToDos (String S)
  {
    return decode(S,win,dos);
  }
  public static String winToKoi (String S)
  {
    return decode(S,win,koi);
  }
  public static String koiToDos (String S)
  {
    return decode(S,koi,dos);
  }
  public static String koiToWin (String S)
  {
    return decode(S,koi,win);
  }
     
}