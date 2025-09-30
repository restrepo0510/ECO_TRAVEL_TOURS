package eco_travel_tours;

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
