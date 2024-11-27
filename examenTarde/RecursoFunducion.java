import java.util.Random;

/**
 * Recurso compartido - sincronización mejorada
 */
public class RecursoFunducion {
    Random random = new Random();
    private boolean termina = false; // Atributo para finalizar programa
    private int LINGOTES_ACTUALES_HORNO = 0;
    private int LINGOTES_PERSONA = 0;

    /**
     * Verifica si el programa ha terminado
     * @return true si ha terminado, false si no
     */
    public synchronized boolean terminaPrograma() {
        return termina;
    }

    /**
     * Método para crear un lingote (hilo horno)
     */
    public void crearLingote() {
        synchronized (this) {
            while (LINGOTES_ACTUALES_HORNO == 1) { // Si ya hay un lingote, espera
                try {
                    System.out.println("Horno espera para crear un lingote...");
                    wait(); // Espera a que el consumidor consuma el lingote
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // Si no hay lingote, crea uno
            LINGOTES_ACTUALES_HORNO++;
            System.out.println("Creando lingote...");
            try {
                Thread.sleep(random.nextInt(3000)); // Simula tiempo de creación
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            notifyAll(); // Notifica a los consumidores
            System.out.println("Horno notifica a los consumidores.");
        }
    }

    /**
     * Método para consumir un lingote (hilo persona)
     */
    public void consumirLingote() {
        synchronized (this) {
            while (LINGOTES_ACTUALES_HORNO == 0) { // Si no hay lingotes, espera
                try {
                    System.out.println("Persona espera por un lingote...");
                    wait(); // Espera a que el horno cree un lingote
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // Si hay un lingote, consúmelo
            LINGOTES_PERSONA++;
            System.out.println("Consumo lingote. Lingotes en mi poder: " + LINGOTES_PERSONA);
            LINGOTES_ACTUALES_HORNO--; // Lingote consumido, horno queda vacío
            notifyAll(); // Notifica al horno que puede crear un nuevo lingote
            System.out.println("Persona notifica al horno.");
        }
    }

    /**
     * Método para verificar si un hilo ha terminado
     * @param nombre Nombre del hilo (persona)
     * @return true si ha terminado, false si no
     */
    public synchronized boolean finish(String nombre) {
        if (LINGOTES_PERSONA >= 2) {
            if (!termina) { // Solo imprime y actualiza una vez
                System.out.println("Soy " + nombre + " y he terminado de craftear");
                termina = true;
                notifyAll(); // Asegura que los otros hilos salgan si están esperando
            }
            return true;
        } else {
            return false;
        }
    }
}
