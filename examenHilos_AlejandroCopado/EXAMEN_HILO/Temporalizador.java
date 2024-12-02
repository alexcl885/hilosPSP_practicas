/**
 * Hilo temporalizador que lo que realizara es 
 * llenar los cubos de agua en un tiempo que eliga
 */
public class Temporalizador extends Thread{
    private int nombre;
    private Depositos depositos;


    public Temporalizador(int nombre, Depositos depositos) {
        this.nombre = nombre;
        this.depositos = depositos;
    }

    //cada hilo temporalizador ejecutara su run
    @Override
    public void run() {
        while (!depositos.isFin()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            depositos.addCubaCaliente();
            depositos.addCubaFria();
            System.out.println("TEMPORIZADOR: +50 FRIA +50 CALIENTE ");
            
        }
        System.out.println("Temporalizador:" +this.nombre +" finalizado.");
    }

}
