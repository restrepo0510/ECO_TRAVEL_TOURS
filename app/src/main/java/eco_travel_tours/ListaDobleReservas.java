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

    //Punto 3

    public int contarReservas() {
        int contReservas = 0;
        NodoDoble p = head;
        while (p != null) { // recorremos toda la lista
            count++;
            p = p.next;
        }
        return contReservas; // devolvemos el total
    }

    public double promedio() {
        if (head == null) return 0; // si la lista está vacía, retorno 0
        double sumaCostos = 0;
        int contProm = 0;
        NodoDoble p = head;
        while (p != null) {
            sumaCostos += p.data.getCosto(); // sumo cada costo
            contProm++;
            p = p.next;
        }
        return sumaCostos / contProm;
    }

    public double minimo() {
        if (head == null) return 0;
        double min = head.data.getCosto();
        NodoDoble p = head.next; // empezamos desde el segundo nodo
        while (p != null) {
            if (p.data.getCosto() < min) { // si encuentro un menor
                min = p.data.getCosto(); // lo actualizo
            }
            p = p.next;
        }
        return min; // retorno el mínimo encontrado
    }

    public double maximo() {
        if (head == null) return 0;
        double max = head.data.getCosto(); // inicio con el primer costo
        NodoDoble p = head.next;
        while (p != null) {
            if (p.data.getCosto() > max) { // si encuentro un mayor
                max = p.data.getCosto(); // lo actualizo
            }
            p = p.next;
        }
        return max;
    }

    public double rango() {
        return maximo() - minimo(); // diferencia entre el mayor y el menor
    }

    public double varianza() {
        if (head == null) return 0;
        double prom = promedio(); // calculo el promedio
        double suma = 0; // acumulador
        int contador= 0; // cantidad de elementos
        NodoDoble p = head;
        while (p != null) {
            suma += Math.pow(p.data.getCosto() - prom, 2); // diferencia al cuadrado respecto al promedio
            count++;
            p = p.next;
        }
        return suma / count; // varianza poblacional
    }

    public double desviacionEstandar() {
        return Math.sqrt(varianza()); // raíz cuadrada de la varianza
    }

    public double mediana() {
        ordenarPorCosto();         // ordenamos la lista por costo
        int n = contarReservas();  // cantidad de reservas
        if (n == 0) return 0;      // lista vacía
        NodoDoble p = head;        // recorremos hasta la mitad
        for (int i = 0; i < (n - 1) / 2; i++) {
            p = p.next;
        }
        if (n % 2 == 1) {
            return p.data.getCosto();                    // si es impar → valor del medio
        } else {
            return (p.data.getCosto() + p.next.data.getCosto()) / 2.0; // si es par → promedio de los 2 del centro
        }
    }

    public double moda() {
        if (head == null) return 0;  
        ordenarPorCosto();           // ordenamos por costo
        double moda = head.data.getCosto(); // arranco con el primero
        int maxCont = 1, cont = 1;        // frecuencias
        NodoDoble p = head.next;

        while (p != null) {
            if (p.data.getCosto() == p.prev.data.getCosto()) {
                cont++;                    // aumento frecuencia
                if (count > maxCont) {     // si es la mayor frecuencia encontrada
                    maxCont = cont;
                    moda = p.data.getCosto(); // actualizo moda
                }
            } else {
                cont = 1;                  // reinicio conteo si cambia el valor
            }
            p = p.next;
        }
        return moda;                        
    }

    public String tablaFrecuencias() {
        if (head == null) return "Lista vacía"; 
        ordenarPorCosto();                     
        StringBuilder sb = new StringBuilder("Costo | Frecuencia\n");
        NodoDoble p = head;
        while (p != null) {
            double costo = p.data.getCosto();   // costo actual
            int cont = 0;                      // frecuencia
            while (p != null && p.data.getCosto() == costo) {
                cont++;                        // contamos cuántos iguales hay
                p = p.next;
            }
            sb.append(costo).append(" | ").append(cont).append("\n"); // añadimos a la tabla
        }
        return sb.toString();                   // devolvemos la tabla
    }

    public String topNAltos(int n) {
        ordenarPorCosto();          
        StringBuilder sb = new StringBuilder();
        NodoDoble p = cola;         // empezamos desde el último (el mayor)
        int count = 0;
        while (p != null && count < n) {
            sb.append(p.data).append("\n"); // añadimos al resultado
            p = p.prev;                     // retrocedemos
            count++;
        }
        return sb.toString();       // devolvemos los N más altos
    }

    public String topNBajos(int n) {
        ordenarPorCosto();          
        StringBuilder sb = new StringBuilder();
        NodoDoble p = head;         // empezamos desde el menor
        int count = 0;
        while (p != null && count < n) {
            sb.append(p.data).append("\n"); // añadimos al resultado
            p = p.next;                    
            count++;
        }
        return sb.toString();       // devolvemos los N más bajos
    }

    public String imprimirEnTexto() {
        // Se usa un StringBuilder para ir armando una cadena de texto con todas las reservas
        StringBuilder sb = new StringBuilder();
        NodoDoble p = head;
        while (p != null) {
            // Se añade cada reserva en formato texto (gracias al toString de Reserva)
            sb.append(p.data.toString()).append("\n");
            // Avanzamos al siguiente nodo
            p = p.next;
        }
        
        // Si la lista está vacía, mostramos un aviso
        if (sb.length() == 0) {
            return "¡La lista está vacía.!";
        }
        // Retornamos el texto completo con todas las reservas
        return sb.toString();
    }

}

