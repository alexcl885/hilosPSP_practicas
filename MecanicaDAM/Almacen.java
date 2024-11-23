import java.util.Random;

/*CLASE SEGURA - RECURSO COMPARTIDO */
public class Almacen {
    Random random = new Random();
    Object object = new Object();
    private int piezas_actuales = 250;
    private boolean cerrado = false;

    // Bandera para indicar si el almacén está cerrado
    public synchronized Object getObject() {
        return this.object;
    }

    public synchronized void setObject(Object object) {
        this.object = object;
    }

    public synchronized int getPiezas_actuales() {
        return this.piezas_actuales;
    }

    public synchronized void setPiezas_actuales(int piezas_actuales) {
        this.piezas_actuales = piezas_actuales;
    }

    public boolean disponible() {
        synchronized (object) {
            return piezas_actuales > 0;
        }
    }

    public boolean suministros(int piezas) {
        synchronized (object) {
            if (piezas_actuales < piezas) {
                piezas_actuales = 0;
                return false;
            } else {
                return true;
            }
        }
    }

    public void reparar(int piezas) {
        synchronized (object) {
            if (piezas_actuales >= piezas) {
                piezas_actuales -= piezas;
            } else {
                piezas_actuales = 0;
                System.out.println("No se puede reparar todo. Piezas actuales insuficientes, ajustado a 0.");
            }
            if (piezas_actuales == 0) {
                cerrado = true;
                System.err.println("No hay piezas disponibles. Finalizando...");
            }
            Estado();
        }
    }

    public boolean piezasDisponibles(int piezas) {
        synchronized (object) {
            return piezas_actuales == 0;
        }
    }

    public void introducirPiezas() {
        synchronized (object) {
            if (!cerrado) {
                int PIEZAS_REPONER = random.nextInt(20, 39);
                piezas_actuales += PIEZAS_REPONER;
                Estado();
            }
        }
    }

    public void Estado() {
        System.out.println("ESTADO ALMACEN: " + this.piezas_actuales);
    }

    public boolean isCerrado() {
        return cerrado;
    }
}