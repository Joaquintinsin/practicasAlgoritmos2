import java.util.Arrays;
import java.lang.Math;
// Mine
public class FakeCoinDivide {
  private static final int CantMonedas = 50;
  
  public static int indexFakeCoin(int[] coins) {
    return indexFakeCoin(coins, 0, CantMonedas-1);
  }
  
  public static int indexFakeCoin(int[] coins, int ini, int end) {
    if (ini == end) {
      return ini;
    }
    int mid = ini + (end - ini) / 2;
    int left = sumOfRange(coins, ini, mid);
    int right = sumOfRange(coins, mid + 1, end);
    if (left < right) {
      return indexFakeCoin(coins, ini, mid);
    } else if (left > right) {
      return indexFakeCoin(coins, mid+1, end);
    } else {
      return -1;
    }
  }
  
  private static int sumOfRange(int[] arr, int ini, int end) {
    int res = 0;
    for (int i = ini ; i <= end ; i++)
      res += arr[i];
    return res;
  }
  
  public static void main(String[] args) {
    int[] coins = new int[CantMonedas];
    Arrays.fill(coins, 3);
    coins[CantMonedas - 1] = 1;
    System.out.println("Indice moneda falsa: " + indexFakeCoin(coins));
  }
}
