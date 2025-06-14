import java.util.ArrayList;
import java.util.List;

public class AgendaPersonal {
  private List<Cita> citas = new ArrayList<>();

  public void agregarCita(Cita c) {
    citas.add(c);
  }

  public List<Cita> getCitas() {
    return citas;
  }

  public static boolean seSolapan(Cita a, Cita b) {
    return !(a.getHoraFin().isBefore(b.getHoraInicio()) || a.getHoraFin().equals(b.getHoraInicio()) ||
        b.getHoraFin().isBefore(a.getHoraInicio()) || b.getHoraFin().equals(a.getHoraInicio()));
  }

  public static List<Cita> seleccionarCitasGreedy(List<Cita> citas) {
    citas.sort((a, b) -> a.getHoraFin().compareTo(b.getHoraFin()));

    List<Cita> seleccionadas = new ArrayList<>();
    for (Cita cita : citas) {
      boolean solapa = false;
      for (Cita sel : seleccionadas) {
        if (seSolapan(cita, sel)) {
          solapa = true;
          break;
        }
      }
      if (!solapa)
        seleccionadas.add(cita);
    }
    return seleccionadas;
  }
}

/*
 * organizamos las clases: Agenda y Citas
 *
 * ordenar las citas de un dia siguiendo una heuristica:
 * las de mayor (prioridad / duracion) primeras
 *
 * seleccionar en orden, ignorando las que se solapan con alguna ya seleccionada
 */