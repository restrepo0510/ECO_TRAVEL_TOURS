package main.java.eco_travel_tours;

public class Reserva {
    private String cliente;
    private int idReserva;
    private double costo;

    // CCONSTRUCTOR
    public Reserva(String cliente, int idReserva, double costo) {
        this.cliente = cliente;
        this.idReserva = idReserva;
        this.costo = costo;
    }

    // GETTERS (obtener datos)
    public String getCliente() {
        return cliente;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public double getCosto() {
        return costo;
    }

    // SETTERS (cambiar datos)
    public void setCliente(String cliente) {
        if (cliente == null) {
            System.out.println("Error: El nombre del cliente no puede estar vacio");
        } else {
            this.cliente = cliente;
        }
    }

    public void setCosto(double costo) {
        if (costo < 0) {
            System.out.println("Error: El costo no puede ser menor a 0");
        } else {
            this.costo = costo;
        }
    }

    // SOBREESCRIBIR el toString de java
    @Override
    public String toString() {
        return "Reserva { id=" + idReserva +
                ", cliente='" + cliente + "', costo=" + costo + " }";
    }
}

