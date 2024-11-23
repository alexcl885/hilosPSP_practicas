public class Partida {
    private Object object = new Object();

    private int contador_zip = 0;
    private int contador_zap = 0;

    private  boolean partido= true ;

    /**
     * EL partido terminara cunado el partido sea false y si es true seguira el juego
     */
    public synchronized  boolean partidoTerminado(){
        return partido;
    }

    public void golpesZip(){
        synchronized (object) {
            contador_zip++;
            System.out.println("ZIP");
        }
    }

    public void golpesZap(){
        synchronized (object) {
            contador_zap++;
            System.out.println("ZAP");
        }
    }

    public void ganadorJuego(int numero){
        synchronized (object) {
            if (numero % 2 == 0){
                System.out.println("SANTI HA GANADO");
            }
            else{
                System.out.println("COPADO HA GANADO");
            }
            numGolpes();
            partido = false;
        }
    }
    public void numGolpes(){
        System.out.println("ZIP: "+ contador_zip);
        System.out.println("ZAP: " + contador_zap);
    }

}