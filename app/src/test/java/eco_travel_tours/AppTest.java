package eco_travel_tours;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test 
    void reservaSeCreaCorrectamente() {
        Reserva r = new Reserva("Ana", 1, 200.0);
        assertEquals("Ana", r.getCliente());
        assertEquals(1, r.getIdReserva());
        assertEquals(200.0, r.getCosto());
    }
}
