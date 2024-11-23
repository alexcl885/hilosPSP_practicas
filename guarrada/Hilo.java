



public class Hilo extends Thread {
    private String num_hilo;
    
    private Compartido compartido;

    public Hilo(String num_hilo, Compartido compartido) {
        this.num_hilo = num_hilo;
        this.compartido = compartido;
    }

    @Override
    public void run() {
        while(true){

            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                System.out.println("He interrumpido el hilo");
                break;
                
            }
            
            System.out.println("Estoy dentro del hilo");
        }
    }
    
}