package eco_travel_tours;

// Constructor: cuando se crea el nodo, guarda la reserva recibida
// y deja las conexiones (anterior y siguiente) vacías al inicio.

public class NodoDoble {
    Reserva data;
    NodoDoble prev;
    NodoDoble next;

    public NodoDoble(Reserva r) {
        this.data = r;
        this.prev = null;
        this.next = null;
    }
}
