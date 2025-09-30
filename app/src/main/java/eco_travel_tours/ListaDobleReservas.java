package eco_travel_tours;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ListaDobleReservas {
    private NodoDoble head; // primero
    private NodoDoble cola; // último
    private int size;

    // ==== utilidades básicas ====
    public boolean estaVacia() { return head == null; }
    public int size() { return size; }
    public NodoDoble getHead() { return head; } // útil si quieres recorrer desde fuera

    // ==== insertar (al final) ====
    public void insertar(Reserva r) {
        if (r == null) {
            System.out.println("Error: reserva nula");
            return;
        }
        // evitar id duplicado
        if (buscarPorId(r.getIdReserva()) != null) {
            System.out.println("Error: id repetido (" + r.getIdReserva() + ")");
            return;
        }
        NodoDoble nuevo = new NodoDoble(r);
        if (head == null) {
            head = cola = nuevo;
        } else {
            cola.next = nuevo;
            nuevo.prev = cola;
            cola = nuevo;
        }
        size++;
    }

    // ==== buscar por id ====
    public Reserva buscarPorId(int id) {
        NodoDoble p = head;
        while (p != null) {
            if (p.data.getIdReserva() == id) return p.data;
            p = p.next;
        }
        return null;
    }

    // ==== eliminar por id ====
    public void eliminarPorId(int id) {
        NodoDoble p = head;
        while (p != null) {
            if (p.data.getIdReserva() == id) {
                if (p.prev != null) p.prev.next = p.next; else head = p.next;
                if (p.next != null) p.next.prev = p.prev; else cola = p.prev;
                size--;
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
        StringBuilder sb = new StringBuilder();
        NodoDoble p = head;
        while (p != null) {
            sb.append(p.data).append("\n");
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
                if (i.data.getCliente().compareToIgnoreCase(j.data.getCliente()) > 0) {
                    Reserva tmp = i.data; i.data = j.data; j.data = tmp;
                }
                j = j.next;
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
                if (i.data.getCosto() > j.data.getCosto()) {
                    Reserva tmp = i.data; i.data = j.data; j.data = tmp;
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

    private ArrayList<Double> getCostos() {
        ArrayList<Double> costos = new ArrayList<>();
        NodoDoble p = head;
        while (p != null) {
            costos.add(p.data.getCosto());
            p = p.next;
        }
        return costos;
    }

    public boolean hayDatos() { return size > 0; }

    public double promedioCosto() {
        ArrayList<Double> c = getCostos();
        if (c.isEmpty()) return 0;
        double suma = 0;
        for (double x : c) suma += x;
        return suma / c.size();
    }

    public double minimoCosto() {
        ArrayList<Double> c = getCostos();
        if (c.isEmpty()) return 0;
        double m = c.get(0);
        for (double x : c) if (x < m) m = x;
        return m;
    }

    public double maximoCosto() {
        ArrayList<Double> c = getCostos();
        if (c.isEmpty()) return 0;
        double M = c.get(0);
        for (double x : c) if (x > M) M = x;
        return M;
    }

    public double rangoCosto() {
        if (!hayDatos()) return 0;
        return maximoCosto() - minimoCosto();
    }

    public double varianzaCosto() {
        ArrayList<Double> c = getCostos();
        if (c.isEmpty()) return 0;
        double prom = promedioCosto();
        double acum = 0;
        for (double x : c) {
            double d = x - prom;
            acum += d * d;
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
