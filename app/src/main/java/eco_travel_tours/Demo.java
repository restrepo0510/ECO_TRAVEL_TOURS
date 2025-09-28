package main.java.eco_travel_tours;

public class Demo {
    public static void main(String[] args) {
        ListaDobleReservas lista = new ListaDobleReservas();

        // Insertar reservas
        lista.insertar(new Reserva("Pedro", 1, 200));
        lista.insertar(new Reserva("Ana", 2, 150));
        lista.insertar(new Reserva("Luis", 3, 300));

        System.out.println("Lista original:");
        lista.imprimir();

        // Buscar
        System.out.println("Buscar id=2 -> " + lista.buscarPorId(2));

        // Eliminar
        lista.eliminarPorId(1);
        System.out.println("Después de eliminar id=1:");
        lista.imprimir();

        // Ordenar por cliente
        lista.ordenarPorCliente();
        System.out.println("Ordenada por cliente:");
        lista.imprimir();

        // Ordenar por costo
        lista.ordenarPorCosto();
        System.out.println("Ordenada por costo:");
        lista.imprimir();
    }
}
