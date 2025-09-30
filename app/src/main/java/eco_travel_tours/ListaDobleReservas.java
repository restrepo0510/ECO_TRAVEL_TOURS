package eco_travel_tours;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ListaDobleReservas {
    private NodoDoble head; // primero , Se definió el atributo head que apunta al primer nodo.
    private NodoDoble cola; // último, definió el atributo cola que apunta al último nodo.
    private int size; // Se definió el atributo size para contar elementos.

    // ==== utilidades básicas ====
    public boolean estaVacia() { return head == null; } //Se implementó método que indica si la lista está vacía (true si head es null).
    public int size() { return size; }  //Se implementó método que devuelve el tamaño almacenado.
    public NodoDoble getHead() { return head; } //Se implementó método que devuelve la referencia al head.

    // ==== insertar (al final) ====
    public void insertar(Reserva r) {
        if (r == null) {
            System.out.println("Error: reserva nula");
            return;
        }
        // evitar id duplicado
        if (buscarPorId(r.getIdReserva()) != null) { //Se consultó si ya existe una reserva con el mismo id.
            System.out.println("Error: id repetido (" + r.getIdReserva() + ")");
            return;
        }
        NodoDoble nuevo = new NodoDoble(r);
        if (head == null) {
            head = cola = nuevo; //Se asignaron head y cola al nuevo nodo (lista de un elemento).
        } else {
            cola.next = nuevo; //Se enlazó el siguiente del cola al nuevo nodo.
            nuevo.prev = cola; //Se enlazó el previo del nuevo nodo al anterior cola.
            cola = nuevo; //Se actualizó cola para que apunte al nuevo nodo (último).
        }
        size++; //Se incrementó el contador size.
    }

    // ==== buscar por id ====
    public Reserva buscarPorId(int id) { //Se declaró el método buscarPorId que recibe un id.
        NodoDoble p = head; //Se inicializó un puntero p en el head.
        while (p != null) { //Se inició un bucle para recorrer la lista mientras haya nodos.
            if (p.data.getIdReserva() == id) return p.data; //Se comparó el id de la reserva del nodo y devuelve la reserva si coincide.
            p = p.next;
        }
        return null;
    }

    // ==== eliminar por id ====
    public void eliminarPorId(int id) {
        NodoDoble p = head;
        while (p != null) { 
            if (p.data.getIdReserva() == id) { //Se comprobó si el nodo actual contiene la reserva con el id buscado.
                if (p.prev != null) p.prev.next = p.next; else head = p.next;  //Se actualizó el enlace del nodo anterior o head si no existe previo.
                if (p.next != null) p.next.prev = p.prev; else cola = p.prev; //Se actualizó el enlace del nodo siguiente o cola si no existe siguiente.
                size--; //Se decrementó el contador size al eliminar el nodo.
                return;
            }
            p = p.next;
        }
        System.out.println("No se encontró id=" + id);
    }

    // ==== imprimir ====
    public void imprimir() {
        System.out.println(imprimirComoTexto());
    }

    // Devolver la lista como texto (útil para JOptionPane)
    public String imprimirComoTexto() {
        StringBuilder sb = new StringBuilder(); //Se creó un StringBuilder para construir el texto.
        NodoDoble p = head; //Se inicializó el puntero p en el head.
        while (p != null) {
            sb.append(p.data).append("\n"); //Se agregó la representación de la reserva y un salto de línea al StringBuilder.
            p = p.next;
        }
        if (sb.length() == 0) sb.append("(lista vacía)");
        return sb.toString();
    }

    // ==== ordenamientos in-place (intercambiando data en nodos) ====
    public void ordenarPorCliente() {
        if (head == null) return;
        NodoDoble i = head;
        while (i != null) {
            NodoDoble j = i.next;
            while (j != null) {
                if (i.data.getCliente().compareToIgnoreCase(j.data.getCliente()) > 0) { //Se compararon los nombres de cliente ignorando mayúsculas para decidir intercambio.
                    Reserva tmp = i.data; i.data = j.data; j.data = tmp; //Se intercambió el contenido (data) de los nodos i y j.
                }
                j = j.next; //Se avanzó j al siguiente nodo.
            }
            i = i.next;
        }
    }

    public void ordenarPorCosto() {
        if (head == null) return;
        NodoDoble i = head;
        while (i != null) {
            NodoDoble j = i.next;
            while (j != null) {
                if (i.data.getCosto() > j.data.getCosto()) { //Se compararon costos para decidir intercambio.
                    Reserva tmp = i.data; i.data = j.data; j.data = tmp; //Se intercambió el contenido (data) de i y j.
                }
                j = j.next;
            }
            i = i.next;
        }
    }

    // ============================================================
    //                P U N T O   3 :  E S T A D Í S T I C A S
    // (Se usa ArrayList solo para cálculos; NO para ordenar la lista)
    // ============================================================

    private ArrayList<Double> getCostos() {  //Se declaró método privado que recopila todos los costos en un ArrayList.
        ArrayList<Double> costos = new ArrayList<>();
        NodoDoble p = head;
        while (p != null) {
            costos.add(p.data.getCosto());
            p = p.next;
        }
        return costos;
    }

    public boolean hayDatos() { return size > 0; } //Se implementó método que indica si hay datos (size mayor a 0).

    public double promedioCosto() { //Se declaró método para calcular el promedio de costos.
        ArrayList<Double> c = getCostos();
        if (c.isEmpty()) return 0; //devuelve 0 si no hay costos.
        double suma = 0; //Se inicializó acumulador suma.
        for (double x : c) suma += x; //Se sumaron todos los elementos de la lista de costos.
        return suma / c.size(); //devuelve la media (suma dividida por cantidad).
    }

    public double minimoCosto() {
        ArrayList<Double> c = getCostos();
        if (c.isEmpty()) return 0;
        double m = c.get(0);
        for (double x : c) if (x < m) m = x;
        return m;
    }

    public double maximoCosto() {
        ArrayList<Double> c = getCostos(); //Se obtuvo la lista de costos.
        if (c.isEmpty()) return 0; //devuelve 0 si la lista está vacía.
        double M = c.get(0); //Se inicializó m con el primer elemento como candidato mínimo.
        for (double x : c) if (x > M) M = x;  //Se recorrieron los costos y se actualizó m si se encontró menor.
        return M;
    }

    public double rangoCosto() {
        if (!hayDatos()) return 0; //devuelve 0 si no hay datos.
        return maximoCosto() - minimoCosto();
    }
 
    public double varianzaCosto() { //Se declaró método para calcular la varianza poblacional.
        ArrayList<Double> c = getCostos();
        if (c.isEmpty()) return 0;
        double prom = promedioCosto();
        double acum = 0;
        for (double x : c) {
            double d = x - prom; //Se calculó la diferencia entre el valor y el promedio.
            acum += d * d; //Se sumó el cuadrado de la diferencia al acumulador.
        }
        return acum / c.size(); // varianza poblacional
    }

    public double desviacionEstandarCosto() {
        return Math.sqrt(varianzaCosto());
    }

    public double medianaCosto() {
        ArrayList<Double> c = getCostos();
        if (c.isEmpty()) return 0;
        Collections.sort(c);
        int n = c.size();
        if (n % 2 == 1) return c.get(n / 2);
        return (c.get(n / 2 - 1) + c.get(n / 2)) / 2.0;
    }

    public Double modaCosto() {
        ArrayList<Double> c = getCostos();
        if (c.isEmpty()) return null;
        Map<Double, Integer> freq = new HashMap<>();
        for (double x : c) freq.put(x, freq.getOrDefault(x, 0) + 1);
        Double moda = null; int mejor = 0;
        for (Map.Entry<Double, Integer> e : freq.entrySet()) {
            if (e.getValue() > mejor) { mejor = e.getValue(); moda = e.getKey(); }
        }
        // si todas aparecen 1 vez, devolvemos null (sin moda)
        return (mejor <= 1) ? null : moda;
    }

    // Top N mayores (costos)
    public ArrayList<Double> topNAltos(int n) {
        ArrayList<Double> c = getCostos();
        Collections.sort(c); // asc
        Collections.reverse(c); // desc
        if (n > c.size()) n = c.size();
        return new ArrayList<>(c.subList(0, n));
    }

    // Top N menores (costos)
    public ArrayList<Double> topNBajos(int n) {
        ArrayList<Double> c = getCostos();
        Collections.sort(c); // asc
        if (n > c.size()) n = c.size();
        return new ArrayList<>(c.subList(0, n));
    }
}
