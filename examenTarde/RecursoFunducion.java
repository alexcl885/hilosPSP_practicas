
public class RecursoFunducion {
    private boolean fin = false;
    private int lingotesEnHorno = 0; // Lingotes disponibles en el horno
    private int lingotesRecolectados = 0; // Total de lingotes recolectados por personas
    private final int MAX_LINGOTES = 3; // Número máximo de lingotes que pueden recolectarse

    public synchronized boolean isFin() {
        return fin;
    }

    private synchronized void verificarFin() {
        if (lingotesRecolectados >= MAX_LINGOTES) {
            fin = true;
            notifyAll(); // Despierta a todos los hilos para que terminen
        }
    }

    public synchronized void crearLingote() {
        while (lingotesEnHorno >= 1 && !fin) {
            try {
                wait(); // Espera a que haya espacio en el horno
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (!fin) {
            lingotesEnHorno++;
            System.out.println("Lingotes en horno: " + lingotesEnHorno);
            notifyAll(); // Notifica a los consumidores
        }
    }

    public synchronized void consumirLingote(String nombre) throws InterruptedException {
        while (lingotesEnHorno == 0 && !fin) {
            wait(); // Espera a que haya lingotes en el horno
        }

        if (!fin) {
            lingotesEnHorno--;
            lingotesRecolectados++;
            System.out.println(nombre + " recolectó un lingote. Total recolectados: " + lingotesRecolectados);
            verificarFin();
            notifyAll(); // Notifica al productor para que pueda crear más lingotes
        }
    }
}