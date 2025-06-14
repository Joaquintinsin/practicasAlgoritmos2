import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {
  private String descripcion;
  private LocalDate fecha;
  private LocalTime horaInicio;
  private LocalTime horaFin;
  private int prioridad;

  public Cita(String descripcion, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, int prioridad) {
    this.descripcion = descripcion;
    this.fecha = fecha;
    this.horaInicio = horaInicio;
    this.horaFin = horaFin;
    this.prioridad = prioridad;
  }

  public String getDescripcion() {
    return descripcion;
  }

  public LocalDate getFecha() {
    return fecha;
  }

  public LocalTime getHoraInicio() {
    return horaInicio;
  }

  public LocalTime getHoraFin() {
    return horaFin;
  }

  public int getPrioridad() {
    return prioridad;
  }

  public int getDuracion() {
    return horaFin.toSecondOfDay() - horaInicio.toSecondOfDay();
  }

  public double getRatio() {
    return (double) prioridad / getDuracion();
  }

  @Override
  public String toString() {
    return descripcion + " (" + horaInicio + " - " + horaFin + ") Prioridad: " + prioridad;
  }
}