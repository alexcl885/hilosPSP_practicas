/**
 * Esta sera mi clase recurso compartido
 * en el cual llamaran los metodos desde el hilo
 */
public class Depositos {
    private int CUBA_FRIA = 600;
    private int CUBA_CALIENTE = 300;

    private int num_platos_actuales;

    private boolean fin = false; //atributo que me sirve para realizar la verificacion para terminar el hilo

    private int CANTIDAD_PLATOS ;
    
    /********************************************************************************************* */
    //METODOS SETTER Y GETTERS SINCRONIZADOS PARA QUE EN UN MOMENTO DETERMINADO SOLO PUEDA ENTRAR UN HILO DE EJECUCIÓN
    synchronized public int getCUBA_FRIA() {
        return this.CUBA_FRIA;
    }

    synchronized public void setCUBA_FRIA(int CUBA_FRIA) {
        this.CUBA_FRIA = CUBA_FRIA;
    }

    synchronized public int getCUBA_CALIENTE() {
        return this.CUBA_CALIENTE;
    }

    synchronized public void setCUBA_CALIENTE(int CUBA_CALIENTE) {
        this.CUBA_CALIENTE = CUBA_CALIENTE;
    }

    synchronized public int getNum_platos_actuales() {
        return this.num_platos_actuales;
    }

    synchronized public void setNum_platos_actuales(int num_platos_actuales) {
        this.num_platos_actuales = num_platos_actuales;
    }

    synchronized public boolean isFin() { //METODO PARA FINALIZAR NUESTRO PROGRAMA
        return this.fin;
    }

    synchronized public boolean getFin() {
        return this.fin;
    }

    synchronized public void setFin(boolean fin) {
        this.fin = fin;
    }

    synchronized public int getCANTIDAD_PLATOS() {
        return this.CANTIDAD_PLATOS;
    }

    synchronized public void setCANTIDAD_PLATOS(int CANTIDAD_PLATOS) {
        this.CANTIDAD_PLATOS = CANTIDAD_PLATOS;
    }

    /*************************************************************************** */

    /*
     * METODO que compruebo si se han sacado mas de 100 platos para
     * poder parar el programa
     */
    synchronized public boolean verificarPlatos(){
        if (num_platos_actuales > 100){
            return true;
        }
        else{
            return false;
        }
    }


    /**
     * metodos para realizar los platos donde si la cantidad que escoge aleatoriamente es mayor
     * a la del deposito se espera y si es mayor realiza un plato.
     * 
     */

    public void prepararPlatoFrio(int cantidad){
        synchronized(this){
            while (CUBA_FRIA < cantidad && !isFin()){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!isFin()) //SI ESTA TODO BIEN RESTA LA CANTIDAD AL CUBA FRIA PARA REALIZAR EL PLATO y suma el contador de platos realizados
                CUBA_FRIA -= cantidad;
                num_platos_actuales++;
                CANTIDAD_PLATOS++;
        }
    }

    public void prepararPlatoCaliente(int cantidad){
        synchronized(this){
            while (CUBA_CALIENTE < cantidad && !isFin()){
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (!isFin()) //SI ESTA TODO BIEN RESTA LA CANTIDAD AL CUBA CALIENTE PARA REALIZAR EL PLATO
                CUBA_CALIENTE -= cantidad;
                num_platos_actuales++;
                CANTIDAD_PLATOS++;
        }
    }

    /**
     * metodos para añadir agua fria o caliente en los depositos
     * y al final hacen un notifyAll para avisar de que ya hay agua y poder realizar platos.
     */
    public void addCubaCaliente(){
        synchronized(this){
            setCUBA_CALIENTE(CUBA_CALIENTE + 50);
            notifyAll(); //notifica a todos los hilos que hay cubas calientes
        }
    }

    public void addCubaFria(){
        synchronized(this){
            setCUBA_FRIA(CUBA_FRIA + 50);
            notifyAll(); //notifica a todos los hilos que hay cubas frias
        }
    }
    
}
