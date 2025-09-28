package main.java.eco_travel_tours;

public class NodoDoble {
    Reserva data;       // la reserva que guarda este nodo
    NodoDoble prev;     // enlace al nodo anterior
    NodoDoble next;     // enlace al nodo siguiente

    // CONSTRUCTOR
    public NodoDoble(Reserva r) {
        this.data = r;
        this.prev = null;
        this.next = null;
    }
}


