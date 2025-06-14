/* 5. Diseñe usando Divide & Conquer e implemente en Java y Haskell un programa que, dadas dos
 * secuencias de caracteres, construya la subsecuencia común a ambas de longitud máxima. Se entiende
 * por subsecuencia una cadena de caracteres que se deriva de la original mediante la eliminación de
 * caracteres pero sin cambiar el orden de los caracteres en la secuencia original.
 */

public class SubsecuenciaComun {
  public static String subsecuenciaComun (String first, String second) {
    if (first == null || second == null) throw new IllegalArgumentException("Ambas cadenas parámetro deben contener algo");
    int firstLength = first.length();
    int secondLength = second.length();
    if (firstLength == 0 || secondLength == 0) return "";
    // Si el último caracter de ambas secuencias es el mismo
    if (first.charAt(firstLength-1) == second.charAt(secondLength-1))
      return subsecuenciaComun(first.substring(0, firstLength-1), second.substring(0, secondLength-1)) + first.charAt(firstLength-1);
    // Si el último caracter de ambas secuencias es diferente, consideramos dos casos y retornamos el máximo de ellos
    String s1Sub = subsecuenciaComun(first, second.substring(0, secondLength-1));
    String s2Sub = subsecuenciaComun(first.substring(0, firstLength-1), second);

    return (s1Sub.length() > s2Sub.length()) ? s1Sub : s2Sub;
  }

  public static void main (String[] args) {
    String s1 = "hola";
    String s2 = "halo";
    System.out.println("subsecuenciaComun entre " + s1 + " y " + s2 + " es:");
    System.out.println(subsecuenciaComun(s1, s2));
    s1 = "AGGTAB";
    s2 = "GXTXAYB";
    System.out.println("Longest Common Subsequence: " + subsecuenciaComun(s1, s2));
  }
}
