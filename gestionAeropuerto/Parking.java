public class Parking {
    private int PLAZAS_ESTANDAR_ACTUALES = 30;
    private int PLAZAS_ELECTRICOS_ACTUALES = 20;

    private int numero_coches_totales = 0; // Contador de coches que han aparcado
    private final int MAX_COCHES = 100; // Máximo de coches que se pueden gestionar

    private boolean fin = false; // Bandera para terminar el programa

    // Método para verificar si el programa debe terminar
    public synchronized boolean isFin() {
        return fin;
    }

    public synchronized void setFin(boolean fin) {
        this.fin = fin;
    }

    // Verificar si se puede seguir gestionando coches
    public synchronized boolean parkingCompleto() {
        if (numero_coches_totales >= MAX_COCHES) {
            setFin(true); // Si llegamos al máximo, indicamos que el programa debe terminar
            return false;
        }
        return true;
    }

    // Método para entrar a la zona de coches normales
    public synchronized boolean entrarZonaNormal() {
        if (PLAZAS_ESTANDAR_ACTUALES > 0) {
            PLAZAS_ESTANDAR_ACTUALES--;
            numero_coches_totales++;
            return true;
        }
        return false; // No hay plazas disponibles
    }

    // Método para salir de la zona de coches normales
    public synchronized void salirZonaNormal() {
        PLAZAS_ESTANDAR_ACTUALES++;
    }

    // Método para entrar a la zona de coches eléctricos
    public synchronized boolean entrarZonaElectricos() {
        if (PLAZAS_ELECTRICOS_ACTUALES > 0) {
            PLAZAS_ELECTRICOS_ACTUALES--;
            numero_coches_totales++;
            return true;
        }
        return false; // No hay plazas disponibles
    }

    // Método para salir de la zona de coches eléctricos
    public synchronized void salirZonaElectricos() {
        PLAZAS_ELECTRICOS_ACTUALES++;
    }
}
