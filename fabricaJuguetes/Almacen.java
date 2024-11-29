public class Almacen {
    private int PLASTICO_ACTUAL = 5000;
    private int PINTURA_ACTUAL = 5000;
    private int PEGAMENTO_ACTUAL = 100;

    private boolean fin = false;
    private int contador_juguete;

    public int getPLASTICO_ACTUAL() {
        return this.PLASTICO_ACTUAL;
    }

    synchronized public void setPLASTICO_ACTUAL(int PLASTICO_ACTUAL) {
        this.PLASTICO_ACTUAL = PLASTICO_ACTUAL;
    }

    synchronized public int getPINTURA_ACTUAL() {
        return this.PINTURA_ACTUAL;
    }

    synchronized public void setPINTURA_ACTUAL(int PINTURA_ACTUAL) {
        this.PINTURA_ACTUAL = PINTURA_ACTUAL;
    }

    synchronized public int getPEGAMENTO_ACTUAL() {
        return this.PEGAMENTO_ACTUAL;
    }

    synchronized public void setPEGAMENTO_ACTUAL(int PEGAMENTO_ACTUAL) {
        this.PEGAMENTO_ACTUAL = PEGAMENTO_ACTUAL;
    }

    synchronized public boolean isFin() {
        return this.fin;
    }

    synchronized public boolean getFin() {
        return this.fin;
    }

    synchronized public void setFin(boolean fin) {
        this.fin = fin;
    }

    synchronized public int getContador_juguete() {
        return this.contador_juguete;
    }

    synchronized public void setContador_juguete(int contador_juguete) {
        this.contador_juguete = contador_juguete;
    }

    /************************************************************** */

    /*
     * Metodo para terminar el programa
     */

    public boolean esFin() {
        synchronized (this) {
            return fin;
        }
    }

    public boolean consumirPlastico(int cantidad) {
        synchronized (this) {
            if (PLASTICO_ACTUAL > cantidad) {
                PLASTICO_ACTUAL -= cantidad;
                return true;
            }
            return false;

        }
    }

    public boolean consumirPintura(int cantidad) {
        synchronized (this) {
            if (PINTURA_ACTUAL > cantidad) {
                PINTURA_ACTUAL -= cantidad;
                return true;
            }
            return false;

        }
    }

    /**
     * Metodo para el trabajador que es el mas importante
     */
    public void fabricarJuguete(int numero) {
        synchronized (this) {
            try {
                while (PEGAMENTO_ACTUAL < 0 && !isFin()) {
                    wait();
                }
                if (!isFin())
                    setPEGAMENTO_ACTUAL(getPEGAMENTO_ACTUAL() - numero);

            } catch (InterruptedException e) {

            }
        }
        System.out.printf("<------Obtengo pegamento\n");

    }

    /**
     * Metodo para el asistente que consiste en rellenar el pegamento
     */
    public void rellenarPegamento() {
        synchronized (this) {
            PEGAMENTO_ACTUAL += 100;
            System.out.println("RELLENANDO PEGAMENTO");
            notifyAll();
        }

    }

}
