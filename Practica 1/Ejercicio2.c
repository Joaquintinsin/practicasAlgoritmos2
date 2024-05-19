#include <stdio.h>
#include <string.h>

void DesplegarMenuDeOpciones();

int main( int argc, char **argv ) {
	int option;
  char rta[250];
  printf("Ejercicio 2 de la práctica 1 de Algoritmos2, UNRC, 2024.\n");
  printf("Para cada uno de los siguientes algoritmos, indique qué problema resuelve y cuál es su tiempo de ejecución en el peor caso.\n");
  do {
    DesplegarMenuDeOpciones();
    scanf("%d", &option);
    switch (option) {
    case 1:
      printf("Opcion Mergesort \n");
      printf("________\n");
      strcpy(rta, "ordena una secuencia de elementos, y su orden en peor caso es O(n log n)");
      printf("%s \n", rta);
      printf("________\n");
      break;
    case 2:
      printf("Opcion Quicksort \n");
      printf("________\n");
      strcpy(rta, "Ordena una secuencia de elementos, y su orden en peor caso es O(n log n)");
      printf("%s \n", rta);
      printf("________\n");
      break;
    case 3:
      printf("Opcion Algoritmo de Dijkstra \n");
      printf("________\n");
      strcpy(rta, "Calcula, dado un origen, los caminos mas cortos entre el origen y cualquier otro nodo dado. Orden cuadrado.");
      printf("%s \n", rta);
      printf("________\n");
      break;
    case 4:
      printf("Opcion Algoritmo de Warshall \n");
      printf("________\n");
      strcpy(rta, "Permite calcular caminos mas cortos aun con costos negativos. Orden cúbico.");
      printf("%s \n", rta);
      printf("________\n");
      break;
    case 5:
      printf("Opcion Algoritmo de Kruskal \n");
      printf("________\n");
      //strcpy(rta, "Busca un subconjunto de aristas dentro de un grafo conexo y ponderado (que tiene costos entre nodos o costos en las aristas) de manera tal que forman un árbol \n tal que el valor de las sumas de las aristas del árbol es la mínima posible. \n Orden (m log m) donde m = cant aristas del grafo.");
      printf("Busca un subconjunto de aristas dentro de un grafo conexo y ponderado (que tiene costos entre nodos o costos en las aristas) \nde manera tal que forman un árbol tal que el valor de las sumas de las aristas del árbol es la mínima posible. \nOrden (m*log m) donde m = cant aristas del grafo. \n");
      printf("________\n");
      break;
    case 6:
      printf("Opcion Busqueda binaria \n");
      printf("________\n");
      strcpy(rta, "En una secuencia donde sus elementos poseen un tipo de orden, la búsqueda binaria permite buscar sobre la secuencia de a mitades, encontrando o no un elemento deseado. Tiene orden (log n) donde n = longitud de la secuencia.");
      printf("%s \n", rta);
      printf("________\n");
      break;
    case 7:
      printf("Opcion Algoritmo de Euclides \n");
      printf("________\n");
      strcpy(rta, "Algoritmo para calcular el máximo común divisor entre dos numeros enteros. Complejidad O(log min(a,b)) donde a y b son los enteros dados.\n");
      printf("%s \n", rta);
      strcpy(rta, "int mcd (int a, int b) { return b ? mcd(b, a % b) : a; }");
      printf("%s \n", rta);
      printf("________\n");
      break;
    case 8:
      printf("Opcion Busqueda a lo ancho \n");
      printf("________\n");
      strcpy(rta, "Breadth-first search (BFS) es un algoritmo de recorrido de un grafo, en donde primero se visitan todos los adyacentes a un nodo y luego el resto de nodos. Complejidad O(E+V) donde E=cantidad de aristas y V=cantidad de vértices.");
      printf("%s \n", rta);
      printf("________\n");
      break;
    case 9:
      printf("Opcion Criba de Eratóstenes \n");
      printf("________\n");
      strcpy(rta, "Dado un número natural, devuelve todos los números primos anteriores al número dado. Complejidad O(n*log(log n))");
      printf("%s \n", rta);
      printf("________\n");
      break;
    default:
      printf("Opcion Salir \n");
      return 0;
    }
  } while (!(option == 10));

	return 0;
}

void DesplegarMenuDeOpciones()
{
    printf("\n");
    printf("· Menu principal");
    printf("\n");
    printf("Seleccione una opcion para continuar: \n");
    printf("\t 1. Mergesort \n");
    printf("\t 2. Quicksort \n");
    printf("\t 3. Algoritmo de Dijkstra \n");
    printf("\t 4. Algoritmo de Warshall \n");
    printf("\t 5. Algoritmo de Kruskal \n");
    printf("\t 6. Búsqueda binaria \n");
    printf("\t 7. Algoritmo de Euclides \n");
    printf("\t 8. Búsqueda a lo ancho \n");
    printf("\t 9. Criba de Eratóstenes \n");
    printf("\t 10. Salir \n");
}
