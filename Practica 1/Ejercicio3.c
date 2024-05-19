#include <stdio.h>
#include <string.h>

void DesplegarMenuDeOpciones();

int main( int argc, char **argv ) {
	int option;
  char rta[250];
  printf("Ejercicio 3 de la práctica 1 de Algoritmos2, UNRC, 2024.\n");
  printf("Para cada uno de los siguientes problemas, indique cuán eficientemente pueden resolverse.\n");
  do {
    DesplegarMenuDeOpciones();
    scanf("%d", &option);
    switch (option) {
    case 1:
      printf("Ordenar una secuencia \n");
      printf("________\n");
      strcpy(rta, "Puede resolverse en O(n log n)");
      printf("%s \n", rta);
      printf("________\n");
      break;
    case 2:
      printf("Buscar un elemento en un conjunto \n");
      printf("________\n");
      strcpy(rta, "Puede resolverse en O(log n)");
      printf("%s \n", rta);
      printf("________\n");
      break;
    case 3:
      printf("Problema de la exponenciación (calcular a^n) \n");
      printf("________\n");
      strcpy(rta, "Puede resolverse en O(log n). [x^(2k) = (x^k)^2] entonces el exponente n se reduce a la mitad en cada iteración, llegando a n=0 o n=1 casos base.");
      printf("%s \n", rta);
      printf("________\n");
      break;
    case 4:
      printf("Eliminación Gaussiana \n");
      printf("________\n");
      strcpy(rta, "El tiempo estándar es cúbico O(n^3) donde n=cant filas o columnas de la matriz.\nPero en caso que la matriz tenga 'm' columnas dispersas o si se utiliza la factorización LU para matrices sparse, tiene complejidad O(m * n^2).");
      printf("%s \n", rta);
      printf("________\n");
      break;
    case 5:
      printf("Encontrar par de elementos más cercanos en un conjunto de puntos del plano \n");
      printf("________\n");
      strcpy(rta, "La mejor complejidad en el algoritmo Closest Pair of Points es de O(n*log n).");
      printf("%s \n", rta);
      printf("________\n");
      break;
    case 6:
      printf("Composición relacional \n");
      printf("________\n");
      strcpy(rta, "Sea llamado también composición de funciones que dadas dos relaciones produce una nueva relación compuesta por elementos que se relacionan de cierta manera; la menor complejidad depende del contexto de esas relaciones y del algoritmo usado.");
      printf("%s \n", rta);
      strcpy(rta, "Dos relaciones binarias representadas como conjuntos o matrices, la complejidad puede ser O(n^3) en el peor caso, donde n=tamaño de las relaciones.");
      printf("%s \n", rta);
      strcpy(rta, "Si las relaciones están representadas como matrices que cumplen ciertas propiedades estructurales, usar por ejemplo el algoritmo de Strassen provee una complejidad de O(n^2.81).");
      printf("%s \n", rta);
      strcpy(rta, "Dependiendo qué propiedades pueden aprovecharse de las relaciones, puede llegar a ser más eficiente, pero el orden cúbico es el que generalmente está presente en los enfoques simples de este tipo de problemas.");
      printf("%s \n", rta);
      printf("________\n");
      break;
    case 7:
      printf("Ordenamiento topológico \n");
      printf("________\n");
      strcpy(rta, "Menor complejidad de O(V+E) donde V=cant de vertices y E=cant de aristas.");
      printf("%s \n", rta);
      strcpy(rta, "Algoritmo de Kahn; en cada iteración, elimina un vértice de grado de entrada cero y las aristas asociadas con él, lo que reduce el número de aristas y vértices en el grafo.");
      printf("________\n");
      break;
    case 8:
      printf("Determinar existencia de ciclos eulerianos en un grafo \n");
      printf("________\n");
      strcpy(rta, "La menor complejidad es O(V+E) donde V=cant vertices y E=cant aristas. El algoritmo implica ver si el grafo es conexo y el grado de los vértices (si todos tienen un grado par) y solo basta recorrer una vez todos los vértices y aristas del grafo.");
      printf("%s \n", rta);
      strcpy(rta, "Si el grafo es conexo y todos los vértices tienen grado par, entonces existe un ciclo euleriano en el grafo, sino no tiene.");
      printf("%s \n", rta);
      printf("________\n");
      break;
    default:
      printf("Opcion Salir \n");
      return 0;
    }
  } while (!(option == 9));

	return 0;
}

void DesplegarMenuDeOpciones()
{
    printf("\n");
    printf("· Menu principal");
    printf("\n");
    printf("Seleccione una opcion para continuar: \n");
    printf("\t 1. Ordenar una secuencia \n");
    printf("\t 2. Buscar un elemento en un conjunto \n");
    printf("\t 3. Problema de la exponenciación (calcular a^n) \n");
    printf("\t 4. Eliminación Gaussiana \n");
    printf("\t 5. Encontrar par de elementos más cercanos en un conjunto de puntos del plano \n");
    printf("\t 6. Composición relacional \n");
    printf("\t 7. Ordenamiento topológico \n");
    printf("\t 8. Determinar existencia de ciclos eulerianos en un grafo \n");
    printf("\t 9. Salir \n");
}
