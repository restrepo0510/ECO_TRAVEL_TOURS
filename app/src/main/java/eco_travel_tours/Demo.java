package main.java.eco_travel_tours;

public class Demo {
    public static void main(String[] args) {
        ListaDobleReservas lista = new ListaDobleReservas();

      
        int opcion; 
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog(
                    "MENÚ RESERVAS\n"
                    + "1. Insertar reserva\n"
                    + "2. Buscar por ID\n"
                    + "3. Eliminar por ID\n"
                    + "4. Mostrar reservas\n"
                    + "5. Ordenar por Cliente\n"
                    + "6. Ordenar por Costo\n"
                    + "0. Salir\n"
                    + "Seleccione una opción:"));

            switch (opcion) {
                case 1: 

                    int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID de la reserva:"));
                    String cliente = JOptionPane.showInputDialog("Ingrese nombre del cliente:");
                    double costo = Double.parseDouble(JOptionPane.showInputDialog("Ingrese costo de la reserva:"));
                    Reserva r = new Reserva(id, cliente, costo);//Se crea un objeto reserva con los datos ingresados.
                    lista.insertar(r); 
                    JOptionPane.showMessageDialog(null, "Reserva insertada con éxito."); 
                    break;

                case 2:

                    int idBuscar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID a buscar:"));
                    Reserva encontrada = lista.buscarPorId(idBuscar);

                    if (encontrada != null) {
                        JOptionPane.showMessageDialog(null, "Reserva encontrada: " + encontrada); //Si existe, mostramos la información.
                    } else {
                        JOptionPane.showMessageDialog(null, "Reserva no encontrada."); //Si no, mostramos mensaje de error.
                    }
                    break;

                case 3:

                    int idEliminar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese ID a eliminar:"));
                    lista.eliminarPorId(idEliminar);
                    JOptionPane.showMessageDialog(null, "Operación realizada (si existía la reserva fue eliminada).");
                    break;

                case 4:

                    StringBuilder sb = new StringBuilder("LISTA DE RESERVAS:\n");
                    NodoDoble p = lista.getHead(); //Obtenemos el primer nodo.

                    while (p != null) {
                        sb.append(p.data).append("\n"); //Concateno cada reserva en el StringBuilder.
                        p = p.next; 
                    }

                    JOptionPane.showMessageDialog(null, sb.toString());
                    break;

                case 5:

                    lista.ordenarPorCliente();
                    JOptionPane.showMessageDialog(null, "Reservas ordenadas por Cliente.");
                    break;

                case 6:

                    lista.ordenarPorCosto();
                    JOptionPane.showMessageDialog(null, "Reservas ordenadas por Costo.");
                    break;

                case 0:

                    JOptionPane.showMessageDialog(null, "Saliendo del sistema...");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida, intente de nuevo.");
            }

        } while (opcion != 0); 
    }
}