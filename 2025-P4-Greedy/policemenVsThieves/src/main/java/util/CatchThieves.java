package util;

import java.util.ArrayList;

/**
 * Greedy Policemen catch thieves.
 *
 * @author sonia
 *
 */

public final class CatchThieves {
  /**
   * Returns the maximum number of thieves that can be caught.
   * Each policeman can catch only one thief which is at most k away from him.
   *
   * @param seqTP     is the sequence of thieves and policemen, T or P.
   * @param distanceK represent the units away from the policemen to catch a
   *                  thieve.
   * @return the maximum number of thieves that can be caught.
   */

  /*
   * cada policia puede capturar solo 1 ladron
   * un policia puede capturar ladrones que esten a lo sumo a distanceK de el
   * hay que hacerlo polinomial
   * puede ser aproximada pero hay q mostrar un caso no optimo
   */
  public final int maxCatch(final char[] seqTP, final int distanceK) {
    if (seqTP == null)
      throw new IllegalArgumentException("seqTP can not be null");
    if (distanceK < 0)
      throw new IllegalArgumentException("distanceK must be non negative");
    if (seqTP.length == 0)
      return 0;

    for (char c : seqTP)
      if (c != 'P')
        if (c != 'T')
          throw new IllegalArgumentException("seqTP can only contain T and P chars");

    int cantThieves = 0;
    int cantPolices = 0;
    for (char c : seqTP)
      if (c == 'P')
        cantPolices++;
      else
        cantThieves++;
    if (cantPolices == seqTP.length || cantThieves == seqTP.length)
      return 0;

    ArrayList<Integer> policias = new ArrayList<>();
    ArrayList<Integer> ladrones = new ArrayList<>();
    for (int i = 0; i < seqTP.length; i++)
      if (seqTP[i] == 'P')
        policias.add(i);
      else
        ladrones.add(i);

    int indicePolicia = 0;
    int indiceLadron = 0;
    int resMax = 0;
    while (indicePolicia < policias.size() && indiceLadron < ladrones.size()) {
      int positionPoliceman = policias.get(indicePolicia);
      int positionThief = ladrones.get(indiceLadron);
      if (Math.abs(positionPoliceman - positionThief) <= distanceK) {
        resMax++;
        indiceLadron++;
        indicePolicia++;
      } else if (positionThief < positionPoliceman) {
        // no lo puede atrapar por la distancia
        // entonces entra a este if, y descarta el ladron porque ya no puede ser
        // atrapado por nadie
        indiceLadron++;
      } else {
        // el policia actual no pudo atrapar a nadie, descarta y prueba el siguiente
        indicePolicia++;
      }
    }
    return resMax;
  }
}



/*
 * recorres la secuencia
 * si es ladorn lo agregas a ladrones
 * si es policia lo agregas a policias
 * ahora tomas el primer ladron y el primer policia
 * si la distancia entre el ladron y el policia es menor a distanciaK, lo atrapa y avanzas en los dos
 * sino (no puede atrapar) revisas
 *  si el ladron estaba antes que el policia, entonces ya no se puede atrapar ese ladron, avanzas en los ladrones
 *  sino (el ladron esta despues que el policia, pero tampoco se pudo atrapar)
 *    significa que el policia no lo pudo atrapar, descartas el policia pero dejas el ladron quieto
 *    avanzas en los policias
 *
 * por cada atrapado sumas 1 al resultado y despues devolves la cantidad de atrapados totales
 */
