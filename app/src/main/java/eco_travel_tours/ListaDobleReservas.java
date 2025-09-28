package main.java.eco_travel_tours;

public class ListaDobleReservas {
    private NodoDoble head; // primer nodo
    private NodoDoble cola; // último nodo

    // FUNCIÓN INSERTAR
    public void insertar(Reserva r) {
        NodoDoble nuevo = new NodoDoble(r);
        if (head == null) { // lista vacía
            head = cola = nuevo;
        } else {
            cola.next = nuevo;
            nuevo.prev = cola;
            cola = nuevo;
        }
    }

    // BUSACR
    public Reserva buscarPorId(int id) {
        NodoDoble p = head;
        while (p != null) {
            if (p.data.getIdReserva() == id) {
                return p.data;
            }
            p = p.next;
        }
        return null; // no encontrado
    }

    // ELIMINAR
    public void eliminarPorId(int id) {
        NodoDoble p = head;
        while (p != null) {
            if (p.data.getIdReserva() == id) {
                if (p.prev != null) {
                    p.prev.next = p.next;
                } else {
                    head = p.next; // era la cabeza
                }
                if (p.next != null) {
                    p.next.prev = p.prev;
                } else {
                    cola = p.prev; // era la cola
                }
                return; // eliminado
            }
            p = p.next;
        }
        System.out.println("No se encontró id=" + id);
    }

    // Imprimir
    public void imprimir() {
        NodoDoble p = head;
        while (p != null) {
            System.out.print(p.data + " <-> ");
            p = p.next;
        }
        System.out.println("null");
    }

    // ORDENAMIENTO 1--> nombre cliente alfabetico
    public void ordenarPorCliente() {
        if (head == null)
            return;
        NodoDoble i = head;
        while (i != null) {
            NodoDoble j = i.next;
            while (j != null) {
                if (i.data.getCliente().compareToIgnoreCase(j.data.getCliente()) > 0) {
                    Reserva tmp = i.data;
                    i.data = j.data;
                    j.data = tmp;
                }
                j = j.next;
            }
            i = i.next;
        }
    }

    // ORDENAMIENTO 2--> costo menor a mayor
    public void ordenarPorCosto() {
        if (head == null)
            return;
        NodoDoble i = head;
        while (i != null) {
            NodoDoble j = i.next;
            while (j != null) {
                if (i.data.getCosto() > j.data.getCosto()) {
                    Reserva tmp = i.data;
                    i.data = j.data;
                    j.data = tmp;
                }
                j = j.next;
            }
            i = i.next;
        }
    }
}
