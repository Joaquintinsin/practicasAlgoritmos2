#include <stdio.h>
#include <string.h>

void DesplegarMenuDeOpciones();

int main( int argc, char **argv ) {
	int option;
  char rta[250];
  printf("Ejercicio 3 de la práctica 1 de Algoritmos2, UNRC, 2024.\n");
  printf("Para los siguientes problemas, describa en palabras y/o pseudo-código, algoritmos que los resuelvan \nbasándose directamente en la descripción del problema:\n");
  do {
    DesplegarMenuDeOpciones();
    scanf("%d", &option);
    switch (option) {
    case 1:
      printf("Decidir si un conjunto de enteros se puede particionar en dos conjuntos de igual suma. \n");
      printf("________\n");
      strcpy(rta, "Tomar un conjunto de enteros; \nParticionar el conjunto en dos, de todas las maneras posibles; \nSumar cada conjunto particionado; \nSi dos sumas coinciden devuelve Verdadero. Sino, devuelve Falso.");
      printf("%s \n", rta);
      printf("________\n");
      break;
    case 2:
      printf("Dadas dos cadenas, decidir si las mismas son anagramas (una es permutación de la otra).\n");
      printf("________\n");
      strcpy(rta, "Tomar dos cadenas; \nSi la longitud de las cadenas difiere entonces no es anagrama; \nSi todos los elementos de una cadena pertenecen a la otra, es anagrama. Sino, no es anagrama.");
      printf("%s \n", rta);
      printf("________\n");
      break;
    case 3:
      printf("Dado un número natural n, descomponerlo en sus factores primos.\n");
      printf("________\n");
      strcpy(rta, "Dado n; \nDividir n por el número primo más cercano a él; \nSi el resultado de la división es 1, está descompuesto por sus factores primos; \nSino, dividir el resultado obtenido por el número primo más cercano al resultado.");
      printf("%s \n", rta);
      printf("________\n");
      break;
    case 4:
      printf("Dadas dos cadenas p y s, decida si p es subcadena de s.\n");
      printf("________\n");
      strcpy(rta, "Verificar las longitudes de la cadena p y s; \nSi p.length > s.length devuelve Falso; \nSino, verifico elemento por elemento que sean iguales; \nSi todos los elementos son iguales, p es subcadena de s. Si alguno es diferente, p no es subcadena de s.");
      printf("%s \n", rta);
      printf("________\n");
      break;
    case 5:
      printf("Dadas dos cadenas p y s, decida si p es subsecuencia de s (los elementos no necesariamente tienen que aparecer contiguos en s).\n");
      printf("________\n");
      strcpy(rta, "Tomar dos cadenas; \nParticionar una cadena (la que sea de mayor longitud) de manera tal que quede con la cantidad de elementos de la cadena de menor longitud; \nTomar una de las particiones obtenidas;");
      printf("%s \n", rta);
      strcpy(rta, "Verificar si todos los elementos de la cadena de menor longitud pertenecen a la particion de la cadena de mayor longitud; \nSi todos pertenecen entonces es un anagrama. Sino, tomar una nueva particion;");
      printf("%s \n", rta);
      strcpy(rta, "Si no hay más particiones para tomar y no se verificó que todos pertenecen, entonces no son anagramas.");
      printf("%s \n", rta);
      printf("________\n");
      break;
    case 6:
      printf("Dada una secuencia s de números, encontrar el k-ésimo elemento más grande en s.\n");
      printf("________\n");
      strcpy(rta, "Si k > s.length no se puede realizar (se pide un k-ésimo elemento inexistente en el arreglo); \nInicializar una variable de máximos encontrados en 1; \nAlmacenar el primer elemento de la secuencia como un máximo;");
      printf("%s \n", rta);
      strcpy(rta, "Si los máximos encontrados es igual a k, se devuelve el máximo encontrado; \nRecorrer la secuencia s;");
      printf("%s \n", rta);
      strcpy(rta, "Si en la recorrida se encuentra uno más grande que el máximo almacenado, se suma la cantidad de máximos encontrados en 1 y se pisa por el nuevo máximo encontrado;");
      printf("%s \n", rta);
      strcpy(rta, "Volver al paso 4 y finalizar cuando se termine de recorrer la secuencia; \nEl algoritmo permite el caso en que, se quiso el k-elemento máximo, pero se puede devolver un j-ésimo elemento tal que j <= k en caso que se termine la secuencia antes.");
      printf("%s \n", rta);
      printf("________\n");
      break;
    default:
      printf("Opcion Salir \n");
      return 0;
    }
  } while (!(option == 7));

	return 0;
}

void DesplegarMenuDeOpciones()
{
  printf("\n");
  printf("· Menu principal");
  printf("\n");
  printf("Seleccione una opcion para continuar: \n");
  printf("\t 1. Decidir si un conjunto de enteros se puede particionar en dos conjuntos de igual suma \n");
  printf("\t 2. Dadas dos cadenas, decidir si las mismas son anagramas (una es permutación de la otra) \n");
  printf("\t 3. Dado un número natural n, descomponerlo en sus factores primos \n");
  printf("\t 4. Dadas dos cadenas p y s, decida si p es subcadena de s \n");
  printf("\t 5. Dadas dos cadenas p y s, decida si p es subsecuencia de s (los elementos no necesariamente tienen que aparecer contiguos en s) \n");
  printf("\t 6. Dada una secuencia s de números, encontrar el k-ésimo elemento más grande en s. \n");
  printf("\t Otro. Salir \n");
}
