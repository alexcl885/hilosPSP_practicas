
import java.util.Random;


public class Trabajador extends Thread {
    private Random random = new Random();
    private int nombre;
    private Almacen almacen;

    public Trabajador(int nombre, Almacen almacen) {
        this.nombre = nombre;
        this.almacen = almacen;
    }
    
    @Override
    public void run() {
        while (!almacen.isFin()){
            int cantidad_pintura = random.nextInt(400);
            int cantidad_platico = random.nextInt(400);
            if (!almacen.consumirPintura(cantidad_pintura) && !almacen.consumirPlastico(cantidad_platico)){
                almacen.setFin(true); //si ya no hay mas pintura y plastico termina el programa
            }
            else {
                System.out.println("Soy el trabajador "+ this.nombre + " y creo un juguete");
                try {
                    Thread.sleep(random.nextInt(3000));
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                int cantidad_pegamento = random.nextInt(40);
                almacen.fabricarJuguete(cantidad_pegamento);
            }


        }
        System.out.println("Trabajador " + this.nombre +" finalizado.");
    }



}