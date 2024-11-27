
import java.util.Random;


public class Hilo extends Thread {
    private String num_hilo;
    Random random = new Random();
    
    private Compartido compartido;

    public Hilo(String num_hilo, Compartido compartido) {
        this.num_hilo = num_hilo;
        this.compartido = compartido;
    }

    @Override
    public void run() {
        while(compartido.puedePasar()){

            try {
                Thread.sleep(random.nextInt(1000,2000));
                if (compartido.verSiSepuede()){
                    compartido.sumarNumero();
                    System.out.println("Soy: "+ this.num_hilo +" y he sumado");
                }
                else{
                    System.out.println("SE ACABO TU TIEMPO MY FRIEND");
                }  
            } catch (InterruptedException ex) {
                System.out.println("He interrumpido el hilo");
                break;
                
            }
            
            
        }
    }
    
}