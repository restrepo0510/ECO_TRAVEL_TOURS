package eco_travel_tours;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Demo { //Se definió el punto de entrada de la aplicación donde se ejecuta el menú.
    public static void main(String[] args) {
        ListaDobleReservas lista = new ListaDobleReservas(); //Se creó la estructura principal (lista doble) que almacenará las reservas y servirá para todas las operaciones.

        int opcion;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog( // Se usa JOptionPane para mostrar el menú y capturar la opción escrita por el usuario

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
                    break; // Se usa switch para ejecutar la acción según la opción elegida (aquí insertar).
                        // Se piden los datos con JOptionPane, se crea la reserva y se agrega a la lista.

                }
                case 3: { // Eliminar
                    int id = Integer.parseInt(JOptionPane.showInputDialog("ID a eliminar:"));
                    lista.eliminarPorId(id);
                    JOptionPane.showMessageDialog(null, "Listo (si existía, se eliminó).");
                    break; // Se pide el ID, se elimina de la lista y se muestra mensaje de confirmación.

                }
                case 4: { // Mostrar
                    JOptionPane.showMessageDialog(null, lista.imprimirComoTexto());
                    break;  // Se muestran todas las reservas en un cuadro de diálogo.
                }
               case 5: { // Ordenar por cliente (pregunta A/D)
                    String resp = JOptionPane.showInputDialog("Ordenar por cliente: A = ascendente, D = descendente").trim();
                    boolean asc = resp.equalsIgnoreCase("A");
                    lista.ordenarPorCliente(asc);
                    JOptionPane.showMessageDialog(null, "Ordenada por cliente (" + (asc ? "A" : "D") + ").");
                    break;
                }
              case 6: { // Ordenar por costo (pregunta A/D) - desempata por cliente
                    String resp = JOptionPane.showInputDialog("Ordenar por costo: A = ascendente, D = descendente").trim();
                    boolean asc = resp.equalsIgnoreCase("A");
                    lista.ordenarPorCosto(asc);
                    JOptionPane.showMessageDialog(null, "Ordenada por costo (" + (asc ? "A" : "D") + ").");
                    break;
                }
                case 7: { // Estadísticas punto 3
                    if (!lista.hayDatos()) {
                        JOptionPane.showMessageDialog(null, "No hay datos.");
                        break; // Se verifica si hay datos; si no, se muestra mensaje y se detiene la opción.
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
                    break;   // Se arma un texto con todas las estadísticas de costo usando StringBuilder
                            // y se muestra al usuario en un cuadro de diálogo.
                }
                case 8: { // Top N
                    if (!lista.hayDatos()) {
                        JOptionPane.showMessageDialog(null, "No hay datos.");
                        break; // Se valida si existen datos; si no, se avisa al usuario y se detiene la opción.
                    }
                    int n = Integer.parseInt(JOptionPane.showInputDialog("¿N?"));
                    ArrayList<Double> altos = lista.topNAltos(n);
                    ArrayList<Double> bajos = lista.topNBajos(n);
                    StringBuilder sb = new StringBuilder();
                    sb.append("Top ").append(n).append(" costos MÁS ALTOS:\n").append(altos).append("\n\n");
                    sb.append("Top ").append(n).append(" costos MÁS BAJOS:\n").append(bajos).append("\n");
                    JOptionPane.showMessageDialog(null, sb.toString());
                    break; // Se pide N, se obtienen los N costos más altos y más bajos, 
                        // se arma el texto con StringBuilder y se muestra al usuario.
                }
                case 0:
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (opcion != 0); //Se empleó un bucle para mostrar el menú repetidamente hasta que el usuario elija salir.
    }
}
