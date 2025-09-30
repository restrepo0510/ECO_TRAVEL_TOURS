package eco_travel_tours;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Demo {
    public static void main(String[] args) {
        ListaDobleReservas lista = new ListaDobleReservas();

        int opcion;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                "MENÚ RESERVAS\n" +
                "1. Insertar reserva\n" +
                "2. Buscar por ID\n" +
                "3. Eliminar por ID\n" +
                "4. Mostrar reservas\n" +
                "5. Ordenar por Cliente\n" +
                "6. Ordenar por Costo\n" +
                "7. Estadísticas (punto 3)\n" +
                "8. Top N más altos / más bajos\n" +
                "0. Salir\n" +
                "Seleccione una opción:"));

            switch (opcion) {
                case 1: { // Insertar
                    String cli = JOptionPane.showInputDialog("Cliente:");
                    int id = Integer.parseInt(JOptionPane.showInputDialog("ID:"));
                    double costo = Double.parseDouble(JOptionPane.showInputDialog("Costo:"));
                    Reserva r = new Reserva(cli, id, costo);
                    lista.insertar(r);
                    JOptionPane.showMessageDialog(null, "Reserva insertada.");
                    break;
                }
                case 2: { // Buscar
                    int id = Integer.parseInt(JOptionPane.showInputDialog("ID a buscar:"));
                    Reserva r = lista.buscarPorId(id);
                    JOptionPane.showMessageDialog(null, (r != null) ? r.toString() : "No encontrada.");
                    break;
                }
                case 3: { // Eliminar
                    int id = Integer.parseInt(JOptionPane.showInputDialog("ID a eliminar:"));
                    lista.eliminarPorId(id);
                    JOptionPane.showMessageDialog(null, "Listo (si existía, se eliminó).");
                    break;
                }
                case 4: { // Mostrar
                    JOptionPane.showMessageDialog(null, lista.imprimirComoTexto());
                    break;
                }
                case 5: { // Ordenar por cliente
                    lista.ordenarPorCliente();
                    JOptionPane.showMessageDialog(null, "Ordenada por cliente.");
                    break;
                }
                case 6: { // Ordenar por costo
                    lista.ordenarPorCosto();
                    JOptionPane.showMessageDialog(null, "Ordenada por costo.");
                    break;
                }
                case 7: { // Estadísticas punto 3
                    if (!lista.hayDatos()) {
                        JOptionPane.showMessageDialog(null, "No hay datos.");
                        break;
                    }
                    StringBuilder sb = new StringBuilder("ESTADÍSTICAS DE COSTO\n");
                    sb.append("Promedio: ").append(lista.promedioCosto()).append("\n");
                    sb.append("Mediana : ").append(lista.medianaCosto()).append("\n");
                    Double moda = lista.modaCosto();
                    sb.append("Moda    : ").append(moda == null ? "(sin moda)" : moda).append("\n");
                    sb.append("Mínimo  : ").append(lista.minimoCosto()).append("\n");
                    sb.append("Máximo  : ").append(lista.maximoCosto()).append("\n");
                    sb.append("Rango   : ").append(lista.rangoCosto()).append("\n");
                    sb.append("Varianza: ").append(lista.varianzaCosto()).append("\n");
                    sb.append("Desv.Est: ").append(lista.desviacionEstandarCosto()).append("\n");
                    JOptionPane.showMessageDialog(null, sb.toString());
                    break;
                }
                case 8: { // Top N
                    if (!lista.hayDatos()) {
                        JOptionPane.showMessageDialog(null, "No hay datos.");
                        break;
                    }
                    int n = Integer.parseInt(JOptionPane.showInputDialog("¿N?"));
                    ArrayList<Double> altos = lista.topNAltos(n);
                    ArrayList<Double> bajos = lista.topNBajos(n);
                    StringBuilder sb = new StringBuilder();
                    sb.append("Top ").append(n).append(" costos MÁS ALTOS:\n").append(altos).append("\n\n");
                    sb.append("Top ").append(n).append(" costos MÁS BAJOS:\n").append(bajos).append("\n");
                    JOptionPane.showMessageDialog(null, sb.toString());
                    break;
                }
                case 0:
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (opcion != 0);
    }
}
