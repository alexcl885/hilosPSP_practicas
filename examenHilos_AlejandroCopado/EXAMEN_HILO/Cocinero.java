
import java.util.Random;

/**
 * Esta es el hilo cocinero , realizara los platos seran frios o calientes (depende del atributo)
 */
public class Cocinero extends Thread {
    private int id; // le asigno un identificador para saber que hilo estamos
    private Depositos depositos; // recurso

    private boolean cubo_frio; // si es true es frio y si es false es un cubo caliente

    Random random = new Random();
    private int numero_platos_realizados;

    public Cocinero(int id, Depositos depositos, boolean cubo_frio) {
        this.id = id;
        this.depositos = depositos;
        this.cubo_frio = cubo_frio;
    }

    // cada hilo cocinero ejecutara su run()
    @Override
    public void run() {
        while (!depositos.isFin()) {
            if (depositos.verificarPlatos()) { //cuando sea verdadero se cambiara el fin a true y se podra salir del bucle
                depositos.setFin(true);
            } else {
                if (cubo_frio) { // cubo frio
                    int agua_fria = random.nextInt(40);

                    System.out.println("Cocinero: " + this.id + " cocinando con agua fria :" + agua_fria
                            + " y he realizado " + numero_platos_realizados + " platos.");
                    depositos.prepararPlatoFrio(agua_fria);
                    try {
                        Thread.sleep(random.nextInt(2000));
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    numero_platos_realizados++;
                    

                } else { // cubo caliente
                    int agua_caliente = random.nextInt(40);
                    depositos.prepararPlatoCaliente(agua_caliente);

                    try {
                        Thread.sleep(random.nextInt(3000));
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    numero_platos_realizados++;
                    System.out.println("Cocinero: " + this.id + " cocinando con agua caliente :" + agua_caliente
                            + " y he realizado " + numero_platos_realizados + " platos.");
                }

            }
            
        }
        System.out.println("Cocineros terminado: " + this.id);
    }

}