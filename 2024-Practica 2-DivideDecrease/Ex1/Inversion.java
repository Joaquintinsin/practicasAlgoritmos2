public class Inversion {
  public static int mergeSort(float arr[]) {
    int n = arr.length;
    if (n <= 1)
      return 0;
    float[] aux1 = new float[n/2];
    float[] aux2 = new float[n - n/2];
    // copy elements
    System.arraycopy(arr, 0, aux1, 0, n/2);
    System.arraycopy(arr, n/2, aux2, 0, n - n/2);
    
    int resultadoInversiones = mergeSort(aux1) + mergeSort(aux2);
    resultadoInversiones += merge(aux1, aux2, arr);
    return resultadoInversiones;
  }

  public static int merge(float left[], float[] right, float[] original) {
    int i = 0;
    int j = 0;
    int k = 0;
    int p = left.length;
    int q = right.length;
    int resultadoSwaps = 0;
    while (i < p && j < q) {
      if (left[i] <= right[j]) {
        original[k] = left[i];
        i++;
      } else {
        original[k] = right[j];
        resultadoSwaps = resultadoSwaps + (p - i);
        j++;
      }
      k++;
    }
    if (i == p) {
      for (int w = j ; w < right.length ; w++)
        original[k++] = right[w];
    } else {
      for (int w = i ; w < left.length ; w++)
        original[k++] = left[w];
    }
    return resultadoSwaps;
  }

  public static void main (String[] args) {
    float[] ejemplo = new float[] {4, 2, 1};
    System.out.println("Resultado inversion [4, 2, 1]");
    System.out.println(mergeSort(ejemplo));
  }
}
