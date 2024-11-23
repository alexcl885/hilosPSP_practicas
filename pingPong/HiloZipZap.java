
import java.util.Random;

public class HiloZipZap extends  Thread {
    Random random = new Random();
    private String nombre;
    private Partida partida;
    private boolean esJugador1;
    

    public HiloZipZap(String nombre, Partida partida, boolean esJugador1) {
        this.nombre = nombre;
        this.partida = partida;
        this.esJugador1 = esJugador1;
    }
    /**
     * Metodo que ejecutara cada jugador (hilo)
     */
    @Override
    public void run() {
        while (partida.partidoTerminado()){
            int num_tiradas = random.nextInt(10);
            for (int i = 0; i < num_tiradas; i++) {
                if (esJugador1){
                    try {
                        Thread.sleep(random.nextInt(2000));
                        partida.golpesZip();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }  
                }
                else{
                    try {
                        Thread.sleep(random.nextInt(2000));
                        partida.golpesZap();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    
                }
            }

            
            int num_quien_gana = random.nextInt(100);
                System.out.println("El ganador del juego por el numero: "+ num_quien_gana);
                partida.ganadorJuego(num_quien_gana);
        }
        
    }

    
}