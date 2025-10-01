package eco_travel_tours;

public class Reserva {
    private String cliente;
    private int idReserva;
    private double costo; 
    //estos tres atributos guardan la información de cada reserva (quién la hizo, su ID y el costo).

    // Constructor simple 
    public Reserva(String cliente, int idReserva, double costo) {
        this.cliente = cliente;
        this.idReserva = idReserva;
        this.costo = costo;
    }

    // GETTERS
    public String getCliente() { return cliente; }
    public int getIdReserva() { return idReserva; }
    public double getCosto() { return costo; }

    // SETTERS con control básico (mensajes y no cambia si es inválido)
    public void setCliente(String cliente) {
        if (cliente == null || cliente.equals("")) {
            System.out.println("Error: El nombre del cliente no puede estar vacío");
        } else {
            this.cliente = cliente;
        }
    }

    public void setCosto(double costo) {
        if (costo < 0) {
            System.out.println("Error: El costo no puede ser menor a 0");
        } else {
            this.costo = costo;
        } //cambia el costo, validando que no sea negativo.
    }

    @Override //indica que se está sobrescribiendo el método toString heredado de Object.
    public String toString() {
        return "Reserva { id=" + idReserva +
               ", cliente='" + cliente + "', costo=" + costo + " }";
    }
} //devuelve la información de la reserva en formato de texto legible.