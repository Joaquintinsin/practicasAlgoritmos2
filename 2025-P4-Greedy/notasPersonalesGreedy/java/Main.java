import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    Cita c1 = new Cita("Reunión A", LocalDate.now(), LocalTime.of(9, 0), LocalTime.of(10, 0), 5);
    Cita c2 = new Cita("Llamada B", LocalDate.now(), LocalTime.of(9, 30), LocalTime.of(11, 0), 3);
    Cita c3 = new Cita("Cita C", LocalDate.now(), LocalTime.of(10, 0), LocalTime.of(11, 0), 8);
    Cita c4 = new Cita("Almuerzo", LocalDate.now(), LocalTime.of(12, 0), LocalTime.of(13, 0), 4);
    Cita c5 = new Cita("Charla D", LocalDate.now(), LocalTime.of(11, 0), LocalTime.of(12, 0), 6);

    List<Cita> todas = new ArrayList<>(List.of(c1, c2, c3, c4, c5));
    List<Cita> seleccionadas = AgendaPersonal.seleccionarCitasGreedy(todas);

    System.out.println("Citas seleccionadas:");
    for (Cita c : seleccionadas) {
      System.out.println(c.getDescripcion() + " (" + c.getHoraInicio() + " - " + c.getHoraFin() + ")");
    }
  }
}
