public class Hamming {
  public static Integer hammingDistance(String str1, String str2) {
    if (str1.length() != str2.length()) {
      throw new IllegalArgumentException("Las cadenas deben tener la misma longitud");
    }
    Integer acum = 0;
    for (Integer i = 0; i < str1.length(); i++) {
      if (str1.charAt(i) != str2.charAt(i)) {
        acum++;
      }
    }
    return acum;
  }

  public static Integer decreaseHamming(String str1, String str2) {
    return decreaseHelper(str1, str2, 0);
  }

  public static Integer decreaseHelper(String str1, String str2, Integer acum) {
    if (str1.isEmpty() && str2.isEmpty()) {
      return acum;
    } else {
      Character headString1 = str1.charAt(0);
      Character headString2 = str2.charAt(0);
      if (!headString1.equals(headString2)) {
        acum++;
      }
      return decreaseHelper(str1.substring(1), str2.substring(1), acum);
    }
  }

  public static void main(String[] args) {
    String string1 = "karolin";
    String string2 = "kathrin";

    int maxDistance = hammingDistance(string1, string2);
    System.out.println("1-2, La máxima distancia de Hamming es: " + maxDistance);

    int maxDistanceDecrease = decreaseHamming(string1, string2);
    System.out.println("1-2, La máxima distancia de Decrease Hamming es: " + maxDistanceDecrease);

    // 4
    String str1 = "toned";
    String str2 = "roses";

    maxDistance = hammingDistance(str1, str2);
    System.out.println("1-2, La máxima distancia de Hamming es: " + maxDistance);

    maxDistanceDecrease = decreaseHamming(str1, str2);
    System.out.println("1-2, La máxima distancia de Decrease Hamming es: " + maxDistanceDecrease);

    // 4
    String str3 = "hello";
    String str4 = "world";

    maxDistance = hammingDistance(str3, str4);
    System.out.println("3-4, La máxima distancia de Hamming es: " + maxDistance);

    maxDistanceDecrease = decreaseHamming(str3, str4);
    System.out.println("3-4, La máxima distancia de Decrease Hamming es: " + maxDistanceDecrease);

    // 1
    String str5 = "apple";
    String str6 = "apxle";

    maxDistance = hammingDistance(str5, str6);
    System.out.println("5-6, La máxima distancia de Hamming es: " + maxDistance);

    maxDistanceDecrease = decreaseHamming(str5, str6);
    System.out.println("5-6, La máxima distancia de Decrease Hamming es: " + maxDistanceDecrease);

    // 0
    String str7 = "same";
    String str8 = "same";

    maxDistance = hammingDistance(str7, str8);
    System.out.println("7-8, La máxima distancia de Hamming es: " + maxDistance);

    maxDistanceDecrease = decreaseHamming(str7, str8);
    System.out.println("7-8, La máxima distancia de Decrease Hamming es: " + maxDistanceDecrease);

    // 4
    String str9 = "abcdefg";
    String str10 = "acegikm";

    maxDistance = hammingDistance(str9, str10);
    System.out.println("9-10, La máxima distancia de Hamming es: " + maxDistance);

    maxDistanceDecrease = decreaseHamming(str9, str10);
    System.out.println("9-10, La máxima distancia de Decrease Hamming es: " + maxDistanceDecrease);

    // 4
    String str11 = "0101";
    String str12 = "1010";

    maxDistance = hammingDistance(str11, str12);
    System.out.println("11-12, La máxima distancia de Hamming es: " + maxDistance);

    maxDistanceDecrease = decreaseHamming(str11, str12);
    System.out.println("11-12, La máxima distancia de Decrease Hamming es: " + maxDistanceDecrease);
  }
}
