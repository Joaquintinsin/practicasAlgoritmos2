import java.util.*;
import java.lang.Math;

public class FakeCoinDecrease {
  public static int Decrease(int[] coins){
    return coinDecrease(coins, 0, coins.length - 1);
  }

  public static int coinDecrease(int[] coins, int start, int end){
    if (end < start) {
      throw new IllegalArgumentException("no hay. Rango inválido");
    } 
    //solo hay una moneda
    if(coins.length == 1){
      return coins[start];
    }
    //Hay solo 2 monedas en el grupo
    if(end - start == 1){
      return Math.min(coins[start], coins[end]);
    } else {
      int tercio = (end - start + 1) / 3;
      int dosTercios = 2 * tercio;

      int left = coinDecrease(coins, start, start + tercio - 1);
      int middle = coinDecrease(coins, start + tercio, start + dosTercios - 1);
      int right = coinDecrease(coins, start + dosTercios, end);

      int resultMax = left == middle ? left : Math.min(left, middle);
      int fakeCoinIs = resultMax == right ? 0 : Math.min(resultMax, right);
      return fakeCoinIs;
    }
  }

  public static void main(String[] args) {
    int[] monedas = {3,3,3,3,3,3,1};
    System.out.println("Moneda falsa: " + Decrease(monedas));
  }
}
