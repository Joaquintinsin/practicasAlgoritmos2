import java.util.Arrays;

public class LongestCommonSubstring {
  public static String longestCommonSubstring(String s1, String s2) {
    if (s1==null || s2==null) throw new IllegalArgumentException("Una de las dos cadenas es vacío");
    char[] s1c = s1.toCharArray();
    char[] s2c = s2.toCharArray();
    char[] lcs1 = new char[s1c.length];
    char[] lcs2 = new char[s2c.length];
    int maxLength = Math.max(s1c.length, s2c.length);
    int k = 0;
    char[] res = new char[maxLength];
    if (s1c.length < s2c.length) {
      for (int i = 0 ; i < s2c.length ; i++) {
        lcs2[i] = s2c[i];
        for (int j = 0 ; j < s1c.length ; j++) {
          lcs1[j] = s1c[j];
          if (lcs2[i] == lcs1[j]) {
            res[k] = lcs2[i];
            k++;
          }
        }
      }
    } else {
      for (int i = 0 ; i < s1c.length ; i++) {
        lcs1[i] = s1c[i];
        for (int j = 0 ; j < s2c.length ; j++) {
          lcs2[j] = s2c[j];
          if (lcs1[i] == lcs2[j]) {
            res[k] = lcs1[i];
            k++;
          }
        }
      }
    }
    return Arrays.toString(res);
  }
  
  public static void main(String[] args) {
    try {
      String s1 = args[0];
      String s2 = args[1];
      System.out.println("LongestCommonSubstring between " + s1 + " and " + s2);
      System.out.println(longestCommonSubstring(s1,s2));
    } catch (IllegalArgumentException e) {
      System.out.println("Minimo dos argumentos");
    }
  }
}
